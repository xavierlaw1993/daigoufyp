package com.xavier.daigoufyp.controller.network;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.xavier.daigoufyp.model.response.AbsResponse;

/**
 * Created by zensis on 8/4/16.
 */
public abstract class AbsRequestListener<T extends AbsResponse> implements RequestListener<T> {
    @Override
    public void onRequestFailure(SpiceException spiceException) {
        onFailure(spiceException.getMessage());
    }

    @Override
    public void onRequestSuccess(T t) {
        if (t.isSuccess()){
            onSuccess(t);
        } else {
            onFailure(t.msg);
        }
    }
    public abstract void onSuccess(T t);
    public abstract void onFailure(String msg);
}
