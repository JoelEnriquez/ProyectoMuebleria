/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesMuebleria.AsignacionPrecio;
import EntidadesMuebleria.Pieza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaPieza {

    private Connection conexion = Conexion.getConexion();
    private ArrayList<DatosLinea> datosPiezas;
    private ArrayList<Error> listaErrores;

    public LecturaPieza(ArrayList<DatosLinea> datosPiezas, ArrayList<Error> listaErrores) {
        this.datosPiezas = datosPiezas;
        this.listaErrores = listaErrores;
    }

    public void analizarPieza() {
        for (DatosLinea datosPieza : datosPiezas) {
            if (datosPieza.getDatos().length == 2) {
                String nombre = datosPieza.getDatos()[0];
                Double precio;
                try {
                    precio = Double.parseDouble(datosPieza.getDatos()[1]);
                    if (existenciaPieza(nombre) == 0) {
                        //No existe la Pieza
                        Pieza nuevaPieza = new Pieza(nombre, 1);
                        agregarPieza(nuevaPieza);
                    } else {
                        //Aumentar Existencia
                        aumentarExistencia(nombre);
                    }
                    AsignacionPrecio nuevaAsignacion = new AsignacionPrecio(precio, false, nombre);
                    agregarAsignacion(nuevaAsignacion);
                } catch (NumberFormatException e) {
                    listaErrores.add(new Error(datosPieza.getNumLinea(), "Formato", "No hay un formato apropiado del precio"));
                }
            }
            else{
                listaErrores.add(new Error(datosPieza.getNumLinea(), "Formato", "No vienen el numero de datos correctos"));
            }
        }
    }

    private void agregarPieza(Pieza nuevaPieza) {
        String query = "INSERT INTO Pieza VALUES (?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevaPieza.getTipo());
            ps.setDouble(2, nuevaPieza.getCantidadStock());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void agregarAsignacion(AsignacionPrecio nuevaAsignacion) {
        String query = "INSERT INTO Asignacion_Precio (precio,tipo_pieza,utilizada) VALUES (?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setDouble(1, nuevaAsignacion.getPrecio());
            ps.setString(2, nuevaAsignacion.getTipoPieza());
            ps.setBoolean(3, nuevaAsignacion.isUtilizada());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
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

    /**
     *
     * @param nombrePieza
     * @return La cantidad de piezas existentes
     */
    private int existenciaPieza(String nombrePieza) {
        String query = "SELECT COUNT(*) FROM Pieza WHERE tipo = ?";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nombrePieza);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
        return 0;
    }
}
