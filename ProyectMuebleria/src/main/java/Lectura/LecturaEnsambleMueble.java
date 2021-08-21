/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsambleMueble;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaEnsambleMueble {

    private Connection conexion = Conexion.getConexion();
    private ArrayList<String[]> datosEnsambleMuebles;

    public LecturaEnsambleMueble(ArrayList<String[]> datosEnsambleMuebles) {
        this.datosEnsambleMuebles = datosEnsambleMuebles;
    }

    public void analizarEnsambleMueble() {
        for (String[] datosEnsambleMueble : datosEnsambleMuebles) {
            if (datosEnsambleMueble.length == 4) {
                Date fechaEnsamble = Date.valueOf(datosEnsambleMueble[0]);
                String nombreUsuario = datosEnsambleMueble[1];
                String nombreMueble = datosEnsambleMueble[2];
                
                Double precioEnsamble = precioVentaMueble(nombreMueble)*0.75;
                EnsambleMueble ensambleMueble = new EnsambleMueble(fechaEnsamble, precioEnsamble, nombreUsuario, nombreMueble);
                agregarEnsambleMueble(ensambleMueble);
            }
                
        }
    }

    private void agregarEnsambleMueble(EnsambleMueble ensambleMueble) {
        String query = "INSERT INTO Ensamble_Mueble (fecha_ensamble,precio_ensamble,nombre_usuario,nombre_mueble) VALUES (?,?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setDate(1, ensambleMueble.getFechaEnsamble());
            ps.setDouble(2, ensambleMueble.getPrecioEnsamble());
            ps.setString(3, ensambleMueble.getNombreUsuario());
            ps.setString(4, ensambleMueble.getNombreMueble());

            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
    
    private Double precioVentaMueble(String nombreMueble){
        String query = "SELECT precio FROM Mueble WHERE nombre = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query);){
            ps.setString(1, nombreMueble);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        }
        return null;
        
    }
}
