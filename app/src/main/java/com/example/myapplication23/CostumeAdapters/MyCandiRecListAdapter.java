package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.Interfaces.MyRecCandidatListEvent;
import com.example.myapplication23.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyCandiRecListAdapter extends FirestoreRecyclerAdapter<Candidate, MyCandiRecListAdapter.MyViewHolder>  {

    private MyRecCandidatListEvent event;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyCandiRecListAdapter(@NonNull FirestoreRecyclerOptions<Candidate> options, MyRecCandidatListEvent event) {
        super(options);

        this.event = event;

    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Candidate candidate) {

        holder.tvCandidateName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());

        Glide.with(holder.itemView.getContext()).asBitmap().load(candidate.getCandidateImage()).into(holder.profileImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.setOnCandidateRecClickListener(candidate);
            }
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_candi_rec_adapter_layout,parent, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView profileImage;
        private TextView tvCandidateName, tvStatus;
        private RatingBar rbCriticsRating_VB;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.profile_image_VB);
            tvCandidateName = itemView.findViewById(R.id.tvName_VB);
            tvStatus = itemView.findViewById(R.id.tvStatus_VB);
            rbCriticsRating_VB = itemView.findViewById(R.id.rbCriticsRating_VB);

        }
    }
}