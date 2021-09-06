/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesReporte.EntidadGanancia;
import EntidadesReporte.EntidadVentas;
import ModeloFinanciera.ModeloReporteFinanzas;
import ModeloFinanciera.ModeloReporteUsuario;
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
@WebServlet(name = "UsuarioMayoresGanancias", urlPatterns = {"/UsuarioMayoresGanancias"})
public class UsuarioMayoresGanancias extends HttpServlet {

    private final ModeloReporteUsuario modeloUsuario = new ModeloReporteUsuario();
    private final ModeloReporteFinanzas modeloReporte = new ModeloReporteFinanzas();
    private final ReporteGanancias reporteGanancias = new ReporteGanancias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Sin parametro de fecha especifico
        ArrayList<EntidadGanancia> listaIngresos = modeloReporte.getGanancias();
        ArrayList<EntidadGanancia> listaPerdidasDevolucion = modeloReporte.getPerdidasDevolucion();
        //Obtener nuestra lista de ganancias
        ArrayList<EntidadGanancia> listaGanancias = reporteGanancias.listaGanancias(listaIngresos, listaPerdidasDevolucion);
        //Obtener el registro del usuario con mayores ganancias
        ArrayList<EntidadGanancia> listaActualizada = elegirUsuarioMayorGanancia(listaGanancias, null, null);
        setearGananciaTotal(listaActualizada);

        request.setAttribute("listaGanancias", listaActualizada);
        request.getRequestDispatcher("AreaFinanciera/UsuarioMayorGanancias.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha1 = request.getParameter("fecha_1");
        String fecha2 = request.getParameter("fecha_2");
        LocalDate date1;
        LocalDate date2;
        ArrayList<EntidadGanancia> listaIngresos = null;
        ArrayList<EntidadGanancia> listaPerdidasDevolucion = null;
        ArrayList<EntidadGanancia> listaGanancias = null;
        ArrayList<EntidadGanancia> listaActualizada = null;

        //Comprobar que vengan ambas fechas o ninguna
        if ((fecha1.isEmpty() && !fecha2.isEmpty() || (!fecha1.isEmpty() && fecha2.isEmpty()))) {
            request.setAttribute("error", "Tiene que venir sin fecha, o con ambas fechas");
        } else {
            if (fecha1.isEmpty() && fecha2.isEmpty()) { //Sin parametros de fecha especificados
                listaIngresos = modeloReporte.getGanancias();
                listaPerdidasDevolucion = modeloReporte.getPerdidasDevolucion();
                //Obtener nuestra lista de ganancias
                listaGanancias = reporteGanancias.listaGanancias(listaIngresos, listaPerdidasDevolucion);
                //Obtener el registro del usuario con mayores ganancias
                listaActualizada = elegirUsuarioMayorGanancia(listaGanancias, null, null);
                setearGananciaTotal(listaActualizada);
            } else {
                //Convertir al formato correcto
                try {
                    date1 = LocalDate.parse(fecha1);
                    date2 = LocalDate.parse(fecha2);

                    //Verificar que la fecha 1, se encuentre antes que la fecha 2
                    if (date1.isAfter(date2)) {
                        request.setAttribute("error", "La fecha 1 tiene que ser anterior a la fecha 2");
                    } else {
                        listaIngresos = modeloReporte.getGananciasFiltro(date1, date2);
                        listaPerdidasDevolucion = modeloReporte.getPerdidasDevolucionFiltro(date1, date2);
                        listaGanancias = reporteGanancias.listaGanancias(listaIngresos, listaPerdidasDevolucion);

                        //Obtener el registro del usuario con mayores ganancias
                        listaActualizada = elegirUsuarioMayorGanancia(listaGanancias, date1, date2);
                        setearGananciaTotal(listaActualizada);
                    }
                } catch (DateTimeException e) {
                    request.setAttribute("error", "Error en el formato de la fecha");
                }
            }
            request.setAttribute("listaGanancias", listaActualizada);
        }

        request.getRequestDispatcher("AreaFinanciera/UsuarioMayorGanancias.jsp").forward(request, response);
    }

    private ArrayList<EntidadGanancia> elegirUsuarioMayorGanancia(ArrayList<EntidadGanancia> listaGanancias, LocalDate fecha1, LocalDate fecha2) {

        ArrayList<String> listaUsuarios;
        if (fecha1 == null && fecha2 == null) {
            listaUsuarios = modeloUsuario.usuariosGanancia();
        } else {
            listaUsuarios = modeloUsuario.usuariosGananciaIntervalo(fecha1, fecha2);
        }

        Double mayorGanancia = 0.0;
        String usuarioMayorGanancia = "";

        for (String usuario : listaUsuarios) {
            Double gananciaAux = 0.0;
            for (EntidadGanancia listaGanancia : listaGanancias) {
                if (usuario.equals(listaGanancia.getNombreUsuario())) {
                    gananciaAux += listaGanancia.getGanancia();
                }
                //Primera vuelta
                if (mayorGanancia == 0.0) {
                    mayorGanancia = gananciaAux;
                    usuarioMayorGanancia = usuario;
                } else {
                    if (gananciaAux > mayorGanancia) {
                        mayorGanancia = gananciaAux;
                        usuarioMayorGanancia = usuario;
                    }
                }
            }
        }

        ArrayList<EntidadGanancia> gananciasUsuario = new ArrayList<>();
        //Hacer una copia de las ganancias del usuario
        for (EntidadGanancia listaGanancia : listaGanancias) {
            if (listaGanancia.getNombreUsuario().equals(usuarioMayorGanancia)) {
                gananciasUsuario.add(listaGanancia);
            }
        }
        return gananciasUsuario;
    }

    private void setearGananciaTotal(ArrayList<EntidadGanancia> listaActualizada) {
        for (EntidadGanancia listaIngreso : listaActualizada) {

            listaIngreso.setIngresoTotal(reporteGanancias.ingresoTotal(listaActualizada));
            listaIngreso.setGananciaTotal(reporteGanancias.gananciaTotal(listaActualizada));
        }
    }

}
