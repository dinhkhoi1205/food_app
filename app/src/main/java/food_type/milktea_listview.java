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

public class milktea_listview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_milktea_listview);
        RecyclerView recyclerView = findViewById(R.id.milktea_recycler_view);
        List<food_items_listData> milkTeaItemsListDataList = new ArrayList<food_items_listData>();

        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.oreo_milk_tea,"34.000", "Oreo Machiato Milk Tea", "Black Boba Include"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.matcha_milk_tea,"59.000", "Matcha Milk Tea", "Sweet milk tea with the smell of matcha's powder"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.purple_yam_milk_tea,"59.000", "Purple Yam Milk Tea", "The fatty of purple yam's milk tea, strong smell of purple yam. It is one of the best seller"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.chocolate_milk_tea,"59.000", "Chocolate Milk Tea", "Sweet milk tea with combine of chocolate bring the best drink"));
        milkTeaItemsListDataList.add(new food_items_listData(R.drawable.mango_milk_tea,"61.000", "Mango Milk Tea", "Green milk tea combine with mango syrup"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,milkTeaItemsListDataList));
    }
}