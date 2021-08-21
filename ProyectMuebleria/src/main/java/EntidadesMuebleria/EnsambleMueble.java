/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesMuebleria;

import java.sql.Date;

/**
 *
 * @author joel
 */
public class EnsambleMueble {
    
    private int id;
    private Date fechaEnsamble;
    private Double precioEnsamble;
    private String nombreUsuario;
    private String nombreMueble;

    public EnsambleMueble(int id, Date fechaEnsamble, Double precioEnsamble, String nombreUsuario, String nombreMueble) {
        this.id = id;
        this.fechaEnsamble = fechaEnsamble;
        this.precioEnsamble = precioEnsamble;
        this.nombreUsuario = nombreUsuario;
        this.nombreMueble = nombreMueble;
    }
    
    public EnsambleMueble(Date fechaEnsamble, Double precioEnsamble, String nombreUsuario, String nombreMueble) {
        this.fechaEnsamble = fechaEnsamble;
        this.precioEnsamble = precioEnsamble;
        this.nombreUsuario = nombreUsuario;
        this.nombreMueble = nombreMueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaEnsamble() {
        return fechaEnsamble;
    }

    public void setFechaEnsamble(Date fechaEnsamble) {
        this.fechaEnsamble = fechaEnsamble;
    }

    public Double getPrecioEnsamble() {
        return precioEnsamble;
    }

    public void setPrecioEnsamble(Double precioEnsamble) {
        this.precioEnsamble = precioEnsamble;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }
    
    
}
