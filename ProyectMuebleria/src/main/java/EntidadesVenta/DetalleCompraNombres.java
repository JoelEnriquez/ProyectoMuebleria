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
public class DetalleCompraNombres extends DetalleCompra{

    private String nombreMueble;

    public DetalleCompraNombres(String nombreMueble, int idEnsamble, Double costoMueble, boolean devolucion, int idFactura) {
        super(idEnsamble, costoMueble, devolucion, idFactura);
        this.nombreMueble = nombreMueble;
    }

    public DetalleCompraNombres(String nombreMueble, Double costoMueble, boolean devolucion, int idFactura) {
        super(costoMueble, devolucion, idFactura);
        this.nombreMueble = nombreMueble;
    }
    
    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }
    
    
    
}
