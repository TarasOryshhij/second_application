package com.taras.secondapplication.activities.item;

import android.content.Intent;

import com.taras.secondapplication.models.CardModel;

public interface ItemInteractor {

    interface OnLoadDataListener {
        void onLoadComplete(CardModel cardModel);
    }

    void loadCardModel(Intent intent, OnLoadDataListener listener);
}
