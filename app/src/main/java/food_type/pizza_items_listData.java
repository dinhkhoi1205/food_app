package food_type;

public class pizza_items_listData {
     private int imageResource;
     private String name, description, price;

    public pizza_items_listData(int imageResource, String price, String name, String description) {
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
