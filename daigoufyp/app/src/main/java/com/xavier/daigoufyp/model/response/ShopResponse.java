package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Shop;

/**
 * Created by xavier on 21/4/16.
 */
public class ShopResponse extends AbsResponse {
    @Expose
    public Shop shop;
}
