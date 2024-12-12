package bottom_nav_fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app_2.R;
import com.google.android.material.button.MaterialButton;


public class CartViewHolder extends RecyclerView.ViewHolder {

    TextView foodNameCart;
    TextView foodPriceCart;
    TextView foodPriceSumOfCart;
    TextView foodQuantityCart;

    MaterialButton incrementButton;

    MaterialButton decrementButton;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        foodNameCart = itemView.findViewById(R.id.food_name_cart_list_view);
        foodPriceCart = itemView.findViewById(R.id.price_text_view_cart_list_view);
        foodPriceSumOfCart = itemView.findViewById(R.id.textView_price_sumOf_cart_list_view);
        foodQuantityCart = itemView.findViewById(R.id.textView_quantity_cart_list_view);
        incrementButton = itemView.findViewById(R.id.button_increment_cart);
        decrementButton = itemView.findViewById(R.id.button_decrement_cart);

    }
}
