package com.xavier.daigoufyp.controller.page.product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 18/4/16.
 */
@ContentView(R.layout.activity_new_product_detail)
public class NewProductDetailActivity extends AbsSpiceBackActivity {
    @InjectView(R.id.nextButton)
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
