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
public class Mueble {
    
    private String nombre;
    private Double precio;
    
    private int numeroMueble;

    public Mueble(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    /**
     * Show in JSP
     * @param nombre
     * @param precio
     * @param numeroMueble 
     */
    public Mueble(int numeroMueble,String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.numeroMueble = numeroMueble;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getNumeroMueble() {
        return numeroMueble;
    }

    public void setNumeroMueble(int numeroMueble) {
        this.numeroMueble = numeroMueble;
    }
    
    
    
    
}
