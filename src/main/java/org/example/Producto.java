package org.example;

public class Producto {
    private int sku;
    private double priceRetail;
    private double priceCurrent;
    private String name;
    private String category;
    
    public Producto(int sku, double priceRetail, double priceCurrent, String name, String category) {
        this.sku = sku;
        this.priceRetail = priceRetail;
        this.priceCurrent = priceCurrent;
        this.name = name;
        this.category = category;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
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
    public String toString() {
        return "Producto [sku=" + sku + ", priceRetail=" + priceRetail + ", priceCurrent=" + priceCurrent + ", name="
                + name + ", category=" + category + "]";
    }
    
    
}
