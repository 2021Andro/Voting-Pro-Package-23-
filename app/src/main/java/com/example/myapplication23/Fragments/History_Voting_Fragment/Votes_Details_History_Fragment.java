package com.example.myapplication23.Fragments.History_Voting_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import de.hdodenhof.circleimageview.CircleImageView;


public class Votes_Details_History_Fragment extends Fragment {

    private Votes votes;
    private String TAG = "Votes_Details_History_Fragment";

    public Votes_Details_History_Fragment() {
        // Required empty public constructor
    }

    public Votes_Details_History_Fragment(Votes votes) {

        if (votes != null){

            this.votes = votes;

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_votes__details__history_, container, false);

        CircleImageView profile_image;
        RatingBar ratingBar;

        TextView tvName, tvStatus, tvSubject, tvVotingDay, tvVotingTime, tvCategoryName, tvVotingName, tvComment;

        profile_image = view.findViewById(R.id.profile_image_HD);
        ratingBar = view.findViewById(R.id.ratingBar_HD);

        tvName = view.findViewById(R.id.tvCandidetName_HD);
        tvStatus = view.findViewById(R.id.tvStatus_HD);
        tvSubject = view.findViewById(R.id.tvSubject_HD);
        tvVotingDay = view.findViewById(R.id.tvVotingDay_HD);
        tvVotingTime = view.findViewById(R.id.tvVotingTime_HD);
        tvCategoryName = view.findViewById(R.id.tvCategoryName_HD);
        tvVotingName = view.findViewById(R.id.tvVotingName_HD);
        tvComment = view.findViewById(R.id.tvComment_HD);


        Glide
                .with(view.getContext())
                .asBitmap()
                .load(votes.getVotingCandidateImage())
                .into(profile_image);

        tvName.setText(votes.getVotingCandidateName());

        tvStatus.setText(votes.getVotingStatus());
        tvSubject.setText(votes.getVotingSubject());
        tvVotingDay.setText(votes.getVotingDay());
        tvVotingTime.setText(votes.getVotingTime());
        tvCategoryName.setText(votes.getVoteCategoryName());
        tvVotingName.setText(votes.getVoteName());
        tvComment.setText(votes.getVotingComments());

        MyApp
                .myCS
                .collection("Categories "+votes.getVoteCategoryName()+" List")
                .document(votes.getVotingCandidateDbRef())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        String rating = (String) documentSnapshot.get("candidateRatingStars");

                        Log.d(TAG, "Rating: "+rating);

                        ratingBar.setRating(Float.parseFloat(rating));

                    }
                });


        return view;
    }
}