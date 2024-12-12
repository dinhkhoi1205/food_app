package bottom_nav_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.example.food_app_2.RecyclerViewInterface;
import com.example.food_app_2.app_home_page;

import java.util.ArrayList;
import java.util.List;

import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.food_type_detail;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;
import restaurant.kfc_restaurant_list_view;
import restaurant.popeyes_restaurant_list_view;
import restaurant.restaurant_detail;
import restaurant.star_buck_list_view;

public class HomeItemSearchScreen extends AppCompatActivity implements RecyclerViewInterface {

    ImageView backButton;
    RecyclerView recyclerView;
     List<HomeItem> homeItemList;
    HomeItemAdapter homeItemAdapter;
     SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_item_seach_screen);

        backButton = findViewById(R.id.back_arrow_home_item_view);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeItemSearchScreen.this, app_home_page.class);
                startActivity(intent);
            }
        });

        searchView = findViewById(R.id.search_bar_item_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        recyclerView = findViewById(R.id.recycler_search_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeItemList = new ArrayList<>();

        //Pizza list view
        homeItemList.add(new HomeItem("Sausage Pizza", R.drawable.sausage_pizza));
        homeItemList.add(new HomeItem("Seafood Pizza", R.drawable.seafood_pizza));
        homeItemList.add(new HomeItem("Pepperoni Pizza", R.drawable.pepperoni_pizza));
        homeItemList.add(new HomeItem("Supreme Meat Lover's", R.drawable.supream_meat_lover_pizza));
        homeItemList.add(new HomeItem("Shrimp Scampi", R.drawable.shrimp_scampi_pizza));

        //Burger list view
        homeItemList.add(new HomeItem("Shrimp Burger", R.drawable.shrimp_burger));
        homeItemList.add(new HomeItem("Bulgogi Burger", R.drawable.bulgogi_burger));
        homeItemList.add(new HomeItem("Mozzarella Burger", R.drawable.mozzarella_burger));
        homeItemList.add(new HomeItem("Beef Burger", R.drawable.beef_burger));
        homeItemList.add(new HomeItem("Fish Burger", R.drawable.fish_burger));

        //Dessert list view
        homeItemList.add(new HomeItem("Tiramisu cake", R.drawable.tiramisu_cake));
        homeItemList.add(new HomeItem("Jelly cake", R.drawable.jelly_cake));
        homeItemList.add(new HomeItem("Chocolate ice cream", R.drawable.chocolate_ice_cream));
        homeItemList.add(new HomeItem("Flan cake", R.drawable.flan_cake));
        homeItemList.add(new HomeItem("Avocado ice cream", R.drawable.avocado_ice_cream));

        //Milk tea list view
        homeItemList.add(new HomeItem("Oreo Machiato Milk Tea", R.drawable.oreo_milk_tea));
        homeItemList.add(new HomeItem("Matcha Milk Tea", R.drawable.matcha_milk_tea));
        homeItemList.add(new HomeItem("Purple Yam Milk Tea", R.drawable.purple_yam_milk_tea));
        homeItemList.add(new HomeItem("Chocolate Milk Tea", R.drawable.chocolate_milk_tea));
        homeItemList.add(new HomeItem("Mango Milk Tea", R.drawable.mango_milk_tea));

        //Rice list view
        homeItemList.add(new HomeItem("Broken rice with rib", R.drawable.broken_rice_rib));
        homeItemList.add(new HomeItem("Broken rice with rib, egg", R.drawable.broken_rice_rib_egg));
        homeItemList.add(new HomeItem("Broken rice with rib,sausage", R.drawable.broken_rice_rib_sausage));
        homeItemList.add(new HomeItem("Broken rice with chicken", R.drawable.broken_rice_chicken));
        homeItemList.add(new HomeItem("Rice with braised catfish", R.drawable.broken_rice_catfish));

        //KFC list view
        homeItemList.add(new HomeItem("KFC",R.drawable.kfc));

        //Popeyes list view
        homeItemList.add(new HomeItem("Popeyes", R.drawable.popeeyes));

        //Starbucks list view
        homeItemList.add(new HomeItem("Starbucks", R.drawable.star_buck_logo));

        homeItemAdapter = new HomeItemAdapter(this,homeItemList,this);
        recyclerView.setAdapter(homeItemAdapter);
    }

    private void filterList(String text) {
        List<HomeItem> filterdList = new ArrayList<>();
        for(HomeItem item: homeItemList){
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
                filterdList.add(item);
        }

        if(filterdList.isEmpty()){
            Toast.makeText(this, "Can not find the food", Toast.LENGTH_SHORT).show();
        }
        else {
            homeItemAdapter.setFilteredList(filterdList);
        }
    }

    @Override
    public void onItemClick(int position) {
        HomeItem selectHomeItem = homeItemList.get(position);
        String itemName = selectHomeItem.getName().toLowerCase();
        Intent intent ;
        if (itemName.contains("kfc")) {
            intent = new Intent(HomeItemSearchScreen.this, kfc_restaurant_list_view.class);
        } else if (itemName.contains("popeyes")) {
            intent = new Intent(HomeItemSearchScreen.this, popeyes_restaurant_list_view.class);
        } else if (itemName.contains("starbucks")) {
            intent = new Intent(HomeItemSearchScreen.this, star_buck_list_view.class);
        } else if (itemName.contains("pizza") || itemName.contains("supreme") || itemName.contains("scampi")) {
            intent = new Intent(HomeItemSearchScreen.this, pizza_listview.class);
        } else if (itemName.contains("burger")) {
            intent = new Intent(HomeItemSearchScreen.this, burger_listview.class);
        } else if (itemName.contains("ice cream") || itemName.contains("cake")) {
            intent = new Intent(HomeItemSearchScreen.this, dessert_listview.class);
        } else if (itemName.contains("milk tea")) {
            intent = new Intent(HomeItemSearchScreen.this, milktea_listview.class);
        } else if (itemName.contains("rice")) {
            intent = new Intent(HomeItemSearchScreen.this, rice_listview.class);
        } else {
            Toast.makeText(this, "No matching category found!", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(intent);
    }
}