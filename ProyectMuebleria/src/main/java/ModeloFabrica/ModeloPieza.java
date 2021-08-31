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

    final String queryStock = "SELECT AP.id, P.tipo, AP.precio, P.cantidad_stock FROM Pieza P INNER JOIN Asignacion_Precio AP ON P.tipo = AP.tipo_pieza WHERE AP.utilizada = 0";
    private Connection conexion = Conexion.getConexion();

    public String nombrePorId(int id){
        String query = "SELECT tipo_pieza FROM Asignacion_Precio WHERE id = ?";
        
        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getString(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return "";
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
    
    private void eliminarExistencia(String tipoPieza) {
        String query = "UPDATE Pieza SET cantidad_stock = cantidad_stock - 1 WHERE tipo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, tipoPieza);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void eliminarPieza(int id) {
        String query = "DELETE FROM Asignacion_Precio WHERE id = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
            
            eliminarExistencia(nombrePorId(id)); //Restar en uno la cantidad de stock

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
        ArrayList<StockPieza> piezas = new ArrayList<>();

        try ( PreparedStatement ps = conexion.prepareStatement(queryStock); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                piezas.add(new StockPieza(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return piezas;
    }
    
    public ArrayList<StockPieza> piezasStockOrden(String orden) {
        ArrayList<StockPieza> piezas = new ArrayList<>();

        try ( PreparedStatement ps = conexion.prepareStatement(queryStock+" ORDER BY P.cantidad_stock "+orden);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                piezas.add(new StockPieza(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4)));
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
    
    public StockPieza devolverPorId(int id){
        String query = "SELECT precio, tipo_pieza FROM Asignacion_Precio WHERE id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, id);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return new StockPieza(id,
                            rs.getString("tipo_pieza"),
                            rs.getDouble("precio"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public ArrayList<String> nombresTipoPieza(){
        String query = "SELECT tipo FROM Pieza";
        ArrayList<String> listaNombres = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                listaNombres.add(rs.getString(1));
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return listaNombres;
    }
    
    public void actualizarPieza(int id, String tipoPieza, Double precio){
        String query = "UPDATE Asignacion_Precio SET precio = ?, tipo_pieza = ? WHERE id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setDouble(1, precio);
            ps.setString(2, tipoPieza);
            ps.setInt(3, id);
            
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
    }
}
