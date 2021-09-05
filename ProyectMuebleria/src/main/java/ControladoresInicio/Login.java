/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Encriptar.Encriptacion;
import ModelosInicio.LoginModel;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private final Encriptacion encriptacion = new Encriptacion();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String passwordEncrypt = "";
        try {
            passwordEncrypt = encriptacion.encriptar(password);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        LoginModel loginM = new LoginModel();
        int tipoPersona = loginM.devolverTipoPersona(nombre, passwordEncrypt);
        //Verificamos que tipo de usuario es y buscamos en la tabla respectiva
        switch (tipoPersona) {
            case 1:
                request.getSession().setAttribute("nombre", nombre);
                request.getSession().setAttribute("persona","Fabrica");
                request.getRequestDispatcher("/AreaFabrica/InicioFabrica.jsp").forward(request, response);
                break;
            case 2:
                request.getSession().setAttribute("nombre", nombre);
                request.getSession().setAttribute("persona","Financiera");
                request.getRequestDispatcher("/AreaFinanciera/InicioFinanciera.jsp").forward(request, response);
                break;
            case 3:
                request.getSession().setAttribute("nombre", nombre);
                request.getSession().setAttribute("persona","Venta");
                request.getRequestDispatcher("/AreaVenta/InicioVenta.jsp").forward(request, response);
                break;
            case 0:
                request.setAttribute("fail", true);
                request.getRequestDispatcher("/Inicio/Login.jsp").forward(request, response);
                break;
        }
    }

}
