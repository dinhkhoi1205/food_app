package restaurant;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.List;

import food_type.RecyclerViewInterface;

public class popeyes_restaurant_list_view extends AppCompatActivity implements RecyclerViewInterface {
    private List<restaurant_items_listData> popeyesListData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_popeyes_restaurant_list_view);

        RecyclerView recyclerView = findViewById(R.id.pop_recycler_view);
        popeyesListData = new ArrayList<>();
        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_1, "64.000", "Combo Snack Wings",
                "1 Chicken Popcorn, 2 Wings, 1 drink"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_2, "284.000", "Combo Wings Cheers",
                "3 Crunch Chickens, 4 Wings, 1 Spaghetti, 1 Shrimp Burger, 1 Cold Slot, 3 drinks"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_3, "169.000", "Combo Wings Chill",
                "1 Crunch Chicken, 2 Wings, 1 Spaghetti, 1 Shrimp Burger, 1 Rice, 2 Drinks"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_4, "77.000", "Combo Fried Chickens (2 pieces)",
                "2 Crunch Chickens, 1 Drink"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_5, "113.000", "Combo Fried Chickens (3 pieces)",
                "3 Crunch Chickens, 1 Drink"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_6, "103.000", "Combo Chicken Fish Sauce (1 person)",
                "2 Chickens Fish Sauce, 1 Salad Lime, 1 Drink"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_7, "185.000", "Combo Chicken Fish Sauce (2 persons)",
                "2 Chickens Fish Sauce, 2 Boneless Chickens, 1 Salad Lime, 1 Pop Burger, 2 Drinks"));

        popeyesListData.add(new restaurant_items_listData(R.drawable.popeyes_item_8, "82.000", "Combo Chickens Boneless (5 pieces)",
                "5 Chickens Boneless"));
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new restaurant_Adapter(this, popeyesListData, this));
    }

    @Override
    public void onItemClick(int position) {
        restaurant_items_listData selectedPopeyes = popeyesListData.get(position);
        Intent intent = new Intent(popeyes_restaurant_list_view.this, restaurant_detail.class);
        intent.putExtra("restaurant_food_name", selectedPopeyes.getRestaurant_food_name());
        intent.putExtra("restaurant_food_price", selectedPopeyes.getRestaurant_food_price());
        intent.putExtra("restaurant_food_description", selectedPopeyes.getRestaurant_food_description());
        intent.putExtra("restaurant_food_image", selectedPopeyes.getRestaurant_image());
        intent.putExtra("restaurant_food", "Popeyes");
        startActivity(intent);
    }
}