package com.xavier.daigoufyp.utils;

import com.xavier.daigoufyp.controller.network.DaigouFypInterface;

/**
 * Created by xavier on 21/3/16.
 */
public class Constants {
    public final static String PREF_USER = "PREF_USER";

    public static String getAPIEndpoint() {
        return "http://";
    }

    public static Class getAPIInterface() {
        return DaigouFypInterface.class;
    }
}
