package com.taras.secondapplication.activities.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.taras.secondapplication.App;
import com.taras.secondapplication.Const;
import com.taras.secondapplication.R;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainInteractorImpl implements MainInteractor {

    /**
     * Get list with tab name.
     */
    @Override
    public void getListTabName(OnGetTabTitleListener listener) {
        try {
            List<String> listTitleTabs = Arrays.asList(App.getInstance().getResources().getStringArray(R.array.main_tabs_title));

            listener.onSucces(listTitleTabs);
        } catch (Resources.NotFoundException notFoundExcepetion) {
            Log.d("tag", "Error Getting The Array", notFoundExcepetion);
        }
    }

    /**
     * Create FacebookModel for current activity.
     */
    @Override
    public void writeFbToDatabase(final LoginResult loginResult, final OnWriteDataListener listener) {
        Profile.getCurrentProfile();
        Bundle params = new Bundle();
        params.putString(GraphRequest.FIELDS_PARAM, Const.FbConstant.FACEBOOK_VALUE);
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                App.sDataManager.saveFbProfile(object, loginResult.getAccessToken().getToken());
                listener.onWriteComplete();
            }
        });
        request.setParameters(params);
        request.executeAsync();
    }
}
