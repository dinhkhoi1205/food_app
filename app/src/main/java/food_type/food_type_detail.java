package food_type;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_app_2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class food_type_detail extends AppCompatActivity {

    ImageView foodImage;
    TextView foodName;
    TextView foodPrice;
    TextView foodDescription;
    TextView foodOptionsTitle;
    RadioGroup foodOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_type_detail);

        foodImage = findViewById(R.id.food_image_detail);
        foodName = findViewById(R.id.food_detail_name);
        foodPrice = findViewById(R.id.food_detail_price);
        foodDescription = findViewById(R.id.food_description_detail);
        foodOptions = findViewById(R.id.food_detail_option);
        foodOptionsTitle = findViewById(R.id.foodOption_textView);

        Intent intent = getIntent();
        String nameDetail = intent.getStringExtra("food_name");
        String priceDetail = intent.getStringExtra("food_price");
        String descriptionDetail = intent.getStringExtra("food_description");
        int imageDetail = intent.getIntExtra("food_image", 0);

        foodName.setText(nameDetail);
        foodDescription.setText(descriptionDetail);
        foodPrice.setText(priceDetail);
        foodImage.setImageResource(imageDetail);

        ArrayList<String> options = getIntent().getStringArrayListExtra("options");
        ArrayList<String> prices = getIntent().getStringArrayListExtra("prices");
        ArrayList<String> titles = getIntent().getStringArrayListExtra("titles");

        if (options != null && prices != null && titles != null) {
            int titleCount = titles.size();
            int optionPerTitle = options.size() / titleCount;
            int optionIndex = 0;
            foodOptions.removeAllViews();
            for (int i = 0; i < titleCount; i++) {
                TextView textView = new TextView(this);
                textView.setText(titles.get(i));
                textView.setTextSize(20f);
                textView.setPadding(30,0,0,30);
                textView.setTextColor(Color.BLACK);
                textView.setTypeface(textView.getTypeface(),Typeface.BOLD);
                foodOptions.addView(textView);

                // Create a RadioGroup for options under each title
                RadioGroup radioGroup = new RadioGroup(this);
                radioGroup.setOrientation(LinearLayout.VERTICAL);
                foodOptions.addView(radioGroup);


                for(int j = 0; j < optionPerTitle;j++) {
                    //Create a linear layout to hold the radio button and price
                    LinearLayout linearLayout = new LinearLayout(this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    // Get margin for linear layout
                    LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    linearLayoutParams.setMargins(50, 50, 0, 0);
                    linearLayout.setLayoutParams(linearLayoutParams);

                    //Create radio buttons
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(options.get(optionIndex));
                    radioButton.setLayoutParams(new LinearLayout.LayoutParams(0,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1f
                    ));

                    //Create price text view right at the end of radio button
                    TextView priceView = new TextView(this);
                    priceView.setText(prices.get(optionIndex));
                    priceView.setTextSize(20f);
                    priceView.setTextColor(Color.BLACK);

                    //Get the current layout of price text view
                    LinearLayout.LayoutParams priceButtonParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    /*priceButtonParams.gravity = Gravity.END;*/
                    priceButtonParams.setMarginStart(150);
                    priceView.setLayoutParams(priceButtonParams);

                    linearLayout.addView(radioButton);
                    linearLayout.addView(priceView);
                    radioGroup.addView(linearLayout);

                    optionIndex++;
                    // Set radio button to checked and unchecked
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (radioButton.isSelected()) {
                                radioButton.setSelected(false);
                                radioButton.setChecked(false);
                            } else {
                                radioButton.setSelected(true);
                                radioButton.setChecked(true);
                            }
                        }
                    });
                }
            }
        }
    }
}