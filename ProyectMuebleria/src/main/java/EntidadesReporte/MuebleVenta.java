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
public class MuebleVenta {
    private String nombreMueble;
    private Double precioEnsamble;
    private Double precioUnidad;
    private int idFactura;
    private LocalDate fechaCompra;
    private Double precioCompra;
    private String NITCliente;

    public MuebleVenta(String nombreMueble, Double precioEnsamble, Double precioUnidad, int idFactura, LocalDate fechaCompra, Double precioCompra, String NITCliente) {
        this.nombreMueble = nombreMueble;
        this.precioEnsamble = precioEnsamble;
        this.precioUnidad = precioUnidad;
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.NITCliente = NITCliente;
    }
    
    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public Double getPrecioEnsamble() {
        return precioEnsamble;
    }

    public void setPrecioEnsamble(Double precioEnsamble) {
        this.precioEnsamble = precioEnsamble;
    }

    public Double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getNITCliente() {
        return NITCliente;
    }

    public void setNITCliente(String NITCliente) {
        this.NITCliente = NITCliente;
    }
    
    
    
}
