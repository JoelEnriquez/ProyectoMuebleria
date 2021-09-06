/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesReporte.MuebleVenta;
import ModeloFinanciera.ModeloReporteMueble;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ReporteMuebles", urlPatterns = {"/ReporteMuebles"})
public class ReporteMuebles extends HttpServlet {

    private final ModeloReporteMueble modeloMueble = new ModeloReporteMueble();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha1 = request.getParameter("fecha_1");
        String fecha2 = request.getParameter("fecha_2");
        String filtrado = request.getParameter("filtrado");
        LocalDate date1;
        LocalDate date2;
        ArrayList<MuebleVenta> listMueble = null;

        //Comprobar que vengan ambas fechas o ninguna
        if ((fecha1.isEmpty() && !fecha2.isEmpty() || (!fecha1.isEmpty() && fecha2.isEmpty()))) {
            request.setAttribute("error", "Tiene que venir sin fecha, o con ambas fechas");
        } else {
            if (fecha1.isEmpty() && fecha2.isEmpty()) { //Sin parametros de fecha especificados
                if (filtrado.equals("mayor_menor")) {
                    listMueble = modeloMueble.getMuebleMasVendido();
                } else {
                    listMueble = modeloMueble.getMuebleMenosVendido();
                }
            } else {
                //Convertir al formato correcto
                try {
                    date1 = LocalDate.parse(fecha1);
                    date2 = LocalDate.parse(fecha2);

                    //Verificar que la fecha 1, se encuentre antes que la fecha 2
                    if (date1.isAfter(date2)) {
                        request.setAttribute("error", "La fecha 1 tiene que ser anterior a la fecha 2");
                    } else {
                        if (filtrado.equals("mayor_menor")) {
                            listMueble = modeloMueble.getMuebleMasVendidoIntervalo(date1, date2);
                        } else {
                            listMueble = modeloMueble.getMuebleMenosVendidoIntervalo(date1, date2);
                        }
                    }
                } catch (DateTimeException e) {
                    request.setAttribute("error", "Error en el formato de la fecha");
                }
            }
            request.setAttribute("muebles", listMueble);
        }

        request.getRequestDispatcher("AreaFinanciera/ReporteMuebles.jsp").forward(request, response);
    }

}
