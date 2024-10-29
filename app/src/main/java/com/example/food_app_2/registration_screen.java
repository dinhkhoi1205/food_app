package com.example.food_app_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class registration_screen extends AppCompatActivity {

    EditText editText_userName, editText_email, editText_password,editText_confirmPass, editText_address1, editText_address2,
            editText_country, editText_city, editText_phoneNumber;
    CheckBox checkBox_agree;
    MaterialButton button_register,button_return;
    SharedPreferences sharedPreferences;
    //Create share preference for each edit text
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CONFIRMPASS = "confirmpassword";
    private static final String KEY_ADDRESS_1 = "address1";
    private static final String KEY_ADDRESS_2 = "address2";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_CITY = "city";
    private static final String KEY_PHONENUMBER = "phonenumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_screen);
        //Take id from each edit text, button, checkbox
        editText_userName = findViewById(R.id.username);
        editText_email = findViewById(R.id.email);
        editText_password= findViewById(R.id.password_toggle);
        editText_confirmPass = findViewById(R.id.confirm_password);
        editText_address1 = findViewById(R.id.adress1);
        editText_address2= findViewById(R.id.adress2);
        editText_country = findViewById(R.id.country);
        editText_city = findViewById(R.id.city);
        editText_phoneNumber= findViewById(R.id.phonenumber);
        checkBox_agree = findViewById(R.id.checkbox);
        button_register = findViewById(R.id.signupButton);
        button_return = findViewById(R.id.return_button);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        //If press return button
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration_screen.this,login_home_page.class);
                startActivity(intent);
            }
        });

        //If press sign up button
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_userName.getText().toString();
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();
                String confirmPass = editText_confirmPass.getText().toString();
                String address1 = editText_address1.getText().toString();
                String city = editText_city.getText().toString();
                String country = editText_country.getText().toString();
                String phonenumber = editText_phoneNumber.getText().toString();
                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || address1.isEmpty()
                || city.isEmpty() || country.isEmpty()|| phonenumber.isEmpty()){
                    Toast.makeText(registration_screen.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                } else
                if(!checkBox_agree.isChecked()){
                    Toast.makeText(registration_screen.this, "You must check agree to the terms", Toast.LENGTH_SHORT).show();
                    return;
                } else
                if(!password.equals(confirmPass)){
                    Toast.makeText(registration_screen.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                //When press a sign up button, it put data on share preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USERNAME,editText_userName.getText().toString());
                editor.putString(KEY_EMAIL,editText_email.getText().toString());
                editor.putString(KEY_PASSWORD,editText_password.getText().toString());
                editor.putString(KEY_CONFIRMPASS,editText_confirmPass.getText().toString());
                editor.putString(KEY_ADDRESS_1,editText_address1.getText().toString());
                editor.putString(KEY_ADDRESS_2,editText_address2.getText().toString());
                editor.putString(KEY_COUNTRY,editText_country.getText().toString());
                editor.putString(KEY_CITY,editText_city.getText().toString());
                editor.putString(KEY_PHONENUMBER,editText_phoneNumber.getText().toString());
                editor.apply();
                Intent intent = new Intent(registration_screen.this,login_home_page.class);
                startActivity(intent);

            }
        });


    }
}