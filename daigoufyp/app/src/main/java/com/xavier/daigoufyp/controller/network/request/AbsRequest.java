package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.google.inject.Inject;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
import com.xavier.daigoufyp.controller.network.DaigouFypInterface;
import com.xavier.daigoufyp.controller.network.DummyInterface;
import com.xavier.daigoufyp.model.User;
import com.xavier.daigoufyp.model.response.AbsResponse;

import roboguice.RoboGuice;

/**
 * Created by xavier on 7/4/16.
 */
//TODO: need to update
public abstract class AbsRequest<T extends AbsResponse> extends RetrofitSpiceRequest<T, DummyInterface>{//DaigouFypInterface> {
    @Inject
    User user;

    Context context;

    public AbsRequest(Context context, Class<T> clazz) {
        super(clazz, DummyInterface.class);//DaigouFypInterface.class);
        RoboGuice.getInjector(context).injectMembersWithoutViews(this);
        this.context = context;
    }
}
