package com.example.myapplication23.CostumeAdapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyRecTrendingAdapter extends RecyclerView.Adapter<MyRecTrendingAdapter.MyViewHolder> {

    private ArrayList<Candidate> candidateList;
    private MyRecyclerViewEvent myEvent;

    public MyRecTrendingAdapter(ArrayList<Candidate> candidateList, MyRecyclerViewEvent myEvent) {
        this.candidateList = candidateList;
        this.myEvent = myEvent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_trending_voting_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Candidate candidate = candidateList.get(position);


        Glide
                .with(holder.itemView.getContext())
                .asBitmap()
                .load(candidate.getCandidateImage())
                .into(holder.profileImage);

        holder.tvName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());
        holder.tvSubject.setText(candidate.getCandidateSubject());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myEvent.setOnItemClickListener(candidate);

            }
        });


    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;
        private TextView tvName, tvStatus, tvSubject, tvAllVotes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image_TA);
            tvName = itemView.findViewById(R.id.tvName_TA);
            tvStatus = itemView.findViewById(R.id.tvStatus_TA);
            tvSubject = itemView.findViewById(R.id.tvSubject_TA);
            tvAllVotes = itemView.findViewById(R.id.tvAllVotes_TA);
        }
    }

    public interface MyRecyclerViewEvent{
        void setOnItemClickListener(Candidate candidate);
    }

}
