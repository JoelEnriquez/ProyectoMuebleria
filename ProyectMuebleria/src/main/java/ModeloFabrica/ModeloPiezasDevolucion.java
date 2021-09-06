/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFabrica;

import DBConnection.Conexion;
import EntidadesFabrica.InfoDevolucion;
import EntidadesVenta.DetalleCompraNombres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloPiezasDevolucion {
    
    private final String queryGetIdForUpdate = "SELECT AP.id from Asignacion_Precio AP INNER JOIN Pieza P ON AP.tipo_pieza=P.tipo WHERE P.tipo = ? AND AP.utilizada = 1 LIMIT ?";
    private final String queryDeleteAsignacion = "DELETE FROM Asignacion_Precio WHERE id = ?";
    private final String querySetUtilizadaFalse = "UPDATE Asignacion_Precio SET utilizada = 0, precio = 0 WHERE id = ?";
    private final String querySetReutilizacionTrue = "UPDATE Detalle_Compra SET reutilizacion_piezas = 1 WHERE id_ensamble = ?";
    private final String queryCantidadRecetaPorPieza = "SELECT cantidad_pieza FROM Ensamble_Pieza WHERE nombre_mueble = ? AND tipo_pieza = ?";
    private final String queryMuebleSinReutilizar = "SELECT * FROM Detalle_Compra WHERE id_ensamble = ? AND devolucion = 1 AND reutilizacion_piezas = 0";
    private final String queryListaMueblesSinReutilizar = "SELECT DC.id_ensamble, EM.nombre_mueble, EM.precio_ensamble FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id WHERE DC.devolucion = 1 AND DC.reutilizacion_piezas = 0";
    private final Connection conexion = Conexion.getConexion();
    private final ModeloMueble modeloMueble = new ModeloMueble();
    
 
    public ArrayList<InfoDevolucion> getMueblesCompraSinReutilizar(){
        ArrayList<InfoDevolucion> mueblesSinReutilizar = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryListaMueblesSinReutilizar);
                ResultSet rs = ps.executeQuery()){
            
                while (rs.next()) {
                    mueblesSinReutilizar.add(new InfoDevolucion(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3)));
                }      
        } catch (Exception e) {
        }
        return mueblesSinReutilizar;
    }
    
    public DetalleCompraNombres getMuebleCompraSinReutilizar(int idEnsamble){
        DetalleCompraNombres muebleSinReutilizar = null;
        try (PreparedStatement ps = conexion.prepareStatement(queryMuebleSinReutilizar)){
            ps.setInt(1, idEnsamble);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    muebleSinReutilizar = new DetalleCompraNombres(
                            modeloMueble.nombrePorIdEnsamble(rs.getInt("id_ensamble")),
                            rs.getInt(1),
                            rs.getDouble(2),
                            rs.getBoolean(3),
                            rs.getBoolean(4),
                            rs.getInt(5));
                }
            }
            
        } catch (Exception e) {
        } 
        return muebleSinReutilizar;
    }
    
   
    public int cantidadPiezasEnReceta(String mueble, String pieza){
        try (PreparedStatement ps = conexion.prepareStatement(queryCantidadRecetaPorPieza)){
            ps.setString(1, mueble);
            ps.setString(2, pieza);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void setReutilizacionTrue(int idEnsamble) {
        try (PreparedStatement ps = conexion.prepareStatement(querySetReutilizacionTrue)){
            ps.setInt(1, idEnsamble);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
    }
    
    public ArrayList<Integer> asignacionesAfectar (String tipoPieza, int numeroPiezas){
        ArrayList<Integer> asignacionesAfectar = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryGetIdForUpdate)){
            ps.setString(1, tipoPieza);
            ps.setInt(2, numeroPiezas);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    asignacionesAfectar.add(rs.getInt(1));
                }
            }
            
        } catch (Exception e) {
        }
        return asignacionesAfectar;
    }
    
    public void resetAsignacionPrecio (int id){
        try (PreparedStatement ps = conexion.prepareStatement(querySetUtilizadaFalse)){
            ps.setInt(1, id);
            ps.execute();
            
        } catch (Exception e) {
        }
    }
    
    public void deleteAsignacionPrecio (int id){
        try (PreparedStatement ps = conexion.prepareStatement(queryDeleteAsignacion)){
            ps.setInt(1, id);
            ps.execute();
            
        } catch (Exception e) {
        }
    }
    
}
