package com.example.food_app_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.food_app_2.databinding.ActivityRegistrationScreenBinding;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class registration_screen extends AppCompatActivity {
    ActivityRegistrationScreenBinding binding;
    FirebaseDatabase db;
    DatabaseReference reference;

    FirebaseAuth firebaseAuth;

    EditText editText_userName, editText_email, editText_password,editText_confirmPass, editText_phone;
    CheckBox checkBox_agree;
    MaterialButton button_register,button_return;

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
        checkBox_agree = findViewById(R.id.checkbox);
        button_register = findViewById(R.id.signupButton);
        button_return = findViewById(R.id.return_button);
        editText_phone = findViewById(R.id.phone);

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
                firebaseAuth = FirebaseAuth.getInstance();
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("User");
                String username = editText_userName.getText().toString();
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();
                String confirmPass = editText_confirmPass.getText().toString();
                String phone = editText_phone.getText().toString();

                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || phone.isEmpty()){
                    Toast.makeText(registration_screen.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!checkBox_agree.isChecked()){
                    Toast.makeText(registration_screen.this, "You must check agree to the terms", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(confirmPass)){
                    Toast.makeText(registration_screen.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(registration_screen.this, "Email is Valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                 if (password.length() < 8) {
                    Toast.makeText(registration_screen.this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                 if (!phone.matches("\\d+")) {
                    Toast.makeText(registration_screen.this, "Phone contains only digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.length() < 10) {
                    Toast.makeText(registration_screen.this, "Phone number must be at 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            String userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            User user = new User(username,password,email,phone);

                            reference.child(userId).setValue(user).addOnCompleteListener(dbTask ->{
                                if(dbTask.isSuccessful()){
                                    Toast.makeText(registration_screen.this, "You have signed successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registration_screen.this,login_home_page.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                    });



            }
        });


    }
}