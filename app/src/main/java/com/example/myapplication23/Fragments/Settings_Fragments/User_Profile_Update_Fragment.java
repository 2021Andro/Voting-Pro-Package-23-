package com.example.myapplication23.Fragments.Settings_Fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication23.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Profile_Update_Fragment extends Fragment {

    private View myView;
    private CircleImageView ivProfile;
    private int imageCode = 1;
    private Uri imageUri;
    boolean isImageSelected = false;

    public User_Profile_Update_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_user__profile__update_, container, false);

        ivProfile = myView.findViewById(R.id.profile_image_Update);


        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // The system invokes this method to make the fragment visible on the user’s device.

        // image selected code
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent, "image"), imageCode);

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == imageCode) {

            if (resultCode == RESULT_OK) {

                imageUri = data.getData();

                isImageSelected = true;

                ivProfile.setImageURI(imageUri);

            }
            if (resultCode == RESULT_CANCELED) {

                isImageSelected = false;

                ivProfile.setImageResource(R.drawable.profile_image);

            }

        }


    }
}