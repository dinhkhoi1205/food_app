package com.example.food_app_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

import com.example.food_app_2.databinding.ActivityLoginHomePageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

    TextView forgotPassword;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_home_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        editText_userName_fill = findViewById(R.id.username_edittext);
        textInput_password = findViewById(R.id.password_input);
        textView_signUp = findViewById(R.id.sign_up_label);
        button_signIn = findViewById(R.id.sign_in_button);
        forgotPassword = findViewById(R.id.forgot_password_label);

        firebaseAuth = FirebaseAuth.getInstance();

        button_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkUser();
            }
        });

        textView_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_home_page.this, registration_screen.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(login_home_page.this, forgot_password.class);
                startActivity(intent);
            }
        });
    }
    

    public void checkUser(){
        String userEmail = editText_userName_fill.getText().toString().trim();
        String userPassword = Objects.requireNonNull(textInput_password.getText()).toString().trim();

        if(userEmail.isEmpty()){
            editText_userName_fill.setError("Email is require");
            editText_userName_fill.requestFocus();
            return;
        }

        if (userPassword.isEmpty()) {
            textInput_password.setError("Password is required");
            textInput_password.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login_home_page.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login_home_page.this, app_home_page.class));
                    finish();
                } else {
                    Toast.makeText(login_home_page.this, "Login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}