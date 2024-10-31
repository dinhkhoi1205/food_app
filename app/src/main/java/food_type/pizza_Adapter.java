package food_type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.google.android.engage.common.datamodel.Image;

import java.util.ArrayList;
import java.util.List;

public class pizza_Adapter extends RecyclerView.Adapter<pizza_viewHolder>{
    Context context;
    List<pizza_items_listData> pizzaItemsListData;

    public pizza_Adapter(Context context, List<pizza_items_listData> pizzaItemsListData) {
        this.context = context;
        this.pizzaItemsListData = pizzaItemsListData;
    }

    @NonNull
    @Override
    public pizza_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new pizza_viewHolder(LayoutInflater.from(context).inflate(R.layout.pizza_items_listview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull pizza_viewHolder holder, int position) {
        pizza_items_listData currentItem = pizzaItemsListData.get(position);
        holder.nameView.setText(currentItem.getName());
        holder.descriptionView.setText(currentItem.getDescription());
        holder.priceView.setText(currentItem.getPrice());
        holder.imageView.setImageResource(currentItem.getImageResource());
    }

    @Override
    public int getItemCount() {
        return pizzaItemsListData.size();
    }
}

