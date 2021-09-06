/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloVenta;

import DBConnection.Conexion;
import EntidadesVenta.DetalleCompraNombres;
import EntidadesVenta.DevolucionCliente;
import EntidadesVenta.Factura;
import EntidadesVenta.FacturaCliente;
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
public class ReportesVenta {

    private final String queryDevolucionesCliente = "SELECT F.id,F.fecha_compra,F.precio_compra,DC.id_ensamble,EM.nombre_mueble FROM Detalle_Compra DC INNER JOIN Factura F ON DC.id_factura=F.id INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id WHERE F.NIT_cliente = ? AND DC.devolucion=1";
    private final String queryVentasDelDia = "SELECT F.id,F.fecha_compra,F.precio_compra,C.NIT,C.nombre,C.direccion,C.municipio,C.departamento FROM Factura F INNER JOIN Cliente C ON F.NIT_cliente=C.NIT WHERE date(now())";
    private final String queryCompras = "SELECT id, fecha_compra, precio_compra, NIT_cliente FROM Factura";
    private final String queryDetalleFactura = "SELECT * FROM Detalle_Compra WHERE id_factura = ?";
    private final String queryComprasCliente = queryCompras + " WHERE NIT_cliente = ?";
    private final String queryComprasClienteFiltroFecha = queryComprasCliente + " AND (fecha_compra BETWEEN ? AND ?)";

    private final Connection conexion = Conexion.getConexion();
    private ModeloMueble modeloMueble = new ModeloMueble();

    public ArrayList<Factura> getListaComprasCliente(String NIT) {
        ArrayList<Factura> listaCompras = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryComprasCliente)) {
            ps.setString(1, NIT);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaCompras.add(
                            new Factura(rs.getInt(1),
                                    rs.getDate(2).toLocalDate(),
                                    rs.getDouble(3),
                                    rs.getString(4)));
                }
            }
        } catch (Exception e) {
        }
        return listaCompras;
    }

    public ArrayList<Factura> getListaComprasClienteFiltroFecha(String NIT, LocalDate fecha1, LocalDate fecha2) {
        ArrayList<Factura> listaCompras = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryComprasClienteFiltroFecha)) {
            ps.setString(1, NIT);
            ps.setDate(2, java.sql.Date.valueOf(fecha1));
            ps.setDate(3, java.sql.Date.valueOf(fecha2));

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaCompras.add(
                            new Factura(rs.getInt(1),
                                    rs.getDate(2).toLocalDate(),
                                    rs.getDouble(3),
                                    rs.getString(4)));
                }
            }
        } catch (Exception e) {
        }
        return listaCompras;
    }

    /**
     * Detalles de la Factura en base al id de la Factura
     *
     * @param idFactura
     * @return Detalles factura
     */
    public ArrayList<DetalleCompraNombres> detallesCompraPorIdFactura(int idFactura) {
        ArrayList<DetalleCompraNombres> detallesCompra = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryDetalleFactura)) {
            ps.setInt(1, idFactura);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detallesCompra.add(new DetalleCompraNombres(
                            modeloMueble.nombrePorIdEnsamble(rs.getInt("id_ensamble")),
                            rs.getInt(1),
                            rs.getDouble(2),
                            rs.getBoolean(3),
                            rs.getBoolean(4),
                            idFactura));
                }
            }
        } catch (Exception e) {
        }
        return detallesCompra;
    }

    public ArrayList<FacturaCliente> getComprasToday() {
        ArrayList<FacturaCliente> comprasDelDia = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryVentasDelDia);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comprasDelDia.add(new FacturaCliente(
                        rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }

        } catch (Exception e) {
        }
        return comprasDelDia;
    }

    public ArrayList<DevolucionCliente> devolucionesCliente(String NIT) {
        ArrayList<DevolucionCliente> devolucionesCliente = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryDevolucionesCliente)) {
            ps.setString(1, NIT);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    devolucionesCliente.add(
                            new DevolucionCliente(
                                    rs.getInt("id_ensamble"),
                                    rs.getString("nombre_mueble"),
                                    rs.getInt("id"),
                                    rs.getDate("fecha_compra").toLocalDate(),
                                    rs.getDouble("precio_compra")));
                }
            }

        } catch (Exception e) {
        }
        return devolucionesCliente;
    }

    
    public ArrayList<DevolucionCliente> devolucionesClienteFiltroFecha(String NIT, LocalDate date1, LocalDate date2) {
        ArrayList<DevolucionCliente> devolucionesCliente = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(queryDevolucionesCliente + " AND (DC.fecha_devolucion BETWEEN ? AND ?)")) {
            ps.setString(1, NIT);
            ps.setDate(2, java.sql.Date.valueOf(date1));
            ps.setDate(3, java.sql.Date.valueOf(date2));

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    devolucionesCliente.add(
                            new DevolucionCliente(
                                    rs.getInt("id_ensamble"),
                                    rs.getString("nombre_mueble"),
                                    rs.getInt("id"),
                                    rs.getDate("fecha_compra").toLocalDate(),
                                    rs.getDouble("precio_compra")));
                }
            }

        } catch (Exception e) {
        }
        return devolucionesCliente;
    }
}
