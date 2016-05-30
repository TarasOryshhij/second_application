package com.taras.secondapplication.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class FacebookModel extends RealmObject{

//    private RealmList<Picture> picture;
//
//    private String id;
//
//    private String email;
//
//    private String name;
//
//    private String token;
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public RealmList<Picture> getPicture() {
//        return picture;
//    }
//
//    public void setPicture(RealmList<Picture> picture) {
//        this.picture = picture;
//    }
//
//    public String getId ()
//    {
//        return id;
//    }
//
//    public void setId (String id)
//    {
//        this.id = id;
//    }
//
//    public String getEmail ()
//    {
//        return email;
//    }
//
//    public void setEmail (String email)
//    {
//        this.email = email;
//    }
//
//    public String getName ()
//    {
//        return name;
//    }
//
//    public void setName (String name)
//    {
//        this.name = name;
//    }

    private Picture picture;

    private String id;

    private String email;

    private String name;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Picture getPicture ()
    {
        return picture;
    }

    public void setPicture (Picture picture)
    {
        this.picture = picture;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}