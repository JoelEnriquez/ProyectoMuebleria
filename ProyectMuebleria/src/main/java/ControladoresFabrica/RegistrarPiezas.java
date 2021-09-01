/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFabrica;

import EntidadesMuebleria.AsignacionPrecio;
import EntidadesMuebleria.Pieza;
import ModeloFabrica.ModeloPieza;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "RegistrarPiezas", urlPatterns = {"/RegistrarPiezas"})
public class RegistrarPiezas extends HttpServlet {

    private ModeloPieza modeloPieza = new ModeloPieza();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        mostrarStock(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoPieza = request.getParameter("pieza");
        String cantidad = request.getParameter("cantidad");
        String precio = request.getParameter("precio");

        int cantidadFabricacion = 0;
        Double precioPieza = 0.0;
        try {
            cantidadFabricacion = Integer.parseInt(cantidad);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error en el formato de la cantidad");
            mostrarStock(request, response);
        }

        try {
            precioPieza = Double.parseDouble(precio);
        } catch (NullPointerException | NumberFormatException e) {
            request.setAttribute("error", "Error en el formato del precio");
            mostrarStock(request, response);
        }

        //Verificar que la cantidad sea mayor que 0
        if (cantidadFabricacion > 0) {
            if (precioPieza >= 0.0) {

                ArrayList<AsignacionPrecio> asignaciones = new ArrayList<>();
                for (int i = 0; i < cantidadFabricacion; i++) {
                    asignaciones.add(new AsignacionPrecio(precioPieza, false, tipoPieza));
                }
                try {
                    modeloPieza.agregarPiezas(asignaciones); //Registrar piezas y aumentar stock
                    request.setAttribute("success", true);
                } catch (SQLException e) {
                    request.setAttribute("error", "No se ha podido realizar el registro de piezas");
                }
            } else {
                request.setAttribute("error", "Error en el formato del precio");
            }
        } else {
            request.setAttribute("error", "La cantidad no puede ser negativa");
        }
        mostrarStock(request, response);
    }

    private void mostrarStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Pieza> stockPiezas = modeloPieza.stockPieza();
        ArrayList<String> tiposPieza = modeloPieza.nombresTipoPieza();

        request.setAttribute("stock", stockPiezas);
        request.setAttribute("tipo_piezas", tiposPieza);

        request.getRequestDispatcher("AreaFabrica/RegistrarPiezas.jsp").forward(request, response);
    }

}
