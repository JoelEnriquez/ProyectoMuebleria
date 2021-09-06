/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloFinanciera;

import DBConnection.Conexion;
import EntidadesReporte.EntidadDevolucion;
import EntidadesReporte.EntidadGanancia;
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
public class ModeloReporteFinanzas {

    private final Connection conexion = Conexion.getConexion();

    private final String gananciasQuery = "SELECT EM.id, EM.nombre_mueble,F.fecha_compra, DC.precio AS ingresos, (EM.precio_ensamble) AS costo, (DC.precio-EM.precio_ensamble) AS ganancia FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id INNER JOIN Factura F ON DC.id_factura=F.id";
    private final String perdidasQuery = "select EM.id as id_ensamble, (EM.precio_ensamble/3) as perdida_devolucion, DC.fecha_devolucion FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble=EM.id WHERE DC.devolucion = 1";
    private final String gananciasFiltroQuery = gananciasQuery + " WHERE (F.fecha_compra BETWEEN ? AND ?)";
    private final String perdiasFiltroQuery = perdidasQuery + " AND (DC.fecha_devolucion BETWEEN ? AND ?)";
    private final String devolucionesQuery = "SELECT EM.id,EM.nombre_mueble, DC.precio,DC.fecha_devolucion, (EM.precio_ensamble/3) AS Perdidas FROM Detalle_Compra DC INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble = EM.id WHERE DC.devolucion=1";
    private final String devolucionesFiltroQuery = devolucionesQuery + " AND (DC.fecha_devolucion BETWEEN ? AND ?)";
    private final String ventasQuery = "SELECT F.id, F.fecha_compra,F.precio_compra,F.NIT_cliente,EM.nombre_mueble, DC.precio FROM Detalle_Compra DC INNER JOIN Factura F ON DC.id_factura = F.id INNER JOIN Ensamble_Mueble EM ON DC.id_ensamble = EM.id";
    private final String ventasIntervaloQuery = ventasQuery + " WHERE (F.fecha_compra BETWEEN ? AND ?)";

    public ArrayList<EntidadVentas> getVentas() {
        ArrayList<EntidadVentas> listaVentas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(ventasQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaVentas.add(new EntidadVentas(
                        rs.getInt(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6)));
            }
        } catch (Exception e) {
        }
        return listaVentas;
    }

    /**
     * Ventas por intervalo de tiempo
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    public ArrayList<EntidadVentas> getVentasFilter(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<EntidadVentas> listaVentas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(ventasIntervaloQuery)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaVentas.add(new EntidadVentas(
                            rs.getInt(1),
                            rs.getDate(2).toLocalDate(),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getDouble(6)));
                }
            }
        } catch (Exception e) {
        }
        return listaVentas;
    }

    public ArrayList<EntidadDevolucion> getDevoluciones() {
        ArrayList<EntidadDevolucion> listaDevoluciones = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(devolucionesQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaDevoluciones.add(new EntidadDevolucion(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDate(4).toLocalDate(),
                        rs.getDouble(5)));
            }
        } catch (Exception e) {
        }
        return listaDevoluciones;
    }

    /**
     * Devoluciones por filtro
     *
     * @param fecha1
     * @param fecha2
     * @return
     */
    public ArrayList<EntidadDevolucion> getDevolucionesFilter(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<EntidadDevolucion> listaDevoluciones = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(devolucionesFiltroQuery)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaDevoluciones.add(new EntidadDevolucion(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getDate(4).toLocalDate(),
                            rs.getDouble(5)));
                }
            }
        } catch (Exception e) {
        }
        return listaDevoluciones;
    }

    public ArrayList<EntidadGanancia> getGanancias() {
        ArrayList<EntidadGanancia> listaGanancias = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(gananciasQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaGanancias.add(new EntidadGanancia(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        0.0));
            }
        } catch (Exception e) {
        }
        return listaGanancias;
    }

    public ArrayList<EntidadGanancia> getPerdidasDevolucion() {
        ArrayList<EntidadGanancia> listaPerdidas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(perdidasQuery);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listaPerdidas.add(new EntidadGanancia(
                        rs.getInt(1),
                        rs.getDouble(2)));
            }
        } catch (Exception e) {
        }
        return listaPerdidas;
    }

    public ArrayList<EntidadGanancia> getGananciasFiltro(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<EntidadGanancia> listaGanancias = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(gananciasFiltroQuery)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaGanancias.add(new EntidadGanancia(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getDate(3).toLocalDate(),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            0.0));
                }
            }

        } catch (Exception e) {
        }
        return listaGanancias;
    }

    public ArrayList<EntidadGanancia> getPerdidasDevolucionFiltro(LocalDate fecha1, LocalDate fecha2) {
        ArrayList<EntidadGanancia> listaPerdidas = new ArrayList<>();
        try ( PreparedStatement ps = conexion.prepareStatement(perdiasFiltroQuery)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha1));
            ps.setDate(2, java.sql.Date.valueOf(fecha2));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaPerdidas.add(new EntidadGanancia(
                            rs.getInt(1),
                            rs.getDouble(2)));
                }
            }
        } catch (Exception e) {
        }
        return listaPerdidas;
    }
}
