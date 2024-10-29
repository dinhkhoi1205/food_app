package com.example.food_app_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class login_home_page extends AppCompatActivity {
    EditText editText_userName_fill;

    TextView textView_signUp;
    MaterialButton button_signIn;

    TextInputEditText textInput_password;

    SharedPreferences sharedPreferences;
    //Create share preference for each edit text
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_home_page);
        editText_userName_fill = findViewById(R.id.username_edittext);
        textInput_password = findViewById(R.id.password_input);
        textView_signUp = findViewById(R.id.sign_up_label);
        button_signIn = findViewById(R.id.sign_in_button);

        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);


        //Press sign in button will move to app home page
        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterUserName = editText_userName_fill.getText().toString();
                String enterPassword = Objects.requireNonNull(textInput_password.getText()).toString();

                if (enterUserName.isEmpty()|| enterPassword.isEmpty()) {
                    Toast.makeText(login_home_page.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }

                String saveUsername = sharedPreferences.getString(KEY_USERNAME,null);
                String savePassword = sharedPreferences.getString(KEY_PASSWORD,null);
                if(enterPassword.equals(savePassword)&& enterUserName.equals(saveUsername)){
                    Intent intent = new Intent(login_home_page.this,app_home_page.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(login_home_page.this, "Invalid password or username", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Press sign up text view will move to registration page
        textView_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_home_page.this,registration_screen.class);
                startActivity(intent);

            }
        });
    }
}