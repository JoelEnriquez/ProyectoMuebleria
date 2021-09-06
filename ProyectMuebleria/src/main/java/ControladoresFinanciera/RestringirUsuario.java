/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import Encriptar.Encriptacion;
import EntidadesPersona.Usuario;
import ModeloFinanciera.ModeloUsuario;
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
@WebServlet(name = "RestringirUsuario", urlPatterns = {"/RestringirUsuario"})
public class RestringirUsuario extends HttpServlet {

    private final ModeloUsuario modeloUsuario = new ModeloUsuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        if (nombre!=null && !cadenaVacia(nombre)) {
            //Poner el acceso restringido a dicho usuario
            modeloUsuario.revocarUsuario(nombre);
            request.setAttribute("success", nombre);
        }
        
        ArrayList<Usuario> listaUsuarios = modeloUsuario.getUsersSaleAndFabrica(); //Mostrar solo empleados de fabrica y venta
        request.setAttribute("usuarios", listaUsuarios);
        request.getRequestDispatcher("AreaFinanciera/RestringirUsuario.jsp").forward(request, response);
    }
    
    private boolean cadenaVacia(String... valores) {
        for (String valor : valores) {
            if (valor.isBlank() || valor.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    
}
