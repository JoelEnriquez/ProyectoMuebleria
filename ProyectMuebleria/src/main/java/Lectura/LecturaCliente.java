/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import DBConnection.Conexion;
import EntidadesPersona.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaCliente {

    private Connection conexion = Conexion.getConexion();
    private ArrayList<String[]> datosClientes;

    public LecturaCliente(ArrayList<String[]> datosClientes) {
        this.datosClientes = datosClientes;
    }

    public void analizarCliente() {
        for (String[] datosCliente : datosClientes) {
            if (datosCliente.length == 3 || datosCliente.length == 5) {
                String nombre = datosCliente[0];
                String NIT = datosCliente[1];
                String direccion = datosCliente[2];
                
                if (datosCliente.length == 5) {
                    String municipio = datosCliente[3];
                    String departamento = datosCliente[4];
                    
                    Cliente clienteEspecifico = new Cliente(NIT, nombre, direccion, municipio, departamento);
                    agregarClienteEspecifico(clienteEspecifico);
                }
                else{
                    Cliente cliente = new Cliente(NIT, nombre, direccion);
                    agregarCliente(cliente);
                }
            }
        }
    }

    private void agregarCliente(Cliente nuevoCliente) {
        String query = "INSERT INTO Cliente (NIT,nombre,direccion) VALUES (?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoCliente.getNIT());
            ps.setString(2, nuevoCliente.getNombre());
            ps.setString(3, nuevoCliente.getDireccion());

            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
    
    private void agregarClienteEspecifico(Cliente nuevoCliente) {
        String query = "INSERT INTO Cliente VALUES (?,?,?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoCliente.getNIT());
            ps.setString(2, nuevoCliente.getNombre());
            ps.setString(3, nuevoCliente.getDireccion());
            ps.setString(4, nuevoCliente.getMunicipio());
            ps.setString(5, nuevoCliente.getDepartamento());

            ps.execute();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace(System.out);
        }
    }
}
