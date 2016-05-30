package com.taras.secondapplication.activities.profile;

import com.taras.secondapplication.App;

public class ProfileInteractorImpl implements ProfileInteractor {

    @Override
    public void getAllDataForProfile(OnGetDataListener listener) {
        listener.onSuccess(App.sDataManager.getFacebookModel());
    }
}
