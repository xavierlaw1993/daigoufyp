package com.xavier.daigoufyp.controller.network;

import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.model.response.ProductResponse;

import retrofit.http.Field;
import retrofit.http.GET;

/**
 * Created by xavier on 21/3/16.
 */
public interface DaigouFypInterface {
    @GET("/get_all_products")
    ProductListResponse getAllProducts();

    @GET("/get_product_detail")
    ProductResponse getProductDetail(@Field("product_id") int product_id);

}
