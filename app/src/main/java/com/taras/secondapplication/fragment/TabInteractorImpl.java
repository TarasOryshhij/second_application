package com.taras.secondapplication.fragment;

import android.util.Log;

import com.taras.secondapplication.App;
import com.taras.secondapplication.api.NetworkAPI;
import com.taras.secondapplication.models.CardModel;
import com.taras.secondapplication.utils.RetrofitUtils;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class TabInteractorImpl implements TabInteractor {

    private static final String TASK_STAUS_IN_WORK = "0,9,5,7,8";
    private static final String TASK_STATUS_DONE = "10,6";
    private static final String TASK_STATUS_PENDING = "1,3,4";
    private static final int LIMIT = 10;

    @Override
    public void makeRequest(final int page, int taskStatus, final OnLoadDataListener listener) {
        NetworkAPI exploreListService = RetrofitUtils.createApi(NetworkAPI.class);

        String state = "";
        switch (taskStatus) {
            case 0:
                state = TASK_STAUS_IN_WORK;
                break;
            case 1:
                state = TASK_STATUS_DONE;
                break;
            case 2:
                state = TASK_STATUS_PENDING;
                break;
        }

        int offset = (page - 1) * LIMIT;

        exploreListService.getCardList(state, LIMIT, offset)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .onErrorReturn(new Func1<Throwable, List<CardModel>>() {
                    @Override
                    public List<CardModel> call(Throwable throwable) {
                        Log.d("tag", "onErrorReturn");
                        return null;
                    }
                })
                .doOnNext(new Action1<List<CardModel>>() {
                    @Override
                    public void call(List<CardModel> cardModels) {
                        App.sDataManager.saveCardModel(cardModels, Realm.getDefaultInstance());
                    }
                }) // cache
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CardModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError();
                    }

                    @Override
                    public void onNext(List<CardModel> cardModels) {
                        if (page == 1) {
                            listener.onRefresh();
                        } else {
                            if (cardModels.size() < LIMIT) {
                                listener.onLoadMore(false);
                            } else {
                                listener.onLoadMore(true);
                            }

                        }
                    }
                });
    }
}
