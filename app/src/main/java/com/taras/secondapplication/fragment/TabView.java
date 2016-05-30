package com.taras.secondapplication.fragment;

public interface TabView {
    void getPositionFragmentFromBundle();

    void getCardList();

    void initViews();

    void initListeners();

    void initPresenter();

    void refresh();

    void loadMore(boolean isLoadList);

    void onError();

    interface OnPageSelectedListener {
        void onShow();
    }
}
