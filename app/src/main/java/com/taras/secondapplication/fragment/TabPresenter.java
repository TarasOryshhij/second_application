package com.taras.secondapplication.fragment;

public interface TabPresenter {
    void loadData(int page, int taskStatus);
    void onDestroy();
}
