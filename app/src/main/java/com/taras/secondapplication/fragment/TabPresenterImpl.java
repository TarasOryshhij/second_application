package com.taras.secondapplication.fragment;

public class TabPresenterImpl implements TabPresenter, TabInteractor.OnLoadDataListener{

    private TabView mTabView;
    private TabInteractorImpl mTabInteractor;

    public TabPresenterImpl(TabView tabView) {
        mTabView = tabView;
        mTabInteractor = new TabInteractorImpl();
    }

    @Override
    public void loadData(int page, int taskStatus) {
        mTabInteractor.makeRequest(page, taskStatus, this);
    }

    @Override
    public void onDestroy() {
        mTabView = null;
    }

    @Override
    public void onRefresh() {
        mTabView.refresh();
    }

    @Override
    public void onLoadMore(boolean isLoadList) {
        mTabView.loadMore(isLoadList);
    }

    @Override
    public void onError() {
        mTabView.onError();
    }
}
