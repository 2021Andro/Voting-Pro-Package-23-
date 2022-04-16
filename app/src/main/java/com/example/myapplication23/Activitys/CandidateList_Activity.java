package com.example.myapplication23.Activitys;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CandidateList_Activity extends AppCompatActivity{

    private RecyclerView rvCandidateList;
    private MyCandiRecListAdapter myCandiAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Candidate> candidateList = new ArrayList<>();

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        categoryName = getIntent().getStringExtra(MyApp.CATEGORY_NAME);

        rvCandidateList = findViewById(R.id.rvCandidateList);

        layoutManager = new LinearLayoutManager(this);

        myCandiAdapter = new MyCandiRecListAdapter(candidateList, this);

        rvCandidateList.setLayoutManager(layoutManager);

        rvCandidateList.setAdapter(myCandiAdapter);



        MyApp.myCS.collection("Categories "+categoryName+" List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot documentSnapshot:list)
                        {
                            Candidate candidate=documentSnapshot.toObject(Candidate.class);

                            candidateList.add(candidate);

                            myCandiAdapter.notifyDataSetChanged();
                        }

                    }
                });



    }

}