package com.xavier.daigoufyp.controller.page.product;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.utils.Constants;
import com.xavier.daigoufyp.view.CustomSpinnerView;
import com.xavier.daigoufyp.view.TimeChooserView;

import roboguice.inject.ContentView;
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

    int quantity = 1;


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

            }

            @Override
            public void onDayChange(int day) {

            }

            @Override
            public void onHourChange(int hour) {

            }
        });
        quantitySpinnerView.bindModel(1, 9999, true);
        quantitySpinnerView.setOnQuantityChangeListener(new CustomSpinnerView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChange(int value) {

            }
        });
        priceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
