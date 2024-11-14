package food_type;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

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

    }

    @Override
    public int getItemCount() {
        return foodItemsListData.size();
    }
}

