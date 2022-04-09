package com.example.myapplication23.Activitys.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication23.Activitys.Home_Activity;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.User_Info;
import com.example.myapplication23.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Activity extends AppCompatActivity {

    private static final String TAG = "Register_Activity";

    private TextInputLayout tfName, tfEmailId, tfPhoneNumber, tfPinCode;
    private TextInputEditText etName, etEmailId, etPhoneNumber, etPinCode;

    private CountryCodePicker ccp;

    private RadioGroup rgGander;

    private String name, emailId, phoneNumber, pinCode, gander;

    private boolean isGanderSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
        {
            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
            finish();
        }

        tfName = findViewById(R.id.tfName_Register);
        tfEmailId = findViewById(R.id.tfEmail_Register);
        tfPhoneNumber = findViewById(R.id.tfPhoneNumber_Register);
        tfPinCode = findViewById(R.id.tfPinCode_Register);

        etName = findViewById(R.id.etName_Register);
        etEmailId = findViewById(R.id.etEmail_Register);
        etPhoneNumber = findViewById(R.id.etPhoneNumber_Register);
        etPinCode = findViewById(R.id.etPinCode_Register);
        rgGander = findViewById(R.id.rgGander_Register);

        ccp = findViewById(R.id.ccp_Register);

        ccp.registerCarrierNumberEditText(etPhoneNumber);

        rgGander.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.radio_button_Male:

                        gander = "Male";
                        isGanderSelected = true;

                        break;

                    case R.id.radio_button_Ather:

                        gander = "Ather";
                        isGanderSelected = true;

                        break;

                    case R.id.radio_button_Female:

                        gander = "Female";
                        isGanderSelected = true;

                        break;

                }


            }
        });

    }

    public void btnRegistration(View view) {

        if (isViewsEmpty())
        {

            Random random = new Random();

            int id = random.nextInt(5);

            String uid = id+"473"+phoneNumber;

            User_Info user = new User_Info(uid, null, name, emailId, phoneNumber, pinCode, gander);

            Intent intent = new Intent(Register_Activity.this, ManageOtp_Activity.class);

            intent.putExtra(MyApp.USER, user);

            startActivity(intent);

            finish();


        }
    }

    private boolean isViewsEmpty() {

        boolean result = true;

        name = etName.getText().toString().trim();
        emailId = etEmailId.getText().toString().trim().trim();
        phoneNumber = ccp.getFullNumberWithPlus().toString().trim();
        pinCode = etPinCode.getText().toString().trim();

        if (name.isEmpty())
        {
            tfName.setError("Enter your name");
            result = false;

        }
        else if (emailId.isEmpty())
        {
            tfEmailId.setError("Enter your email id");
            result = false;

        }
        else if (!isEmailValid(emailId))
        {
            tfEmailId.setError("Enter your correct email id");
            result = false;

        }
        else if (phoneNumber.isEmpty())
        {
            tfPhoneNumber.setError("Enter your phone number");
            result = false;

        }
        else if (phoneNumber.length() != 13)
        {
            Log.d(TAG, "isViewsEmpty: Phone Number --> "+phoneNumber);
            Log.d(TAG, "isViewsEmpty: Phone Length --> "+phoneNumber.length());

            tfPhoneNumber.setError("Enter your correct phone number");
            result = false;

        }
        else if (pinCode.isEmpty())
        {
            tfPinCode.setError("Enter your pin code");
            result = false;

        }
        else if (pinCode.length() != 6)
        {
            tfPinCode.setError("Enter your correct pin code");
            result = false;

        }
        else if (!isGanderSelected)
        {
            Toast.makeText(this, "Select Gander", Toast.LENGTH_SHORT).show();
            result = false;

        }


        return result;
    }

    // Checking is email valid or not
    public static boolean isEmailValid(String email) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

}