/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresFabrica;

import EntidadesFabrica.InfoDevolucion;
import EntidadesMuebleria.EnsamblePieza;
import ModeloFabrica.ModeloLogicaEnsamble;
import ModeloFabrica.ModeloPieza;
import ModeloFabrica.ModeloPiezasDevolucion;
import java.io.IOException;
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
@WebServlet(name = "ReutilizarPiezas", urlPatterns = {"/ReutilizarPiezas"})
public class ReutilizarPiezas extends HttpServlet {

    private final ModeloPieza modeloPieza = new ModeloPieza();
    private final ModeloLogicaEnsamble modeloLogica = new ModeloLogicaEnsamble();
    private final ModeloPiezasDevolucion modeloPiezasDevolucion = new ModeloPiezasDevolucion();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URL = "AreaFabrica/ReutilizarPiezas.jsp";
        String idDevolucion = request.getParameter("id");
        String nombreMueble = request.getParameter("nombre");

        if (nombreMueble == null || nombreMueble.isBlank() || nombreMueble.isEmpty()) {
            ArrayList<InfoDevolucion> mueblesSinReutilizar = modeloPiezasDevolucion.getMueblesCompraSinReutilizar();
            request.setAttribute("info_devoluciones", mueblesSinReutilizar);
        } else {
            URL = "AreaFabrica/RescatarPiezas.jsp";
            if (nombreMueble.equals("finish")) {
                //Finalizar proceso
                ArrayList<EnsamblePieza> listaRescatar = (ArrayList<EnsamblePieza>) request.getSession().getAttribute("listaRescatar");
                int idEnsamble = Integer.parseInt(request.getSession().getAttribute("idEnsamble").toString());
                
                modeloPiezasDevolucion.setReutilizacionTrue(idEnsamble);
                for (EnsamblePieza ensamblePieza : listaRescatar) {
                    String tipoPieza = ensamblePieza.getNombrePieza();
                    String mueble = ensamblePieza.getNombreMueble();
                    int cantidadPiezas = ensamblePieza.getCantidadPieza();
                    
                    //Obtener cantidad receta, para saber cuantas asignaciones afectar, y cuantas eliminar
                    int cantidadReceta = modeloPiezasDevolucion.cantidadPiezasEnReceta(mueble,tipoPieza);
                    int cantidadDelete = cantidadReceta - cantidadPiezas;
                    
                    ArrayList<Integer> idAsignacionesUpdate = modeloPiezasDevolucion.asignacionesAfectar(tipoPieza, cantidadPiezas);
                    for (Integer id : idAsignacionesUpdate) {
                        modeloPiezasDevolucion.resetAsignacionPrecio(id);
                        modeloPieza.aumentarExistencia(tipoPieza);
                    }
                    
                    ArrayList<Integer> idAsignacionDelete = modeloPiezasDevolucion.asignacionesAfectar(tipoPieza, cantidadDelete);
                    for (Integer id : idAsignacionDelete) {
                        modeloPiezasDevolucion.deleteAsignacionPrecio(id);
                    }
                    
                }
                
                request.getSession().removeAttribute("listaRescatar"); //Matar la lista
                request.getSession().removeAttribute("idEnsamble"); //Matar el id
                //Retornar al JSP de Reutilizar Piezas
                ArrayList<InfoDevolucion> mueblesSinReutilizar = modeloPiezasDevolucion.getMueblesCompraSinReutilizar();
                request.setAttribute("info_devoluciones", mueblesSinReutilizar);
                URL = "AreaFabrica/ReutilizarPiezas.jsp";
                
            } else {
                ArrayList<EnsamblePieza> recetaPorMueble = modeloLogica.recetaPorMueble(nombreMueble);
                request.setAttribute("recetas", recetaPorMueble);
                request.getSession().setAttribute("idEnsamble", idDevolucion);
            }

        }
        
        request.getRequestDispatcher(URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mueble = request.getParameter("mueble");
        String pieza = request.getParameter("pieza");
        String cantidad = request.getParameter("cantidad");
        int cantidadSalvar = 0;
        boolean success = true;
        ArrayList<EnsamblePieza> listaRescatar;
        ArrayList<EnsamblePieza> recetaPorMueble = modeloLogica.recetaPorMueble(mueble);

        //Verificar si ya hay una lista de compra disponible
        if (request.getSession().getAttribute("listaRescatar") != null) {
            listaRescatar = (ArrayList<EnsamblePieza>) request.getSession().getAttribute("listaRescatar");
        } else {
            listaRescatar = new ArrayList<>();
        }

        try {
            cantidadSalvar = Integer.parseInt(cantidad);
        } catch (NullPointerException | NumberFormatException e) {
            request.setAttribute("error", "Error en el formato de la cantidad");
            success = false;
        }

        if (success) {
            //Verificar que venga una cantidad en el intervalo correcto
            int cantidadReceta = modeloPiezasDevolucion.cantidadPiezasEnReceta(mueble, pieza);
            if (cantidadSalvar<0 || cantidadSalvar>cantidadReceta) {
                request.setAttribute("error", "Error en el intervalo. Escojer una cantidad entre 0 y "+cantidadReceta);
            } else {
                EnsamblePieza ensamblePieza = new EnsamblePieza(mueble, pieza, cantidadSalvar);
                listaRescatar.add(ensamblePieza);
                
                //Mostrar la instruccion que no este en la lista a rescatar
                if (listaRescatar.size()==recetaPorMueble.size()) {
                    //Ya se ha cumplido el proceso
                    request.setAttribute("finish", true);
                }
            }
        }
        recetaPorMueble = ajustarLista(recetaPorMueble,listaRescatar);
        request.getSession().setAttribute("listaRescatar", listaRescatar);
        request.setAttribute("recetas", recetaPorMueble);
        request.getRequestDispatcher("AreaFabrica/RescatarPiezas.jsp").forward(request, response);
    }

    private ArrayList<EnsamblePieza> ajustarLista(ArrayList<EnsamblePieza> recetaPorMueble, ArrayList<EnsamblePieza> listaSalvar){
        for (EnsamblePieza ensamblePieza : listaSalvar) {
            for (int i = 0; i < recetaPorMueble.size(); i++) {
                if (ensamblePieza.getNombrePieza().equals(recetaPorMueble.get(i).getNombrePieza())) {
                    recetaPorMueble.remove(i);
                }
            }
        }
        return recetaPorMueble;
    }

}
