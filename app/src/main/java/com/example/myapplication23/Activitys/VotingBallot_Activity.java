package com.example.myapplication23.Activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.hdodenhof.circleimageview.CircleImageView;

public class VotingBallot_Activity extends AppCompatActivity {

    private CircleImageView profileImage;

    private TextView tvName, tvStatus, tvTodaySubject, tvAfterVotingMassage;
    private EditText etComment;

    private String candDbId, candImage, candName, candCategoryName, candStatus, candiSubject, comment, userAuthID;
    private LinearLayout voting_button_layout;
    private DatabaseReference votingRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_ballot);

        Intent intent = getIntent();

        candDbId = intent.getStringExtra(MyApp.CANDIDET_DB_REF);
        candImage = intent.getStringExtra(MyApp.CANDIDET_IMAGE);
        candName = intent.getStringExtra(MyApp.CANDIDET_NAME);
        candCategoryName = intent.getStringExtra(MyApp.CANDIDET_CATEGORY);
        candStatus = intent.getStringExtra(MyApp.CANDIDET_STATUS);
        candiSubject = intent.getStringExtra(MyApp.CANDIDET_SUBJECT);

        userAuthID = MyApp.myAuth.getUid().trim();

        votingRef = MyApp.myRef.getReference("Voting");

        votingRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Votes votes = snapshot.getValue(Votes.class);

                if (
                        snapshot.child("Like").child(candDbId).hasChild(userAuthID) ||
                                snapshot.child("Neutral").child(candDbId).hasChild(userAuthID) ||
                                snapshot.child("Dislike").child(candDbId).hasChild(userAuthID)

                ) {
                    // User already voting completed

                        Log.d(MyApp.myTag, "Subject : "+votes.getVotingSubject());
                        setVotinglayoutVisible();

                } else {
                    // User voting is not completed
                    setVotinglayoutInvisible();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d(MyApp.myTag, "Candidate Name : " + candName);
        Log.d(MyApp.myTag, "Candidate Category : " + candCategoryName);
        Log.d(MyApp.myTag, "Candidate Status : " + candStatus);
        Log.d(MyApp.myTag, "Candidate Subject  : " + candiSubject);
        Log.d(MyApp.myTag, "Candidate DR : " + candDbId);

        profileImage = findViewById(R.id.profile_image_VB);

        tvName = findViewById(R.id.tvName_VB);
        tvStatus = findViewById(R.id.tvStatus_VB);
        tvTodaySubject = findViewById(R.id.tvTodaySubject_VB);
        tvAfterVotingMassage = findViewById(R.id.tvAfterVotingMassage_VB);
        etComment = findViewById(R.id.etComment_VB);

        voting_button_layout = findViewById(R.id.voting_button_layout_VB);

        tvName.setText(candName);
        tvStatus.setText(candStatus);
        tvTodaySubject.setText(candiSubject);

        Glide
                .with(getApplicationContext())
                .asBitmap()
                .load(candImage)
                .into(profileImage);


    }

    // This function have voting layout visible ( Voting has not been done )
    private void setVotinglayoutVisible() {
        voting_button_layout.setVisibility(View.GONE);
        tvAfterVotingMassage.setVisibility(View.VISIBLE);
        etComment.setVisibility(View.GONE);

    }

    // This function have voting not layout visible ( Voting has been done )
    private void setVotinglayoutInvisible() {
        voting_button_layout.setVisibility(View.VISIBLE);
        tvAfterVotingMassage.setVisibility(View.GONE);
        etComment.setVisibility(View.VISIBLE);

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
                    setVoteRecord("Like");

                    break;

                case R.id.btnNeutral_VB:

                    Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show();
                    setVoteRecord("Neutral");

                    break;

                case R.id.btnDislike_VB:

                    Toast.makeText(this, "Dislike", Toast.LENGTH_SHORT).show();
                    setVoteRecord("Dislike");

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

        vote.setVotingCandidateName(candName);
        vote.setVotingStatus(candStatus);
        vote.setVotingSubject(candiSubject);
        vote.setVotingDate(votingDateTime);
        vote.setVotingComments(comment);
        vote.setVotes(true);

        setVoteRecordDbRef(vote, voteName);


    }


    private void setVoteRecordDbRef(Votes vote, String votingName) {


        votingRef
                .child(votingName)
                .child(candDbId)
                .child(MyApp.myAuth.getUid().toString())
                .setValue(vote)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            setVotinglayoutInvisible();

                            Intent intent = new Intent(getApplicationContext(), Home_Activity.class);

                            startActivity(intent);


                        } else {

                            setVotinglayoutVisible();

                            Toast.makeText(VotingBallot_Activity.this, "Sum problem for summit vote", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
}