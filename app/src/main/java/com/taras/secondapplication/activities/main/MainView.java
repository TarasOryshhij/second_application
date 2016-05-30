package com.taras.secondapplication.activities.main;

import java.util.List;

public interface MainView {

    void initViews();

    void initPresenter();

    void loadSectionTabs();

    void initFabToolbarNavigation();

    void makeTabAndViewPager();

    void initPagerAdapter(List<String> listTitleTabs);

    void initFbCallbackManager();

    void startProfileActivity();
}
