package com.example.myapplication23.CostumeClasses;

import java.io.Serializable;

public class User_Info implements Serializable
{
    private String UserID;
    private String UserImage;
    private String UserName;
    private String UserEmailID;
    private String UserPhoneNumber;
    private String UserPinCode;
    private String UserGander;
    private long LikeVotes;
    private long NeutralVotes;
    private long DislikeVotes;
    private long AllVotes;
    private boolean IsVoteSubmitted;

    public User_Info() {
    }

    public User_Info(String userID, String userImage, String userName, String userEmailID, String userPhoneNumber, String userPinCode, String userGander) {
        UserID = userID;
        UserImage = userImage;
        UserName = userName;
        UserEmailID = userEmailID;
        UserPhoneNumber = userPhoneNumber;
        UserPinCode = userPinCode;
        UserGander = userGander;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmailID() {
        return UserEmailID;
    }

    public void setUserEmailID(String userEmailID) {
        UserEmailID = userEmailID;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public String getUserPinCode() {
        return UserPinCode;
    }

    public void setUserPinCode(String userPinCode) {
        UserPinCode = userPinCode;
    }

    public String getUserGander() {
        return UserGander;
    }

    public void setUserGander(String userGander) {
        UserGander = userGander;
    }

    @Override
    public String toString() {
        return "User_Info{" +
                "UserID=" + UserID +
                ", UserImage='" + UserImage + '\'' +
                ", UserName='" + UserName + '\'' +
                ", UserEmailID='" + UserEmailID + '\'' +
                ", UserPhoneNumber='" + UserPhoneNumber + '\'' +
                ", UserPinCode='" + UserPinCode + '\'' +
                ", UserGander='" + UserGander + '\'' +
                '}';
    }
}
