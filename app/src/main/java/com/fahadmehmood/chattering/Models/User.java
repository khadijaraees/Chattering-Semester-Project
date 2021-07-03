package com.fahadmehmood.chattering.Models;

public class User {
    private String Uid,name,phoneNumber,profileImage;

    public User() {//Firebase sath jab b deal kar rhy ho to empty constructor hona zroori hai so


    }

    public User(String uid, String name, String phoneNumber, String profileImage) {
        Uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public String getUid() {
        return Uid;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}

