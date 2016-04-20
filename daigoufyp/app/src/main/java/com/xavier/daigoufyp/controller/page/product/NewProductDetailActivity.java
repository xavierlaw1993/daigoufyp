package com.xavier.daigoufyp.controller.page.product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.utils.Utils;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 18/4/16.
 */
@ContentView(R.layout.activity_new_product_detail)
public class NewProductDetailActivity extends AbsSpiceBackActivity {
    @InjectView(R.id.nextButton)
    Button nextButton;

    @InjectView(R.id.descriptionEditText)
    EditText descriptionEditText;

    @InjectView(R.id.remarkEditText)
    EditText remarkEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(descriptionEditText.getEditableText())) {
                    Intent i = new Intent(NewProductDetailActivity.this, NewProductAttributeActivity.class);
                    if (getIntent().getExtras() != null)
                        i.putExtras(getIntent().getExtras());
                    i.putExtra("PRODUCT_DESCRIPTION", descriptionEditText.getEditableText());
                    if (!TextUtils.isEmpty(remarkEditText.getEditableText()))
                        i.putExtra("PRODUCT_REMARK", remarkEditText.getEditableText());
                    startActivity(i);
                } else {
                    Utils.showFailureSnackbar(findViewById(android.R.id.content),
                            "Please enter a description for your product");
                }
            }
        });
    }


}
