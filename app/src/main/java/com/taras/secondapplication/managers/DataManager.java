package com.taras.secondapplication.managers;

import com.taras.secondapplication.Const;
import com.taras.secondapplication.models.CardModel;
import com.taras.secondapplication.models.FacebookModel;
import org.json.JSONObject;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class DataManager {
    private Realm mRealm;
    private static final String STATE_ID = "state.id";

    public DataManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public CardModel getItemModel(int id) {
        return mRealm.where(CardModel.class).equalTo(Const.IntentConstant.ID, id).findFirst();
    }

    public void saveFbProfile(JSONObject object, String token) {
        mRealm.beginTransaction();
        mRealm.delete(FacebookModel.class);
        FacebookModel model = mRealm.createObjectFromJson(FacebookModel.class, object);
        model.setToken(token);
        mRealm.commitTransaction();
    }

    public FacebookModel getFacebookModel() {
        return mRealm.where(FacebookModel.class).findFirst();
    }

    public void swipeToRefresh(RealmResults<CardModel> mData) {
        mRealm.beginTransaction();
        mData.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    public RealmResults<CardModel> getDataForTab(int mPositionTab) {
        switch (mPositionTab) {
            case 0:
                return mRealm.where(CardModel.class).equalTo(STATE_ID, 0).or().equalTo(STATE_ID, 9)
                        .or().equalTo(STATE_ID, 5).or().equalTo(STATE_ID, 7).or().equalTo(STATE_ID, 8).findAll();
            case 1:
                return mRealm.where(CardModel.class).equalTo(STATE_ID, 6).or().equalTo(STATE_ID, 10).findAll();
            default:
                return mRealm.where(CardModel.class).equalTo(STATE_ID, 1).or().equalTo(STATE_ID, 3)
                        .or().equalTo(STATE_ID, 4).findAll();
        }
    }

    public void saveCardModel(List<CardModel> cardModels, Realm realm){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cardModels);
        realm.commitTransaction();
    }
}
