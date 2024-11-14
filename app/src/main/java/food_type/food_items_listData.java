package food_type;

import java.util.List;

public class food_items_listData {
     private int imageResource;
     private String name, description, price;



    public food_items_listData(int imageResource, String price, String name, String description) {
        this.imageResource = imageResource;
        this.price = price;
        this.name = name;
        this.description = description;

    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
