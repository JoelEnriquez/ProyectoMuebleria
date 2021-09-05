/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ControlLogOut", urlPatterns = {"/ControlLogOut"})
public class ControlLogOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se eliminan los atributos y se redirige al Login
        request.getSession().removeAttribute("nombre");
        request.getSession().removeAttribute("persona");
        request.getSession().removeAttribute("id_compras");
        request.getSession().removeAttribute("listaRescatar");
        request.getSession().removeAttribute("idEnsamble");

        response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
    }
}
