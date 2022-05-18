package com.example.myapplication23.Activitys.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication23.Activitys.Home_Activity;
import com.example.myapplication23.Fragments.Settings_Fragments.Setting_Home_Fragment;
import com.example.myapplication23.R;

public class Setting_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.fcHome, new Setting_Home_Fragment())
        .commit();

    }

}