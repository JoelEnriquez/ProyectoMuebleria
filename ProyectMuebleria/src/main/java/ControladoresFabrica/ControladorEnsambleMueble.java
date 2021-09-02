/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFabrica;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsambleMueble;
import EntidadesMuebleria.EnsamblePieza;
import ModeloFabrica.ModeloLogicaEnsamble;
import ModeloFabrica.ModeloMueble;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ControladorEnsambleMueble", urlPatterns = {"/ControladorEnsambleMueble"})
public class ControladorEnsambleMueble extends HttpServlet {

    private Connection conexion = Conexion.getConexion();
    private ModeloMueble modeloMueble = new ModeloMueble();
    private ModeloLogicaEnsamble modeloLogico = new ModeloLogicaEnsamble();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filtro = "";
        if (request.getParameter("mueble_creacion") != null) {
            filtro = request.getParameter("mueble_creacion");
        }
        mostrarInfoMuebles(request, response, filtro);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreMueble = request.getParameter("mueble");
        String cantidad = request.getParameter("cantidad");
        int cantidadFabricar = 0;
        int auxMueblesFabrica = 0; //Si ocurre un error, indicar el numero de muebles que se pueden fabricar

        LocalDate fechaEnsamble = LocalDate.now();
        try {
            cantidadFabricar = Integer.parseInt(cantidad);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error en el formato de la cantidad");
        }

        if (cantidadFabricar >= 1) {
            ArrayList<EnsamblePieza> recetaMueble = modeloLogico.recetaPorMueble(nombreMueble); //Obtener receta
            boolean success = true;
            if (recetaMueble.isEmpty()) {
                request.setAttribute("error", "No hay receta disponible");
            } else {
                try {
                    conexion.setAutoCommit(false);
                    do {
                        Double precioEnsamble = 0.0;
                        for (EnsamblePieza ensamblePieza : recetaMueble) {
                            if (ensamblePieza.getCantidadPieza() > modeloLogico.disponibilidadPieza(ensamblePieza.getNombrePieza())) {
                                success = false;
                                request.setAttribute("error", "No hay piezas suficientes de " + ensamblePieza.getNombrePieza() + ". Se pueden fabricar " + auxMueblesFabrica + " muebles tipo \"" + nombreMueble + "\".");
                                conexion.rollback();
                                break;
                            } else {
                                precioEnsamble += modeloLogico.costoEnsamblePieza(ensamblePieza.getNombrePieza(), ensamblePieza.getCantidadPieza()); //Se suman el precio de la materia prima, para calcular, el precio de ensamble
                            }
                        }
                        if (success) {
                            EnsambleMueble nuevoEnsambleMueble = new EnsambleMueble(fechaEnsamble, precioEnsamble, request.getSession().getAttribute("nombre").toString(), nombreMueble);
                            try {
                                modeloMueble.agregarEnsambleMueble(nuevoEnsambleMueble);
                                auxMueblesFabrica++;
                            } catch (SQLException e) {
                                conexion.rollback();
                                request.setAttribute("error", e.getMessage());
                                success = false;
                            }
                        }
                    } while (auxMueblesFabrica < cantidadFabricar && success != false);

                    if (success) {
                        conexion.commit();
                        request.setAttribute("success", true);
                    }

                } catch (SQLException e) {
                    request.setAttribute("error", "Error en el formato de la cantidad");
                } finally {
                    try {
                        conexion.setAutoCommit(true);
                    } catch (SQLException ex) {
                        request.setAttribute("error", "No se ha podido realizar el autocommit");
                    }
                }

                //Calcular el precio de ensamblaje
            }
        } else {
            request.setAttribute("error", "Solo se pueden crear de 1 a mas muebles");
        }
        mostrarInfoMuebles(request, response, "");
    }

    private void mostrarInfoMuebles(HttpServletRequest request, HttpServletResponse response, String filtro)
            throws ServletException, IOException {

        ArrayList<EnsambleMueble> listaEnsambles = null;
        if (filtro.equals("")) {
            listaEnsambles = modeloMueble.getListaEnsambles();
        } else {
            listaEnsambles = modeloMueble.getListaEnsambles(filtro);
        }
        ArrayList<String> listaNombreMuebles = modeloMueble.getNombreMuebles();

        request.setAttribute("ensambles", listaEnsambles);
        request.setAttribute("nombre_muebles", listaNombreMuebles);

        request.getRequestDispatcher("AreaFabrica/EnsambleMueble.jsp").forward(request, response);
    }

}
