package com.taras.secondapplication.activities.item;

import android.content.Intent;

import com.taras.secondapplication.App;
import com.taras.secondapplication.Const;

public class ItemInteractorImpl implements ItemInteractor{

    @Override
    public void loadCardModel(Intent intent, OnLoadDataListener listener) {
        int id = intent.getIntExtra(Const.IntentConstant.ID, -1);
        listener.onLoadComplete(App.sDataManager.getItemModel(id));
    }

}
