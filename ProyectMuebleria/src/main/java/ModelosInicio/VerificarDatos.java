/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosInicio;

import DBConnection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class VerificarDatos {
    
    Connection conexion = Conexion.getConexion();
    
    public int ComprobarDatos(){
        String query = "SELECT COUNT(*) FROM Cliente";
        
        try (PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
}
