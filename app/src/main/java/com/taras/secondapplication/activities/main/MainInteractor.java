package com.taras.secondapplication.activities.main;

import com.facebook.login.LoginResult;

import java.util.List;

public interface MainInteractor {
    interface OnGetTabTitleListener{
        void onSucces(List<String> listTitleTabs);
    }
    void getListTabName(OnGetTabTitleListener listener);

    interface OnWriteDataListener{
        void onWriteComplete();
    }
    void writeFbToDatabase(LoginResult loginResult, OnWriteDataListener listener);
}
