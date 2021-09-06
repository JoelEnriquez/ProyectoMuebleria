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
public class EntidadGanancia {
    
    private int idEnsamble;
    private String nombreMueble;
    private LocalDate fechaCompra;
    private Double ingresosVenta;
    private Double costoFabricacion;
    private Double diferencia;
    
    private Double perdidasDevolucion;
    private Double ganancia;
    
    private Double ingresoTotal;
    private Double gananciaTotal;

    public EntidadGanancia(int idEnsamble, String nombreMueble, LocalDate fechaCompra, Double ingresosVenta, Double costoFabricacion, Double diferencia, Double perdidasDevolucion) {
        this.idEnsamble = idEnsamble;
        this.nombreMueble = nombreMueble;
        this.fechaCompra = fechaCompra;
        this.ingresosVenta = ingresosVenta;
        this.costoFabricacion = costoFabricacion;
        this.diferencia = diferencia;
        this.perdidasDevolucion = perdidasDevolucion;
    }

    /**
     * Contructor Completo
     * @param idEnsamble
     * @param nombreMueble
     * @param fechaCompra
     * @param ingresosVenta
     * @param costoFabricacion
     * @param diferencia
     * @param perdidasDevolucion
     * @param ganancia 
     */
    public EntidadGanancia(int idEnsamble, String nombreMueble, LocalDate fechaCompra, Double ingresosVenta, Double costoFabricacion, Double diferencia, Double perdidasDevolucion, Double ganancia) {
        this.idEnsamble = idEnsamble;
        this.nombreMueble = nombreMueble;
        this.fechaCompra = fechaCompra;
        this.ingresosVenta = ingresosVenta;
        this.costoFabricacion = costoFabricacion;
        this.diferencia = diferencia;
        this.perdidasDevolucion = perdidasDevolucion;
        this.ganancia = ganancia;
    }

    public EntidadGanancia(int idEnsamble, Double perdidasDevolucion) {
        this.idEnsamble = idEnsamble;
        this.perdidasDevolucion = perdidasDevolucion;
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

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getIngresosVenta() {
        return ingresosVenta;
    }

    public void setIngresosVenta(Double ingresosVenta) {
        this.ingresosVenta = ingresosVenta;
    }

    public Double getCostoFabricacion() {
        return costoFabricacion;
    }

    public void setCostoFabricacion(Double costoFabricacion) {
        this.costoFabricacion = costoFabricacion;
    }

    public Double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Double diferencia) {
        this.diferencia = diferencia;
    }

    public Double getPerdidasDevolucion() {
        return perdidasDevolucion;
    }

    public void setPerdidasDevolucion(Double perdidasDevolucion) {
        this.perdidasDevolucion = perdidasDevolucion;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public Double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(Double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public Double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(Double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
    
    
    
    
}
