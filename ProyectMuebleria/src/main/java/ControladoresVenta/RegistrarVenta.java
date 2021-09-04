/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresVenta;

import EntidadesVenta.StockEnsamble;
import ModeloFabrica.ModeloMueble;
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
@WebServlet(name = "RegistrarVenta", urlPatterns = {"/RegistrarVenta"})
public class RegistrarVenta extends HttpServlet {

    private ModeloMueble modeloMueble = new ModeloMueble();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validarSession(request, response);
        
        String idEliminar = "";
        if (request.getParameter("eliminarId") != null) {
            idEliminar = request.getParameter("eliminarId");
        }
        
        String action = request.getParameter("show");      

        mostrarEnsambles(request, response, idEliminar, action);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        validarSession(request, response);
        String id = request.getParameter("id_mueble");
        boolean success = true;
        ArrayList<Integer> idCompras;
        Integer idEnsamble = 0;

        //Verificar si ya hay una lista de compra disponible
        if (request.getSession().getAttribute("id_compras") != null) {
            idCompras = (ArrayList<Integer>) request.getSession().getAttribute("id_compras");
        } else {
            idCompras = new ArrayList<>();
        }

        try {
            idEnsamble = Integer.parseInt(id);
        } catch (NullPointerException | NumberFormatException e) {
            request.setAttribute("error", "Error en el formato del id");
            success = false;
        }

        if (success) {
            if (idCompras.contains(idEnsamble)) {
                request.setAttribute("error", "Ya existe ese id en el carrito");
            } else {
                //Comprobar que exista ese id
                if (modeloMueble.existenciaId(idEnsamble) == 1) {
                    idCompras.add(idEnsamble);
                } else {
                    request.setAttribute("error", "No existe ese id");
                }
            }
        }

        request.getSession().setAttribute("id_compras", idCompras); //Agregar el id al carrito
        mostrarEnsambles(request, response, "","sale");

    }

    private void mostrarEnsambles(HttpServletRequest request, HttpServletResponse response, String idEliminar, String show)
            throws ServletException, IOException {

        String URL = "";
        ArrayList<Integer> idCompras;
        ArrayList<StockEnsamble> ensamblesStock = modeloMueble.getListaEnsamblesSinVenta();
        ArrayList<StockEnsamble> carritoCompras;

        if (request.getSession().getAttribute("id_compras") != null) {
            idCompras = (ArrayList<Integer>) request.getSession().getAttribute("id_compras");

            if (!idEliminar.equals("")) {
                idCompras.remove(Integer.valueOf(idEliminar));
            }

            if (idCompras.size() > 0) {
                //Ajustar carrito y stock
                ensamblesStock = eliminarEnCarrito(ensamblesStock, idCompras);
                carritoCompras = new ArrayList<>();
                for (Integer idCompra : idCompras) {
                    carritoCompras.add(modeloMueble.getEnsambleStockPorId(idCompra));
                }
                request.setAttribute("carrito", carritoCompras);
                request.getSession().setAttribute("id_compras", idCompras); //Volver a guardar en la sesion
            }
        }
        
        if (show.equals("stock")) {
            URL = "AreaVenta/StockMuebles.jsp";
        }
        else{
            URL = "AreaVenta/RegistrarVenta.jsp";
        }
        request.setAttribute("ensambles", ensamblesStock);
        request.getRequestDispatcher(URL).forward(request, response);
    }

    private ArrayList<StockEnsamble> eliminarEnCarrito(ArrayList<StockEnsamble> ensambles, ArrayList<Integer> idCompras) {
        for (Integer idCompra : idCompras) {
            for (int i = 0; i < ensambles.size(); i++) {
                if (ensambles.get(i).getId() == idCompra) {
                    ensambles.remove(i);
                }
            }
        }
        return ensambles;
    }
    
    /**
     * Verifica sesion existente
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void validarSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (request.getSession().getAttribute("nombre") == null || !request.getSession().getAttribute("persona").equals("Venta")) {
            response.sendRedirect(request.getContextPath() + "/ControlLogOut");
        }
    }
}
