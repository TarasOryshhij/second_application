package com.taras.secondapplication.models;

import android.graphics.Bitmap;

public class ProfileModel {

    byte[] avatar;
    String idFacebook;
    String email;
    String gender;

    public ProfileModel(byte[] avatar, String idFacebook, String email, String gender) {
        this.avatar = avatar;
        this.idFacebook = idFacebook;
        this.email = email;
        this.gender = gender;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
