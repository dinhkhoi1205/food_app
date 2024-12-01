package restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;

import java.util.List;

import food_type.RecyclerViewInterface;

public class restaurant_Adapter extends RecyclerView.Adapter<restaurant_viewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<restaurant_items_listData> restaurantItemsListData;

    public restaurant_Adapter(Context context,
                              List<restaurant_items_listData> restaurantItemsListData,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.restaurantItemsListData = restaurantItemsListData;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public restaurant_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new restaurant_viewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_items_list_view,parent,false),recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull restaurant_viewHolder holder, int position) {
        restaurant_items_listData currentItem = restaurantItemsListData.get(position);
        holder.foodResImg.setImageResource(currentItem.getRestaurant_image());
        holder.resFoodPrice.setText(currentItem.getRestaurant_food_price());
        holder.resFoodName.setText(currentItem.getRestaurant_food_name());
    }


    @Override
    public int getItemCount() {
        return restaurantItemsListData.size();
    }
}
