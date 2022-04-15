package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Candidate extends Support_Class implements Serializable
{

    private String CandidateRefID;
    private String CandidateImage;
    private String CandidateName;
    private String CandidateStatus;
    private String CandidateSubject;
    private String CandidateEmailID;
    private String CandidateCategoryName;
    private String CandidateRatingStars;
    private String CandidateLikeVotes;
    private String CandidateNeutralVotes;
    private String CandidateDislikeVotes;
    private String CandidateAllVotes;
    private String CandidateCommentVotes;
    private boolean IsVoteSubmitted;


    public Candidate() {
    }

    public Candidate(String candidateName) {
        CandidateName = candidateName;
    }

    public String getCandidateRefID() {
        return CandidateRefID;
    }

    public void setCandidateRefID(String candidateRefID) {
        CandidateRefID = candidateRefID;
    }

    public String getCandidateImage() {
        return CandidateImage;
    }

    public void setCandidateImage(String candidateImage) {
        CandidateImage = candidateImage;
    }

    public String getCandidateName() {
        return CandidateName;
    }

    public void setCandidateName(String candidateName) {
        CandidateName = candidateName;
    }

    public String getCandidateStatus() {
        return CandidateStatus;
    }

    public void setCandidateStatus(String candidateStatus) {
        CandidateStatus = candidateStatus;
    }

    public String getCandidateSubject() {
        return CandidateSubject;
    }

    public void setCandidateSubject(String candidateSubject) {
        CandidateSubject = candidateSubject;
    }

    public String getCandidateEmailID() {
        return CandidateEmailID;
    }

    public void setCandidateEmailID(String candidateEmailID) {
        CandidateEmailID = candidateEmailID;
    }

    public String getCandidateCategoryName() {
        return CandidateCategoryName;
    }

    public void setCandidateCategoryName(String candidateCategoryName) {
        CandidateCategoryName = candidateCategoryName;
    }

    public String getCandidateRatingStars() {
        return CandidateRatingStars;
    }

    public void setCandidateRatingStars(String candidateRatingStars) {
        CandidateRatingStars = candidateRatingStars;
    }

    public String getCandidateLikeVotes() {
        return CandidateLikeVotes;
    }

    public void setCandidateLikeVotes(String candidateLikeVotes) {
        CandidateLikeVotes = candidateLikeVotes;
    }

    public String getCandidateNeutralVotes() {
        return CandidateNeutralVotes;
    }

    public void setCandidateNeutralVotes(String candidateNeutralVotes) {
        CandidateNeutralVotes = candidateNeutralVotes;
    }

    public String getCandidateDislikeVotes() {
        return CandidateDislikeVotes;
    }

    public void setCandidateDislikeVotes(String candidateDislikeVotes) {
        CandidateDislikeVotes = candidateDislikeVotes;
    }

    public String getCandidateAllVotes() {
        return CandidateAllVotes;
    }

    public void setCandidateAllVotes(String candidateAllVotes) {
        CandidateAllVotes = candidateAllVotes;
    }

    public String getCandidateCommentVotes() {
        return CandidateCommentVotes;
    }

    public void setCandidateCommentVotes(String candidateCommentVotes) {
        CandidateCommentVotes = candidateCommentVotes;
    }

    public boolean isVoteSubmitted() {
        return IsVoteSubmitted;
    }

    public void setVoteSubmitted(boolean voteSubmitted) {
        IsVoteSubmitted = voteSubmitted;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "CandidateRefID='" + CandidateRefID + '\'' +
                ", CandidateImage='" + CandidateImage + '\'' +
                ", CandidateName='" + CandidateName + '\'' +
                ", CandidateStatus='" + CandidateStatus + '\'' +
                ", CandidateSubject='" + CandidateSubject + '\'' +
                ", CandidateEmailID='" + CandidateEmailID + '\'' +
                ", CandidateCategoryName='" + CandidateCategoryName + '\'' +
                ", CandidateRatingStars='" + CandidateRatingStars + '\'' +
                ", CandidateLikeVotes='" + CandidateLikeVotes + '\'' +
                ", CandidateNeutralVotes='" + CandidateNeutralVotes + '\'' +
                ", CandidateDislikeVotes='" + CandidateDislikeVotes + '\'' +
                ", CandidateAllVotes='" + CandidateAllVotes + '\'' +
                ", CandidateCommentVotes='" + CandidateCommentVotes + '\'' +
                ", IsVoteSubmitted=" + IsVoteSubmitted +
                '}';
    }
}
