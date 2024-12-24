package com.example.food_app_2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class food_order_view_holder extends RecyclerView.ViewHolder {
    TextView foodName, quantity, price, placed;
    public food_order_view_holder(@NonNull View itemView) {
        super(itemView);
        foodName = itemView.findViewById(R.id.title_order);
        quantity = itemView.findViewById(R.id.quantity_order);
        price = itemView.findViewById(R.id.price_order);
        placed = itemView.findViewById(R.id.place_order_text_view);
    }
}
