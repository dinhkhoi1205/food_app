package bottom_nav_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.Order;
import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.List;

import assest.CartDBHelper;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    List<Order> orderListData;
    Context context;

    CartDBHelper cartDBHelper;
    private OnQuantityChangedListener onQuantityChangedListener;

    public interface OnQuantityChangedListener {
        void onQuantityChanged(List<Order> updatedOrderList);
    }
    public CartAdapter(List<Order> orderListData, Context context, OnQuantityChangedListener listener) {
        this.orderListData = orderListData;
        this.context = context;
        this.onQuantityChangedListener = listener;
        cartDBHelper = new CartDBHelper(context);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_list_view,parent,false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Order currentItem = orderListData.get(position);
        holder.foodNameCart.setText(currentItem.getNameCart());
        holder.foodPriceCart.setText(String.format("%.3f",currentItem.getPriceCart()));
        holder.foodQuantityCart.setText(String.valueOf(currentItem.getQuantityCart()));


        holder.foodPriceSumOfCart.setText(String.format("%.3f",currentItem.getPriceCart() * currentItem.getQuantityCart()));

        holder.incrementButton.setOnClickListener(v -> {
            int quantity = currentItem.getQuantityCart() + 1;
            currentItem.setQuantityCart(quantity);
            holder.foodQuantityCart.setText(String.format("%.3f",currentItem.getPriceCart()));
            holder.foodPriceSumOfCart.setText(String.format("%.3f",currentItem.getPriceCart() * quantity));
            notifyItemChanged(position);
            onQuantityChangedListener.onQuantityChanged(orderListData);
        });

        holder.decrementButton.setOnClickListener(v ->{
            int quantity = Math.max(0,currentItem.getQuantityCart() - 1);
            currentItem.setQuantityCart(quantity);
            holder.foodQuantityCart.setText(String.format("%.3f",currentItem.getPriceCart()));
            holder.foodPriceSumOfCart.setText(String.format("%.3f",currentItem.getPriceCart() * quantity));
            if(quantity == 0){
                cartDBHelper.cartDelete(currentItem.getNameCart());
                if(position >= 0 && position < orderListData.size()) {
                    orderListData.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position,orderListData.size());
                }
            }
            else{
                notifyItemChanged(position);
            }
            onQuantityChangedListener.onQuantityChanged(orderListData);
        });
    }

    @Override
    public int getItemCount() {
        return orderListData.size();
    }
}
