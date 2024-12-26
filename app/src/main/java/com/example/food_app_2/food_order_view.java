package com.example.food_app_2;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Order_History;

public class food_order_view extends AppCompatActivity {
    RecyclerView recyclerView;
    food_order_Adapter foodOrderAdapter;
    List<Order_History> orderHistories;
    Button clearOrderButton;


    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_order_view);

        recyclerView = findViewById(R.id.recycler_orders);
        clearOrderButton = findViewById(R.id.clear_order_button);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        orderHistories = new ArrayList<>();
        foodOrderAdapter = new food_order_Adapter(orderHistories,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodOrderAdapter);

        reference = FirebaseDatabase.getInstance().getReference("Request");
        readData();
        clearOrderButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                orderHistories.clear();
                clearOrder();
                foodOrderAdapter.notifyDataSetChanged();
            }
        });
    }

    public void readData(){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot order: snapshot.getChildren()){
                    //Access foods array
                    DataSnapshot foodOrder = order.child("foods");
                    for (DataSnapshot foodItem: foodOrder.getChildren()){
                        String nameCart = foodItem.child("nameCart").getValue(String.class);
                        Integer priceCart = foodItem.child("priceCart").getValue(Integer.class);
                        Integer quantityCart = foodItem.child("quantityCart").getValue(Integer.class);

                        if (nameCart != null && priceCart != null && quantityCart != null){
                            orderHistories.add(new Order_History(nameCart,priceCart,quantityCart));
                        }
                    }
                }
                foodOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clearOrder(){
        reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(food_order_view.this, "Order cleared successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}