package com.example.food_app_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food_app_2.databinding.ActivityAppHomePageBinding;

import bottom_nav_fragment.cart_screen;
import bottom_nav_fragment.home_screen;
import bottom_nav_fragment.person_screen;
import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;

public class app_home_page extends AppCompatActivity {
    ActivityAppHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAppHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new home_screen());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home_bottom) {
                replaceFragment(new home_screen());
            } else if (id == R.id.cart_bottom) {
                replaceFragment(new cart_screen());
            } else if (id == R.id.person_bottom) {
                replaceFragment(new person_screen());
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
}