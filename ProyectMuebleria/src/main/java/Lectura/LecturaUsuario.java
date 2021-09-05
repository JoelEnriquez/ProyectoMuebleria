/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lectura;

import Error.Error;
import DBConnection.Conexion;
import Encriptar.Encriptacion;
import EntidadesPersona.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class LecturaUsuario {

    private Connection conexion = Conexion.getConexion();
    private ArrayList<DatosLinea> datosUsuario;
    private ArrayList<Error> listaErrores;
    private Encriptacion encriptacion;

    public LecturaUsuario(ArrayList<DatosLinea> datosUsuario, ArrayList<Error> listaErrores) {
        this.datosUsuario = datosUsuario;
        this.listaErrores = listaErrores;
        encriptacion = new Encriptacion();
    }

    public void analizarUsuario() {
        for (DatosLinea datosUser : datosUsuario) {
            if (datosUser.getDatos().length == 3) {
                String nombre = datosUser.getDatos()[0];
                String password = datosUser.getDatos()[1];
                String passwordEncriptada;
                int tipoUsuario = 0;
                if (password.length() < 6) {
                    listaErrores.add(new Error(datosUser.getNumLinea(), "Formato", "La contraseña no tiene 6 caracteres como minimo"));
                } else {
                    try {
                        passwordEncriptada = encriptacion.encriptar(password);
                        tipoUsuario = Integer.valueOf(datosUser.getDatos()[2]);
                        //Verificar que solo venga 1,2 o 3
                        if (validarTipoUsuario(tipoUsuario)) {
                            Usuario nuevoUsuario = new Usuario(nombre, tipoUsuario, passwordEncriptada);
                            agregarUsuario(nuevoUsuario, datosUser.getNumLinea());
                        } else {
                            listaErrores.add(new Error(datosUser.getNumLinea(), "Logico", "No viene un tipo de usuario correcto"));
                        }

                    } catch (NumberFormatException e) {
                        listaErrores.add(new Error(datosUser.getNumLinea(), "Formato", "No existe un numero entero"));
                    } catch (Exception ex) {
                        listaErrores.add(new Error(datosUser.getNumLinea(), "Formato", "Error en la encriptacion del usuario"));
                    }
                }
            } else {
                listaErrores.add(new Error(datosUser.getNumLinea(), "Formato", "No vienen el numero de datos correctos"));
            }
        }
    }

    private void agregarUsuario(Usuario nuevoUsuario, int numeroLinea) {
        String query = "INSERT INTO Usuario (nombre,tipo,password) VALUES (?,?,?)";

        try ( PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nuevoUsuario.getNombre());
            ps.setInt(2, nuevoUsuario.getTipoUsuario());
            ps.setString(3, nuevoUsuario.getPassword());

            ps.execute();
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 1062:
                    //Llave Primaria Duplicada
                    listaErrores.add(new Error(numeroLinea, "Logico", "Se duplica la llave primaria de usuario"));
                    break;
                case 1406:
                    //Caracteres excedidos permitidos
                    listaErrores.add(new Error(numeroLinea, "Logico", "Se sobrepasa la cantidad de caracteres"));
                    break;
                default:
                    listaErrores.add(new Error(numeroLinea, "Logico", "No se ha podido ingresar el usuario correctamente"));
                    break;
            }
        }
    }

    private boolean validarTipoUsuario(int tipoUsuario) {
        return tipoUsuario >= 1 && tipoUsuario <= 3;
    }

}
