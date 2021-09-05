/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesPersona;

/**
 *
 * @author joel
 */
public class Usuario {
    
    private String nombre;
    private int tipoUsuario;
    private String password;
    private boolean revocado;

    private String nombreTipoUsuario;
    private int numeroUsuario;
    
    
    /**
     * To show in jsp, to create or revoque permitions
     * @param numeroUsuario
     * @param nombre
     * @param nombreTipoUsuario 
     */
    public Usuario(int numeroUsuario, String nombre, String nombreTipoUsuario) {
        this.numeroUsuario = numeroUsuario;
        this.nombre = nombre;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public Usuario(String nombre, String nombreTipoUsuario){
        this.nombre = nombre;
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    /**
     * Insertar a la DB
     * @param nombre
     * @param tipoUsuario
     * @param password 
     */
    public Usuario(String nombre, int tipoUsuario, String password) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public Usuario(String nombre, int tipoUsuario, String password, boolean revocado) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
        this.revocado = revocado;
    }

    public boolean isRevocado() {
        return revocado;
    }

    public void setRevocado(boolean revocado) {
        this.revocado = revocado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public int getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(int numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }
    
    
}
