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
public class EntidadVentas {
    
    private int idFactura;
    private LocalDate fechaCompra;
    private Double precioFactura;
    private String NITCliente;
    private String nombreMueble;
    private Double precioCompra;
    private String nombreUsuario;

    public EntidadVentas(int idFactura, LocalDate fechaCompra, Double precioFactura, String NITCliente, String nombreMueble, Double precioCompra) {
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioFactura = precioFactura;
        this.NITCliente = NITCliente;
        this.nombreMueble = nombreMueble;
        this.precioCompra = precioCompra;
    }

    public EntidadVentas(int idFactura, LocalDate fechaCompra, Double precioFactura, String NITCliente, String nombreMueble, Double precioCompra, String nombreUsuario) {
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioFactura = precioFactura;
        this.NITCliente = NITCliente;
        this.nombreMueble = nombreMueble;
        this.precioCompra = precioCompra;
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public Double getPrecioFactura() {
        return precioFactura;
    }

    public void setPrecioFactura(Double precioFactura) {
        this.precioFactura = precioFactura;
    }

    public String getNITCliente() {
        return NITCliente;
    }

    public void setNITCliente(String NITCliente) {
        this.NITCliente = NITCliente;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    
    
    
   
    
}
