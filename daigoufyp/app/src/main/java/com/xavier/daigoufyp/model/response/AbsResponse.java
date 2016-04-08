package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;

/**
 * Created by xavier on 7/4/16.
 */
public class AbsResponse {

    private static String TAG = "AbsResponse";

    @Expose
    public int status;
    @Expose
    public String msg;

    public boolean isSuccess() {
        return status == 0;
    }

    @Override
    public String toString() {
        return "AbsResponse{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}