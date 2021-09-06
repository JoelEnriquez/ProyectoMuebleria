/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesReporte.EntidadVentas;
import ModeloFinanciera.ModeloReporteFinanzas;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
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
@WebServlet(name = "ReporteVentasFinanciera", urlPatterns = {"/ReporteVentasFinanciera"})
public class ReporteVentasFinanciera extends HttpServlet {

    private final ModeloReporteFinanzas modeloReporte = new ModeloReporteFinanzas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<EntidadVentas> listVentas = modeloReporte.getVentas();
        request.setAttribute("ventas", listVentas);

        request.getRequestDispatcher("AreaFinanciera/ReporteVentas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha1 = request.getParameter("fecha_1");
        String fecha2 = request.getParameter("fecha_2");
        LocalDate date1;
        LocalDate date2;
        ArrayList<EntidadVentas> listVentas = null;

        //Comprobar que vengan ambas fechas o ninguna
        if ((fecha1.isEmpty() && !fecha2.isEmpty() || (!fecha1.isEmpty() && fecha2.isEmpty()))) {
            request.setAttribute("error", "Tiene que venir sin fecha, o con ambas fechas");
        } else {
            if (fecha1.isEmpty() && fecha2.isEmpty()) { //Sin parametros de fecha especificados
                listVentas = modeloReporte.getVentas();
            } else {
                //Convertir al formato correcto
                try {
                    date1 = LocalDate.parse(fecha1);
                    date2 = LocalDate.parse(fecha2);

                    //Verificar que la fecha 1, se encuentre antes que la fecha 2
                    if (date1.isAfter(date2)) {
                        request.setAttribute("error", "La fecha 1 tiene que ser anterior a la fecha 2");
                    } else {
                        listVentas = modeloReporte.getVentasFilter(date1, date2);
                    }
                } catch (DateTimeException e) {
                    request.setAttribute("error", "Error en el formato de la fecha");
                }
            }
            request.setAttribute("ventas", listVentas);
        }

        request.getRequestDispatcher("AreaFinanciera/ReporteVentas.jsp").forward(request, response);
    }

}
