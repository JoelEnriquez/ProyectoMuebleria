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
@WebServlet(name = "CreacionUsuario", urlPatterns = {"/CreacionUsuario"})
public class CreacionUsuario extends HttpServlet {

    private final ModeloUsuario modeloUsuario = new ModeloUsuario();
    private final Encriptacion encriptar = new Encriptacion();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        refrescarListaUsuarios(request, response);
        request.getRequestDispatcher("AreaFinanciera/CrearUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String contraseña = request.getParameter("password");

        int tipoUsuario = 0;
        String passwordEncrypt = "";

        if (cadenaVacia(nombre, tipo, contraseña)) {
            request.setAttribute("error", "Alguno de los parametros viene vacio");
        } else {
            try {
                tipoUsuario = Integer.parseInt(tipo); //Formatear al formato correcto
                passwordEncrypt = encriptar.encriptar(contraseña);

                //Ingresar el nuevo usuario al sistema
                Usuario usuario = new Usuario(nombre, tipoUsuario, passwordEncrypt);
                modeloUsuario.ingresarUsuario(usuario);
                request.setAttribute("success", true);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Error en el formato del tipo de usuario");
            } catch (SQLException exsql) {
                request.setAttribute("error", exsql.getMessage());
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());
            }
        }
        refrescarListaUsuarios(request, response);
        request.getRequestDispatcher("AreaFinanciera/CrearUsuario.jsp").forward(request, response);
    }

    private boolean cadenaVacia(String... valores) {
        for (String valor : valores) {
            if (valor.isBlank() || valor.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtener los usuario en el sistema
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void refrescarListaUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Usuario> listaUsuarios = modeloUsuario.getUsers();
        request.setAttribute("usuarios", listaUsuarios);
        request.setAttribute("tipos_usuario", listaUsuarios);
    }
}
