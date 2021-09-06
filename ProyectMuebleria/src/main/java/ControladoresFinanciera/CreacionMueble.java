/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFinanciera;

import EntidadesMuebleria.EnsamblePieza;
import EntidadesMuebleria.Mueble;
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
    private final ModeloCRUDMueble modeloCrud = new ModeloCRUDMueble();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setListMueblesAndReceta(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    private void setListMueblesAndReceta(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        //Set Data in Request
        ArrayList<Mueble> listMuebles = modeloMueble.getListMuebles();
        ArrayList<EnsamblePieza> listRecetas = modeloMueble.getListRecetaMueble();
        request.setAttribute("muebles", listMuebles);
        request.setAttribute("recetas", listRecetas);
        
        request.getRequestDispatcher("AreaFinanciera/CreacionMueble.jsp").forward(request, response);
    }
}
