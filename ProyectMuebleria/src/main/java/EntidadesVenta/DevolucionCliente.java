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
public class DevolucionCliente extends Factura {
    
    private int idEnsamble;
    private String nombreMueble;

    public DevolucionCliente(int idEnsamble, String nombreMueble, int idFactura, LocalDate fechaCompra, Double precioCompra) {
        super(idFactura, fechaCompra, precioCompra);
        this.idEnsamble = idEnsamble;
        this.nombreMueble = nombreMueble;
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
    
    
}
