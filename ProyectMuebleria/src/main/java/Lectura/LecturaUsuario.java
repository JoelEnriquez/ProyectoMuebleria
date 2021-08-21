/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesPersona.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaUsuario {
    
    private Connection conexion = Conexion.getConexion();
    private ArrayList<String[]> datosUsuario;

    public LecturaUsuario(ArrayList<String[]> datosUsuario) {
        this.datosUsuario = datosUsuario;
    }

    
    public void analizarUsuario(){
        for (String[] datosUser : datosUsuario) {
            if (datosUser.length==3) {
                String nombre = datosUser[0];
                String password = datosUser[1];
                int tipoUsuario = Integer.valueOf(datosUser[2]);
                
                Usuario nuevoUsuario = new Usuario(nombre, tipoUsuario, password);
                agregarUsuario(nuevoUsuario);
            }
        }
    }
    
    private void agregarUsuario(Usuario nuevoUsuario) {
        String query = "INSERT INTO Usuario VALUES (?,?,?)";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoUsuario.getNombre());
            ps.setInt(2, nuevoUsuario.getTipoUsuario());
            ps.setString(3, nuevoUsuario.getPassword());
            
            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
    
}
