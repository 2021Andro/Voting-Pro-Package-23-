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
    private long CandidateLikeVotes;
    private long CandidateNeutralVotes;
    private long CandidateDislikeVotes;
    private long CandidateAllVotes;
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

    public long getCandidateLikeVotes() {
        return CandidateLikeVotes;
    }

    public void setCandidateLikeVotes(long candidateLikeVotes) {
        CandidateLikeVotes = candidateLikeVotes;
    }

    public long getCandidateNeutralVotes() {
        return CandidateNeutralVotes;
    }

    public void setCandidateNeutralVotes(long candidateNeutralVotes) {
        CandidateNeutralVotes = candidateNeutralVotes;
    }

    public long getCandidateDislikeVotes() {
        return CandidateDislikeVotes;
    }

    public void setCandidateDislikeVotes(long candidateDislikeVotes) {
        CandidateDislikeVotes = candidateDislikeVotes;
    }

    public long getCandidateAllVotes() {
        return CandidateAllVotes;
    }

    public void setCandidateAllVotes(long candidateAllVotes) {
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
}
