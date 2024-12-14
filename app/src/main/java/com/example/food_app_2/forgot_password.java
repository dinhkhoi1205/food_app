package com.example.food_app_2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class forgot_password extends AppCompatActivity {
    MaterialButton btnReset, btnBack;
    EditText edtEmail;
    ProgressBar progressBar;

    DatabaseReference reference;
    FirebaseAuth auth;

    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);

        btnBack = findViewById(R.id.back_button_reset_password);
        btnReset = findViewById(R.id.reset_password_button);
        edtEmail = findViewById(R.id.email_fill_rest_password);
        progressBar = findViewById(R.id.forget_password_progressbar);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("User");

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 strEmail = edtEmail.getText().toString().trim();
                if(!TextUtils.isEmpty(strEmail)){
                    ResetPassword();
                }
                else {
                    edtEmail.setError("Email filed can't be empty");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgot_password.this, login_home_page.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void ResetPassword() {
        progressBar.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.INVISIBLE);

        auth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(forgot_password.this, "Reset password link has been sent to your email", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(forgot_password.this, login_home_page.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(forgot_password.this, "Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
            }
        });
    }


}