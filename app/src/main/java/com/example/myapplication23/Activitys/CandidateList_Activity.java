package com.example.myapplication23.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication23.CostumeAdapters.MyCandiRecListAdapter;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.Interfaces.MyRecCandidatListEvent;
import com.example.myapplication23.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CandidateList_Activity extends AppCompatActivity implements MyRecCandidatListEvent{

    private RecyclerView rvCandidateList;
    private MyCandiRecListAdapter myCandiAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        categoryName = getIntent().getStringExtra(MyApp.CATEGORY_NAME);

        rvCandidateList = findViewById(R.id.rvCandidateList);

        layoutManager = new LinearLayoutManager(this);

        FirestoreRecyclerOptions<Candidate> options = new FirestoreRecyclerOptions.Builder<Candidate>()
                .setQuery(MyApp.myCS.collection("Categories "+categoryName+" List"), Candidate.class)
                .build();

        myCandiAdapter = new MyCandiRecListAdapter(options, this);

        rvCandidateList.setLayoutManager(layoutManager);

        rvCandidateList.setAdapter(myCandiAdapter);


    }

    @Override
    public void setOnCandidateRecClickListener(Candidate candidate) {

        Intent intent = new Intent(getApplicationContext(), VotingBallot_Activity.class);

        intent.putExtra(MyApp.CANDIDATE, candidate);

        startActivity(intent);

        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        myCandiAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myCandiAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);

        startActivity(intent);

        finish();
    }
}