package com.example.myapplication23.Fragments.Settings_Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication23.CostumeAdapters.MySettingsRVAdapter;
import com.example.myapplication23.CostumeClasses.MyAppSettings;
import com.example.myapplication23.Interfaces.MyRecCandidatListEvent;
import com.example.myapplication23.R;

import java.util.ArrayList;


public class Setting_Home_Fragment extends Fragment  implements MySettingsRVAdapter.MyRecyclerEvent {

    private View myView;
    private RecyclerView rvList;
    private ArrayList<MyAppSettings> settingList;
    private MySettingsRVAdapter mySettingsRVAdapter;
    private RecyclerView.LayoutManager layoutManager;



    public Setting_Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_setting__home_, container, false);

        rvList = myView.findViewById(R.id.rcSettingsCustomer);

        layoutManager = new LinearLayoutManager(getContext());

        settingList = new ArrayList<>();

        mySettingsRVAdapter = new MySettingsRVAdapter(settingList, this);

        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(mySettingsRVAdapter);

        settingList.add(new MyAppSettings("Profile Update", false));


        return myView;
    }


    @Override
    public void setOnClickItem(String settingName, int position) {


        switch (position){

            case 1: // User Profile Update Fragment

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fcHome, new User_Profile_Update_Fragment())
                        .addToBackStack(null)
                        .commit();

                break;
        }

    }
}