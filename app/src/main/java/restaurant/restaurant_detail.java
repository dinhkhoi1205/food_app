package restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_app_2.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import assest.CartDBHelper;
import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.food_type_detail;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;

public class restaurant_detail extends AppCompatActivity {
    ImageView resFoodImage;
    TextView resFoodName;
    TextView resFoodPrice;
    TextView resFoodDescription;

    TextView resTextQuantity;

    ImageButton resReturnButton;

    MaterialButton resAddItemButton;

    int count = 0;
    double basePrice = 0.0;
    CartDBHelper cartDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_detail);

        resFoodImage = findViewById(R.id.restaurant_food_image_detail);
        resFoodName = findViewById(R.id.restaurant_food_detail_name);
        resFoodPrice = findViewById(R.id.restaurant_food_detail_price);
        resFoodDescription = findViewById(R.id.restaurant_food_description_detail);
        resReturnButton = findViewById(R.id.return_button_restaurant_detail);
        resTextQuantity = findViewById(R.id.text_quantity_restaurant_detail);
        resAddItemButton = findViewById(R.id.add_item_button_restaurant_detail);
        cartDBHelper = new CartDBHelper(this);

        Intent intent = getIntent();
        String resNameDetail = intent.getStringExtra("restaurant_food_name");
        String resPriceDetail = intent.getStringExtra("restaurant_food_price");
        String resDescriptionDetail = intent.getStringExtra("restaurant_food_description");
        String resFood = getIntent().getStringExtra("restaurant_food_type");
        int resImageDetail = intent.getIntExtra("restaurant_food_image", 0);

        resFoodImage.setImageResource(resImageDetail);
        resFoodName.setText(resNameDetail);
        resFoodPrice.setText(resPriceDetail);
        resFoodDescription.setText(resDescriptionDetail);

        if (resPriceDetail != null && !resPriceDetail.isEmpty()) {
            try {
                basePrice = Double.parseDouble(resPriceDetail);
            } catch (NumberFormatException e) {
                Log.e("food_type_detail", "Invalid base price format: " + resPriceDetail, e);
            }
        }

        updateButtonAdd();

        resReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent ;
                switch (Objects.requireNonNull(resFood)){
                    case "KFC":
                        intent = new Intent(restaurant_detail.this, kfc_restaurant_list_view.class);
                        break;
                    case "Popeyes":
                        intent = new Intent(restaurant_detail.this, popeyes_restaurant_list_view.class);
                        break;
                    case "StarBucks":
                        intent = new Intent(restaurant_detail.this, star_buck_list_view.class);
                        break;
                    default:
                        return;
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        resTextQuantity.setText(String.valueOf(count));
            resAddItemButton.setOnClickListener(v -> {
                if(count != 0) {
                    if(cartDBHelper.isItemInCart(resNameDetail)){
                        Toast.makeText(this, "You already have this item in cart", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean checkInsertCartData = cartDBHelper.cartInsert(resNameDetail, basePrice, count);
                        if (checkInsertCartData) {
                            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                    Toast.makeText(this, "You must set quantity in order to order", Toast.LENGTH_SHORT).show();
            });


    }

    public void increment_restaurant(View v){
        count++;
        resTextQuantity.setText(String.valueOf(count));
        updateButtonAdd();
    }


    public void decrement_restaurant(View v){
        if(count <= 0) count = 0;
        else count--;

        resTextQuantity.setText(String.valueOf(count));
        updateButtonAdd();
    }

    public void updateButtonAdd(){
        String priceText = resFoodPrice.getText().toString();
        if(priceText.isEmpty()){
            resAddItemButton.setText("Add to your cart");
        }

        double totalPrice = basePrice * count;

        String buttonAdd = "Add to your cart - " + String.format("%.2f",totalPrice);

        resAddItemButton.setText(buttonAdd);
    }
}