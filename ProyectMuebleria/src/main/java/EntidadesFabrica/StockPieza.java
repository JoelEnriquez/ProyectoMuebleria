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
public class StockPieza {
    
    private int id;
    private String tipoPieza;
    private Double precio;
    private int cantidadStock;

    public StockPieza(int id, String tipoPieza, Double precio, int cantidadStock) {
        this.id = id;
        this.tipoPieza = tipoPieza;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    
    /**
     * @return the tipoPieza
     */
    public String getTipoPieza() {
        return tipoPieza;
    }

    /**
     * @param tipoPieza the tipoPieza to set
     */
    public void setTipoPieza(String tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

    /**
     * @return the cantidadStock
     */
    public int getCantidadStock() {
        return cantidadStock;
    }

    /**
     * @param cantidadStock the cantidadStock to set
     */
    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
