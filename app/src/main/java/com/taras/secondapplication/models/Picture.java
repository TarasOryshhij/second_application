package com.taras.secondapplication.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Picture extends RealmObject {
    //    private RealmList<Data> data;
//
//    public RealmList<Data> getData() {
//        return data;
//    }
//
//    public void setData(RealmList<Data> data) {
//        this.data = data;
//    }
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}