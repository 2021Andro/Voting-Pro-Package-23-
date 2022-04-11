package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.Activitys.VotingBallot_Activity;
import com.example.myapplication23.CostumeClasses.CandidateInfo;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.Interfaces.MyRecCandidatListEvent;
import com.example.myapplication23.R;

import java.util.ArrayList;

public class MyCandiRecListAdapter extends RecyclerView.Adapter<MyCandiRecListAdapter.MyViewHolder> {

    private ArrayList<CandidateInfo> candidateList;
    private Context context;
    private MyRecCandidatListEvent event;

    public MyCandiRecListAdapter(Context context) {
        this.context = context;
        this.event = (MyRecCandidatListEvent) context;
    }

    public void setCandidateList(ArrayList<CandidateInfo> candidateList) {

        if (candidateList != null)
        {

            this.candidateList = candidateList;

        }else {

            this.candidateList = new ArrayList<>();

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_candi_rec_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CandidateInfo candidate = candidateList.get(position);
        holder.itemView.setTag(candidateList.get(position));

        holder.tvName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());

        Glide
                .with(context)
                .asBitmap()
                .load(candidate.getCandidateImage())
                .into(holder.ivProfile);

    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        private LinearLayout laShowDeal;
        private ImageView ivProfile, ivArrowUp, ivArrowDown;
        private TextView tvName, tvStatus, tvShowStarRating;
        private RatingBar ratingBar;
        private Button btnGoToVote;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);


            setOnViewsInitialization(itemView);


            // TODO: 26-03-2022 This button voter click than voter going Voting ballot activity for votes
            btnGoToVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    laShowDeal.setVisibility(View.GONE);
                    ivArrowUp.setVisibility(View.GONE);
                    ivArrowDown.setVisibility(View.VISIBLE);
                    event.setOnCandidateRecClickListener( candidateList.indexOf( (CandidateInfo) itemView.getTag() ) );
                }
            });

            laShowDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                laShowDeal.setVisibility(View.GONE);
                ivArrowUp.setVisibility(View.GONE);
                ivArrowDown.setVisibility(View.VISIBLE);
            }
        });

            // TODO: 26-03-2022 This code occur when voters down arrow click Details window visible
            ivArrowDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    laShowDeal.setVisibility(View.VISIBLE);
                    ivArrowUp.setVisibility(View.VISIBLE);
                    ivArrowDown.setVisibility(View.GONE);


                }
            });

            // TODO: 26-03-2022 This code occur when voters up arrow click Details window invisible
            ivArrowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    laShowDeal.setVisibility(View.GONE);
                    ivArrowUp.setVisibility(View.GONE);
                    ivArrowDown.setVisibility(View.VISIBLE);

                }
            });


        }

        // TODO: 26-03-2022 Initialization of views
        private void setOnViewsInitialization(View itemView) {

            laShowDeal = itemView.findViewById(R.id.laShowDeal_CAT_LIST);
            ivProfile = itemView.findViewById(R.id.ivProfile_CAT_LIST);
            ivArrowUp = itemView.findViewById(R.id.ivArrowUp_CAT_LIST);
            ivArrowDown = itemView.findViewById(R.id.ivArrowDown_CAT_LIST);
            tvName = itemView.findViewById(R.id.tvName_CAT_LIST);
            tvStatus = itemView.findViewById(R.id.tvStatus_CAT_LIST);
            tvShowStarRating = itemView.findViewById(R.id.tvShowStarRating_CAT_LIST);
            ratingBar = itemView.findViewById(R.id.ratingBar1_CAT_LIST);
            btnGoToVote = itemView.findViewById(R.id.btnGoToVote_CAT_LIST);
        }

    }
}
