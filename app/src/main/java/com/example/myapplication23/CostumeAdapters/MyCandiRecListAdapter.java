package com.example.myapplication23.CostumeAdapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyCandiRecListAdapter extends RecyclerView.Adapter<MyCandiRecListAdapter.MyViewHolder> {


    private TextView tvVotingMassageFromUser;
    private ArrayList<Candidate> candidateList;
    private Context context;
    private Candidate candidate;

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

        candidate = candidateList.get(position);

        Glide.with(context).asBitmap().load(candidate.getCandidateImage()).into(holder.profile_image);

        holder.tvName.setText(candidate.getCandidateName());
        holder.tvStatus.setText(candidate.getCandidateStatus());
        holder.tvTodaySubject.setText(candidate.getCandidateSubject());

        holder.tvLike.setText(""+candidate.getCandidateLikeVotes());
        holder.tvNeutral.setText(""+candidate.getCandidateNeutralVotes());
        holder.tvDislike.setText(""+candidate.getCandidateDislikeVotes());



    }


    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView profile_image;
        private ImageView ivLike, ivNeutral, ivDislike;

        private RatingBar rbCriticsRating;
        private TextView tvName, tvStatus, tvTodaySubject, tvLike, tvNeutral, tvDislike, tvVotingMassageFromUser;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialization of views
            initializeView(itemView);

            ivLike.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {

                    saveVoteDbRef("candidateLikeVotes","Like");
                    ivLike.setImageResource(R.drawable.ic_like);

                }
            });

            ivNeutral.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {


                    saveVoteDbRef("candidateNeutralVotes","Neutral");
                    ivNeutral.setImageResource(R.drawable.ic_neutral);

                }
            });

            ivDislike.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {

                    saveVoteDbRef("candidateDislikeVotes","Dislike");
                    ivDislike.setImageResource(R.drawable.ic_dislike);


                }
            });


        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void saveVoteDbRef(String filedName, String voteName) {

            DocumentReference cel = MyApp.myCS.collection("Categories "+candidate.getCandidateCategoryName()+" List").document(candidate.getCandidateRefID());

            cel.update(filedName, FieldValue.increment(1));
            Votes vote = new Votes();

            vote.setVotingCandidateName(candidate.getCandidateName());
            vote.setVoteName(voteName);
            vote.setVotingStatus(candidate.getCandidateStatus());
            vote.setVotingSubject(candidate.getCandidateSubject());
            vote.setVotingDate(MyApp.getCurrentDateAndTime());
            vote.setVotingComments("No Comments");
            vote.setVotes(true);

            DatabaseReference myVotesRef = MyApp.myRef.getReference("Votes");

            myVotesRef
                    .child(voteName)
                    .child(candidate.getCandidateRefID())
                    .child(MyApp.myAuth.getUid().toString())
                    .setValue(vote)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful())
                            {
                                tvVotingMassageFromUser.setVisibility(View.VISIBLE);

                                ivLike.setClickable(false);
                                ivNeutral.setClickable(false);
                                ivDislike.setClickable(false);
                            }
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

        }

    }

    private void setOnInvisibleView() {

        tvVotingMassageFromUser.setVisibility(View.VISIBLE);

    }

    private void setOnVisibleView() {

        tvVotingMassageFromUser.setVisibility(View.GONE);

    }

}
