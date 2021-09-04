/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import DBConnection.Conexion;
import EntidadesVenta.DetalleCompraNombres;
import EntidadesVenta.Factura;
import ModeloFabrica.ModeloMueble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloDevolucion {
    
    private final String queryDetailsFacturaSinDevolucion = "SELECT * FROM Detalle_Compra WHERE devolucion = 0";
    private final String queryExistenciaFactura = "SELECT COUNT(*) FROM Factura WHERE id = ?";
    private final String queryFechaCompra = "SELECT fecha_compra FROM Factura WHERE id = ?";
    private final String queryGetFacturaById = "SELECT * FROM Factura WHERE id = ?";
    private final Connection conexion = Conexion.getConexion();
    private final ModeloMueble modeloMueble = new ModeloMueble();
    
    public int verificarExistenciaFactura(int id){
        try (PreparedStatement ps = conexion.prepareStatement(queryExistenciaFactura)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public LocalDate fechaCompra(int id){
        try (PreparedStatement ps = conexion.prepareStatement(queryFechaCompra)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDate(1).toLocalDate();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public Factura getFacturaPorID(int id){
        Factura factura = null;
        try (PreparedStatement ps = conexion.prepareStatement(queryGetFacturaById)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    factura = new Factura(
                            rs.getInt(1),
                            rs.getDate(2).toLocalDate(),
                            rs.getDouble(3),
                            rs.getString(4));
                }
            }
        } catch (Exception e) {
        }
        return factura;
    }
    
    /**
     * Detalles de la Factura sin ejecutar devolucion
     * @param idFactura
     * @return Detalles factura
     */
    public ArrayList<DetalleCompraNombres> detallesCompraSinDevolucion(int idFactura){
        ArrayList<DetalleCompraNombres> detallesCompra = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryDetailsFacturaSinDevolucion)){
            ps.setInt(1, idFactura);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    detallesCompra.add(new DetalleCompraNombres(
                            modeloMueble.nombrePorIdEnsamble(rs.getInt("id_ensamble")),
                            rs.getInt(1),
                            rs.getDouble(2),
                            rs.getBoolean(3),
                            idFactura));
                }
            }
        } catch (Exception e) {
        }
        return detallesCompra;
    }
}