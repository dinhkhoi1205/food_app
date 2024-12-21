package bottom_nav_fragment;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.food_app_2.R;
import com.example.food_app_2.app_home_page;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

import asset.Map;
import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;
import restaurant.kfc_restaurant_list_view;
import restaurant.popeyes_restaurant_list_view;
import restaurant.star_buck_list_view;


public class home_screen extends Fragment {

    DatabaseReference reference;
    FirebaseAuth auth;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        FrameLayout pizza_frame_type = view.findViewById(R.id.food_frame_1);
        FrameLayout burger_frame_type = view.findViewById(R.id.food_frame_2);
        FrameLayout milkTea_frame_type = view.findViewById(R.id.food_frame_3);
        FrameLayout rice_frame_type = view.findViewById(R.id.food_frame_4);
        FrameLayout dessert_frame_type = view.findViewById(R.id.food_frame_5);
        TextView userName_textView = view.findViewById(R.id.username_text_view_home_screen);
        TextView address_textView = view.findViewById(R.id.address_text_view_home_screen);
        TextView deliver_to_textView = view.findViewById(R.id.deliver_to_text_view);


        ActivityResultLauncher<Intent> mapActivityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String address = data.getStringExtra("short_address");
                            address_textView.setText(address);
                        }
                    }
                });

        deliver_to_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Map.class);
                mapActivityResultLauncher.launch(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("User");

        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        reference.child(userId).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String username = snapshot.getValue(String.class);
                    userName_textView.setText(username);
                }
                else
                    userName_textView.setText("User");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SearchView searchView = view.findViewById(R.id.search_bar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeItemSearchScreen.class);
                startActivity(intent);
            }
        });

        CardView kfc_cardView = view.findViewById(R.id.kfc_card_view);
        CardView pop_cardView = view.findViewById(R.id.pop_card_view);
        CardView star_bucks_cardView = view.findViewById(R.id.star_buck_card_view);
        pizza_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), pizza_listview.class);
                startActivity(intent);
            }
        });

        burger_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), burger_listview.class);
                startActivity(intent);
            }
        });

        milkTea_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), milktea_listview.class);
                startActivity(intent);
            }
        });

        rice_frame_type.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), rice_listview.class);
                startActivity(intent);
            }
        }));

        dessert_frame_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), dessert_listview.class);
                startActivity(intent);
            }
        });

        kfc_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), kfc_restaurant_list_view.class);
                startActivity(intent);
            }
        });

        pop_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), popeyes_restaurant_list_view.class);
                startActivity(intent);
            }
        });

        star_bucks_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), star_buck_list_view.class);
                startActivity(intent);
            }
        });

        return view;
    }



}