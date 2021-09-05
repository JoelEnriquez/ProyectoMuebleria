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
    /**
     * Para Query Get Users 1 & 2
     * @param nombre
     * @param tipoUsuario 
     */
    public Usuario(String nombre, int tipoUsuario) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

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
    
    
}
