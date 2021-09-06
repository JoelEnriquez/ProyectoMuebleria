/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import DBConnection.Conexion;
import EntidadesPersona.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joel
 */
public class ModeloNIT {

    private final String queryDatosCliente = "SELECT * FROM Cliente WHERE NIT=?";
    private Connection conexion = Conexion.getConexion();

    public Cliente getPorNIT(String NIT) {
        Cliente cliente = null;
        try ( PreparedStatement ps = conexion.prepareStatement(queryDatosCliente)) { 
            ps.setString(1, NIT);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5));
                }
            }
        } catch (Exception e) {
        }
        return cliente;
    }

    public void agregarCliente(Cliente cliente) throws SQLException{
        String query = "INSERT INTO Cliente VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, cliente.getNIT());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getMunicipio());
            ps.setString(5, cliente.getDepartamento());
            
            ps.execute();
            
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1406:
                    throw new SQLException("Se excede en la cantidad de caracteres");
                case 1062:
                    throw new SQLException("Ya existe un cliente con ese NIT");
                default:
                    throw new SQLException("No se ha podido ingresar el Cliente");
            }
        }
    }
    
    public int existenciaNIT(String NIT){
        String query = "SELECT COUNT(*) FROM Cliente WHERE NIT = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, NIT);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    
    
    
}
