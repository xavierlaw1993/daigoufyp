package com.xavier.daigoufyp.utils;

import com.xavier.daigoufyp.controller.network.DaigouFypInterface;
import com.xavier.daigoufyp.controller.network.DummyInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<String> getCategoryList() {
        String[] array = new String[]{
                "Accessories",
                "Women's Apparel & Shoes",
                "Men's Apparel & Shoes",
                "Baby/Children Products",
                "Cosmetics/Skin Care Products",
                "Sport Related Goods",
                "Electronics/Gadgets",
                "Healthcare/Food",
                "Souvenirs/Interesting Things",
                "Others"};
        return Arrays.asList(array);
    }

    public static List<String> getCountryList() {
        String[] array = new String[]{
                "Japan",
                "Taiwan",
                "Europe",
                "S.E.Asia",
                "Australia/New Zealand",
                "Europle",
                "North America",
                "Others"};
        return Arrays.asList(array);
    }
}
