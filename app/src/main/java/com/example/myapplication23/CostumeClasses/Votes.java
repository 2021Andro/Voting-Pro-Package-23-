package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Votes implements Serializable {

    private String VotingCategoryName;
    private String VotingSubject;
    private String VotingDate;
    private String VoteName;
    private String VotingComments;
    private boolean IsVotes;

    public Votes() {
    }

    public Votes(String votingCategoryName, String votingSubject, String votingDate, String voteName, String votingComments, boolean isVotes) {
        VotingCategoryName = votingCategoryName;
        VotingSubject = votingSubject;
        VotingDate = votingDate;
        VoteName = voteName;
        VotingComments = votingComments;
        IsVotes = isVotes;
    }

    public String getVotingCategoryName() {
        return VotingCategoryName;
    }

    public void setVotingCategoryName(String votingCategoryName) {
        VotingCategoryName = votingCategoryName;
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

    @Override
    public String toString() {
        return "Votes{" +
                "VotingCategoryName='" + VotingCategoryName + '\'' +
                ", VotingSubject='" + VotingSubject + '\'' +
                ", VotingDate='" + VotingDate + '\'' +
                ", VoteName='" + VoteName + '\'' +
                ", VotingComments='" + VotingComments + '\'' +
                ", IsVotes=" + IsVotes +
                '}';
    }
}
