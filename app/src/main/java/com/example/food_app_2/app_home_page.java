package com.example.food_app_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;

public class app_home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_home_page);
        FrameLayout pizza_frame_type = findViewById(R.id.food_frame_1);
        FrameLayout burger_frame_type = findViewById(R.id.food_frame_2);
        FrameLayout milkTea_frame_type = findViewById(R.id.food_frame_3);
        FrameLayout rice_frame_type = findViewById(R.id.food_frame_4);
        FrameLayout dessert_frame_type = findViewById(R.id.food_frame_5);

        pizza_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app_home_page.this, pizza_listview.class);
                startActivity(intent);
            }
        });

        burger_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app_home_page.this, burger_listview.class);
                startActivity(intent);
            }
        });

        milkTea_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app_home_page.this, milktea_listview.class);
                startActivity(intent);
            }
        });

        rice_frame_type.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app_home_page.this, rice_listview.class);
                startActivity(intent);
            }
        }));

        dessert_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(app_home_page.this, dessert_listview.class);
                startActivity(intent);
            }
        });
    }
}