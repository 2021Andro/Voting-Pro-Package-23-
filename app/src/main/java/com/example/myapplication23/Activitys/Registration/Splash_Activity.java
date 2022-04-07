package com.example.myapplication23.Activitys.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication23.R;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                try {

                    Thread.sleep(3*1000);

                    startActivity(new Intent(Splash_Activity.this, Register_Activity.class));
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}