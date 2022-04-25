package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class Candidate implements Serializable
{


    private String CandidateRefID;
    private String CandidateImage;
    private String CandidateName;
    private String CandidateStatus;
    private String CandidateSubject;
    private String CandidateEmailID;
    private String CandidateCategoryName;
    private String CandidateRatingStars;
    private long LikeVotes;
    private long NeutralVotes;
    private long DislikeVotes;
    private long AllVotes;
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

    public long getLikeVotes() {
        return LikeVotes;
    }

    public void setLikeVotes(long likeVotes) {
        LikeVotes = likeVotes;
    }

    public long getNeutralVotes() {
        return NeutralVotes;
    }

    public void setNeutralVotes(long neutralVotes) {
        NeutralVotes = neutralVotes;
    }

    public long getDislikeVotes() {
        return DislikeVotes;
    }

    public void setDislikeVotes(long dislikeVotes) {
        DislikeVotes = dislikeVotes;
    }

    public long getAllVotes() {
        return AllVotes;
    }

    public void setAllVotes(long allVotes) {
        AllVotes = allVotes;
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
}
