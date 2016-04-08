package com.xavier.daigoufyp.utils;

import com.xavier.daigoufyp.controller.network.DaigouFypInterface;
import com.xavier.daigoufyp.controller.network.DummyInterface;

/**
 * Created by xavier on 21/3/16.
 */
public class Constants {
    public final static String PREF_USER = "PREF_USER";

    public static String getAPIEndpoint() {
        //TODO: need to update
        return "http://xavierfypscope.netai.net/daigou_dummy/";
    }

    public static Class getAPIInterface() {
        //TODO: need to update
//        return DaigouFypInterface.class;
        return DummyInterface.class;
    }
}
