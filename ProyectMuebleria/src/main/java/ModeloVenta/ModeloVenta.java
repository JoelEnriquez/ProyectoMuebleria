/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import DBConnection.Conexion;
import EntidadesVenta.DetalleCompra;
import EntidadesVenta.Factura;
import EntidadesVenta.StockEnsamble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloVenta {
    
    private Connection conexion = Conexion.getConexion();
    private final String queryGetPrecioFactura = "SELECT precio_compra FROM Factura WHERE id = ?";
    private final String queryGetPrecioDetalleCompra = "SELECT precio FROM Detalle_Compra WHERE id_ensamble = ?";
    private final String queryPrecioPorIdEnsamble = "SELECT M.precio FROM Mueble M INNER JOIN Ensamble_Mueble EM ON M.nombre = EM.nombre_mueble WHERE EM.id=?";
    private final String queryAgregarDetalleCompra = "INSERT INTO Detalle_Compra (id_ensamble,precio,devolucion,reutilizacion_piezas,id_factura) VALUES (?,?,?,?,?)";
    private final String queryAgregarFactura = "INSERT INTO Factura (fecha_compra, precio_compra, NIT_Cliente,nombre_usuario) VALUES (?,?,?,?)";
    
    public Double costoCompra (ArrayList<StockEnsamble> carritoCompras){
        Double costoTotal = 0.0;
        
        for (StockEnsamble carritoCompra : carritoCompras) {
            costoTotal += carritoCompra.getPrecio();
        }
        return costoTotal;
    }
    
    public int agregarFactura(Factura factura) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(queryAgregarFactura, Statement.RETURN_GENERATED_KEYS)){
            ps.setDate(1, java.sql.Date.valueOf(factura.getFechaCompra()));
            ps.setDouble(2, factura.getPrecioCompra());
            ps.setString(3, factura.getNITCliente());
            ps.setString(4, factura.getNombreUsuario());
            
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }         
        } catch (SQLException e) {
            if (e.getErrorCode()==1406) {
                throw new SQLException("Cantidad excedida en los caracteres establecidos");
            } else {
            throw new SQLException("No se ha podido realizar la insersion correctamente");
            }
        }
        return 0;
    }
    
    public void agregarDetalleCompra(DetalleCompra detalle) throws SQLException{    
        try (PreparedStatement ps = conexion.prepareStatement(queryAgregarDetalleCompra)){
            ps.setInt(1, detalle.getIdEnsamble());
            ps.setDouble(2, detalle.getCostoMueble());
            ps.setBoolean(3, detalle.isDevolucion());
            ps.setBoolean(4,detalle.isReutilizacionPiezas());
            ps.setInt(5, detalle.getIdFactura());
            
            ps.execute();
            
        } catch (SQLException e) {
            if (e.getErrorCode()==1406) {
                throw new SQLException("Cantidad excedida en los caracteres establecidos");
            } else {
            throw new SQLException("No se ha podido realizar la insersion correctamente");
            }
        }
    }
    
    public Double precioMueblePorId(int idEnsamble){
        try (PreparedStatement ps = conexion.prepareStatement(queryPrecioPorIdEnsamble)){
            ps.setInt(1, idEnsamble);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        }
        return 0.0;
    }
    
    public Double getCostoTotalFactura(int idFactura){
        try (PreparedStatement ps = conexion.prepareStatement(queryGetPrecioFactura)){
            ps.setInt(1, idFactura);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        }
        return 0.0;
    }
    
    public Double getCostoDetalleCompra(int idDetalle){
        try (PreparedStatement ps = conexion.prepareStatement(queryGetPrecioDetalleCompra)){
            ps.setInt(1, idDetalle);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
        }
        return 0.0;
    }
    
}
