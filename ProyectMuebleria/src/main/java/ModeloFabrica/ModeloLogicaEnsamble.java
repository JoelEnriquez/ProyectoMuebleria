/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFabrica;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsambleMueble;
import EntidadesMuebleria.EnsamblePieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloLogicaEnsamble {
    
    private final Connection conexion = Conexion.getConexion();
    
    public ArrayList<EnsamblePieza> recetaPorMueble(String nombreMueble) {
        String query = "SELECT tipo_pieza, cantidad_pieza FROM Ensamble_Pieza WHERE nombre_mueble=?";
        ArrayList<EnsamblePieza> recetaPiezas = new ArrayList<>();

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nombreMueble);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    recetaPiezas.add(new EnsamblePieza(
                            nombreMueble,
                            rs.getString("tipo_pieza"),
                            rs.getInt("cantidad_pieza")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return recetaPiezas;
    }
    
    public int disponibilidadPieza(String tipoPieza) {
        String query = "SELECT count(*) FROM Asignacion_Precio WHERE tipo_pieza = ? AND utilizada = 0";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, tipoPieza);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    public Double costoEnsamblePieza(String tipoPieza, int cantidadPiezas) {
        String query = "select id, precio FROM Asignacion_Precio where tipo_pieza = ? AND utilizada = 0 LIMIT ?";
        Double costoEnsamblado = 0.0;

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, tipoPieza);
            ps.setInt(2, cantidadPiezas);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    costoEnsamblado += rs.getDouble("precio");
                    cambiarEstado(rs.getInt("id"));
                    restarExistencia(tipoPieza);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return costoEnsamblado;
    }

    public void cambiarEstado(int id) {
        String query = "UPDATE Asignacion_Precio SET utilizada = 1 WHERE id = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void restarExistencia(String tipoPieza){
        String query = "UPDATE Pieza SET cantidad_stock = cantidad_stock - 1 WHERE tipo = ?";
        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, tipoPieza);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
