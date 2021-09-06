/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesMuebleria.EnsamblePieza;
import EntidadesMuebleria.Mueble;
import ModeloFabrica.ModeloPieza;
import ModeloFinanciera.ModeloCRUDMueble;
import ModeloFinanciera.ModeloConsultaMueble;
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
@WebServlet(name = "CreacionMueble", urlPatterns = {"/CreacionMueble"})
public class CreacionMueble extends HttpServlet {

    private final ModeloConsultaMueble modeloMueble = new ModeloConsultaMueble();
    private final ModeloPieza modeloPieza = new ModeloPieza();
    private final ModeloCRUDMueble modeloCrud = new ModeloCRUDMueble();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String crear = request.getParameter("crear");
        if (crear!=null) {
            
        }
        setListMueblesAndReceta(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String pieza = request.getParameter("pieza");
        String cantidad = request.getParameter("cantidad");
        
        if (nombre!=null && precio!=null) {
            Mueble mueble = new Mueble(nombre, Double.parseDouble(pieza));
            request.getSession().setAttribute("mueble", mueble);
        }

    }

    private void setListMueblesAndReceta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Set Data in Request
        ArrayList<Mueble> listMuebles = modeloMueble.getListMuebles();
        ArrayList<String> tiposPieza = modeloPieza.nombresTipoPieza();
        ArrayList<EnsamblePieza> listRecetas = modeloMueble.getListRecetaMueble();

        request.setAttribute("muebles", listMuebles);
        request.setAttribute("recetas", listRecetas);
        request.setAttribute("tipo_piezas", tiposPieza);

        request.getRequestDispatcher("AreaFinanciera/CreacionMueble.jsp").forward(request, response);
    }
}
