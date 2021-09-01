/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFabrica;

import EntidadesFabrica.StockPieza;
import ModeloFabrica.ModeloPieza;
import java.io.IOException;
import java.sql.SQLException;
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
                redirigirModificar(request, response, id);
                break;
            default:
                response.sendRedirect("/ConsultarInventario?op=modificar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipoPieza = request.getParameter("pieza");
        String precio = request.getParameter("precio");

        Double costo = 0.0;
        try {
            costo = Double.parseDouble(precio);
        } catch (NumberFormatException | NullPointerException e) {
            request.setAttribute("error_change", true);
            request.setAttribute("error", "Error en el formato del precio");
            redirigirModificar(request, response, id);
        }
        
        //Verificar que el coste se mayor que 0
        if (costo>=0.0) {
            try {
                modeloPieza.actualizarPieza(id, tipoPieza, costo);
                request.setAttribute("success", true);
            } catch (SQLException e) {
                request.setAttribute("error_change", true);
                request.setAttribute("error", "No se ha podido realizar el registro");
            }          
            
            redirigirModificar(request, response, id);
        }
        else{
            request.setAttribute("error_change", true);
            request.setAttribute("error", "El numero no puede ser negativo");
            redirigirModificar(request, response, id);
        }

    }

    /**
     * Se envia al jsp editar para evitar los atributos de la pieza
     * @param request
     * @param response
     * @param id
     * @throws ServletException
     * @throws IOException 
     */
    private void redirigirModificar(HttpServletRequest request, HttpServletResponse response, int id)
            throws ServletException, IOException {
        StockPieza piezaModificar = modeloPieza.devolverPorId(id);
        ArrayList<String> nombresTipoPieza = modeloPieza.nombresTipoPieza();
        nombresTipoPieza.remove(piezaModificar.getTipoPieza()); //Se elimina el tipo de pieza actual
        request.setAttribute("pieza", piezaModificar);
        request.setAttribute("tipo_piezas", nombresTipoPieza);
        request.getRequestDispatcher("/AreaFabrica/EditarPieza.jsp").forward(request, response);
    }

}
