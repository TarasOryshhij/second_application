package com.taras.secondapplication.activities.profile;

import com.taras.secondapplication.models.FacebookModel;

public class ProfilePresenterImpl implements ProfilePresenter, ProfileInteractor.OnGetDataListener{

    private ProfileInteractorImpl mInteractor;
    private ProfileView mView;

    public ProfilePresenterImpl(ProfileView mView) {
        this.mView = mView;
        mInteractor = new ProfileInteractorImpl();
    }

    @Override
    public void loadData() {
        mInteractor.getAllDataForProfile(this);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onSuccess(FacebookModel facebookModel) {
        mView.setDataOnView(facebookModel);
    }
}
