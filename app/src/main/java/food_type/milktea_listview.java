package food_type;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

public class milktea_listview extends AppCompatActivity implements RecyclerViewInterface {
    List<food_items_listData> milkTeaItemsListDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_milktea_listview);
        RecyclerView recyclerView = findViewById(R.id.milktea_recycler_view);
        milkTeaItemsListDataList = new ArrayList<>();

        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.oreo_milk_tea,"34.000", "Oreo Machiato Milk Tea", "Black Boba Include"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.matcha_milk_tea,"59.000", "Matcha Milk Tea", "Sweet milk tea with the smell of matcha's powder"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.purple_yam_milk_tea,"59.000", "Purple Yam Milk Tea", "The fatty of purple yam's milk tea, strong smell of purple yam. It is one of the best seller"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.chocolate_milk_tea,"59.000", "Chocolate Milk Tea", "Sweet milk tea with combine of chocolate bring the best drink"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.mango_milk_tea,"61.000", "Mango Milk Tea", "Green milk tea combine with mango syrup"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,milkTeaItemsListDataList,this));
    }

    @Override
    public void onItemClick(int position) {
        food_items_listData selectedMilkTea = milkTeaItemsListDataList.get(position);
        Intent intent = new Intent(milktea_listview.this, food_type_detail.class);
        intent.putExtra("food_name",selectedMilkTea.getName());
        intent.putExtra("food_price",selectedMilkTea.getPrice());
        intent.putExtra("food_description",selectedMilkTea.getDescription());
        intent.putExtra("food_image",selectedMilkTea.getImageResource());

        ArrayList<String> options = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

            //Title 1: Choose size
            titles.add("Size");
            options.add("Size M");
            prices.add("");
            options.add("Size L");
            prices.add("+8.000");

            //Title 2: Choose sugar
            titles.add("Choose sugar");
            options.add("Normal");
            prices.add("");
            options.add("Less sugar");
            prices.add("");

            //Title 3: Choose ice
            titles.add("Choose ice");
            options.add("50% ice");
            prices.add("");
            options.add("70% ice");

            //Title 4: Topping have
            titles.add("Topping have");
            options.add("White bubble");
            prices.add("");
            options.add("Black bubble");
            prices.add("");
            options.add("Don't add bubble");
            prices.add("");


        intent.putExtra("food_name",selectedMilkTea.getName());
        intent.putStringArrayListExtra("titles",titles);
        intent.putStringArrayListExtra("options", options);
        intent.putStringArrayListExtra("prices", prices);
        startActivity(intent);
    }
}