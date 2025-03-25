/**
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios / Aux: Cristian Túnchez
 * @author: Marcela Castillo y Andrés Ismalej
*/
package org.example;

public class Producto implements Comparable <Producto> {
    private String sku;
    private double priceRetail;
    private double priceCurrent;
    private String name;
    private String category;
    
    public Producto(String sku, double priceRetail, double priceCurrent, String name, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.name = name;
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getPriceRetail() {
        return priceRetail;
    }

    public void setPriceRetail(double priceRetail) {
        this.priceRetail = priceRetail;
    }

    public double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int compareTo(Producto otroProducto) {
        if (this.sku == null || otroProducto.sku == null) {
            throw new IllegalArgumentException("El SKU no puede ser nulo");
        }
        return this.sku.compareTo(otroProducto.sku);
    }
    


    @Override
    public String toString() {
        return "Producto [sku=" + sku + ", priceRetail=" + priceRetail + ", priceCurrent=" + priceCurrent + ", name="
                + name + ", category=" + category + "]";
    }
    
    
}
