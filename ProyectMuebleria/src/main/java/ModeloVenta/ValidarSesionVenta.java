/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
public class ValidarSesionVenta {
    
    public static void validarSesion(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (request.getSession().getAttribute("nombre") == null || !request.getSession().getAttribute("persona").equals("Venta")) {
            response.sendRedirect(request.getContextPath() + "/ControlLogOut");
        }
    }
}
