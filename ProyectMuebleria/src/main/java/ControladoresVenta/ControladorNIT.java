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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ControladorNIT", urlPatterns = {"/ControladorNIT"})
public class ControladorNIT extends HttpServlet {

    ModeloNIT modeloNIT = new ModeloNIT();
    ModeloMueble modeloMueble = new ModeloMueble();
    ModeloVenta calculosVenta = new ModeloVenta();
    private String NITPorError;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String NIT="";
        if (NITPorError==null) {
            NIT = request.getParameter("NIT_cliente"); //Por JSP Normal
        }else{
            NIT = NITPorError; //Por error en finalizar venta
        }
        
        String URL = "AreaVenta/FinalizarVenta.jsp";
        
        //Verificar su existencia en la BD
        if (modeloNIT.existenciaNIT(NIT)==1) {
            Cliente cliente = modeloNIT.getPorNIT(NIT);
            request.setAttribute("cliente", cliente);
            
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
        } else {
            URL = "AreaVenta/SolicitarNIT.jsp";
            request.setAttribute("error", "No existe dicho NIT en el sistema");
        }
        
        request.getRequestDispatcher(URL).forward(request, response);
    }

    public void setNITPorError(String NITPorError) {
        this.NITPorError = NITPorError;
    }
}
