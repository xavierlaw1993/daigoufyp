package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Shop;

import java.util.List;

/**
 * Created by xavier on 21/4/16.
 */
public class ShopListResponse extends AbsResponse{
    @Expose
    public List<Shop> shops;
}
