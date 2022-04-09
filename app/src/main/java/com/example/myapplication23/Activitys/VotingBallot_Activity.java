package com.example.myapplication23.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication23.CostumeClasses.CandidateInfo;
import com.example.myapplication23.CostumeClasses.MyApp;
import com.example.myapplication23.CostumeClasses.Votes;
import com.example.myapplication23.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.hdodenhof.circleimageview.CircleImageView;

public class VotingBallot_Activity extends AppCompatActivity {

    private CircleImageView profileImage;

    private TextView tvName, tvStatus, tvTodaySubject, tvAfterVotingMassage;
    private EditText etComment;

    private String name, status, TodaySubject, comment;
    private CandidateInfo mainCandidateInfo;
    private LinearLayout voting_button_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_ballot);

        mainCandidateInfo = (CandidateInfo) getIntent().getSerializableExtra(MyApp.CANDIDATE);


        profileImage = findViewById(R.id.profile_image_VB);

        tvName = findViewById(R.id.tvName_VB);
        tvStatus = findViewById(R.id.tvStatus_VB);
        tvTodaySubject = findViewById(R.id.tvTodaySubject_VB);
        tvAfterVotingMassage = findViewById(R.id.tvAfterVotingMassage_VB);
        etComment = findViewById(R.id.etComment_VB);

        voting_button_layout = findViewById(R.id.voting_button_layout_VB);

        tvName.setText(mainCandidateInfo.getCandidateName());
        tvStatus.setText(mainCandidateInfo.getCandidateStatus());
        tvTodaySubject.setText(mainCandidateInfo.getCandidateSubject());

        Glide
                .with(getApplicationContext())
                .asBitmap()
                .load(mainCandidateInfo.getCandidateImage())
                .into(profileImage);



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


            switch (view.getId())
            {
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
        LocalDateTime now = LocalDateTime.now();

        Votes vote = new Votes(mainCandidateInfo.getCandidateName(), mainCandidateInfo.getCandidateName(), dtf.format(now).toString(), voteName, comment, true);



    }


    private void setVoteRecordDbRef(Votes vote) {



    }
}