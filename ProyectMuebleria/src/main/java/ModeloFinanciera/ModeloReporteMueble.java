/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFinanciera;

import DBConnection.Conexion;
import EntidadesReporte.MuebleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class ModeloReporteMueble {

    private final String muebleMasVendido = "SELECT EM.nombre_mueble,EM.precio_ensamble, DC.precio,F.id,F.fecha_compra,F.precio_compra,F.NIT_cliente FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id INNER JOIN Factura F ON DC.id_factura=F.id WHERE EM.nombre_mueble = (SELECT EM.nombre_mueble From Ensamble_Mueble EM INNER JOIN Detalle_Compra DC ON EM.id=DC.id_ensamble group by EM.nombre_mueble order by EM.nombre_mueble ASC limit 1)";
    private final String muebleMasVendidoIntervalo = muebleMasVendido+" AND (F.fecha_compra BETWEEN ? AND ?)";
    private final String muebleMenosVendido = "SELECT EM.nombre_mueble,EM.precio_ensamble, DC.precio,F.id,F.fecha_compra,F.precio_compra,F.NIT_cliente FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id INNER JOIN Factura F ON DC.id_factura=F.id WHERE EM.nombre_mueble = (SELECT EM.nombre_mueble From Ensamble_Mueble EM INNER JOIN Detalle_Compra DC ON EM.id=DC.id_ensamble group by EM.nombre_mueble order by EM.nombre_mueble DESC limit 1)";
    private final String muebleMenosVendidoIntervalo = muebleMenosVendido+" AND (F.fecha_compra BETWEEN ? AND ?)";
    
    private final Connection conexion = Conexion.getConexion();

    public ArrayList<MuebleVenta> getMuebleMasVendido() {
        ArrayList<MuebleVenta> listaMuebles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(muebleMasVendido);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                listaMuebles.add(new MuebleVenta(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDouble(6),
                        rs.getString(7)));
            }

        } catch (Exception e) {
        }
        return listaMuebles;
    }

    public ArrayList<MuebleVenta> getMuebleMasVendidoIntervalo(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<MuebleVenta> listaMuebles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(muebleMasVendidoIntervalo)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    listaMuebles.add(new MuebleVenta(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDouble(6),
                        rs.getString(7)));
                }
            }

        } catch (Exception e) {
        }
        return listaMuebles;
    }
    
    public ArrayList<MuebleVenta> getMuebleMenosVendido() {
        ArrayList<MuebleVenta> listaMuebles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(muebleMenosVendido);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                listaMuebles.add(new MuebleVenta(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDouble(6),
                        rs.getString(7)));
            }

        } catch (Exception e) {
        }
        return listaMuebles;
    }
    
    public ArrayList<MuebleVenta> getMuebleMenosVendidoIntervalo(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<MuebleVenta> listaMuebles = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(muebleMenosVendidoIntervalo)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    listaMuebles.add(new MuebleVenta(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getDouble(6),
                        rs.getString(7)));
                }
            }

        } catch (Exception e) {
        }
        return listaMuebles;
    }

}
