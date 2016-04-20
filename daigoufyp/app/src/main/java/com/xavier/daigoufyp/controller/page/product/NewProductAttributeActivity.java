package com.xavier.daigoufyp.controller.page.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.CreateProductRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.controller.page.home.MainActivity;
import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.utils.Constants;
import com.xavier.daigoufyp.utils.Utils;
import com.xavier.daigoufyp.view.CustomSpinnerView;
import com.xavier.daigoufyp.view.TimeChooserView;

import retrofit.http.POST;
import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 18/4/16.
 */
@ContentView(R.layout.activity_new_product_attribute)
public class NewProductAttributeActivity extends AbsSpiceBackActivity {
    @InjectView(R.id.categorySpinner)
    Spinner categorySpinner;

    @InjectView(R.id.countrySpinner)
    Spinner countrySpinner;

    @InjectView(R.id.quantitySpinnerView)
    CustomSpinnerView quantitySpinnerView;

    @InjectView(R.id.priceEditText)
    EditText priceEditText;

    @InjectView(R.id.timeChooserView)
    TimeChooserView timeChooserView;

    @InjectView(R.id.createProductButton)
    Button createProductButton;

    @InjectView(R.id.progressbar)
    ProgressBar progressBar;

    @InjectExtra(value = "PRODUCT_NAME", optional = false)
    String productName;

    @InjectExtra(value = "PRODUCT_DESCRIPTION", optional = false)
    String productDescription;

    @InjectExtra(value = "PRODUCT_REMARK", optional = true)
    String productRemark = "";

    int quantity, price;
    long weekMillis, dayMillis, hourMillis;
    long duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        priceEditText.setText("0");
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_item, Constants.getCategoryList());
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(this, R.layout.view_spinner_item, Constants.getCountryList());
        categorySpinner.setAdapter(categoriesAdapter);
        countrySpinner.setAdapter(countriesAdapter);

        timeChooserView.setOnTimeChangeListener(new TimeChooserView.OnTimeChangeListener() {
            @Override
            public void onWeekChange(int week) {
                weekMillis = week * 7 * 24 * 60 * 60 * 1000;
            }

            @Override
            public void onDayChange(int day) {
                dayMillis = day * 24 * 60 * 60 * 1000;
            }

            @Override
            public void onHourChange(int hour) {
                hourMillis = hour * 60 * 60 * 1000;
            }
        });
        quantitySpinnerView.bindModel(1, 9999, true);
        quantitySpinnerView.setOnQuantityChangeListener(new CustomSpinnerView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChange(int value) {
                quantity = value;
            }
        });
        priceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (TextUtils.isEmpty(s.toString()))
                        priceEditText.setText("0");
                    price = Integer.parseInt(s.toString());
                } catch (Exception ex) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duration = weekMillis + dayMillis + hourMillis;
                if (quantity <= 0)
                    Utils.showFailureSnackbar(findViewById(android.R.id.content),
                            "The quantity of your product cannot be zero");
                else if (price <= 0)
                    Utils.showFailureSnackbar(findViewById(android.R.id.content),
                            "The price of your product cannot be zero");
                else if (duration <= 0)
                    Utils.showFailureSnackbar(findViewById(android.R.id.content),
                            "The duration of the product you sell must be at least one hour");
                else
                    requestCreateProductAPI();
            }
        });
    }

    private void requestCreateProductAPI() {
        progressBar.setVisibility(View.VISIBLE);
        getSpiceManger().execute(
                new CreateProductRequest(this, productName, productDescription,
                        categorySpinner.getSelectedItem().toString(), countrySpinner.getSelectedItem().toString(),
                        productRemark, Utils.getCurrentTimeMilliSeconds(), Utils.getCurrentTimeMilliSeconds() + duration,
                        Integer.parseInt(priceEditText.getEditableText().toString()), quantity),
                new CreateProductRequestListener());
    }

    private class CreateProductRequestListener extends AbsRequestListener<AbsResponse> {

        @Override
        public void onSuccess(AbsResponse absResponse) {
            progressBar.setVisibility(View.GONE);
            Intent i = new Intent(NewProductAttributeActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

        @Override
        public void onFailure(String msg) {
            progressBar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }
}
