package com.example.myapplication23.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.Activitys.Settings.Setting_Activity;
import com.example.myapplication23.CostumeAdapters.MyCatRecAdapter;
import com.example.myapplication23.CostumeClasses.Category;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.User_Info;
import com.example.myapplication23.Interfaces.MyRecEvent;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Activity extends AppCompatActivity implements MyRecEvent {

    private RecyclerView rvList;
    private ArrayList<Category> categoryList;
    private RecyclerView.LayoutManager layoutManager;
    private MyCatRecAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // TODO: 08-04-2022 This function set navigation bar  ( Function call )
        setNavBar();

        rvList = findViewById(R.id.rvList_Home);

        categoryList = new ArrayList<>();

        myAdapter = new MyCatRecAdapter(this);

        layoutManager = new GridLayoutManager(this, 2);

        rvList.setLayoutManager(layoutManager);

        rvList.setAdapter(myAdapter);

        Category educationCat = new Category(0, "Education", MyApp.CATEGORY_IMAGES_EDUCATION);
        Category entertainmentCat = new Category(1, "Entertainment", MyApp.CATEGORY_IMAGES_ENTERTAINMENT);
        Category politicalCat = new Category(2, "Political", MyApp.CATEGORY_IMAGES_POLITICAL);
        Category socialCat = new Category(3, "Social", MyApp.CATEGORY_IMAGES_SOCIAL);
        Category electionCat = new Category(4, "Election", MyApp.CATEGORY_IMAGES_ELECTION);
        Category sportsCat = new Category(5, "Sports", MyApp.CATEGORY_IMAGES_SPORTS);

        categoryList.add(educationCat);
        categoryList.add(entertainmentCat);
        categoryList.add(politicalCat);
        categoryList.add(socialCat);
        categoryList.add(electionCat);
        categoryList.add(sportsCat);

        myAdapter.setCatList(categoryList);


    }

    // TODO: 09-04-2022 User click category
    @Override
    public void setOnRecItemClickListener(int position) {

        Toast.makeText(this, "Index "+categoryList.get(position).getCategoryID(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), CandidateList_Activity.class);

        intent.putExtra(MyApp.CATEGORY_NAME, categoryList.get(position).getCategoryName());

        startActivity(intent);

    }

    // TODO: 08-04-2022 This function set navigation bar and all DB Related code is implementation ( Function implementation )
    private void setNavBar() {

        TextView tvName, tvEmail;
        CircleImageView profileImage;

        NavigationView nav = findViewById(R.id.navBar);

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

                switch (item.getItemId()) {
                    case R.id.miSetting:

                        Toast.makeText(Home_Activity.this, "Settings", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), Setting_Activity.class));

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

                        if (userInfo.getUserImage() != null) {

                            Glide
                                    .with(getApplicationContext())
                                    .asBitmap()
                                    .load(userInfo.getUserImage())
                                    .into(profileImage);

                        } else {

                            profileImage.setImageResource(R.drawable.profile_image);

                        }

                    }
                });

    }

}