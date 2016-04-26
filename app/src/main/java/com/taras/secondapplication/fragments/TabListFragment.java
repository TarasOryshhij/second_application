package com.taras.secondapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.taras.secondapplication.App;
import com.taras.secondapplication.BusStation;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.ListAdapter;
import com.taras.secondapplication.events.VisibleFabEvent;

public class TabListFragment extends Fragment {

    private View mRootView;
    private int mLastFirstVisibleItem = 0; //[Comment] 0 is default for int
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BusStation.getBus().register(this);
        mRootView = inflater.inflate(R.layout.fragment_tab_list, container, false);

        makeListView();
        hideFabAndToolbarOnScroll();

        return mRootView;
    }

    /**
     * Make ListView and ListAdapter.
     */
    private void makeListView() {
        mListView = (ListView) mRootView.findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter(getContext(), App.sDataManager.getDataList(), getArguments().getString(Const.IntentConstant.STATUS));
        mListView.setAdapter(listAdapter);
    }

    /**
     * Set OnScrollListener for ListView and make Event while user scroll ListView.
     */
    private void hideFabAndToolbarOnScroll() {
        ViewCompat.setNestedScrollingEnabled(mListView, true);

        //Hide Fab and Tollbar (if Api < 21)  on scroll
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getId() == mListView.getId()) {

                    if (firstVisibleItem > mLastFirstVisibleItem) {
                        BusStation.getBus().post(new VisibleFabEvent(false));
                    } else if (firstVisibleItem < mLastFirstVisibleItem) {
                        BusStation.getBus().post(new VisibleFabEvent(true));
                    }

                    mLastFirstVisibleItem = firstVisibleItem;
                }
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BusStation.getBus().unregister(this);
    }
}
