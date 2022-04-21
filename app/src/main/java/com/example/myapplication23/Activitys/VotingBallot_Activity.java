package com.example.myapplication23.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.Candidate;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.hdodenhof.circleimageview.CircleImageView;

public class VotingBallot_Activity extends AppCompatActivity {

    private CircleImageView profileImage;
    private ImageView ivLike, ivNeutral, ivDislike;

    private TextView tvName, tvStatus, tvTodaySubject, tvAfterVotingMassage, tvLike, tvNeutral, tvDislike;
    private EditText etComment;

    private String comment, userAuthID, TAG="VotingBallot_Activity";
    private LinearLayout voting_button_layout, show_vote_layout;
    private DatabaseReference votingRef;
    private ValueEventListener addVoteListener;
    private Candidate candidate;
    private CollectionReference categoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_ballot);

        candidate = (Candidate) getIntent().getSerializableExtra(MyApp.CANDIDATE);

        profileImage = findViewById(R.id.profile_image_VB1);

        tvName = findViewById(R.id.tvName_VB1);
        tvStatus = findViewById(R.id.tvStatus_VB1);
        tvTodaySubject = findViewById(R.id.tvTodaySubject_VB1);

        ivLike = findViewById(R.id.ivLike_VB1);
        ivNeutral = findViewById(R.id.ivNeutral_VB1);
        ivDislike = findViewById(R.id.ivDislike_VB1);

        tvLike = findViewById(R.id.tvLike_VB1);
        tvNeutral = findViewById(R.id.tvNeutral_VB1);
        tvDislike = findViewById(R.id.tvDislike_VB1);

        tvAfterVotingMassage = findViewById(R.id.tvAfterVotingMassage_VB1);
        etComment = findViewById(R.id.etComment_VB1);

        voting_button_layout = findViewById(R.id.voting_button_layout_VB1);
        show_vote_layout = findViewById(R.id.voting_button_layout_VB11);

        userAuthID = MyApp.myAuth.getUid().trim();

        votingRef = MyApp.myRef.getReference("Voting");

        categoryRef = MyApp.myCS.collection("Categories " + candidate.getCandidateCategoryName() + " List");

        // This is checking user voting submit or not
        addVoteListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Votes votes = snapshot.getValue(Votes.class);

                boolean like = snapshot.child("candidateLikeVotes").child(candidate.getCandidateRefID()).hasChild(userAuthID);
                boolean neutral = snapshot.child("candidateNeutralVotes").child(candidate.getCandidateRefID()).hasChild(userAuthID);
                boolean dislike = snapshot.child("candidateDislikeVotes").child(candidate.getCandidateRefID()).hasChild(userAuthID);

                if (like) {
                    // User already voting completed

                    setViolatingVisible(1);

                } else if (neutral) {
                    // User already voting completed

                    setViolatingVisible(2);

                } else if (dislike) {
                    // User already voting completed
                    setViolatingVisible(3);

                } else {
                    // User voting is not completed
                    setViolatingInvisible();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();


        tvName.setText(candidate.getCandidateName());
        tvStatus.setText(candidate.getCandidateStatus());
        tvTodaySubject.setText(candidate.getCandidateSubject());

        Glide
                .with(getApplicationContext())
                .asBitmap()
                .load(candidate.getCandidateImage())
                .into(profileImage);


        votingRef.addValueEventListener(addVoteListener);


        categoryRef
                .document(candidate.getCandidateRefID())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                        Candidate candidate1 = value.toObject(Candidate.class);


                        long like = candidate1.getCandidateLikeVotes();
                        long neutral = candidate1.getCandidateNeutralVotes();
                        long dislike = candidate1.getCandidateDislikeVotes();

                        tvLike.setText(""+like);
                        tvNeutral.setText(""+neutral);
                        tvDislike.setText(""+dislike);
                    }
                });



    }

    // This function have voting layout visible ( Voting has not been done )
    private void setViolatingVisible(int position) {

        show_vote_layout.setVisibility(View.VISIBLE);
        tvAfterVotingMassage.setVisibility(View.VISIBLE);
        voting_button_layout.setVisibility(View.GONE);
        etComment.setVisibility(View.GONE);

        switch (position)
        {

            case 1:

                ivLike.setImageResource(R.drawable.ic_like);

                break;

            case 2:

                ivNeutral.setImageResource(R.drawable.ic_neutral);

                break;

            case 3:

                ivDislike.setImageResource(R.drawable.ic_dislike);

                break;
        }

    }

    // This function have voting not layout visible ( Voting has been done )
    private void setViolatingInvisible() {
        etComment.setVisibility(View.VISIBLE);
        voting_button_layout.setVisibility(View.VISIBLE);
        tvAfterVotingMassage.setVisibility(View.GONE);
        show_vote_layout.setVisibility(View.GONE);

    }


    // TODO: 03-04-2022 Like button
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void btnSetOnVoteSubmit(View view) {

        comment = etComment.getText().toString().trim();

        if (comment.isEmpty()) {

            etComment.setError("Enter your comment");
            return;

        } else if (comment.length() >= 25) {

            etComment.setError("Enter your comment on 25 character");
            return;


        } else {


            switch (view.getId()) {
                case R.id.btnLike_VB:

                    Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                    setVoteRecord("candidateLikeVotes");

                    break;

                case R.id.btnNeutral_VB:

                    Toast.makeText(this, "Neutral", Toast.LENGTH_SHORT).show();
                    setVoteRecord("candidateNeutralVotes");

                    break;

                case R.id.btnDislike_VB:

                    Toast.makeText(this, "Dislike", Toast.LENGTH_SHORT).show();
                    setVoteRecord("candidateDislikeVotes");

                    break;

            }
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setVoteRecord(String voteName) {


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        LocalDateTime localDateTime = LocalDateTime.now();

        String votingDateTime = localDateTime.format(dtf);


        Votes vote = new Votes();

        vote.setVotingCandidateName(candidate.getCandidateName());
        vote.setVotingStatus(candidate.getCandidateStatus());
        vote.setVotingSubject(candidate.getCandidateSubject());
        vote.setVotingDate(votingDateTime);
        vote.setVotingComments(comment);
        vote.setVotes(true);

        // setVoteRecordHistoryDbRef(vote, voteName);

        setVoteRecordDbRef(vote, voteName);


    }

    private void setVoteRecordDbRef(Votes vote, String votingName) {



        votingRef
                .child(votingName)
                .child(candidate.getCandidateRefID())
                .child(MyApp.myAuth.getUid().toString())
                .setValue(vote)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {


                            categoryRef.document(candidate.getCandidateRefID()).update(votingName, FieldValue.increment(1));



                            Intent intent = new Intent(getApplicationContext(), Home_Activity.class);

                            startActivity(intent);


                        } else {

                            setViolatingVisible(0);

                            Toast.makeText(VotingBallot_Activity.this, "Sum problem for summit vote", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setVoteRecordHistoryDbRef(Votes vote, String voteName) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        LocalDateTime localDateTime = LocalDateTime.now();

        String currentDay = LocalDate.now().getDayOfWeek().name();
        String userId = MyApp.myAuth.getUid();

        CollectionReference user_voting_history = MyApp.myCS.collection("User Voting History");

        user_voting_history
                .document(userId.toString())
                .collection(currentDay)
                .document(localDateTime.toString())
                .set(vote);


    }

}