package food_type;

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

public class burger_listview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_burger_listview);
        RecyclerView recyclerView = findViewById(R.id.burger_recycler_view);
        List<food_items_listData> burgerItemsListDataList = new ArrayList<food_items_listData>();

        burgerItemsListDataList.add(new food_items_listData(R.drawable.shrimp_burger,"49.000", "Shrimp Burger", "Shrimp Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.bulgogi_burger,"49.000", "Bulgogi Burger", "Bulgogi Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.mozzarella_burger,"88.000", "Mozzarella Burger", "Mozzarella Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.beef_burger,"38.000", "Beef Burger", "Beef Burger"));
        burgerItemsListDataList.add(new food_items_listData(R.drawable.fish_burger,"40.000", "Fish Burger", "Fish Burger"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,burgerItemsListDataList));
    }
}