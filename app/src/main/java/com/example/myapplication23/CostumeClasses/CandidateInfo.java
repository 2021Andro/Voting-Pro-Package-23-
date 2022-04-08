package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class CandidateInfo extends Support_Class implements Serializable
{
    private int CandidateID;
    private String CandidateName;
    private String CandidateStatus;
    private String CandidateSubject;
    private String CandidateEmailID;

    public CandidateInfo() {
    }

    public CandidateInfo(int candidateID, String candidateName, String candidateStatus, String candidateSubject, String candidateEmailID) {
        CandidateID = candidateID;
        CandidateName = candidateName;
        CandidateStatus = candidateStatus;
        CandidateSubject = candidateSubject;
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
