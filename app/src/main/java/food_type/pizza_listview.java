package food_type;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
        List<food_items_listData> pizzaItemsListDataList = new ArrayList<food_items_listData>();

        pizzaItemsListDataList.add(new food_items_listData(R.drawable.sausage_pizza,"45.000", "Sausage Pizza", "Pizza + Tomato sauce + Mozzarella Cheese + Oregano"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.seafood_pizza,"139.000", "Seafood Pizza", "Shrimps + Crab sticks + Tomatoes + Sweet corns + Mozzarella Cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.pepperoni_pizza,"119.000", "Pepperoni Pizza", "Pizza + Pepperoni + Mozzarella cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.supream_meat_lover_pizza,"139.000", "Supreme Meat Lover's", "Bacon + Sausage + Beef + Ham + Pepperoni + Mozzarella cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.shrimp_scampi_pizza,"139.000", "Shrimp Scampi", "Shrimps + Onions + Garlic butter sauce +  Mozzarella cheese"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,pizzaItemsListDataList));

    }
}