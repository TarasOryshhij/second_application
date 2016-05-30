package com.taras.secondapplication.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.fragment.TabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        TabFragment tabRecyclerFragment = new TabFragment();

        switch (position) {
            case 0:
                bundle.putInt(Const.IntentConstant.STATUS, position);
                tabRecyclerFragment.setArguments(bundle);
                return tabRecyclerFragment;
            case 1:
                bundle.putInt(Const.IntentConstant.STATUS, position);
                tabRecyclerFragment.setArguments(bundle);
                return tabRecyclerFragment;
            case 2:
                bundle.putInt(Const.IntentConstant.STATUS, position);
                tabRecyclerFragment.setArguments(bundle);
                return tabRecyclerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}