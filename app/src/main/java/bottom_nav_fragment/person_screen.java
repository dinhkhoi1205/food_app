package bottom_nav_fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.food_app_2.R;
import com.example.food_app_2.login_home_page;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class person_screen extends Fragment {
    DatabaseReference reference;
    FirebaseAuth auth;

    Dialog dialog;

    int SELECT_PICTURE = 200;
    ImageView imageProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requireActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Create dialog
        dialog = new Dialog(requireActivity());
        dialog.setContentView(R.layout.custom_log_out_dialog);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);


        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_person_screen, container, false);
        EditText username = view.findViewById(R.id.username_edittext_profile);
        EditText phone = view.findViewById(R.id.phone_number_edittext_profile);
        TextView email = view.findViewById(R.id.email_edittext_profile);
        MaterialButton save_btn = view.findViewById(R.id.save_button_profile);
        MaterialButton log_out_btn = view.findViewById(R.id.log_out_button_profile);
        MaterialButton changePassword = view.findViewById(R.id.change_password_profile);
        FrameLayout changeImage = view.findViewById(R.id.change_image_profile);
        imageProfile = view.findViewById(R.id.image_profile);
        loadImageFromInternalStorage();

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

            if(newUsername.isEmpty() || newPhone.isEmpty() || newEmail.isEmpty()){
                Toast.makeText(getActivity(), "Can not leave the filed empty", Toast.LENGTH_SHORT).show();
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                Toast.makeText(getActivity(), "Invalid email format", Toast.LENGTH_SHORT).show();
                return;
            }

            reference.child(userId).child("userName").setValue(newUsername);
            reference.child(userId).child("phone").setValue(newPhone);

        });


        //Change password
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentEmail = auth.getCurrentUser().getEmail();

                if (currentEmail == null || currentEmail.isEmpty()) {
                    Toast.makeText(getActivity(), "Unable to change email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Send a password reset email
                auth.sendPasswordResetEmail(currentEmail).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Password reset email sent to " + currentEmail, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to send reset email: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
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

        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                if(data != null && data.getData() != null){
                    Uri selectImageURI = data.getData();
                    try {
                        // Save the selected image to internal storage
                        Bitmap selectedImage = getBitmapFromUri(selectImageURI);
                        saveImageToInternalStorage(selectedImage);

                        // Display the saved image
                        Glide.with(requireActivity()).load(selectImageURI).transform(new CircleCrop()).into(imageProfile);

                        //Set a line around image
                        imageProfile.setBackgroundResource(R.drawable.rounded_image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                resultLauncher.launch(intent);
            }
        });

        return view;
    }
public void saveImageToInternalStorage(Bitmap bitmap){
    try {
        // Save the image as a JPEG file
        String fileName = "profile_image.jpg";
        FileOutputStream fos = requireActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    // Load the image from internal storage
    public void loadImageFromInternalStorage() {
        try {
            String fileName = "profile_image.jpg";
            // Open the saved image file
            FileInputStream fis = requireActivity().openFileInput(fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            fis.close();
            // Apply CircleCrop transformation when loading the image
            Glide.with(requireActivity())
                    .load(bitmap)
                    .transform(new CircleCrop())
                    .into(imageProfile);
            //Set a line around image
            imageProfile.setBackgroundResource(R.drawable.rounded_image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ContentResolver resolver = requireActivity().getContentResolver();
        return ImageDecoder.decodeBitmap(ImageDecoder.createSource(resolver, uri));
    }
}