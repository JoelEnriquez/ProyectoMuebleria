/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesMuebleria;

/**
 *
 * @author joel
 */
public class EnsamblePieza {
    private String nombreMueble;
    private String nombrePieza;
    private int cantidadPieza;
    
    private int numeroEnsamble;

    public EnsamblePieza(String nombreMueble, String nombrePieza, int cantidadPieza) {
        this.nombreMueble = nombreMueble;
        this.nombrePieza = nombrePieza;
        this.cantidadPieza = cantidadPieza;
    }

    /**
     * Constructor to show in JSP
     * @param nombreMueble
     * @param nombrePieza
     * @param cantidadPieza
     * @param numeroEnsamble 
     */
    public EnsamblePieza(int numeroEnsamble,String nombreMueble, String nombrePieza, int cantidadPieza) {
        this.nombreMueble = nombreMueble;
        this.nombrePieza = nombrePieza;
        this.cantidadPieza = cantidadPieza;
        this.numeroEnsamble = numeroEnsamble;
    }
    
    

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public int getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }

    public int getNumeroEnsamble() {
        return numeroEnsamble;
    }

    public void setNumeroEnsamble(int numeroEnsamble) {
        this.numeroEnsamble = numeroEnsamble;
    }
    
    
    
}
