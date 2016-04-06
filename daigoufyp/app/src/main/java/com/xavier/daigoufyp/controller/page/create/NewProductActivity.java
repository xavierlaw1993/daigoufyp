package com.xavier.daigoufyp.controller.page.create;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceActivity;

public class NewProductActivity extends AbsSpiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
    }
}
