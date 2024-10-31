package food_type;

import android.app.ActivityManager;
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

public class pizza_listview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza_listview);

        RecyclerView recyclerView = findViewById(R.id.pizza_recycler_view);
        List<pizza_items_listData> pizzaItemsListDataList = new ArrayList<pizza_items_listData>();

        pizzaItemsListDataList.add(new pizza_items_listData(R.drawable.sausage_pizza,"45.000", "Sausage Pizza", "Pizza + Tomato sauce + Mozzarella Cheese + Oregano"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new pizza_Adapter(this,pizzaItemsListDataList));

    }
}