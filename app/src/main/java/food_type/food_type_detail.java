package food_type;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_app_2.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import asset.CartDBHelper;

public class food_type_detail extends AppCompatActivity {

    ImageView foodImage;
    TextView foodName;
    TextView foodPrice;
    TextView foodDescription;

    TextView textQuantity;

    ImageButton returnButton;

    MaterialButton addItemButton;


    int count = 0;
    double basePrice = 0.0;
    CartDBHelper cartDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_type_detail);
        cartDBHelper = new CartDBHelper(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        foodImage = findViewById(R.id.food_image_detail);
        foodName = findViewById(R.id.food_detail_name);
        foodPrice = findViewById(R.id.food_detail_price);
        foodDescription = findViewById(R.id.food_description_detail);
        returnButton = findViewById(R.id.return_button);
        textQuantity = findViewById(R.id.text_quantity);
        addItemButton = findViewById(R.id.add_item_button);

        Intent intent = getIntent();
        String nameDetail = intent.getStringExtra("food_name");
        String priceDetail = intent.getStringExtra("food_price");
        String descriptionDetail = intent.getStringExtra("food_description");
        String foodType = getIntent().getStringExtra("food_type");
        int imageDetail = intent.getIntExtra("food_image", 0);


        foodName.setText(nameDetail);
        foodDescription.setText(descriptionDetail);
        foodPrice.setText(priceDetail);
        foodImage.setImageResource(imageDetail);

        if (priceDetail != null && !priceDetail.isEmpty()) {
            try {
                basePrice = Double.parseDouble(priceDetail);
            } catch (NumberFormatException e) {
                Log.e("food_type_detail", "Invalid base price format: " + priceDetail, e);
            }
        }

        updateButtonAdd();


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent ;
                switch (Objects.requireNonNull(foodType)){
                    case "desserts":
                        intent = new Intent(food_type_detail.this,dessert_listview.class);
                        break;
                    case "burgers":
                        intent = new Intent(food_type_detail.this,burger_listview.class);
                        break;
                    case "pizza":
                        intent = new Intent(food_type_detail.this,pizza_listview.class);
                        break;
                    case "rice":
                        intent = new Intent(food_type_detail.this,rice_listview.class);
                        break;
                    case "milk tea":
                        intent = new Intent(food_type_detail.this,milktea_listview.class);
                        break;
                    default:
                        return;
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        textQuantity.setText(String.valueOf(count));
            addItemButton.setOnClickListener(v -> {
                if (count != 0) {
                    if(cartDBHelper.isItemInCart(nameDetail)){
                        Toast.makeText(this, "You already have this item in cart", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Save data to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("foodImageCart", imageDetail);
                        editor.apply();
                        //Save data in sql
                        boolean checkInsertCartData = cartDBHelper.cartInsert(nameDetail, basePrice, count);
                        if (checkInsertCartData) {
                            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                    Toast.makeText(this, "You must set quantity in order to order ", Toast.LENGTH_SHORT).show();
            });


    }


    public void increment(View v){
        count++;
        textQuantity.setText(String.valueOf(count));
        updateButtonAdd();
    }


    public void decrement(View v){
        if(count <= 0) count = 0;
        else count--;

        textQuantity.setText(String.valueOf(count));
        updateButtonAdd();
    }

    public void updateButtonAdd(){
        String priceText = foodPrice.getText().toString();

        if(priceText.isEmpty()){
            addItemButton.setText("Add to your cart");
        }

        double totalPrice = basePrice * count;

        String buttonAdd = "Add to your cart - " + String.format("%.2f",totalPrice);

        addItemButton.setText(buttonAdd);
    }
}