package com.example.myapplication23.Fragments.Settings_Fragments;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.myapplication23.Activitys.Registration.Register_Activity.isEmailValid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication23.Activitys.Home_Activity;
import com.example.myapplication23.Activitys.Registration.SetProfileImage_Activity;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.User_Info;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Profile_Update_Fragment extends Fragment {

    private static final String TAG = "upuf";
    private View myView;
    private CircleImageView ivProfile;
    private int imageCode = 1;
    private Uri imageUri;
    boolean isImageSelected = true;

    private TextInputLayout tfName, tfEmailId, tfPinCode;
    private TextInputEditText etName, etEmailId, etPinCode;

    private String name, emailId, pinCode, gander;

    private RadioGroup rgGander;
    private RadioButton rbMale, rbOther, rbFimale;

    //private boolean isGanderSelected = false;
    private Button btnUpdateProfile;
    private User_Info userInfoOld;

    public User_Profile_Update_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        // TODO: 6/28/2022 This fragment containing user profile updated

        myView = inflater.inflate(R.layout.fragment_user__profile__update_, container, false);


        // This function was initialize views ( Function call )
        initializeViews(myView);

        MyApp.myCS
                .collection("User_Info")
                .document(MyApp.myAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        userInfoOld = documentSnapshot.toObject(User_Info.class);

                        if (userInfoOld.getUserImage() == null) {

                            // the user cannot set profile pictures than return null

                            ivProfile.setImageResource(R.drawable.profile_image);

                            isImageSelected = false;

                        } else {

                            // user set the profile pictures on registration than return image uri

                            isImageSelected = true;

                            imageUri = Uri.parse(userInfoOld.getUserImage());

                            Glide
                                    .with(getContext())
                                    .asBitmap()
                                    .load(userInfoOld.getUserImage())
                                    .into(ivProfile);

                        }

                        etName.setText(userInfoOld.getUserName());
                        etEmailId.setText(userInfoOld.getUserEmailID());
                        etPinCode.setText(userInfoOld.getUserPinCode());

                        gander = userInfoOld.getUserGander();

                        if ("Male".equals(gander)) {

                            rbMale.setChecked(true);

                        } else if ("Other".equals(gander)) {

                            rbOther.setChecked(true);

                        } else if ("Female".equals(gander)) {

                            rbFimale.setChecked(true);

                        }

                    }
                });

        return myView;
    }

    // This function was initialize views ( Function definition )
    private void initializeViews(View myView) {

        ivProfile = myView.findViewById(R.id.profile_image_Update);
        tfName = myView.findViewById(R.id.tfName_Update);
        etName = myView.findViewById(R.id.etName_Update);

        tfEmailId = myView.findViewById(R.id.tfEmail_Update);
        etEmailId = myView.findViewById(R.id.etEmail_Update);

        tfPinCode = myView.findViewById(R.id.tfPinCode_Update);
        etPinCode = myView.findViewById(R.id.etPinCode_Update);

        rgGander = myView.findViewById(R.id.rgGander_Update);

        rbMale = myView.findViewById(R.id.radio_button_Male_Update);
        rbOther = myView.findViewById(R.id.radio_button_Other_Update);
        rbFimale = myView.findViewById(R.id.radio_button_Female_Update);

        btnUpdateProfile = myView.findViewById(R.id.btnUpdateProfile);


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

        // This is checking user gander
        rgGander.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radio_button_Male:

                        gander = "Male";

                        break;

                    case R.id.radio_button_Other:

                        gander = "Other";

                        break;

                    case R.id.radio_button_Female:

                        gander = "Female";

                        break;

                }


            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isViewsEmpty()) {

                    Random random = new Random();

                    int id = random.nextInt(5);

                    // String uid = id+""+phoneNumber;

                    userInfoOld.setUserName(name);
                    userInfoOld.setUserEmailID(emailId);
                    userInfoOld.setUserPinCode(pinCode);

                    Log.d(TAG, "Name: " + name);
                    Log.d(TAG, "Email: " + emailId);
                    Log.d(TAG, "Pincode: " + pinCode);
                    Log.d(TAG, "Gander: " + gander);

                    setOnUpdateProfile(userInfoOld);


                }

            }
        });


    }

    private void setOnUpdateProfile(User_Info userInfo) {

        RadioButton chooseGander = myView.findViewById(rgGander.getCheckedRadioButtonId());

        DocumentReference userInfoRef = MyApp.myCS.collection("User_Info").document(MyApp.myAuth.getUid().toString());



        if (!isImageSelected){


            if (imageUri != null)
            {

                // This is code run user choose the image in a profile


                StorageReference profileFolder = MyApp.myVPS.getReference();

                StorageReference user_profile_images = profileFolder.child("User Profile Images");

                StorageReference user_image_id = user_profile_images.child(UUID.randomUUID().toString());

                UploadTask uploadTask = user_image_id.putFile(imageUri);

                uploadTask
                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {


                                user_image_id
                                        .getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                String imageUri = uri.toString();


                                                Map<String, Object> updateUserInfo = new HashMap<>();

                                                updateUserInfo.put("userName", userInfo.getUserName());
                                                updateUserInfo.put("userEmailID", userInfo.getUserEmailID());
                                                updateUserInfo.put("userPinCode", userInfo.getUserPinCode());
                                                updateUserInfo.put("userGander", chooseGander.getText());
                                                updateUserInfo.put("userImage", imageUri);

                                                userInfoRef.update(updateUserInfo);

                                                startActivity(new Intent(getContext(), Home_Activity.class));
                                                getActivity().finish();

                                            }
                                        });

                            }
                        });

            } else {

                // This is code run user not choose the image in a profile
                Toast.makeText(getContext(), "Image not selected "+isImageSelected, Toast.LENGTH_SHORT).show();

                Map<String, Object> updateUserInfo = new HashMap<>();

                updateUserInfo.put("userName", userInfo.getUserName());
                updateUserInfo.put("userEmailID", userInfo.getUserEmailID());
                updateUserInfo.put("userPinCode", userInfo.getUserPinCode());
                updateUserInfo.put("userGander", chooseGander.getText());

                userInfoRef.update(updateUserInfo);

                startActivity(new Intent(getContext(), Home_Activity.class));
                getActivity().finish();


            }



        } else {


            // This is code run user not choose the image in a profile
            Toast.makeText(getContext(), "Image not selected "+isImageSelected, Toast.LENGTH_SHORT).show();

            Map<String, Object> updateUserInfo = new HashMap<>();

            updateUserInfo.put("userName", userInfo.getUserName());
            updateUserInfo.put("userEmailID", userInfo.getUserEmailID());
            updateUserInfo.put("userPinCode", userInfo.getUserPinCode());
            updateUserInfo.put("userGander", chooseGander.getText());

            userInfoRef.update(updateUserInfo);

            startActivity(new Intent(getContext(), Home_Activity.class));
            getActivity().finish();

        }






    }

    // This function checking views are empty or not ( Function definition )
    private boolean isViewsEmpty() {

        boolean result = true;

        name = etName.getText().toString().trim();
        emailId = etEmailId.getText().toString().trim().trim();
        pinCode = etPinCode.getText().toString().trim();

        if (name.isEmpty()) {
            tfName.setError("Enter your name");
            result = false;

        } else if (emailId.isEmpty()) {
            tfEmailId.setError("Enter your email id");
            result = false;

        } else if (!isEmailValid(emailId)) {
            tfEmailId.setError("Enter your correct email id");
            result = false;

        } else if (pinCode.isEmpty()) {
            tfPinCode.setError("Enter your pin code");
            result = false;

        } else if (pinCode.length() != 6) {
            tfPinCode.setError("Enter your correct pin code");
            result = false;

        }


        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == imageCode) {

            if (resultCode == RESULT_OK) {

                imageUri = data.getData();

                isImageSelected = false;

                ivProfile.setImageURI(imageUri);

            }
            if (resultCode == RESULT_CANCELED) {

                isImageSelected = true;

                ivProfile.setImageResource(R.drawable.profile_image);


            }

        }


    }


}