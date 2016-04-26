package com.taras.secondapplication.managers;

import android.content.Context;

import com.taras.secondapplication.R;
import com.taras.secondapplication.models.CardModel;

import java.util.ArrayList;

public class DataManager {

    private Context mContext;

    public DataManager(Context context) {
        mContext = context;
    }

    /**
     * Make dataList for item ListView and RecyclerView.
     */
    public ArrayList<CardModel> getDataList(){

        CardModel cardModel1 = new CardModel(0, mContext.getString(R.string.dtm_title1), mContext.getString(R.string.dtm_street1), mContext.getString(R.string.dtm_date1), 10);
        CardModel cardModel2 = new CardModel(1, mContext.getString(R.string.dtm_title2), mContext.getString(R.string.dtm_street2), mContext.getString(R.string.dtm_date2), 10);
        CardModel cardModel3 = new CardModel(1, mContext.getString(R.string.dtm_title3), mContext.getString(R.string.dtm_street3), mContext.getString(R.string.dtm_date3), 10);
        CardModel cardModel4 = new CardModel(1, mContext.getString(R.string.dtm_title4), mContext.getString(R.string.dtm_street4), mContext.getString(R.string.dtm_date4), 9);
        CardModel cardModel5 = new CardModel(1, mContext.getString(R.string.dtm_title5), mContext.getString(R.string.dtm_street5), mContext.getString(R.string.dtm_date5), 6);
        CardModel cardModel6 = new CardModel(1, mContext.getString(R.string.dtm_title6), mContext.getString(R.string.dtm_street6), mContext.getString(R.string.dtm_date6), 6);
        CardModel cardModel7 = new CardModel(1, mContext.getString(R.string.dtm_title7), mContext.getString(R.string.dtm_street7), mContext.getString(R.string.dtm_date7), 6);
        CardModel cardModel8 = new CardModel(1, mContext.getString(R.string.dtm_title8), mContext.getString(R.string.dtm_street8), mContext.getString(R.string.dtm_date8), 6);
        CardModel cardModel9 = new CardModel(1, mContext.getString(R.string.dtm_title9), mContext.getString(R.string.dtm_street9), mContext.getString(R.string.dtm_date9), 6);
        CardModel cardModel10 = new CardModel(1, mContext.getString(R.string.dtm_title10), mContext.getString(R.string.dtm_street10), mContext.getString(R.string.dtm_date10), 6);

        ArrayList<CardModel> arrCardModel = new ArrayList<>(); //[Comment] Use abstraction instead of realization
        arrCardModel.add(cardModel1);
        arrCardModel.add(cardModel2);
        arrCardModel.add(cardModel3);
        arrCardModel.add(cardModel4);
        arrCardModel.add(cardModel5);
        arrCardModel.add(cardModel6);
        arrCardModel.add(cardModel7);
        arrCardModel.add(cardModel8);
        arrCardModel.add(cardModel9);
        arrCardModel.add(cardModel10);

        return arrCardModel;
    }
}
