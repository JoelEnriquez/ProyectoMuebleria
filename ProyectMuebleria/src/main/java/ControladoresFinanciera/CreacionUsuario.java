/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesPersona.Usuario;
import ModeloFinanciera.ModeloUsuario;
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
@WebServlet(name = "CreacionUsuario", urlPatterns = {"/CreacionUsuario"})
public class CreacionUsuario extends HttpServlet {

    private final ModeloUsuario modeloUsuario = new ModeloUsuario();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Usuario> listaUsuarios = modeloUsuario.getUsers();
        request.setAttribute("usuario", listaUsuarios);
        
        request.getRequestDispatcher("AreaFinanciera/CrearUsuario.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    

}
