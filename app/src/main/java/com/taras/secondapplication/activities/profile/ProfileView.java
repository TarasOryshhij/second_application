package com.taras.secondapplication.activities.profile;

import com.taras.secondapplication.models.FacebookModel;

public interface ProfileView {

    void initPresenter();

    void makeToolbarAndArrow();

    void loadDataFromDb();

    void setDataOnView(FacebookModel facebookModel);
}
