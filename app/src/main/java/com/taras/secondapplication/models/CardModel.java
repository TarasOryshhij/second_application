package com.taras.secondapplication.models;

public class CardModel {

    private int countLike;
    private String title;
    private String street;
    private String date;
    private int day;

    public CardModel(int countLike, String title, String street, String date, int day) {
        this.countLike = countLike;
        this.title = title;
        this.street = street;
        this.date = date;
        this.day = day;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
