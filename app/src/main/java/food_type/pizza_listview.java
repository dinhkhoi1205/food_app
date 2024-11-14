package food_type;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pizza_listview extends AppCompatActivity implements RecyclerViewInterface  {

    private List<food_items_listData> pizzaItemsListDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza_listview);

        RecyclerView recyclerView = findViewById(R.id.pizza_recycler_view);
        pizzaItemsListDataList = new ArrayList<>();

        pizzaItemsListDataList.add(new food_items_listData(R.drawable.sausage_pizza,"45.000", "Sausage Pizza", "Pizza + Tomato sauce + Mozzarella Cheese + Oregano"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.seafood_pizza,"139.000", "Seafood Pizza", "Shrimps + Crab sticks + Tomatoes + Sweet corns + Mozzarella Cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.pepperoni_pizza,"119.000", "Pepperoni Pizza", "Pizza + Pepperoni + Mozzarella cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.supream_meat_lover_pizza,"139.000", "Supreme Meat Lover's", "Bacon + Sausage + Beef + Ham + Pepperoni + Mozzarella cheese"));
        pizzaItemsListDataList.add(new food_items_listData(R.drawable.shrimp_scampi_pizza,"139.000", "Shrimp Scampi", "Shrimps + Onions + Garlic butter sauce +  Mozzarella cheese"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,pizzaItemsListDataList,this));

    }

    @Override
    public void onItemClick(int position) {
        food_items_listData selectedPizza = pizzaItemsListDataList.get(position);
        Intent intent = new Intent(pizza_listview.this, food_type_detail.class);
        intent.putExtra("food_name",selectedPizza.getName());
        intent.putExtra("food_price",selectedPizza.getPrice());
        intent.putExtra("food_description",selectedPizza.getDescription());
        intent.putExtra("food_image",selectedPizza.getImageResource());

        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

            titles.add("Choose Pizza Size - Crust/Edge");
            options.add("Small - (The crust crunch)");
            options.add("Small - (The crust stretch hand)");
            options.add("Medium - (The crust crunch)");
            options.add("Medium - (The sausage crust)");
            options.add("Medium - (The cheese crust)");
            options.add("Large - (The crust crunch)");
            options.add("Large - (The crust stretch hand)");
            options.add("Large - (The sausage crust)");
            options.add("Large - (The cheese crust)");
            prices.add("");
            prices.add("");
            prices.add("+90.000");
            prices.add("+90.000");
            prices.add("+159.000");
            prices.add("+159.000");
            prices.add("+160.000");
            prices.add("+160.000");
            prices.add("+249.000");
            prices.add("+249.000");

        intent.putExtra("food_name",selectedPizza.getName());
        intent.putStringArrayListExtra("titles", titles);
        intent.putStringArrayListExtra("options", options);
        intent.putStringArrayListExtra("prices", prices);
        startActivity(intent);
    }
}