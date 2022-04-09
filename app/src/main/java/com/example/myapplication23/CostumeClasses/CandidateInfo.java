package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class CandidateInfo extends Support_Class implements Serializable
{
    private int CandidateID;
    private String CandidateImage;
    private String CandidateName;
    private String CandidateStatus;
    private String CandidateSubject;
    private String CandidateEmailID;

    public CandidateInfo() {
    }

    public CandidateInfo(int candidateID, String candidateImage, String candidateName, String candidateStatus, String candidateSubject, String candidateEmailID) {
        CandidateID = candidateID;
        CandidateImage = candidateImage;
        CandidateName = candidateName;
        CandidateStatus = candidateStatus;
        CandidateSubject = candidateSubject;
        CandidateEmailID = candidateEmailID;
    }

    public String getCandidateImage() {
        return CandidateImage;
    }

    public void setCandidateImage(String candidateImage) {
        CandidateImage = candidateImage;
    }

    public int getCandidateID() {
        return CandidateID;
    }

    public void setCandidateID(int candidateID) {
        CandidateID = candidateID;
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

    @Override
    public String toString() {
        return "CandidateInfo{" +
                "CandidateID=" + CandidateID +
                ", CandidateName='" + CandidateName + '\'' +
                ", CandidateStatus='" + CandidateStatus + '\'' +
                ", CandidateSubject='" + CandidateSubject + '\'' +
                ", CandidateEmailID='" + CandidateEmailID + '\'' +
                '}';
    }
}
