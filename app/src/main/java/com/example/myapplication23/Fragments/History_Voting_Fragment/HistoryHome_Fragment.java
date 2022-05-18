package com.example.myapplication23.Fragments.History_Voting_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication23.CostumeAdapters.MyRecVotingHistoryAdapter;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryHome_Fragment extends Fragment implements MyRecVotingHistoryAdapter.MyRecHistoryEvent{

    private RecyclerView rvList;
    private ArrayList<Votes> votesList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private MyRecVotingHistoryAdapter myRecAdapter;


    private Spinner spCategory;
    private String[] catList = new String[]{"Select the category", "Education", "Social", "Political", "Entertainment", "Election", "Sports"};
    private String categoryName;
    private ArrayAdapter myAdapter;
    private String TAG = "HistoryHome_Fragment";

    public HistoryHome_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_home_, container, false);

        spCategory = view.findViewById(R.id.spinnerHistory);
        rvList = view.findViewById(R.id.rvListHistory);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myRecAdapter = new MyRecVotingHistoryAdapter(votesList, this);

        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(myRecAdapter);


        myAdapter = new ArrayAdapter(getActivity().getApplicationContext(),  android.R.layout.simple_spinner_item, catList);

        spCategory.setAdapter(myAdapter);

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (i != 0)
                {
                    getHistoryList(catList[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return view;
    }

    private void getHistoryList(String categoryName) {

        Log.d(TAG, "Category Name: "+categoryName);

        String userID = MyApp.myAuth.getUid().toString();
        MyApp
                .myCS.collection("User_Info")
                .document(userID)
                .collection(categoryName)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                if (snapshotList != null) {
                    for (DocumentSnapshot doc : snapshotList) {
                        Votes votes = doc.toObject(Votes.class);

                        votesList.add(votes);
                    }

                    myRecAdapter.notifyDataSetChanged();

                }
                else
                {

                    rvList.setVisibility(View.GONE);

                }
            }
        });

    }

    @Override
    public void setOnMyRecItemClick(Votes votes) {

    }
}