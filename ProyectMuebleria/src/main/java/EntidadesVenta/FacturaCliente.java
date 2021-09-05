/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesVenta;

import EntidadesPersona.Cliente;
import java.time.LocalDate;

/**
 *
 * @author joel
 */
public class FacturaCliente extends Cliente{
    
    private int idFactura;
    private LocalDate fechaCompra;
    private Double precioCompra;

    public FacturaCliente(int idFactura, LocalDate fechaCompra, Double precioCompra, String NIT, String nombre, String direccion, String municipio, String departamento) {
        super(NIT, nombre, direccion, municipio, departamento);
        this.idFactura = idFactura;
        this.fechaCompra = fechaCompra;
        this.precioCompra = precioCompra;
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
    
    
}
