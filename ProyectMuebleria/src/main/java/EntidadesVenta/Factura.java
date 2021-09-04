/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesVenta;

import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class Factura {
    
    private int idFactura;
    private LocalDate fechaCompra;
    private Double precioCompra;
    private boolean devolucion;
    private String NITCliente;
    private String nombreUsuario;

    public Factura(int idFactura, LocalDate fechaCompra, Double precioCompra, boolean devolucion, String NITCliente, String nombreUsuario) {
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.devolucion = devolucion;
        this.NITCliente = NITCliente;
        this.nombreUsuario = nombreUsuario;
    }

    public Factura(LocalDate fechaCompra, Double precioCompra, boolean devolucion, String NITCliente, String nombreUsuario) {
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.devolucion = devolucion;
        this.NITCliente = NITCliente;
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Constructor Reporte Compras Cliente
     * @param idFactura
     * @param fechaCompra
     * @param precioCompra
     * @param devolucion
     * @param NITCliente 
     */
    public Factura(int idFactura, LocalDate fechaCompra, Double precioCompra, boolean devolucion, String NITCliente) {
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
        this.devolucion = devolucion;
        this.NITCliente = NITCliente;
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

    public boolean isDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public String getNITCliente() {
        return NITCliente;
    }

    public void setNITCliente(String NITCliente) {
        this.NITCliente = NITCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
}
