package food_type;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.List;

public class dessert_listview extends AppCompatActivity implements RecyclerViewInterface {

    List<food_items_listData> dessertItemsListDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dessert_listview);
        RecyclerView recyclerView = findViewById(R.id.dessert_recycler_view);
        dessertItemsListDataList = new ArrayList<>();

        dessertItemsListDataList.add(new food_items_listData(R.drawable.tiramisu_cake,"420.000", "Tiramisu cake", " "));
        dessertItemsListDataList.add(new food_items_listData(R.drawable.jelly_cake,"360.000", "Jelly cake", " "));
        dessertItemsListDataList.add(new food_items_listData(R.drawable.chocolate_ice_cream,"38.000", "Chocolate ice cream", " "));
        dessertItemsListDataList.add(new food_items_listData(R.drawable.flan_cake,"18.000", "Flan cake", " "));
        dessertItemsListDataList.add(new food_items_listData(R.drawable.avocado_ice_cream,"38.000", "Avocado ice cream", " "));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,dessertItemsListDataList,this));
    }

    @Override
    public void onItemClick(int position) {
        food_items_listData selectedDessert = dessertItemsListDataList.get(position);
        Intent intent = new Intent(dessert_listview.this, food_type_detail.class);
        intent.putExtra("food_name",selectedDessert.getName());
        intent.putExtra("food_price",selectedDessert.getPrice());
        intent.putExtra("food_description",selectedDessert.getDescription());
        intent.putExtra("food_image",selectedDessert.getImageResource());

        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        if("Tiramisu cake".equals(selectedDessert.getName())){
            titles.add("Size");
            options.add("Slice");
            options.add("Half cake");
            options.add("Full cake");
            prices.add("60.000");
            prices.add("240.000");
            prices.add("420.000");
        } else if("Jelly cake".equals(selectedDessert.getName())){
            titles.add("Flavor");
            options.add("Mango");
            options.add("Strawberry");
            options.add("Matcha");
            prices.add("60.000");
            prices.add("60.000");
            prices.add("60.000");
        } else if ("Chocolate ice cream".equals(selectedDessert.getName()) || "Avocado ice cream".equals(selectedDessert.getName()) ||
                "Flan cake".equals(selectedDessert.getName())){
            titles.add("Topping");
            options.add("Coconut Jelly");
            options.add("Bubble");
            options.add("Aloe Vera");
            prices.add("+3.000");
            prices.add("+3.000");
            prices.add("+3.000");
        }
        intent.putExtra("food_name",selectedDessert.getName());
        intent.putStringArrayListExtra("titles",titles);
        intent.putStringArrayListExtra("options", options);
        intent.putStringArrayListExtra("prices", prices);
        startActivity(intent);
    }
}