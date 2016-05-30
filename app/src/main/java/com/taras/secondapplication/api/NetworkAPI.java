package com.taras.secondapplication.api;

import com.taras.secondapplication.models.CardModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkAPI {
    String AMOUNT = "amount";
    String OFFSET = "offset";
    String STATE = "state";

    @GET("/rest/v1/tickets")
    Observable<List<CardModel>> getCardList(@Query(STATE) String state,
                                            @Query(AMOUNT) int limit,
                                            @Query(OFFSET) int offset);
}
