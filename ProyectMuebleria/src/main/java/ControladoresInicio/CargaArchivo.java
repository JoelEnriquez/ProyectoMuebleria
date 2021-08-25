/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Error.Error;
import Lectura.Lector;
import ModelosInicio.VerificarDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
@WebServlet(name = "CargaArchivo", urlPatterns = {"/CargaArchivo"})
public class CargaArchivo extends HttpServlet {



    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part part = request.getPart("archivoTXT"); //Extraer el txt del form
        InputStream inputStream = part.getInputStream();
        
        Lector lector = new Lector();
        lector.leerTXT(inputStream);
        
        ArrayList<Error> listaErrores = lector.getListaErrores();
        
        //Comprobar carga de archivos
        VerificarDatos verificarDatos = new VerificarDatos();
        if (verificarDatos.comprobarDatos()==0) {
            request.setAttribute("Sucess", false);
        }
        else{
            request.setAttribute("Sucess", true);
        }
        
        request.getRequestDispatcher("/Inicio/CargaDatos.jsp").forward(request, response);
    }

    
}
