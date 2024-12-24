package com.example.food_app_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bottom_nav_fragment.CartViewHolder;
import model.Order_History;

public class food_order_Adapter extends RecyclerView.Adapter<food_order_view_holder> {
    private final List<Order_History> orderHistories;
    Context context;

    public food_order_Adapter(List<Order_History> orderHistories, Context context) {
        this.orderHistories = orderHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public food_order_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new food_order_view_holder(LayoutInflater.from(context).inflate(R.layout.order_view_history,parent,false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull food_order_view_holder holder, int position) {
        Order_History orderHistory = orderHistories.get(position);
        holder.foodName.setText(orderHistory.getFood_name());
        holder.quantity.setText("Quantity: " + orderHistory.getQuantity());
        holder.price.setText(String.format("%.3f",orderHistory.getPrice()));

    }

    @Override
    public int getItemCount() {
        return orderHistories.size();
    }
}
