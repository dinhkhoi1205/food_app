package food_type;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.example.food_app_2.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class burger_listview extends AppCompatActivity implements RecyclerViewInterface {
    List<food_items_listData> burgerItemsListDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_burger_listview);
        RecyclerView recyclerView = findViewById(R.id.burger_recycler_view);
         burgerItemsListDataList = new ArrayList<>();

        burgerItemsListDataList.add(new food_items_listData(R.drawable.shrimp_burger,"49.000", "Shrimp Burger", "Shrimp Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.bulgogi_burger,"49.000", "Bulgogi Burger", "Bulgogi Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.mozzarella_burger,"88.000", "Mozzarella Burger", "Mozzarella Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.beef_burger,"38.000", "Beef Burger", "Beef Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.fish_burger,"40.000", "Fish Burger", "Fish Burger"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,burgerItemsListDataList,this));
    }

    @Override
    public void onItemClick(int position) {
        food_items_listData selectedBurger = burgerItemsListDataList.get(position);
        Intent intent = new Intent(burger_listview.this, food_type_detail.class);
        intent.putExtra("food_name",selectedBurger.getName());
        intent.putExtra("food_price",selectedBurger.getPrice());
        intent.putExtra("food_description",selectedBurger.getDescription());
        intent.putExtra("food_image",selectedBurger.getImageResource());
        intent.putExtra("food_type", "burgers");
        intent.putExtra("food_name",selectedBurger.getName());
        startActivity(intent);
    }
}