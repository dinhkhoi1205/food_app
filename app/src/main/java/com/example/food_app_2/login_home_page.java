package com.example.food_app_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login_home_page extends AppCompatActivity {
    EditText editText_userName_fill;

    TextView textView_signUp;
    MaterialButton button_signIn;

    TextInputEditText textInput_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_home_page);
        editText_userName_fill = findViewById(R.id.username_edittext);
        textInput_password = findViewById(R.id.password_input);
        textView_signUp = findViewById(R.id.sign_up_label);
        button_signIn = findViewById(R.id.sign_in_button);

        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validUserName() | !validPassWord()){

                } else {
                    checkUser();
                }
            }
        });

        textView_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_home_page.this, registration_screen.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validUserName(){
        String val = editText_userName_fill.getText().toString();
        if (val.isEmpty()){
            editText_userName_fill.setError("Username must be fill");
            return false;
        }
        else
        {
            editText_userName_fill.setError(null);
            return true;
        }
    }

    public Boolean validPassWord(){
        String val = Objects.requireNonNull(textInput_password.getText()).toString();
        if (val.isEmpty()){
            textInput_password.setError("Password must be fill");
            return false;
        }
        else
        {
            textInput_password.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userName = editText_userName_fill.getText().toString().trim();
        String userPassword = Objects.requireNonNull(textInput_password.getText()).toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDataBase = reference.orderByChild("userName").equalTo(userName);

        checkUserDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    editText_userName_fill.setError(null);
                    String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);

                    if(Objects.equals(passwordFromDB, userPassword)){
                        editText_userName_fill.setError(null);
                        Intent intent = new Intent(login_home_page.this, app_home_page.class);
                        startActivity(intent);
                    } else{
                        textInput_password.setError("Invalid Password");
                        textInput_password.requestFocus();
                    }
                } else{
                    editText_userName_fill.setError("User does not exist");
                    editText_userName_fill.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}