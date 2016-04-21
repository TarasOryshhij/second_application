package com.taras.secondapplication.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;
import com.taras.secondapplication.fragments.TabRecyclerFragment;
import com.taras.secondapplication.fragments.TabListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private Context mContext;

    public PagerAdapter(FragmentManager fm, int numOfTabs, Context ctx) {
        super(fm);
        mNumOfTabs = numOfTabs;
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();

        TabRecyclerFragment tab1and2 = new TabRecyclerFragment();
        switch (position) {
            case 0:
                bundle.putString(Const.IntentConstant.STATUS, mContext.getString(R.string.tab_in_work));
                tab1and2.setArguments(bundle);
                return tab1and2;
            case 1:
                bundle.putString(Const.IntentConstant.STATUS, mContext.getString(R.string.tab_complete));
                tab1and2.setArguments(bundle);
                return tab1and2;
            case 2:
                TabListFragment tabFragment3 = new TabListFragment();
                bundle.putString(Const.IntentConstant.STATUS, mContext.getString(R.string.tab_expects));
                tabFragment3.setArguments(bundle);
                return tabFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}