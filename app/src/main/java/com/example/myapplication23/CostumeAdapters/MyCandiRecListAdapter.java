package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCandiRecListAdapter extends RecyclerView.Adapter<MyCandiRecListAdapter.MyViewHolder> {


    private ArrayList<Candidate> candidateList;
    private Context context;
    private LinearLayout voting_button_layout;
    private TextView tvVotingMassageFromUser;

    public MyCandiRecListAdapter(ArrayList<Candidate> candidateList, Context context) {
        this.candidateList = candidateList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_candi_rec_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Candidate candidate = candidateList.get(position);

        Glide.with(context).asBitmap().load(candidate.getCandidateImage()).into(holder.profile_image);

        holder.tvName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());
        holder.tvTodaySubject.setText(candidate.getCandidateSubject());
        holder.tvLike.setText(candidate.getLikeVotes());
        holder.tvNeutral.setText(candidate.getNeutralVotes());
        holder.tvDislike.setText(candidate.getDislikeVotes());

        if (candidate.isVoteSubmitted()) {
            // The voting buttons are invisible

            setOnInvisibleView();
        } else {

            // The voting buttons are visible

            setOnVisibleView();


        }


    }

    private void setOnInvisibleView() {

        tvVotingMassageFromUser.setVisibility(View.VISIBLE);

    }

    private void setOnVisibleView() {

        tvVotingMassageFromUser.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profile_image;
        private ImageView ivLike, ivNeutral, ivDislike;

        private RatingBar rbCriticsRating;
        private TextView tvName, tvStatus, tvTodaySubject, tvLike, tvNeutral, tvDislike;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            initializeView(itemView);

            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setOnInvisibleView();

                    ivLike.setImageResource(R.drawable.ic_like);
                }
            });

            ivNeutral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setOnInvisibleView();

                    ivLike.setClickable(false);
                    ivNeutral.setClickable(false);
                    ivDislike.setClickable(false);

                    ivNeutral.setImageResource(R.drawable.ic_neutral);

                }
            });

            ivDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setOnInvisibleView();

                    ivLike.setClickable(false);
                    ivNeutral.setClickable(false);
                    ivDislike.setClickable(false);

                    ivDislike.setImageResource(R.drawable.ic_dislike);
                }
            });


        }

        private void initializeView(View view) {

            profile_image = view.findViewById(R.id.profile_image_VB);

            ivLike = view.findViewById(R.id.ivLike_VB);
            ivNeutral = view.findViewById(R.id.ivNeutral_VB);
            ivDislike = view.findViewById(R.id.ivDislike_VB);

            rbCriticsRating = view.findViewById(R.id.rbCriticsRating_VB);

            tvName = view.findViewById(R.id.tvName_VB);
            tvStatus = view.findViewById(R.id.tvStatus_VB);
            tvTodaySubject = view.findViewById(R.id.tvTodaySubject_VB);

            tvLike = view.findViewById(R.id.tvLike_VB);
            tvNeutral = view.findViewById(R.id.tvNeutral_VB);
            tvDislike = view.findViewById(R.id.tvDislike_VB);

            tvVotingMassageFromUser = view.findViewById(R.id.tvVotingMassageFromUser_VB);
            voting_button_layout = view.findViewById(R.id.voting_button_layout_VB);



        }

    }

}
