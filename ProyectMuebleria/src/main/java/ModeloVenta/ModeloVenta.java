/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import DBConnection.Conexion;
import EntidadesVenta.Factura;
import EntidadesVenta.StockEnsamble;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloVenta {
    
    private Connection conexion = Conexion.getConexion();
    private final String queryAgregarIdToEnsamble = "UPDATE Ensamble_Mueble SET id_factura = ? WHERE id = ?";
    private String queryAgregarFactura = "INSERT INTO Factura (fecha_compra, precio_compra, devolucion, NIT_Cliente,nombre_usuario) VALUES (?,?,?,?,?)";
    
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
            ps.setBoolean(3, factura.isDevolucion());
            ps.setString(4, factura.getNITCliente());
            ps.setString(5, factura.getNombreUsuario());
            
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
    
    public void agregarIdFacturaEnsamble(int idFactura, int idEnsamble) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(queryAgregarIdToEnsamble)){
            ps.setInt(1, idFactura);
            ps.setInt(2, idEnsamble);
            
            ps.execute();
        } catch (SQLException e) {
            throw new SQLException("Error al agregar id factura");
        }
    }
    
    
}
