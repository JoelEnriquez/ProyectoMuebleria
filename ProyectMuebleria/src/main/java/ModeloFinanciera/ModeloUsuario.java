/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFinanciera;

import DBConnection.Conexion;
import EntidadesPersona.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloUsuario {
    
    private final String queryGetUsers = "SELECT nombre,tipo FROM Usuario";
    private final String queryGetUserVentaFabrica = queryGetUsers+" WHERE NOT tipo = 3";
    private final String queryRevocarUsuario = "UPDATE Usuario SET revocado = 1 WHERE nombre = ?";
    private final String queryInsertarUsuario = "INSERT INTO Usuario (nombre,tipo,password) VALUES (?,?,?)";
    private final Connection conexion = Conexion.getConexion();
    
    public void ingresarUsuario(Usuario usuario) throws SQLException{
        try (PreparedStatement ps = conexion.prepareStatement(queryInsertarUsuario)){
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getTipoUsuario());
            ps.setString(3, usuario.getPassword());
            ps.execute();
            
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    throw new SQLException("Ya existe un usuario con dicho nombre");
                case 1406:
                    throw new SQLException("Se excede en la cantidad de caracteres permitidos");
                default:
                    throw new SQLException("No se ha podido insertar al usuario");
            }
        }
    }
    
    /**
     * Restringir el acceso a dicho usuario especificado
     * @param nombreUsuario 
     */
    public void revocarUsuario(String nombreUsuario){
        try (PreparedStatement ps = conexion.prepareStatement(queryRevocarUsuario)){
            ps.setString(1, nombreUsuario);
            ps.execute();
            
        } catch (SQLException e) {
        }
    }
    
    /**
     * Obtener la lista de Usuarios para restringir el acceso posterior
     * @return 
     */
    public ArrayList<Usuario> getUsersSaleAndFabrica() {
        ArrayList<Usuario> getUsersSaleAndFabrica = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryGetUserVentaFabrica);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                getUsersSaleAndFabrica.add(new Usuario(rs.getString(1), rs.getInt(2)));
            }
            
        } catch (Exception e) {
        }
        return getUsersSaleAndFabrica;  
    }
    
    public ArrayList<Usuario> getUsers (){
        ArrayList<Usuario> listUsers = new ArrayList<>();
        try (PreparedStatement ps = conexion.prepareStatement(queryGetUsers);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                listUsers.add(new Usuario(rs.getString(1), rs.getInt(2)));
            }
            
        } catch (Exception e) {
        }
        return listUsers;
    }
}
