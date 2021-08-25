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
    
    public int comprobarDatos(){
        int contador = 0;
        String query = "SELECT COUNT(*) FROM Cliente UNION "
                + "SELECT COUNT(*) FROM Asignacion_Precio UNION "
                + "SELECT COUNT(*) FROM Usuario UNION "
                + "SELECT COUNT(*) FROM Ensamble_Mueble UNION "
                + "SELECT COUNT(*) FROM Pieza UNION "
                + "SELECT COUNT(*) FROM Mueble UNION ALL "
                + "SELECT COUNT(*) FROM Ensamble_Pieza;";
        
        try (PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery()){
            
            while (rs.next()) {
                if(rs.getInt(1)>0){
                    contador++;
                }
            }
            
            if (contador==7) {
                return 1;
            } else {
                return 0;
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
}
