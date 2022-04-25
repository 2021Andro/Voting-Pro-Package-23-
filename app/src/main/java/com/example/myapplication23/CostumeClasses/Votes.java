package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Votes implements Serializable {

    private String VotingCandidateDbRef;
    private String VotingCandidateName;
    private String VotingStatus;
    private String VotingSubject;
    private String VotingDay;
    private String VotingTime;
    private String VoteName;
    private String VoteCategoryName;
    private String VotingComments;
    private boolean IsVotes;

    public Votes() {
    }


    public String getVotingCandidateDbRef() {
        return VotingCandidateDbRef;
    }

    public void setVotingCandidateDbRef(String votingCandidateDbRef) {
        VotingCandidateDbRef = votingCandidateDbRef;
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

    public String getVotingDay() {
        return VotingDay;
    }

    public void setVotingDay(String votingDay) {
        VotingDay = votingDay;
    }

    public String getVotingTime() {
        return VotingTime;
    }

    public void setVotingTime(String votingTime) {
        VotingTime = votingTime;
    }

    public String getVoteName() {
        return VoteName;
    }

    public void setVoteName(String voteName) {
        VoteName = voteName;
    }

    public String getVoteCategoryName() {
        return VoteCategoryName;
    }

    public void setVoteCategoryName(String voteCategoryName) {
        VoteCategoryName = voteCategoryName;
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
                "VotingCandidateDbRef='" + VotingCandidateDbRef + '\'' +
                ", VotingCandidateName='" + VotingCandidateName + '\'' +
                ", VotingStatus='" + VotingStatus + '\'' +
                ", VotingSubject='" + VotingSubject + '\'' +
                ", VotingDay='" + VotingDay + '\'' +
                ", VotingTime='" + VotingTime + '\'' +
                ", VoteName='" + VoteName + '\'' +
                ", VoteCategoryName='" + VoteCategoryName + '\'' +
                ", VotingComments='" + VotingComments + '\'' +
                ", IsVotes=" + IsVotes +
                '}';
    }
}