package restaurant;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.google.android.material.button.MaterialButton;

import com.example.food_app_2.RecyclerViewInterface;

public class restaurant_viewHolder extends RecyclerView.ViewHolder {
    ImageView foodResImg;
    MaterialButton resAddBtn;
    TextView resFoodName;
    TextView resFoodPrice;
    public restaurant_viewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        foodResImg = itemView.findViewById(R.id.food_restaurant_image);
        resAddBtn = itemView.findViewById(R.id.button_add_restaurant);
        resFoodName = itemView.findViewById(R.id.food_name_restaurant_list_view);
        resFoodPrice = itemView.findViewById(R.id.food_price_restaurant_list_view);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
