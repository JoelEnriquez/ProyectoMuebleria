/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import DBConnection.Conexion;
import EntidadesVenta.DetalleCompra;
import EntidadesVenta.Factura;
import ModeloVenta.ModeloVenta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet(name = "RealizarVenta", urlPatterns = {"/RealizarVenta"})
public class RealizarVenta extends HttpServlet {

    private ModeloVenta modeloVenta = new ModeloVenta();
    private Connection conexion = Conexion.getConexion();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        boolean success = false;
        String URL = "AreaVenta/FinalizarVenta.jsp";
        ArrayList<Integer> idCompras = (ArrayList<Integer>) request.getSession().getAttribute("id_compras");
        
        LocalDate fechaCompra = LocalDate.now();
        String costo = request.getParameter("costo");
        Double costoVenta=0.0;
        String NIT = request.getParameter("NIT");
        String nombreUsuario = request.getSession().getAttribute("nombre").toString();
        
        try {
            costoVenta = Double.valueOf(costo); //Transformar al formato correcto
            
            conexion.setAutoCommit(false); //Manejar errores en dicha transaccion y que no se realize hasta completar detalle factura
            Factura factura = new Factura(fechaCompra, costoVenta, NIT, nombreUsuario);
            int idFactura =  modeloVenta.agregarFactura(factura); //Obtenemos la ultima llave primaria generada
            
            for (Integer idCompra : idCompras) {
                DetalleCompra detalleCompra = new DetalleCompra(idCompra, modeloVenta.precioMueblePorId(idCompra), false, false, idFactura);
                modeloVenta.agregarDetalleCompra(detalleCompra);
            }
            
            success = true;
            request.setAttribute("success", true);
            request.getSession().removeAttribute("id_compras"); //Remover la lista de ids ensambles
            conexion.commit(); //Realizar cambios
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error en el formato del precio");
        } catch (SQLException ex) {
            try {
                request.setAttribute("error", ex.getMessage());
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        //Redirigir al lugar correcto
        if (success) {
            request.getRequestDispatcher(URL).forward(request, response);
        }
        else{
            ControladorNIT controladorNIT = new ControladorNIT();
            controladorNIT.setNITPorError(NIT);
            controladorNIT.doPost(request, response);
        }

    }

}
