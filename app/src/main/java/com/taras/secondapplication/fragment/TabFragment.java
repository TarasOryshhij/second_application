package com.taras.secondapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.taras.secondapplication.App;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.TabRecyclerAdapter;
import com.taras.secondapplication.models.CardModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class TabFragment extends Fragment implements TabView, TabView.OnPageSelectedListener {

    @BindView(R.id.recycler_view_tab)
    RecyclerView mRecViewTab;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefresh;
    private View mRootView;
    private TabRecyclerAdapter mTabRecAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = true;
    private int mPastItems, mVisibleItemsCount, mTotalItemsCount;
    private int mPositionTab;
    private int mPage;
    private RealmResults<CardModel> mData;
    private TabPresenterImpl mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tab_recycler, container, false);
        getPositionFragmentFromBundle();

        if (mPositionTab == 0) {
            initViews();
            initPresenter();
            initListeners();
            getCardList();
        }

        return mRootView;
    }

    @Override
    public void getPositionFragmentFromBundle() {
        mPositionTab = getArguments().getInt(Const.IntentConstant.STATUS);
    }

    @Override
    public void getCardList() {
        getDataForCurrentTab();

        assert mData != null;
        if (mData.size() == 0) {
            mPage = 1;
            mPresenter.loadData(mPage, mPositionTab);
        } else {
            mTabRecAdapter = new TabRecyclerAdapter(mData);
            mRecViewTab.setAdapter(mTabRecAdapter);
        }
    }

    @Override
    public void initViews() {
        ButterKnife.bind(this, mRootView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecViewTab.setLayoutManager(mLayoutManager);
    }

    @Override
    public void initListeners() {

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isLoading = true;
                App.sDataManager.swipeToRefresh(mData);
                mPage = 1;
                mPresenter.loadData(mPage, mPositionTab);
                mSwipeRefresh.setRefreshing(false);
            }
        });

        //check when scroll down
        mRecViewTab.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mVisibleItemsCount = mLayoutManager.getChildCount();
                mTotalItemsCount = mLayoutManager.getItemCount();
                mPastItems = mLayoutManager.findLastVisibleItemPosition();

                if (dy > 0) {
                    mVisibleItemsCount = mLayoutManager.getChildCount();
                    mTotalItemsCount = mLayoutManager.getItemCount();
                    mPastItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (isLoading) {
                        if ((mVisibleItemsCount + mPastItems) >= mTotalItemsCount) {
                            isLoading = false;
                            mPage++;
                            mPresenter.loadData(mPage, mPositionTab);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new TabPresenterImpl(this);
    }

    @Override
    public void refresh() {
        getDataForCurrentTab();

        mTabRecAdapter = new TabRecyclerAdapter(mData);
        mRecViewTab.setAdapter(mTabRecAdapter);
    }

    @Override
    public void loadMore(boolean isLoadList) {
        isLoading = isLoadList;

        getDataForCurrentTab();
        mTabRecAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), R.string.tst_no_data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShow() {
        initViews();
        initPresenter();
        initListeners();
        getCardList();
    }

    public void getDataForCurrentTab() {
        mData = App.sDataManager.getDataForTab(mPositionTab);
    }
}
