/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFinanciera;

import DBConnection.Conexion;
import EntidadesReporte.EntidadVentas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloReporteUsuario {

    private final String repeticionUserFactura = "select count(F.nombre_usuario) as count,F.nombre_usuario FROM Factura F group by F.nombre_usuario order by count DESC limit 1";
    private final String repeticionUserFacturaIntervalo = "select count(F.nombre_usuario) as count,F.nombre_usuario FROM Factura F WHERE (F.fecha_compra BETWEEN ? AND ?) group by F.nombre_usuario order by count DESC limit 1";
    private final String userRegistraMasVentasQuery = "SELECT F.id, F.fecha_compra, F.precio_compra, F.NIT_cliente,EM.nombre_mueble,DC.precio, F.nombre_usuario FROM Detalle_Compra DC INNER JOIN Factura F ON DC.id_factura = F.id INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id WHERE F.nombre_usuario=?";
    private final String userRegistraMasVentasIntervaloQuery = userRegistraMasVentasQuery+" AND (F.fecha_compra BETWEEN ? AND ?)";
    private final String listaUsersGanancias = "SELECT F.nombre_usuario FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id INNER JOIN Factura F ON DC.id_factura=F.id group by F.nombre_usuario";
    private final String listaUsersGananciasIntervalo = "SELECT F.nombre_usuario FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id INNER JOIN Factura F ON DC.id_factura=F.id WHERE (F.fecha_compra BETWEEN ? AND ?) group by F.nombre_usuario";
    
    private Connection conexion = Conexion.getConexion();

    public String nombreUsuarioMayorRepeticion() {
        try ( PreparedStatement ps = conexion.prepareStatement(repeticionUserFactura);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("nombre_usuario");
            }

        } catch (Exception e) {
        }
        return "";
    }
    
    public String nombreUsuarioMayorRepeticionIntervalo(LocalDate fecha1, LocalDate fecha2){
        try ( PreparedStatement ps = conexion.prepareStatement(repeticionUserFacturaIntervalo)){
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getString("nombre_usuario");
                }
            }
        } catch (Exception e) {
        }
        return "";
        
    }

    public ArrayList<EntidadVentas> ventasUsuarioMayorVentas(String usuario) {
        ArrayList<EntidadVentas> ventasUser = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(userRegistraMasVentasQuery)) {
            ps.setString(1, usuario);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ventasUser.add(new EntidadVentas(
                            rs.getInt(1),
                            rs.getDate(2).toLocalDate(),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6),
                            rs.getString(7)));
                }
            }

        } catch (Exception e) {
        }
        return ventasUser;
    }
    
    public ArrayList<EntidadVentas> ventasUsuarioMayorVentasIntervalo(String usuario, LocalDate fecha1, LocalDate fecha2) {
        ArrayList<EntidadVentas> ventasUser = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(userRegistraMasVentasIntervaloQuery)) {
            ps.setString(1, usuario);
            ps.setDate(2, java.sql.Date.valueOf(fecha1));
            ps.setDate(3, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ventasUser.add(new EntidadVentas(
                            rs.getInt(1),
                            rs.getDate(2).toLocalDate(),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6),
                            rs.getString(7)));
                }
            }

        } catch (Exception e) {
        }
        return ventasUser;
    }
    
    public ArrayList<String> usuariosGanancia(){
        ArrayList<String> listaUsuario = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(listaUsersGanancias)) {
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaUsuario.add(rs.getString(1));
                }
            }

        } catch (Exception e) {
        }
        return listaUsuario;
    }
    
    public ArrayList<String> usuariosGananciaIntervalo(LocalDate fecha1, LocalDate fecha2){
        ArrayList<String> listaUsuario = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(listaUsersGananciasIntervalo)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaUsuario.add(rs.getString(1));
                }
            }

        } catch (Exception e) {
        }
        return listaUsuario;
    }

}
