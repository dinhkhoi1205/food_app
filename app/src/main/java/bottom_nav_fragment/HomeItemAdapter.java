package bottom_nav_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.example.food_app_2.RecyclerViewInterface;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeViewHolder> {
     List<HomeItem> homeItemList;

    private final RecyclerViewInterface recyclerViewInterFace;

    Context context;

    public void setFilteredList(List<HomeItem> filteredList){
        this.homeItemList = filteredList;
        notifyDataSetChanged();
    }

    public HomeItemAdapter(Context context,List<HomeItem> homeItemList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.homeItemList = homeItemList;
        this.recyclerViewInterFace = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item_search,parent,false),recyclerViewInterFace);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeItem currentItem = homeItemList.get(position);
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.nameTextView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return homeItemList.size();
    }
}
