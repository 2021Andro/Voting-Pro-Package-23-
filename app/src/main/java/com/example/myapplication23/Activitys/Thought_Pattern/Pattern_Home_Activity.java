package com.example.myapplication23.Activitys.Thought_Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication23.Activitys.Home_Activity;
import com.example.myapplication23.CostumeAdapters.MyRecTrendingAdapter;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Pattern_Home_Activity extends AppCompatActivity implements MyRecTrendingAdapter.MyRecyclerViewEvent{

    private final String TAG = "Trending_Home_Activity";

    private androidx.appcompat.widget.Toolbar toolbar;

    private RecyclerView rvList;
    private RecyclerView.LayoutManager layoutManager;
    private MyRecTrendingAdapter myAdapter;
    private ArrayList<Candidate> candidateList;
    private ArrayList<Candidate> cloneList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_home);

        initializeView();



    }

    private void initializeView() {
        rvList = findViewById(R.id.rvList_T);

        candidateList = new ArrayList<>();


        myAdapter = new MyRecTrendingAdapter(candidateList, Pattern_Home_Activity.this);

        layoutManager = new LinearLayoutManager(Pattern_Home_Activity.this);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(myAdapter);


    }


    private void trendingChoose(String categoryName) {

        candidateList.clear();
        myAdapter.notifyDataSetChanged();

        MyApp
                .myCS
                .collection("Categories "+categoryName+" List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> docList = queryDocumentSnapshots.getDocuments();



                        for (DocumentSnapshot snapshot : docList)
                        {
                            Candidate candidate = snapshot.toObject(Candidate.class);

                            Log.d(TAG, "Gob Dun");

                            candidateList.add(candidate);

                        }

                        cloneList = candidateList;


                        myAdapter.notifyDataSetChanged();



                    }
                });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void setOnItemClickListener(Candidate candidate) {

    }
}