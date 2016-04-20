package com.xavier.daigoufyp.controller.network;

import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.model.response.ProductResponse;
import com.xavier.daigoufyp.model.response.UserResponse;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by zensis on 8/4/16.
 */
public interface DummyInterface {
    @GET("/login.php")
    UserResponse login(@Query("user_id") String user_id,
                       @Query("user_name") String user_name,
                       @Query("user_email") String user_email,
                       @Query("user_profile_pic") String user_profile_pic);

    @GET("/get_all_products.json")
    ProductListResponse getAllProducts();

    @GET("/get_product_detail.json")
    ProductResponse getProductDetail(@Query("product_id") int product_id);

    @GET("/create_product")
    AbsResponse createProduct(@Query("user_id") String user_id,
                              @Query("product_name") String product_name,
                              @Query("product_description") String product_description,
                              @Query("product_create_time") long product_create_time,
                              @Query("product_end_time") long product_end_time,
                              @Query("product_price") int product_price,
                              @Query("category_name") String category_name,
                              @Query("country_name") String country_name,
                              @Query("quantity") int quantity,
                              @Query("remark") String remark);

    @GET("/get_user_orders")
    ProductListResponse getUserOrders(@Query("user_id") String user_id,
                                      @Query("type") String type);

    @GET("/get_order_detail")
    ProductResponse getOrderDetail(@Query("order_id") int order_id);

    @GET("/create_order")
    AbsResponse createOrder(@Query("seller_id") String seller_id,
                            @Query("buyer_id") String buyer_id,
                            @Query("product_ids") List<Integer> product_ids,
                            @Query("order_create_time") String order_create_time,
                            @Query("order_status") String order_status,
                            @Query("order_total_price") String order_total_price,
                            @Query("delivery_address") String delivery_address);


}
