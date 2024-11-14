package food_type;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.google.android.material.button.MaterialButton;

public class food_viewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView nameView, descriptionView, priceView;

    MaterialButton addButton;
    public food_viewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        imageView = itemView.findViewById(R.id.food_image);
        nameView = itemView.findViewById(R.id.food_name);
        descriptionView = itemView.findViewById(R.id.food_description);
        priceView = itemView.findViewById(R.id.food_price);
        addButton = itemView.findViewById(R.id.food_add_button);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewInterface != null){
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });
    }
}
