/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import EntidadesVenta.DetalleCompraNombres;
import EntidadesVenta.Factura;
import ModeloVenta.ModeloDevolucion;
import ModeloVenta.ModeloVenta;
import ModeloVenta.ReportesVenta;
import ModeloVenta.ValidarSesionVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
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
@WebServlet(name = "Devolucion", urlPatterns = {"/Devolucion"})
public class Devolucion extends HttpServlet {

    private ReportesVenta reportesVenta = new ReportesVenta();
    private ModeloVenta modeloVenta = new ModeloVenta();
    private ModeloDevolucion modeloDevolucion = new ModeloDevolucion();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ValidarSesionVenta.validarSesion(request, response);

        String idFact = request.getParameter("idFactura");
        int idFactura = 0;

        if (idFact.isBlank() || idFact.isEmpty()) {
            request.setAttribute("error", "El id viene vacio. Favor de ingresarlo");
        } else {
            try {
                idFactura = Integer.parseInt(idFact); //Formatearlo correctamente

                //Verificar si existe id factura
                if (modeloDevolucion.verificarExistenciaFactura(idFactura) == 0) {
                    request.setAttribute("error", "No existe el id de factura");
                } else {
                    LocalDate fechaCompra = modeloDevolucion.fechaCompra(idFactura);

                    //Verificamos que no tenga mas de una semana de compra
                    long diasDiferencia = DAYS.between(fechaCompra, LocalDate.now());
                    if (diasDiferencia > 7) {
                        request.setAttribute("error", "Ya paso mas de una semana desde la compra");
                    } else {
                        Factura factura = modeloDevolucion.getFacturaPorID(idFactura);
                        //Obtener los detalles de la factura en base al id
                        ArrayList<DetalleCompraNombres> detallesFactura = modeloDevolucion.detallesCompraSinDevolucion(idFactura);

                        request.setAttribute("detalles", detallesFactura);
                        request.setAttribute("factura", factura);
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El id viene en formato incorrecto");
            }
        }

        request.getRequestDispatcher("AreaVenta/Devolucion.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String idFact = request.getParameter("idFactura");
        int idDevolucion;
        int idFactura;
        try {
            idDevolucion = Integer.parseInt(id);
            idFactura = Integer.parseInt(idFact);

            if (modeloDevolucion.verificarExistenciaFactura(idFactura) == 0) {
                request.setAttribute("error", "No existe una factura con dicho id");
            } else {
                //Verificar existencia id
                if (modeloDevolucion.verificarExistenciaDetalleCompra(idDevolucion) == 0) {
                    request.setAttribute("error", "No existe un ensamble con dicho id");
                } else {
                    Double costoFactura = modeloVenta.getCostoTotalFactura(idFactura); //Costo de factura
                    Double costoDetalle = modeloVenta.getCostoDetalleCompra(idDevolucion); //Costo de Mueble
                     
                    //Set Precio 0 y devolucion true. Ademas, setear el nuevo precio
                    modeloDevolucion.setDevolucionTrue(idDevolucion);
                    modeloDevolucion.setPrecioVacio(idDevolucion);
                    modeloDevolucion.setPrecioActualFactura(costoFactura-costoDetalle, idFactura);
                    request.setAttribute("success", true);
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El id viene en formato incorrecto");
        }
        request.getRequestDispatcher("AreaVenta/Devolucion.jsp").forward(request, response);
    }

}
