package com.example.myapplication23.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class CandidateList_Activity extends AppCompatActivity implements MyRecCandidatListEvent {

    private RecyclerView rvCandidateList;
    private ArrayList<CandidateInfo> candidateList;
    private RecyclerView.LayoutManager layoutManager;
    private MyCandiRecListAdapter myCandiAdapter;

    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        category = (Category) getIntent().getSerializableExtra(MyApp.CATEGORY_NAME);

        rvCandidateList = findViewById(R.id.rvCandidateList);

        layoutManager = new LinearLayoutManager(this);

        myCandiAdapter = new MyCandiRecListAdapter(this);

        candidateList = new ArrayList<>();

        rvCandidateList.setLayoutManager(layoutManager);

        rvCandidateList.setAdapter(myCandiAdapter);

        myCandiAdapter.setCandidateList(candidateList);

        MyApp
                .myCS
                .collection("Categories "+category.getCategoryName()+" List")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are
                            // hiding our progress bar and adding
                            // our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                CandidateInfo c = d.toObject(CandidateInfo.class);

                                // and we will pass this object class
                                // inside our arraylist which we have
                                // created for recycler view.
                                candidateList.add(c);
                            }
                            // after adding the data to recycler view.
                            // we are calling recycler view notifuDataSetChanged
                            // method to notify that data has been changed in recycler view.
                            myCandiAdapter.notifyDataSetChanged();
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(CandidateList_Activity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    @Override
    public void setOnCandidateRecClickListener(int position) {


        Toast.makeText(this, "Name "+candidateList.get(position).getCandidateName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), VotingBallot_Activity.class);

        startActivity(intent);



    }
}