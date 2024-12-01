package restaurant;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.List;

import food_type.RecyclerViewInterface;
import food_type.food_Adapter;
import food_type.food_type_detail;
import food_type.pizza_listview;

public class kfc_restaurant_list_view extends AppCompatActivity implements RecyclerViewInterface {
    private List<restaurant_items_listData> kfcItemsListData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kfc_restaurant_list_view);

        RecyclerView recyclerView = findViewById(R.id.kfc_recycler_view);
        kfcItemsListData = new ArrayList<>();
        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_1,"127.000","Combo Group 2",
                "2 Fried Chicken + 1 Burger Zinger + 2 Pepsi (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_2,"59.000","Combo Chicken 1",
                "1 Fried Chicken + 1 French Fries (R)/ Mashed Potato (R) & Coleslaw (R) + 1 Pepsi (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_3,"89.000","Combo Chicken 2",
                "2 Fried Chicken + 1 French Fries (R)/ Mashed Potato (R) & Coleslaw (R) + 1 Pepsi (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_4,"117.000","Combo Big Juicy",
                "1 Big Juicy + 1 Salad Hat + 1 Lipton (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_5,"84.000","Combo Fillet",
                "1 Flavor Fillet + 1 Salad Hat + 1 Lipton (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_6,"77.000","Combo Burger Lava",
                "1 Burger Shrimp + 1 French Fries (R) + 1 Pepsi (L)"));

        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_7,"47.000","Combo Popcorn Pasta", "" +
                "1 Popcorn Pasta + 1 Pepsi (L)"));
        kfcItemsListData.add(new restaurant_items_listData(R.drawable.kfc_item_8,"69.000","Combo Teriyaki Rice",
                "1 Teriyaki Chicken Rice + 1 Soup + 1 Pepsi (L)"));

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new restaurant_Adapter(this, kfcItemsListData,this));
    }

    @Override
    public void onItemClick(int position) {
       restaurant_items_listData selectedKfc = kfcItemsListData.get(position);
        Intent intent = new Intent(kfc_restaurant_list_view.this, restaurant_detail.class);
        intent.putExtra("restaurant_food_name",selectedKfc.getRestaurant_food_name());
        intent.putExtra("restaurant_food_price",selectedKfc.getRestaurant_food_price());
        intent.putExtra("restaurant_food_description",selectedKfc.getRestaurant_food_description());
        intent.putExtra("restaurant_food_image",selectedKfc.getRestaurant_image());
        intent.putExtra("restaurant_food", "KFC");
        startActivity(intent);
    }
}