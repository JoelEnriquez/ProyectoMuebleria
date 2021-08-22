/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesMuebleria.Mueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaMueble {
    
    private Connection conexion = Conexion.getConexion();
    private ArrayList<String[]> datosMuebles;
    private ArrayList<Error> listaErrores;

    public LecturaMueble(ArrayList<String[]> datosMuebles, ArrayList<Error> listaErrores) {
        this.datosMuebles = datosMuebles;
        this.listaErrores = listaErrores;
    }
    
    public void analizarMueble() {
        for (String[] datosMueble : datosMuebles) {
            if (datosMueble.length == 2) {
                String nombre = datosMueble[0];
                Double precio = Double.parseDouble(datosMueble[1]);             

                Mueble nuevoMueble = new Mueble(nombre, precio);
                agregarMueble(nuevoMueble);
            }
        }
    }

    private void agregarMueble(Mueble nuevoMueble) {
        String query = "INSERT INTO Mueble VALUES (?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoMueble.getNombre());
            ps.setDouble(2, nuevoMueble.getPrecio());

            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
    
}
