/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFabrica;

import DBConnection.Conexion;
import EntidadesFabrica.StockPieza;
import EntidadesMuebleria.AsignacionPrecio;
import EntidadesMuebleria.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloPieza {

    private Connection conexion = Conexion.getConexion();

    public void crearPieza(Pieza pieza) throws SQLException {
        String query = "INSERT INTO Pieza VALUES (?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, pieza.getTipo());
            ps.setInt(2, pieza.getCantidadStock());
            ps.execute();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new SQLException("El nombre de la pieza ya existe");
            }
        }
    }

    public void agregarPiezas(ArrayList<AsignacionPrecio> nuevasAsignaciones) {
        String query = "INSERT INTO Asignacion_Precio (precio,tipo_pieza,utilizada) VALUES (?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            for (AsignacionPrecio nuevaAsignacion : nuevasAsignaciones) {
                ps.setDouble(1, nuevaAsignacion.getPrecio());
                ps.setString(2, nuevaAsignacion.getTipoPieza());
                ps.setBoolean(3, nuevaAsignacion.isUtilizada());
                ps.executeUpdate();

                aumentarExistencia(nuevaAsignacion.getTipoPieza());
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452) {
                //Llave Foranea Incorrecta
            }
        }
    }

    private void aumentarExistencia(String tipoPieza) {
        String query = "UPDATE Pieza SET cantidad_stock = cantidad_stock + 1 WHERE tipo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, tipoPieza);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void eliminarPieza(String nombrePieza) {
        String query = "DELETE FROM Pieza WHERE tipo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nombrePieza);
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     *
     * @param tipoPieza
     * @param nuevoNombre
     * @return Si retorna 0, el nombre es el mismo. Si retorna 1 se cambia el
     * nombre correctamente
     * @throws SQLException
     */
    public int modificarNombrePieza(String tipoPieza, String nuevoNombre) throws SQLException {
        String query = "UPDATE Pieza SET tipo = ? WHERE tipo = ?";

        if (tipoPieza.equals(nuevoNombre)) {
            return 0;
        }

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoNombre);
            ps.setString(2, tipoPieza);
            return ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                //Llave Primaria Duplicada
                throw new SQLException("El nombre de pieza ya existe");
            }
        }
        return 0;
    }

    public ArrayList<StockPieza> piezasStock() {
        String query = "SELECT P.tipo, AP.precio, P.cantidad_stock FROM Pieza P INNER JOIN "
                + "Asignacion_Precio AP ON P.tipo = AP.tipo_pieza WHERE AP.utilizada = 0;";
        ArrayList<StockPieza> piezas = new ArrayList<>();

        try ( PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                piezas.add(new StockPieza(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return piezas;
    }
    
    public ArrayList<Pieza> stockPieza(){
        String query = "SELECT * FROM Pieza";
        ArrayList<Pieza> stockPiezas = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                stockPiezas.add(new Pieza(rs.getString(1), rs.getInt(2)));
            }    
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return stockPiezas;
    }
}
