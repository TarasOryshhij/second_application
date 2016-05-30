package com.taras.secondapplication.activities.main;

import com.facebook.login.LoginResult;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnGetTabTitleListener,
        MainInteractor.OnWriteDataListener{

    private MainView mMainView;
    private MainInteractorImpl mMainInteractorImpl;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mMainInteractorImpl = new MainInteractorImpl();
    }

    @Override
    public void loadSectionsTabs() {
        mMainInteractorImpl.getListTabName(this);
    }

    @Override
    public void writeFbToDatabase(LoginResult loginResult) {
        mMainInteractorImpl.writeFbToDatabase(loginResult, this);
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }

    @Override
    public void onSucces(List<String> listTitleTabs) {
        mMainView.initPagerAdapter(listTitleTabs);
    }

    @Override
    public void onWriteComplete() {
        mMainView.startProfileActivity();
    }
}
