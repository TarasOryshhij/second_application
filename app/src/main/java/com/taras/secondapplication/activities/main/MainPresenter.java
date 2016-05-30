package com.taras.secondapplication.activities.main;

import com.facebook.login.LoginResult;

public interface MainPresenter {

    void loadSectionsTabs();

    void writeFbToDatabase(LoginResult loginResult);

    void onDestroy();
}
