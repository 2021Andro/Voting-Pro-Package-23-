package com.example.myapplication23.Fragments.History_Voting_Fragment;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication23.CostumeAdapters.MyRecVotingHistoryAdapter;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistoryHome_Fragment extends Fragment implements MyRecVotingHistoryAdapter.MyRecHistoryEvent {

    private RecyclerView rvList;
    private ArrayList<Votes> votesList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private MyRecVotingHistoryAdapter myRecAdapter;

    private Spinner spinner;
    private ArrayAdapter<String> myArrayAdapter;
    private String[] categoryList = new String[]{"Choose the category", "Education", "Entertainment", "Political", "Social", "Election", "Sports"};

    // private String categoryName = "Education";

    private TextView tvShowHistory;
    private ImageView ivCalender;
    private CalendarView cvCalender;
    private ConstraintLayout calendarLayout;
    private int year, month, day;
    private String votingDay = "00/00/00";

    public HistoryHome_Fragment() {

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_home_, container, false);

        initializeView(view);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0){

                    calendarLayout.setVisibility(View.VISIBLE);

                    openCalendar(categoryList[i]);


                } else {


                    votesList.clear();
                    myArrayAdapter.notifyDataSetChanged();
                    chooseHistory(categoryList[i], votingDay);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openCalendar(String categoryName) {



        final Calendar calendar = Calendar.getInstance();


        ivCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {


                        m++;
                        String year = String.format("%02d", y);
                        String month = String.format("%02d", m);
                        String day = String.format("%02d", d);

                        String votingDay = year+"/"+month+"/"+day;
                        tvShowHistory.setText(votingDay);

                        votesList.clear();
                        myArrayAdapter.notifyDataSetChanged();

                        chooseHistory(categoryName, votingDay);

                    }
                }, year, month, day);

                dialog.show();

            }
        });





    }

    private void initializeView(View view) {

        // RecyclerView related code
        rvList = view.findViewById(R.id.rvListHistory);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        myRecAdapter = new MyRecVotingHistoryAdapter(votesList, this);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(myRecAdapter);

        // Spinner related code
        spinner = view.findViewById(R.id.spinner);
        myArrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, categoryList);
        spinner.setAdapter(myArrayAdapter);
        calendarLayout = view.findViewById(R.id.calendarLayout);
        tvShowHistory = view.findViewById(R.id.tvShowHistory);
        ivCalender = view.findViewById(R.id.ivCalender);

        calendarLayout.setVisibility(View.GONE);


    }

    private void chooseHistory(String categoryName, String votingDay) {





        MyApp
                .myCS
                .collection("User_Info")
                .document(MyApp.myAuth.getUid().toString())
                .collection(categoryName)
                .whereEqualTo("votingDay", votingDay)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<DocumentSnapshot> docList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot snapshot : docList)
                        {
                            Votes votes = snapshot.toObject(Votes.class);

                            votesList.add(votes);

                        }

                        myRecAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public void setOnMyRecItemClick(Votes votes) {

        Votes_Details_History_Fragment votesDetails = new Votes_Details_History_Fragment(votes);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fcvHistoryHome, votesDetails)
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void onPause() {
        super.onPause();

        spinner.setSelection(0);
        calendarLayout.setVisibility(View.GONE);
        votesList.clear();
    }
}