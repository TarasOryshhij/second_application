package com.taras.secondapplication.activities.item;

import android.content.Intent;

import com.taras.secondapplication.models.CardModel;

public class ItemPresenterImpl implements ItemPresenter, ItemInteractor.OnLoadDataListener{

    private ItemView mView;
    private ItemInteractorImpl mInteractor;
    private Intent mIntent;

    public ItemPresenterImpl(ItemView mView, Intent intent) {
        this.mView = mView;
        mInteractor = new ItemInteractorImpl();
        mIntent = intent;
    }

    @Override
    public void getCardModel() {
        mInteractor.loadCardModel(mIntent, this);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onLoadComplete(CardModel cardModel) {
        mView.setValuesFromIntent(cardModel);
        mView.makeStatus(cardModel);
        mView.makeToolbarAndArrow(cardModel.getTicket_id());
        mView.makeRecyclerView(cardModel.getFiles());
    }
}
