package com.example.myapplication23.Activitys.Registration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication23.Activitys.Home_Activity;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetProfileImage_Activity extends AppCompatActivity {

    private static final String TAG = "SetProfileImage_Activit";

    private static final int image_code = 1;
    private Uri imageUri;
    private CircleImageView profile_image;
    private boolean isImageSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile_image);

        // TODO: 6/28/2022 This activity was Set profile image

        profile_image = findViewById(R.id.profile_image_set);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent, "image"), image_code);


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == image_code) {

            if (resultCode == RESULT_OK) {

                imageUri = data.getData();

                isImageSelected = true;

                profile_image.setImageURI(imageUri);

            }
            if (resultCode == RESULT_CANCELED) {

                isImageSelected = false;

                profile_image.setImageResource(R.drawable.profile_image);

            }

        }


    }

    public void btnSetImage(View view) {

        if (isImageSelected) {

            // Set the profile image ( Function call )
            setOnImageSelected(imageUri);

        } else {

            Log.d(TAG, "btnSetImage: Image not selected");

        }


    }

    // Set the profile image ( Function definition )
    private void setOnImageSelected(Uri imageUri) {


        StorageReference profileFolder = MyApp.myVPS.getReference();

        StorageReference user_profile_images = profileFolder.child("User Profile Images");

        StorageReference user_image_id = user_profile_images.child(UUID.randomUUID().toString());

        UploadTask uploadTask = user_image_id.putFile(imageUri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                user_image_id
                        .getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imUri = uri.toString();

                                // Save image uri on collection DB
                                MyApp.myCS
                                        .collection(MyApp.DB_COL_NAME_USER_INFO)
                                        .document(MyApp.myAuth.getUid().toString())
                                        .update("userImage", imUri)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful())
                                                {

                                                    Toast.makeText(SetProfileImage_Activity.this, "Profile image is uploaded", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(getApplicationContext(), Home_Activity.class));

                                                    finish();

                                                }
                                                else {

                                                    Toast.makeText(SetProfileImage_Activity.this, "Sum problem of uploaded profile image", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(getApplicationContext(), Home_Activity.class));

                                                    finish();


                                                }
                                            }
                                        });

                            }
                        });


            }
        });


    }

    // This function related next anyway button ( Function definition )
    public void btnNextAnyway(View view) {

        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
        finish();

    }
}