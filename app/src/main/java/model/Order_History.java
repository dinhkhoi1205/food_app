package model;

public class Order_History {
    private String food_name;
    private int quantity;
    private double price;


    public Order_History(String food_name,double price, int quantity) {
        this.food_name = food_name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
