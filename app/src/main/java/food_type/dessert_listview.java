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
        intent.putExtra("food_type", "desserts");
        intent.putExtra("food_name",selectedDessert.getName());
        startActivity(intent);
    }
}