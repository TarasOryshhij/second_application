package com.taras.secondapplication.activities.profile;

import com.taras.secondapplication.models.FacebookModel;

public interface ProfileInteractor {

    interface OnGetDataListener{
        void onSuccess(FacebookModel facebookModel);
    }

    void getAllDataForProfile(OnGetDataListener listener);
}
