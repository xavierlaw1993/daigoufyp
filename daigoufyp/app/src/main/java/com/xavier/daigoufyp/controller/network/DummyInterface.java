package com.xavier.daigoufyp.controller.network;

import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.model.response.ProductResponse;

import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by zensis on 8/4/16.
 */
public interface DummyInterface {
    @GET("/get_all_products.json")
    ProductListResponse getAllProducts();

    @GET("/get_product_detail.json")
    ProductResponse getProductDetail(@Query("product_id") int product_id);
}
