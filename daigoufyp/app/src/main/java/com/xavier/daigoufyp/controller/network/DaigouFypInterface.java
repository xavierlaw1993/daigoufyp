package com.xavier.daigoufyp.controller.network;

import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.model.response.ProductResponse;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by xavier on 21/3/16.
 */
public interface DaigouFypInterface {
    @GET("/get_all_products")
    ProductListResponse getAllProducts();

    @GET("/get_product_detail")
    ProductResponse getProductDetail(@Query("product_id") int product_id);

    @POST("/create_product")
    AbsResponse createProduct(@Query("user_id") String user_id,
                              @Query("product_name") String product_name,
                              @Query("product_description") String product_description,
                              @Query("product_create_time") String product_create_time,
                              @Query("product_end_time") String product_end_time,
                              @Query("product_price") String product_price,
                              @Query("category_name") String category_name,
                              @Query("country_name") String country_name,
                              @Query("quantity") String quantity,
                              @Query("remark") String remark);

    @GET("/get_user_orders")
    ProductListResponse getUserOrders(@Query("user_id") String user_id,
                                      @Query("type") String type);

    @GET("/get_order_detail")
    ProductResponse getOrderDetail(@Query("order_id") int order_id);

    @POST("/create_order")
    AbsResponse createOrder(@Query("seller_id") String seller_id,
                            @Query("buyer_id") String buyer_id,
                            @Query("product_ids") List<Integer> product_ids,
                            @Query("order_create_time") String order_create_time,
                            @Query("order_status") String order_status,
                            @Query("order_total_price") String order_total_price,
                            @Query("delivery_address") String delivery_address);
}
