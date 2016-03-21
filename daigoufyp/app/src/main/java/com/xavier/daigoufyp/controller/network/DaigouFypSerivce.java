package com.xavier.daigoufyp.controller.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.xavier.daigoufyp.utils.Constants;

import retrofit.RestAdapter;

/**
 * Created by xavier on 21/3/16.
 */
public class DaigouFypSerivce extends RetrofitGsonSpiceService {

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Constants.getAPIInterface());
    }

    @Override
    protected String getServerUrl() {
        return Constants.getAPIEndpoint();
    }

    @Override
    public int getThreadCount() {
        return 3;
    }
}
