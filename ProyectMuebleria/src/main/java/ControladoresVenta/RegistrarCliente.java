/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import EntidadesPersona.Cliente;
import EntidadesVenta.StockEnsamble;
import ModeloFabrica.ModeloMueble;
import ModeloVenta.ModeloVenta;
import ModeloVenta.ModeloNIT;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "RegistrarCliente", urlPatterns = {"/RegistrarCliente"})
public class RegistrarCliente extends HttpServlet {

    private ModeloNIT modeloNIT = new ModeloNIT();
    private ModeloMueble modeloMueble = new ModeloMueble();
    private ModeloVenta calculosVenta = new ModeloVenta();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validarSession(request, response);
        
        String URL = "AreaVenta/RegistrarCliente.jsp";
        boolean success = false;
        Cliente cliente = null;
        String error = "";
        String NIT = request.getParameter("NIT");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String municipio = request.getParameter("municipio");
        String departamento = request.getParameter("departamento");

        //Verificar que NIT, nombre y direccion no vengan nulos
        if (espaciosVacios(NIT, nombre, direccion)) {
            error = "Alguno de los parametros obligatorios viene vacio";
        } //Verificar que vengan departamento y municipio si son ingresados
        else if (espaciosVacios(municipio) && departamento.length() > 0) {
            error = "Es necesario ingresar municipio y departamento juntos";
        } else if (municipio.length() > 0 && espaciosVacios(departamento)) {
            error = "Es necesario ingresar municipio y departamento juntos";
        } else {
            cliente = new Cliente(NIT, nombre, direccion);
            
            //Verificar que tipo de Cliente Registrar
            if (!espaciosVacios(municipio, departamento)) {
                cliente.setMunicipio(municipio);
                cliente.setDepartamento(departamento);
            }

            try {
                modeloNIT.agregarCliente(cliente);
                URL = "AreaVenta/FinalizarVenta.jsp";
                success = true;
            } catch (SQLException ex) {
                error = ex.getMessage();
            }
        }
        if (success) {
            //Redirigir al JSP con los items seleccionados
            ArrayList<Integer> idCompras = (ArrayList<Integer>) request.getSession().getAttribute("id_compras");
            ArrayList<StockEnsamble> carritoCompras = new ArrayList<>();

            //Se llena la info de la compra por sus ids
            for (Integer idCompra : idCompras) {
                carritoCompras.add(modeloMueble.getEnsambleStockPorId(idCompra));
            }
            //Obtenemos el total de la compra
            Double totalCompra = calculosVenta.costoCompra(carritoCompras);
            
            request.setAttribute("costo", totalCompra);     
            request.setAttribute("carrito", carritoCompras);
            request.setAttribute("cliente", cliente);
        } else {
            request.setAttribute("error", error);
        }
        request.getRequestDispatcher(URL).forward(request, response);

    }

    private boolean espaciosVacios(String... vals) {
        for (String val : vals) {
            if (val.isEmpty() || val.isBlank()) {
                return true;
            }
        }
        return false;
    }

    private void validarSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (request.getSession().getAttribute("nombre") == null || !request.getSession().getAttribute("persona").equals("Venta")) {
            response.sendRedirect(request.getContextPath() + "/ControlLogOut");
        } else if (request.getSession().getAttribute("id_compras") == null) {
            response.sendRedirect(request.getContextPath() + "/RegistrarVenta");
        }
    }

}
