package com.xavier.daigoufyp.controller.page.detail;

import android.os.Bundle;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;

@ContentView(R.layout.activity_product_detail)
public class ProductDetailActivity extends AbsSpiceActivity {

    @InjectExtra(value = "product_id", optional = false)
    int product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
