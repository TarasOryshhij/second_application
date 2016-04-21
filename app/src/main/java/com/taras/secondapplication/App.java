package com.taras.secondapplication;

import android.app.Application;

import com.taras.secondapplication.managers.DataManager;

public class App extends Application {

    public static DataManager sDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sDataManager = new DataManager(this);
    }

}
