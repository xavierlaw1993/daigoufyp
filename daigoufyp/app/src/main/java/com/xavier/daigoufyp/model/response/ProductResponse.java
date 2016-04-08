package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.Product;

/**
 * Created by zensis on 7/4/16.
 */
public class ProductResponse extends AbsResponse{
    @Expose
    public Product product;
}
