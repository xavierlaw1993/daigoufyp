package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Product;

import java.util.List;

/**
 * Created by zensis on 7/4/16.
 */
public class ProductListResponse extends AbsResponse {
    @Expose
    public List<Product> products;

}
