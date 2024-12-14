package bottom_nav_fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_app_2.R;
import com.example.food_app_2.login_home_page;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class person_screen extends Fragment {
    DatabaseReference reference;
    FirebaseAuth auth;

    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Create dialog
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_log_out_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);


        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_person_screen, container, false);
        EditText username = view.findViewById(R.id.username_edittext_profile);
        EditText phone = view.findViewById(R.id.phone_number_edittext_profile);
        EditText email = view.findViewById(R.id.email_edittext_profile);
        MaterialButton save_btn = view.findViewById(R.id.save_button_profile);
        MaterialButton log_out_btn = view.findViewById(R.id.log_out_button_profile);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("User");

        String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String username_editText = snapshot.child("userName").getValue(String.class);
                    String phone_editText = snapshot.child("phone").getValue(String.class);
                    String email_editText = snapshot.child("email").getValue(String.class);
                    if (username_editText != null) {
                        username.setText(username_editText);
                    }
                    if (phone_editText != null) {
                        phone.setText(phone_editText);
                    }
                    if (email_editText != null) {
                        email.setText(email_editText);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Save button
        save_btn.setOnClickListener(v -> {
            String newUsername = username.getText().toString();
            String newPhone = phone.getText().toString();
            String newEmail = email.getText().toString();

            // Save updated data to Firebase
            if(newUsername.isEmpty() || newPhone.isEmpty() || newEmail.isEmpty()){
                Toast.makeText(getActivity(), "Can not leave the filed empty", Toast.LENGTH_SHORT).show();
            }
            else {
                reference.child(userId).child("userName").setValue(newUsername);
                reference.child(userId).child("phone").setValue(newPhone);
                reference.child(userId).child("email").setValue(newEmail);
                Toast.makeText(getActivity(), "Updated profile", Toast.LENGTH_SHORT).show();
            }
        });



        //Log out button
        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialButton btnDialogNo, btnDialogYes;
                btnDialogNo = dialog.findViewById(R.id.no_dialog_button_profile);
                btnDialogYes = dialog.findViewById(R.id.yes_dialog_button_profile);
                dialog.show();

                btnDialogNo.setOnClickListener(view -> dialog.dismiss());

                btnDialogYes.setOnClickListener(view ->{
                    auth.signOut();
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), login_home_page.class));
                });
            }
        });
        return view;
    }
}