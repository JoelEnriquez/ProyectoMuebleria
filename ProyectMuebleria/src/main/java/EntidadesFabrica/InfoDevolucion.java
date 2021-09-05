/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesFabrica;

/**
 *
 * @author joel
 */
public class InfoDevolucion {
    
    private int idEnsamble;
    private String nombreMueble;
    private Double costoEnsamble;

    public InfoDevolucion(int idEnsamble, String nombreMueble, Double costoEnsamble) {
        this.idEnsamble = idEnsamble;
        this.nombreMueble = nombreMueble;
        this.costoEnsamble = costoEnsamble;
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

    public Double getCostoEnsamble() {
        return costoEnsamble;
    }

    public void setCostoEnsamble(Double costoEnsamble) {
        this.costoEnsamble = costoEnsamble;
    }
    
    
}
