/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import EntidadesPersona.Cliente;
import EntidadesVenta.DevolucionCliente;
import EntidadesVenta.Factura;
import ModeloVenta.ModeloNIT;
import ModeloVenta.ReportesVenta;
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
@WebServlet(name = "DevolucionesCliente", urlPatterns = {"/DevolucionesCliente"})
public class DevolucionesCliente extends HttpServlet {

    private final ModeloNIT modeloNIT = new ModeloNIT();
    private final ReportesVenta reportesVenta = new ReportesVenta();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String NIT = request.getParameter("NIT");
        String fecha1 = request.getParameter("fecha_1");
        String fecha2 = request.getParameter("fecha_2");
        LocalDate date1;
        LocalDate date2;
        ArrayList<DevolucionCliente> devolucionesCliente = null;

        //Comprobacion dato field NIT
        if (NIT.isBlank() || NIT.isBlank()) {
            request.setAttribute("error", "No se ha especificado ningun NIT");
        } else {
            //Comprobacion existencia de NIT
            if (modeloNIT.existenciaNIT(NIT) == 0) {
                request.setAttribute("error", "No existe dicho NIT");
            } else {
                Cliente cliente = modeloNIT.getPorNIT(NIT);
                //Comprobar que vengan ambas fechas o ninguna
                if ((fecha1.isEmpty() && !fecha2.isEmpty() || (!fecha1.isEmpty() && fecha2.isEmpty()))) {
                    request.setAttribute("error", "Tiene que venir sin fecha, o con ambas fechas");
                } else {
                    if (fecha1.isEmpty() && fecha2.isEmpty()) { //Sin parametros de fecha especificados
                        devolucionesCliente = reportesVenta.devolucionesCliente(NIT);
                    } else {
                        //Convertir al formato correcto
                        try {
                            date1 = LocalDate.parse(fecha1);
                            date2 = LocalDate.parse(fecha2);

                            //Verificar que la fecha 1, se encuentre antes que la fecha 2
                            if (date1.isAfter(date2)) {
                                request.setAttribute("error", "La fecha 1 tiene que ser anterior a la fecha 2");
                            } else {
                                devolucionesCliente = reportesVenta.devolucionesClienteFiltroFecha(NIT, date1, date2);
                            }
                        } catch (DateTimeException e) {
                            request.setAttribute("error", "Error en el formato de la fecha");
                        }
                    }
                    request.setAttribute("devoluciones", devolucionesCliente);
                    request.setAttribute("cliente", cliente);
                }
            }
        }
        request.getRequestDispatcher("AreaVenta/DevolucionesCliente.jsp").forward(request, response);
    }

    

}
