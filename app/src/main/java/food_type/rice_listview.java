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

public class rice_listview extends AppCompatActivity implements RecyclerViewInterface {
    List<food_items_listData> riceItemsListDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rice_listview);
        RecyclerView recyclerView = findViewById(R.id.rice_recycler_view);
         riceItemsListDataList = new ArrayList<>();

        riceItemsListDataList.add(new food_items_listData(R.drawable.broken_rice_rib,"39.000", "Broken rice with rib", "Flavorful ribs, tender broken rice"));
        riceItemsListDataList.add(new food_items_listData(R.drawable.broken_rice_rib_egg,"49.000", "Broken rice with rib, egg", "Fragrant rice ribs with egg"));
        riceItemsListDataList.add(new food_items_listData(R.drawable.broken_rice_rib_sausage,"59.000", "Broken rice with rib,sausage", "Delicious grilled pork with savory sausage"));
        riceItemsListDataList.add(new food_items_listData(R.drawable.broken_rice_chicken,"38.000", "Broken rice with chicken", "Richly flavored chicken"));
        riceItemsListDataList.add(new food_items_listData(R.drawable.broken_rice_catfish,"50.000", "Rice with braised catfish", "Braised ginger fish"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new food_Adapter(this,riceItemsListDataList,this));
    }

    @Override
    public void onItemClick(int position) {
        food_items_listData selectedRice = riceItemsListDataList.get(position);
        Intent intent = new Intent(rice_listview.this, food_type_detail.class);
        intent.putExtra("food_name",selectedRice.getName());
        intent.putExtra("food_price",selectedRice.getPrice());
        intent.putExtra("food_description",selectedRice.getDescription());
        intent.putExtra("food_image",selectedRice.getImageResource());
        intent.putExtra("food_type", "rice");
        intent.putExtra("food_name",selectedRice.getName());
        startActivity(intent);
    }
}