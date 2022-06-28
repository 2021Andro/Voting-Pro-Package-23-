package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.CostumeClasses.Category;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecVotingHistoryAdapter extends RecyclerView.Adapter<MyRecVotingHistoryAdapter.MyViewHolder> {

    private MyRecHistoryEvent myEvent;
    private ArrayList<Votes> myList = new ArrayList<>();

    public MyRecVotingHistoryAdapter(ArrayList<Votes> myList, MyRecHistoryEvent myEvent) {
        this.myList = myList;
        this.myEvent = myEvent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_rec_voting_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Votes votes = myList.get(position);

        holder.tvName.setText(votes.getVoteName());

        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(myList.get(position).getVotingCandidateImage())
                .into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myEvent.setOnMyRecItemClick(votes);

            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private CircleImageView profileImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName_History);
            profileImage = itemView.findViewById(R.id.profile_image_History);
        }
    }

    public interface MyRecHistoryEvent{
        void setOnMyRecItemClick(Votes votes);
    }
}
