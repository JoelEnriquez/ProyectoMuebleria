/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import ModelosInicio.VerificarDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ComprobarDatos", urlPatterns = {"/ComprobarDatos"})
public class ComprobarDatos extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        VerificarDatos vd = new VerificarDatos();
        
        //Comprobar la ausencia de datos; si es asi redirigir para cargar txt
        if (vd.ComprobarDatos()==0) {
            request.setAttribute("SolicitudDatos", 0);
        }
        else{
            request.setAttribute("SolicitudDatos", 1);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    

}
