package restaurant;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.List;

import com.example.food_app_2.RecyclerViewInterface;

public class star_buck_list_view extends AppCompatActivity implements RecyclerViewInterface {
    private List<restaurant_items_listData> starBucksItemsListData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_star_buck_list_view);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RecyclerView recyclerView = findViewById(R.id.star_bucks_recycler_view);
        starBucksItemsListData = new ArrayList<>();
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_1,"163.000","Caffè Mocha",
                ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_2,"60.000","Flat White", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_3,"70.000","Hazelnut Macchiato", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_4,"65.000","Skinny Flavored Latte", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_5,"60.000","Cappuccino", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_6,"65.000","Espresso Macchiato", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_7,"70.000","Iced Caffè Latte", ""));
        starBucksItemsListData.add(new restaurant_items_listData(R.drawable.star_buck_item_8,"65.000","Flavored Latte", ""));
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new restaurant_Adapter(this, starBucksItemsListData,this));
    }

    @Override
    public void onItemClick(int position) {
        restaurant_items_listData selectedStarBucks = starBucksItemsListData.get(position);
        Intent intent = new Intent(star_buck_list_view.this, restaurant_detail.class);
        intent.putExtra("restaurant_food_name",selectedStarBucks.getRestaurant_food_name());
        intent.putExtra("restaurant_food_price",selectedStarBucks.getRestaurant_food_price());
        intent.putExtra("restaurant_food_description",selectedStarBucks.getRestaurant_food_description());
        intent.putExtra("restaurant_food_image",selectedStarBucks.getRestaurant_image());
        intent.putExtra("restaurant_food", "StarBucks");
        startActivity(intent);
    }
}