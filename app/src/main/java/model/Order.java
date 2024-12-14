package model;

public class Order {
    private int idCart;
    private String nameCart;
    private double priceCart;
    private int quantityCart;



    public Order(int idCart, String nameCart, double priceCart, int quantityCart) {
        this.idCart = idCart;
        this.nameCart = nameCart;
        this.priceCart = priceCart;
        this.quantityCart = quantityCart;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public String getNameCart() {
        return nameCart;
    }

    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    public double getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(double priceCart) {
        this.priceCart = priceCart;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(int quantityCart) {
        this.quantityCart = quantityCart;
    }

}
