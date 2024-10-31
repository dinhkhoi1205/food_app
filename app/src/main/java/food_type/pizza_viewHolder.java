package food_type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.google.android.engage.common.datamodel.Image;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class pizza_viewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView nameView, descriptionView, priceView;

    MaterialButton addButton;
    public pizza_viewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.pizza_image);
        nameView = itemView.findViewById(R.id.pizza_name);
        descriptionView = itemView.findViewById(R.id.pizza_description);
        priceView = itemView.findViewById(R.id.pizza_price);
        addButton = itemView.findViewById(R.id.pizza_add_button);
    }
}
