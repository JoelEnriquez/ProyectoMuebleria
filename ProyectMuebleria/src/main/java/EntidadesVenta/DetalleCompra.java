/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesVenta;

/**
 *
 * @author joel
 */
public class DetalleCompra {
    
    private int idEnsamble;
    private Double costoMueble;
    private boolean devolucion;
    private int idFactura;

    public DetalleCompra(int idEnsamble, Double costoMueble, boolean devolucion, int idFactura) {
        this.idEnsamble = idEnsamble;
        this.costoMueble = costoMueble;
        this.devolucion = devolucion;
        this.idFactura = idFactura;
    }

    public DetalleCompra(Double costoMueble, boolean devolucion, int idFactura) {
        this.costoMueble = costoMueble;
        this.devolucion = devolucion;
        this.idFactura = idFactura;
    }

    public int getIdEnsamble() {
        return idEnsamble;
    }

    public void setIdEnsamble(int idEnsamble) {
        this.idEnsamble = idEnsamble;
    }

    public Double getCostoMueble() {
        return costoMueble;
    }

    public void setCostoMueble(Double costoMueble) {
        this.costoMueble = costoMueble;
    }

    public boolean isDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
    
}
