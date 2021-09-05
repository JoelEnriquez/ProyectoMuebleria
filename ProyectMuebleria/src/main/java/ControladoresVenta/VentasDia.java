/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import EntidadesVenta.FacturaCliente;
import ModeloVenta.ReportesVenta;
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
@WebServlet(name = "VentasDia", urlPatterns = {"/VentasDia"})
public class VentasDia extends HttpServlet {

    private final ReportesVenta reportesVenta = new ReportesVenta();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<FacturaCliente> comprasDelDia = reportesVenta.getComprasToday();
        
        request.setAttribute("compras", comprasDelDia);
        request.getRequestDispatcher("AreaVenta/VentasDia.jsp").forward(request, response);
    }

    

}
