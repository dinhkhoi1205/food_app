package food_type;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.example.food_app_2.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class food_Adapter extends RecyclerView.Adapter<food_viewHolder>{
    private final RecyclerViewInterface recyclerViewInterFace;
    Context context;
    List<food_items_listData> foodItemsListData;

    public food_Adapter(Context context, List<food_items_listData> foodItemsListData,
                        RecyclerViewInterface recyclerViewInterFace ) {
        this.context = context;
        this.foodItemsListData = foodItemsListData;
        this.recyclerViewInterFace = recyclerViewInterFace;
    }

    @NonNull
    @Override
    public food_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new food_viewHolder(LayoutInflater.from(context).inflate(R.layout.food_items_listview,parent,false), recyclerViewInterFace);
    }

    @Override
    public void onBindViewHolder(@NonNull food_viewHolder holder, int position) {
        food_items_listData currentItem = foodItemsListData.get(position);
        holder.nameView.setText(currentItem.getName());
        holder.descriptionView.setText(currentItem.getDescription());
        holder.priceView.setText(currentItem.getPrice());
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.addButton.setOnClickListener(v -> {
            navigateToFoodDetail(currentItem);
        });
    }

    @Override
    public int getItemCount() {
        return foodItemsListData.size();
    }

    private void navigateToFoodDetail(food_items_listData item) {
        Intent intent = new Intent(context, food_type_detail.class);
        intent.putExtra("food_name", item.getName());
        intent.putExtra("food_price", item.getPrice());
        intent.putExtra("food_description", item.getDescription());
        intent.putExtra("food_image", item.getImageResource());
        context.startActivity(intent);
    }
}

