/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFabrica;

import EntidadesVenta.StockEnsamble;
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
    private final String queryEnsamblesSinVenta = "SELECT EM.id, M.nombre, M.precio FROM Ensamble_Mueble EM INNER JOIN Mueble M ON EM.nombre_mueble=M.nombre WHERE isnull(id_factura)";
    private final String queryListaEnsamble = "SELECT id, fecha_ensamble,precio_ensamble,nombre_usuario,nombre_mueble FROM Ensamble_Mueble";
    private final String queryNombreMuebles = "SELECT nombre FROM Mueble";

    public ArrayList<EnsambleMueble> getListaEnsambles() {
        ArrayList<EnsambleMueble> listaEnsambles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryListaEnsamble);  ResultSet rs = ps.executeQuery()) {
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
     *
     * @param filtro
     * @return
     */
    public ArrayList<EnsambleMueble> getListaEnsambles(String filtro) {
        ArrayList<EnsambleMueble> listaEnsambles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryListaEnsamble + " ORDER BY fecha_ensamble " + filtro);  ResultSet rs = ps.executeQuery()) {
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
     * Ensambles sin Venta Aun
     *
     * @return
     */
    public ArrayList<StockEnsamble> getListaEnsamblesSinVenta() {
        ArrayList<StockEnsamble> listaEnsambles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryEnsamblesSinVenta);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaEnsambles.add(new StockEnsamble(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)));
            }
        } catch (Exception e) {
        }
        return listaEnsambles;
    }

    public StockEnsamble getEnsambleStockPorId(int id) {
        try ( PreparedStatement ps = conexion.prepareStatement(queryEnsamblesSinVenta + " AND EM.id = ?")) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new StockEnsamble(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3));
                }
            }

        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<String> getNombreMuebles() {
        ArrayList<String> nombreMuebles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryNombreMuebles);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                nombreMuebles.add(
                        rs.getString(1));
            }
        } catch (Exception e) {
        }
        return nombreMuebles;
    }

    public void agregarEnsambleMueble(EnsambleMueble ensambleMueble) throws SQLException {
        String query = "INSERT INTO Ensamble_Mueble (fecha_ensamble,precio_ensamble,nombre_usuario,nombre_mueble) VALUES (?,?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setDate(1, java.sql.Date.valueOf(ensambleMueble.getFechaEnsamble()));
            ps.setDouble(2, ensambleMueble.getPrecioEnsamble());
            ps.setString(3, ensambleMueble.getNombreUsuario());
            ps.setString(4, ensambleMueble.getNombreMueble());

            ps.execute();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1406) {
                throw new SQLException("Se sobrepasa la cantidad de caracteres permitidos");
            } else {
                throw new SQLException("No se ha podido realizar el ensamble");
            }
        }
    }

    public int existenciaId(Integer idEnsamble) {
        String query = "SELECT COUNT(*) FROM Ensamble_Mueble WHERE isnull(id_factura) AND id=?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, idEnsamble);
            
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
