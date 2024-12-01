package bottom_nav_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.food_app_2.R;
import com.example.food_app_2.app_home_page;

import food_type.burger_listview;
import food_type.dessert_listview;
import food_type.milktea_listview;
import food_type.pizza_listview;
import food_type.rice_listview;
import restaurant.kfc_restaurant_list_view;
import restaurant.popeyes_restaurant_list_view;
import restaurant.star_buck_list_view;


public class home_screen extends Fragment {


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