package com.taras.secondapplication;

import android.app.Application;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.taras.secondapplication.managers.DataManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    private static App mInstance;
    public static DataManager sDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        initRealm();
        sDataManager = new DataManager();
    }

    public static App getInstance() {
        return mInstance;
    }

    private void initRealm() {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build());
    }
}
