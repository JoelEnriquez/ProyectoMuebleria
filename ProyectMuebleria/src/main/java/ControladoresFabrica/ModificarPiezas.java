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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ModificarPiezas", urlPatterns = {"/ModificarPiezas"})
public class ModificarPiezas extends HttpServlet {

    ModeloPieza modeloPieza = new ModeloPieza();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String edit = request.getParameter("edit");
        int id = Integer.parseInt(request.getParameter("id"));
        
        switch (edit) {
            case "delete":
                modeloPieza.eliminarPieza(id);
                request.setAttribute("sucess-delete", true);
                request.getRequestDispatcher("/ConsultarInventario?op=modificar").forward(request, response);
                break;
            case "modify":
                StockPieza piezaModificar = modeloPieza.devolverPorId(id);
                request.setAttribute("pieza", piezaModificar);
                request.getRequestDispatcher("/AreaFabrica/EditarPieza.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("/ConsultarInventario?op=modificar");
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        
    }

}