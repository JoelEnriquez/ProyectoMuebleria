/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFabrica;

import DBConnection.Conexion;
import EntidadesMuebleria.EnsambleMueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloMueble {
    
    private final Connection conexion = Conexion.getConexion();
    private final String queryListaEnsamble = "SELECT id, fecha_ensamble,precio_ensamble,nombre_usuario,nombre_mueble FROM Ensamble_Mueble";
    private final String queryNombreMuebles = "SELECT nombre FROM Mueble";
    
    public ArrayList<EnsambleMueble> getListaEnsambles(){
        ArrayList<EnsambleMueble> listaEnsambles = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryListaEnsamble);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                listaEnsambles.add(new EnsambleMueble(
                        rs.getInt(1), 
                        rs.getDate(2).toLocalDate(), 
                        rs.getDouble(3), 
                        rs.getString(4), 
                        rs.getString(5)));
            }         
        } catch (Exception e) {
        }
        return listaEnsambles;
    }
    
    /**
     * Devuelve ordenados por filtro de creacion
     * @param filtro
     * @return 
     */
    public ArrayList<EnsambleMueble> getListaEnsambles(String filtro){
        ArrayList<EnsambleMueble> listaEnsambles = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryListaEnsamble+" ORDER BY fecha_ensamble "+filtro);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                listaEnsambles.add(new EnsambleMueble(
                        rs.getInt(1), 
                        rs.getDate(2).toLocalDate(), 
                        rs.getDouble(3), 
                        rs.getString(4), 
                        rs.getString(5)));
            }         
        } catch (Exception e) {
        }
        return listaEnsambles;
    }
    
    public ArrayList<EnsambleMueble> getListaEnsamblesSinVenta(){
        ArrayList<EnsambleMueble> listaEnsambles = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryListaEnsamble);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                listaEnsambles.add(new EnsambleMueble(
                        rs.getInt(1), 
                        rs.getDate(2).toLocalDate(), 
                        rs.getDouble(3), 
                        rs.getString(4), 
                        rs.getString(5)));
            }         
        } catch (Exception e) {
        }
        return listaEnsambles;
    }
    
    public ArrayList<String> getNombreMuebles(){
        ArrayList<String> nombreMuebles = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryNombreMuebles);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                nombreMuebles.add(
                        rs.getString(1));
            }
        } catch (Exception e) {
        }
        return nombreMuebles;
    }

    public void agregarEnsambleMueble(EnsambleMueble ensambleMueble) throws  SQLException{
        String query = "INSERT INTO Ensamble_Mueble (fecha_ensamble,precio_ensamble,nombre_usuario,nombre_mueble) VALUES (?,?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(ensambleMueble.getFechaEnsamble()));
            ps.setDouble(2, ensambleMueble.getPrecioEnsamble());
            ps.setString(3, ensambleMueble.getNombreUsuario());
            ps.setString(4, ensambleMueble.getNombreMueble());

            ps.execute();
        } catch (SQLException e) {
            if (e.getErrorCode()==1406) {
                throw new SQLException("Se sobrepasa la cantidad de caracteres permitidos");
            } else {
                throw new SQLException("No se ha podido realizar el ensamble");
            }
        }
    }
}
