/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesReporte;

import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class EntidadDevolucion {
    
    private int idEnsamble;
    private String nombreMueble;
    private Double precioUnitario;
    private LocalDate fechaDevolucion;
    private Double perdidas;

    public EntidadDevolucion(int idEnsamble, String nombreMueble, Double precioUnitario, LocalDate fechaDevolucion, Double perdidas) {
        this.idEnsamble = idEnsamble;
        this.nombreMueble = nombreMueble;
        this.precioUnitario = precioUnitario;
        this.fechaDevolucion = fechaDevolucion;
        this.perdidas = perdidas;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Double getPerdidas() {
        return perdidas;
    }

    public void setPerdidas(Double perdidas) {
        this.perdidas = perdidas;
    }
    
    
}
