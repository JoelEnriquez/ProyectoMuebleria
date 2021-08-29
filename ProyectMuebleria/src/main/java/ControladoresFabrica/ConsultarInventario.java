/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFabrica;

import EntidadesFabrica.StockPieza;
import ModeloFabrica.ModeloPieza;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ConsultarInventario", urlPatterns = {"/ConsultarInventario"})
public class ConsultarInventario extends HttpServlet {

    ModeloPieza modeloPieza = new ModeloPieza();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String operacion = request.getParameter("op");

        ArrayList<StockPieza> stockPiezas = modeloPieza.piezasStock();
        if (!stockPiezas.isEmpty()) {
            request.setAttribute("stockPiezas", stockPiezas);
        }
        else{
            request.setAttribute("stockPiezas", null);
        }
        
        //Redireccionar al lugar correcto
        RequestDispatcher rd = null;
        if (operacion.equals("inventario")) {
            rd = request.getRequestDispatcher("/AreaFabrica/StockPiezas.jsp");
        } else if (operacion.equals("modificar")) {
            rd = request.getRequestDispatcher("/AreaFabrica/ModificarPieza.jsp");
        }
        
        rd.forward(request, response);
    }

    

}
