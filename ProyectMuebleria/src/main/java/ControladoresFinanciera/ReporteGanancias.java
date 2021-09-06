/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesReporte.EntidadGanancia;
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
@WebServlet(name = "ReporteGanancias", urlPatterns = {"/ReporteGanancias"})
public class ReporteGanancias extends HttpServlet {

    private final ModeloReporteFinanzas modeloReporte = new ModeloReporteFinanzas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<EntidadGanancia> listaIngresas = modeloReporte.getGanancias();
        ArrayList<EntidadGanancia> listaEgresos = modeloReporte.getPerdidasDevolucion();
        ArrayList<EntidadGanancia> listaGanancias = listaGanancias(listaIngresas, listaEgresos);

        request.setAttribute("listaGanancias", listaGanancias);

        request.getRequestDispatcher("AreaFinanciera/ReporteGanancias.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha1 = request.getParameter("fecha_1");
        String fecha2 = request.getParameter("fecha_2");
        LocalDate date1;
        LocalDate date2;
        ArrayList<EntidadGanancia> listaIngresas = null;
        ArrayList<EntidadGanancia> listaEgresos = null;
        ArrayList<EntidadGanancia> listaGanancias = null;

        //Comprobar que vengan ambas fechas o ninguna
        if ((fecha1.isEmpty() && !fecha2.isEmpty() || (!fecha1.isEmpty() && fecha2.isEmpty()))) {
            request.setAttribute("error", "Tiene que venir sin fecha, o con ambas fechas");
        } else {
            if (fecha1.isEmpty() && fecha2.isEmpty()) { //Sin parametros de fecha especificados
                listaIngresas = modeloReporte.getGanancias();
                listaEgresos = modeloReporte.getPerdidasDevolucion();
                listaGanancias = listaGanancias(listaIngresas, listaEgresos);
            } else {
                //Convertir al formato correcto
                try {
                    date1 = LocalDate.parse(fecha1);
                    date2 = LocalDate.parse(fecha2);

                    //Verificar que la fecha 1, se encuentre antes que la fecha 2
                    if (date1.isAfter(date2)) {
                        request.setAttribute("error", "La fecha 1 tiene que ser anterior a la fecha 2");
                    } else {
                        listaIngresas = modeloReporte.getGananciasFiltro(date1, date2);
                        listaEgresos = modeloReporte.getPerdidasDevolucionFiltro(date1, date2);
                        listaGanancias = listaGanancias(listaIngresas, listaEgresos);
                    }
                } catch (DateTimeException e) {
                    request.setAttribute("error", "Error en el formato de la fecha");
                }
            }
            request.setAttribute("listaGanancias", listaGanancias);
        }

        request.getRequestDispatcher("AreaFinanciera/ReporteGanancias.jsp").forward(request, response);
    }

    /**
     * Setear los valores correctos, sumando los ingresos, egresos y perdidas
     * @param listaIngresos
     * @param listaEgresos
     * @return 
     */
    private ArrayList<EntidadGanancia> listaGanancias(ArrayList<EntidadGanancia> listaIngresos, ArrayList<EntidadGanancia> listaEgresos) {
        for (EntidadGanancia listaIngreso : listaIngresos) {
            for (EntidadGanancia listaEgreso : listaEgresos) {
                if (listaIngreso.getIdEnsamble() == listaEgreso.getIdEnsamble()) {
                    listaIngreso.setPerdidasDevolucion(listaEgreso.getPerdidasDevolucion()); //Setear perdidas por devolucion
                }
            }
        }

        for (EntidadGanancia listaIngreso : listaIngresos) {
            listaIngreso.setGanancia(listaIngreso.getDiferencia() - listaIngreso.getPerdidasDevolucion());
        }
        
        for (EntidadGanancia listaIngreso : listaIngresos) {
            listaIngreso.setIngresoTotal(ingresoTotal(listaIngresos));
            listaIngreso.setGananciaTotal(gananciaTotal(listaIngresos));
        }

        return listaIngresos;
    }

    private Double gananciaTotal(ArrayList<EntidadGanancia> listaGanancias) {
        Double gananciaTotal = 0.0;

        for (EntidadGanancia listaGanancia : listaGanancias) {
            gananciaTotal += listaGanancia.getGanancia();
        }
        
        return gananciaTotal;
    }

    private Double ingresoTotal(ArrayList<EntidadGanancia> listaGanancias) {
        Double ingresoTotal = 0.0;

        for (EntidadGanancia listaGanancia : listaGanancias) {
            ingresoTotal += listaGanancia.getIngresosVenta();
        }
        return ingresoTotal;
    }

}
