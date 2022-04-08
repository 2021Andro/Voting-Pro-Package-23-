package com.example.myapplication23.Activitys.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.User_Info;
import com.example.myapplication23.R;
import com.example.myapplication23.Setting_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Activity extends AppCompatActivity {


    private TextView tvName, tvEmail;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        setNavBar();
    }

    private void setNavBar() {


        NavigationView nav = findViewById(R.id.navBar);;
        ActionBarDrawerToggle toggle;
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);

        View myView = nav.getHeaderView(0);

        tvName = myView.findViewById(R.id.tvName_Home);
        tvEmail = myView.findViewById(R.id.tvEmail_Home);
        profileImage = myView.findViewById(R.id.profile_image_Home);


        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_hader, R.string.close_hader);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.miSetting:

                        Toast.makeText(Home_Activity.this, "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                }

                return true;
            }
        });


        MyApp
                .myCS
                .collection(MyApp.DB_COL_NAME_USER_INFO)
                .document(MyApp.myAuth.getUid().toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        User_Info userInfo = documentSnapshot.toObject(User_Info.class);

                        tvName.setText(userInfo.getUserName());
                        tvEmail.setText(userInfo.getUserEmailID());

                        if (userInfo.getUserImage() != null)
                        {

                            Glide
                                    .with(getApplicationContext())
                                    .asBitmap()
                                    .load(userInfo.getUserImage())
                                    .into(profileImage);

                        }
                        else {

                            profileImage.setImageResource(R.drawable.profile_image);

                        }

                    }
                });

    }
}