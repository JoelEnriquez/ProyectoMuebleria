/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsamblePieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaEnsamblePieza {

    private Connection conexion = Conexion.getConexion();
    private ArrayList<String[]> datosEnsamblePiezas;

    public LecturaEnsamblePieza(ArrayList<String[]> datosEnsamblePiezas) {
        this.datosEnsamblePiezas = datosEnsamblePiezas;
    }

    public void analizarEnsamblePieza() {
        for (String[] datosEnsamblePieza : datosEnsamblePiezas) {
            if (datosEnsamblePieza.length == 3) {
                String nombreMueble = datosEnsamblePieza[0];
                String nombrePieza = datosEnsamblePieza[1];
                int cantidadPieza = Integer.parseInt(datosEnsamblePieza[2]);
                
                EnsamblePieza nuevoEnsamblePieza = new EnsamblePieza(nombreMueble, nombrePieza, cantidadPieza);
                agregarEnsamblePieza(nuevoEnsamblePieza);
            }
        }
    }

    private void agregarEnsamblePieza(EnsamblePieza nuevoEnsamblePieza) {
        String query = "INSERT INTO Ensamble_Pieza VALUES (?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoEnsamblePieza.getNombreMueble());
            ps.setString(2, nuevoEnsamblePieza.getNombrePieza());
            ps.setInt(3, nuevoEnsamblePieza.getCantidadPieza());

            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }

}
