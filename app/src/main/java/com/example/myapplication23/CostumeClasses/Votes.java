package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Votes implements Serializable {

    private String VotingCandidateName;
    private String VotingStatus;
    private String VotingSubject;
    private String VotingDate;
    private String VoteName;
    private String VotingComments;
    private boolean IsVotes;

    public Votes() {
    }

    public Votes(String votingCandidateName, String votingStatus, String votingSubject, String votingDate, String voteName, String votingComments, boolean isVotes) {
        VotingCandidateName = votingCandidateName;
        VotingStatus = votingStatus;
        VotingSubject = votingSubject;
        VotingDate = votingDate;
        VoteName = voteName;
        VotingComments = votingComments;
        IsVotes = isVotes;
    }

    public String getVotingCandidateName() {
        return VotingCandidateName;
    }

    public void setVotingCandidateName(String votingCandidateName) {
        VotingCandidateName = votingCandidateName;
    }

    public String getVotingStatus() {
        return VotingStatus;
    }

    public void setVotingStatus(String votingStatus) {
        VotingStatus = votingStatus;
    }

    public String getVotingSubject() {
        return VotingSubject;
    }

    public void setVotingSubject(String votingSubject) {
        VotingSubject = votingSubject;
    }

    public String getVotingDate() {
        return VotingDate;
    }

    public void setVotingDate(String votingDate) {
        VotingDate = votingDate;
    }

    public String getVoteName() {
        return VoteName;
    }

    public void setVoteName(String voteName) {
        VoteName = voteName;
    }

    public String getVotingComments() {
        return VotingComments;
    }

    public void setVotingComments(String votingComments) {
        VotingComments = votingComments;
    }

    public boolean isVotes() {
        return IsVotes;
    }

    public void setVotes(boolean votes) {
        IsVotes = votes;
    }
}
