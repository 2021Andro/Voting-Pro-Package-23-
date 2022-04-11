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
import com.example.myapplication23.CostumeClasses.CandidateInfo;
import com.example.myapplication23.CostumeClasses.Category;
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

public class CandidateList_Activity extends AppCompatActivity implements MyRecCandidatListEvent{

    private RecyclerView rvCandidateList;
    private ArrayList<CandidateInfo> candidateList, temporaryCandList;
    private RecyclerView.LayoutManager layoutManager;
    private MyCandiRecListAdapter myCandiAdapter;

    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        Intent intent = getIntent();

        categoryName = intent.getStringExtra(MyApp.CATEGORY_NAME);

        rvCandidateList = findViewById(R.id.rvCandidateList);

        layoutManager = new LinearLayoutManager(this);

        myCandiAdapter = new MyCandiRecListAdapter(this);

        candidateList = new ArrayList<>();

        rvCandidateList.setLayoutManager(layoutManager);

        rvCandidateList.setAdapter(myCandiAdapter);

        myCandiAdapter.setCandidateList(candidateList);

        MyApp
                .myCS
                .collection("Categories "+categoryName+" List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {

                                CandidateInfo candidate = d.toObject(CandidateInfo.class);

                                Log.d(MyApp.myTag, "CandidateList_Activity Database Reference --> "+candidate.getDbRef().toString());

                                candidateList.add(candidate);
                            }

                            temporaryCandList = candidateList;

                            myCandiAdapter.notifyDataSetChanged();

                        } else {

                            Toast.makeText(CandidateList_Activity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    @Override
    public void setOnCandidateRecClickListener(int position) {

        CandidateInfo candidate = temporaryCandList.get(position);



        Log.d(MyApp.myTag, "Candidate Name : "+candidate.getCandidateName());
        Log.d(MyApp.myTag, "Candidate Category : "+candidate.getCategoryName());
        Log.d(MyApp.myTag, "Candidate Status : "+candidate.getCandidateStatus());
        Log.d(MyApp.myTag, "Candidate Subject  : "+candidate.getCandidateSubject());
        Log.d(MyApp.myTag, "Candidate Like Vote : "+candidate.getLikeVotes());
        Log.d(MyApp.myTag, "Candidate Neutral Vote : "+candidate.getNeutralVotes());
        Log.d(MyApp.myTag, "Candidate Dislike Vote : "+candidate.getDislikeVotes());
        Log.d(MyApp.myTag, "Candidate All Vote : "+candidate.getAllVotes());
        Log.d(MyApp.myTag, "Candidate Vote DB : "+candidate.getDbRef());
        Log.d(MyApp.myTag, "==========================");

        Intent intent = new Intent(getApplicationContext(), VotingBallot_Activity.class);

        intent.putExtra(MyApp.CANDIDET_DB_REF, candidate.getDbRef());
        intent.putExtra(MyApp.CANDIDET_IMAGE, candidate.getCandidateImage());
        intent.putExtra(MyApp.CANDIDET_NAME, candidate.getCandidateName());
        intent.putExtra(MyApp.CANDIDET_CATEGORY, candidate.getCategoryName());
        intent.putExtra(MyApp.CANDIDET_STATUS, candidate.getCandidateStatus());
        intent.putExtra(MyApp.CANDIDET_SUBJECT, candidate.getCandidateSubject());
        intent.putExtra(MyApp.CANDIDET_DB_REF, candidate.getDbRef());

        startActivity(intent);
    }
}