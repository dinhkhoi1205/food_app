package restaurant;

public class restaurant_items_listData {
    private int restaurant_image;
    private String restaurant_food_name;
    private String restaurant_food_price;

    private String restaurant_food_description;

    public restaurant_items_listData(int restaurant_image, String restaurant_food_price, String restaurant_food_name,String restaurant_food_description) {
        this.restaurant_image = restaurant_image;
        this.restaurant_food_price = restaurant_food_price;
        this.restaurant_food_name = restaurant_food_name;
        this.restaurant_food_description = restaurant_food_description;
    }

    public String getRestaurant_food_description() {
        return restaurant_food_description;
    }

    public void setRestaurant_food_description(String restaurant_food_description) {
        this.restaurant_food_description = restaurant_food_description;
    }

    public int getRestaurant_image() {
        return restaurant_image;
    }

    public void setRestaurant_image(int restaurant_image) {
        this.restaurant_image = restaurant_image;
    }

    public String getRestaurant_food_name() {
        return restaurant_food_name;
    }

    public void setRestaurant_food_name(String restaurant_food_name) {
        this.restaurant_food_name = restaurant_food_name;
    }

    public String getRestaurant_food_price() {
        return restaurant_food_price;
    }

    public void setRestaurant_food_price(String restaurant_food_price) {
        this.restaurant_food_price = restaurant_food_price;
    }
}
