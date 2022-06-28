package com.example.myapplication23.Activitys.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.User_Info;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ManageOtp_Activity extends AppCompatActivity {

    private static final String TAG = "ManageOtp_Activity";

    private TextInputLayout tfOtp;
    private TextInputEditText etOtp;
    private String phoneNumber, otpId;

    private User_Info user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_otp);

        // TODO: 6/28/2022 This activity was manage otp


        user = (User_Info) getIntent().getSerializableExtra(MyApp.USER);

        phoneNumber = user.getUserPhoneNumber();

        tfOtp = findViewById(R.id.tfOtp_Otp);
        etOtp = findViewById(R.id.etOtp_Otp);

        // This function related from initialize otp ( Function call )
        initializeOtp();

        Log.d(TAG, "onCreate: Name "+user.getUserName());


    }

    // This function related from initialize otp ( Function definition )
    private void initializeOtp() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(MyApp.myAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                otpId = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(ManageOtp_Activity.this, "Otp Exception : "+e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }

    // This function related from sign in button ( Function definition )
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        MyApp.myAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user1 = task.getResult().getUser();

                            Log.d(MyApp.myTag, "onComplete: User Id --> "+MyApp.myAuth.getUid());


                            // This function related from user register record ( Function definition )
                            registerRecord(task.getResult().getUser().getUid());


                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(ManageOtp_Activity.this, "Otp is involved", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });

    }

    // This function related from user register record ( Function call )
    private void registerRecord(String dbId) {

        user.setUserID(dbId);

        MyApp.myCS
                .collection(MyApp.DB_COL_NAME_USER_INFO)
                .document(dbId)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {

                            Intent intent = new Intent(getApplicationContext(), SetProfileImage_Activity.class);

                            startActivity(intent);

                            finish();

                        }
                        else {

                            Toast.makeText(ManageOtp_Activity.this, "Store Exception : "+task.getException(), Toast.LENGTH_SHORT).show();


                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });



    }

    // This function related from otp button ( Function definition )
    public void btnSetOtp(View view) {

        String otp = etOtp.getText().toString().trim();

        if (otp.isEmpty())
        {
            tfOtp.setError("Enter the otp");
            return;
        }
        else if (otp.length() != 6)
        {
            tfOtp.setError("Enter the correct length of otp");
            return;
        }
        else {

            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpId,otp);

            // This function related from sign in button ( Function call)
            signInWithPhoneAuthCredential(credential);





        }
    }

}