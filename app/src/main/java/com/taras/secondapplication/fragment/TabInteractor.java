package com.taras.secondapplication.fragment;

public interface TabInteractor {
    void makeRequest(int page, int taskStatus, OnLoadDataListener listener);

    interface OnLoadDataListener{
        void onRefresh();
        void onLoadMore(boolean isLoadList);
        void onError();
    }
}
