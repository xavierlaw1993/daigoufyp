package com.xavier.daigoufyp.controller.network;

import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.xavier.daigoufyp.utils.Constants;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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
    protected RestAdapter.Builder createRestAdapterBuilder() {
        RestAdapter.Builder restAdapterBuilder = super.createRestAdapterBuilder()
                .setConverter(new GsonConverter(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
//                        request.addHeader("Content-Type", "application/json");
//                        request.addHeader("Accept-Language", CLPConsumerApplication.getInstance().getAppLanguage());
                    }
                })
//                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.BASIC);
        return restAdapterBuilder;
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
