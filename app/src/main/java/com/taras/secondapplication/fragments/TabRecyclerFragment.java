package com.taras.secondapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taras.secondapplication.App;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.adapters.RecyclerAdapter;

public class TabRecyclerFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_tab_recycler, container, false);

        makeRecyclerView();

        return mRootView;
    }

    /**
     * Make RecycleView and RecyclerAdapter.
     */
    private void makeRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view1);

        LinearLayoutManager llm = new LinearLayoutManager(getContext()); //[Comment] Llm bad name
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(App.sDataManager.getDataList(), getArguments().getString(Const.IntentConstant.STATUS));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
