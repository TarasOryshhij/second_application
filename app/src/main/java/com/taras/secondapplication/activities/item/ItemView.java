package com.taras.secondapplication.activities.item;

import com.taras.secondapplication.models.CardModel;
import com.taras.secondapplication.models.Files;

import io.realm.RealmList;

public interface ItemView {

    void initPresenter();

    void getCardModel();

    void initListeners();

    void setValuesFromIntent(CardModel cardModel);

    void makeStatus(CardModel cardModel);

    void makeToolbarAndArrow(String title);

    void  makeRecyclerView(RealmList<Files> files);
}
