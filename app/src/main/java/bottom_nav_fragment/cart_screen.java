package bottom_nav_fragment;

import static android.app.PendingIntent.FLAG_MUTABLE;
import static android.content.Context.MODE_PRIVATE;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import asset.Map;
import model.Order;

import com.example.food_app_2.R;

import model.Request;

import com.example.food_app_2.food_order_view;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import asset.CartDBHelper;

public class cart_screen extends Fragment implements CartAdapter.OnQuantityChangedListener {
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    CartDBHelper cartDBHelper;
    MaterialButton placeOrderButton;

    TextView chooseLocation;

    MaterialButton clearOrderButton;

    TextView totalPriceTextView;


    Dialog dialog;

    MaterialButton btnDialogYes, btnDialogNo;

    private List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create dialog
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_enter_address);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[] {android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_screen, container, false);
        cartRecyclerView = view.findViewById(R.id.cart_detail_order_recyclerview);
        placeOrderButton = view.findViewById(R.id.place_order_button);
        clearOrderButton = view.findViewById(R.id.clear_order_button);
        totalPriceTextView = view.findViewById(R.id.total_price_textview);


        cartDBHelper = new CartDBHelper(getContext());
        orderList = loadCartItems();

        clearOrderButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });

        cartRecyclerView = view.findViewById(R.id.cart_detail_order_recyclerview);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(orderList, getContext(), this);
        cartRecyclerView.setAdapter(cartAdapter);


        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderList.isEmpty()) {
                    Toast.makeText(getActivity(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                btnDialogNo = dialog.findViewById(R.id.no_dialog_button);
                btnDialogYes = dialog.findViewById(R.id.yes_dialog_button);
                EditText enterAddress = dialog.findViewById(R.id.location_text_view);
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("locationPrefs", MODE_PRIVATE);
                String addressSave = sharedPreferences.getString("addressCart", "No location selected");
                enterAddress.setText(addressSave);
                dialog.show();


                @SuppressLint("DefaultLocale") String total = String.format("%.3f", totalPrice(orderList));

                btnDialogNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnDialogYes.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(View v) {
                        String addressToSave = enterAddress.getText().toString();
                        if (addressToSave.isEmpty()) {
                            Toast.makeText(getActivity(), "Please enter address", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser == null) {
                            Toast.makeText(getActivity(), "User not authenticated", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String userId = currentUser.getUid();
                        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("User").child(userId);
                        DatabaseReference requestReference = FirebaseDatabase.getInstance().getReference("Request");
                        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String phone = snapshot.child("phone").getValue(String.class);
                                String name = snapshot.child("userName").getValue(String.class);
                                Request request = new Request(phone, name, addressToSave, total, orderList);

                                requestReference.push().setValue(request).addOnCompleteListener(uploadTask -> {
                                    if (uploadTask.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Order successfully", Toast.LENGTH_SHORT).show();
                                        clearCart();
                                        dialog.dismiss();

                                        makeNotification();
                                    } else
                                        Toast.makeText(getActivity(), "Order fail", Toast.LENGTH_SHORT).show();
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }
        });

        updateTotalPrice();
        return view;
    }

    private List<Order> loadCartItems() {
        List<Order> itemList = new ArrayList<>();
        Cursor cursor = cartDBHelper.getCartData();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("ItemName"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("ItemPrice"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("ItemQuantity"));
                itemList.add(new Order(id, name, price, quantity));
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        return itemList;
    }


    private double totalPrice(List<Order> orderList) {
        double total = 0.0;
        for (Order order : orderList) {
            total += order.getPriceCart() * order.getQuantityCart();
        }
        return total;
    }

    private void updateTotalPrice() {
        double totalPriceCart = totalPrice(orderList);
        @SuppressLint("DefaultLocale") String totalText = "Total: " + String.format("%.3f", totalPriceCart);
        totalPriceTextView.setText(totalText);
    }

    @Override
    public void onQuantityChanged(List<Order> updatedOrderList) {
        this.orderList = updatedOrderList;
        updateTotalPrice();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearCart() {
        cartDBHelper.clearCart();
        orderList.clear();
        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();
        Toast.makeText(getActivity(), "Cart cleared", Toast.LENGTH_SHORT).show();
    }

    public void makeNotification() {
        String channelID = "CHANNEL_ID_NOTIFICATION";

        NotificationManager notificationManager =
                (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    channelID,
                    "Order Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            notificationChannel.setDescription("Channel for order notifications");
            notificationManager.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(requireActivity().getApplicationContext(), channelID);
            builder.setSmallIcon(R.drawable.baseline_notifications_active_24)
                    .setContentTitle("Order Placed")
                    .setContentText("Food order has been placed. Please check in your order")
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            Intent intent = new Intent(requireActivity().getApplicationContext(), food_order_view.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("data","Some value to pass here");

            PendingIntent pendingIntent = PendingIntent.getActivity(requireActivity().getApplicationContext(),
                    0, intent,PendingIntent.FLAG_UPDATE_CURRENT | FLAG_MUTABLE);
            builder.setContentIntent(pendingIntent);

            // Display the notification
            notificationManager.notify(1, builder.build());
        }
    }
}