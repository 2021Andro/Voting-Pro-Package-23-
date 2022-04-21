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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyCandiRecListAdapter extends RecyclerView.Adapter<MyCandiRecListAdapter.MyViewHolder> {


    private ArrayList<Candidate> candidateList;
    private Context context;
    private Candidate candidate;
    private MyRecCandidatListEvent event;

    public MyCandiRecListAdapter(ArrayList<Candidate> candidateList, Context context) {
        this.candidateList = candidateList;
        this.context = context;
        this.event = (MyRecCandidatListEvent) context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_candi_rec_adapter_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        candidate = candidateList.get(position);

        holder.itemView.setTag(candidateList.get(position));

        Glide.with(context).asBitmap().load(candidate.getCandidateImage()).into(holder.profile_image);

        holder.tvName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());


    }


    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profile_image;

        private RatingBar rbCriticsRating;
        private TextView tvName, tvStatus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialization of views
            initializeView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    event.setOnCandidateRecClickListener(candidateList.indexOf( (Candidate) itemView.getTag() ));
                }
            });


        }

        private void initializeView(View view) {

            profile_image = view.findViewById(R.id.profile_image_VB);

            rbCriticsRating = view.findViewById(R.id.rbCriticsRating_VB);

            tvName = view.findViewById(R.id.tvName_VB);
            tvStatus = view.findViewById(R.id.tvStatus_VB);

        }

    }

}