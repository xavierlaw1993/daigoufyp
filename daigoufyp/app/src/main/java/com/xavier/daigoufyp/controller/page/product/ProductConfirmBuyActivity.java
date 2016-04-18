package com.xavier.daigoufyp.controller.page.product;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.view.CustomSpinnerView;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 15/4/16.
 */
@ContentView(R.layout.activity_product_confirm_buy)
public class ProductConfirmBuyActivity extends AbsSpiceBackActivity {
    @InjectView(R.id.quantitySpinnerView)
    CustomSpinnerView quantitySpinnerView;

    @InjectView(R.id.totalPriceTextView)
    TextView totalPriceTextView;

    @InjectExtra(value = "PRODUCT_OBJ", optional = false)
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalPriceTextView.setText(getString(R.string.product__price_unit) + " " + (int) product.product_price);
        quantitySpinnerView.bindModel(1, product.quantity, true);
        quantitySpinnerView.setOnQuantityChangeListener(new CustomSpinnerView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChange(int value) {
                totalPriceTextView.setText(getString(R.string.product__price_unit) + " " + (int) (value * product.product_price));
            }
        });
    }

    public void onConfirmToBuyClick(View v) {
        //TODO create an order
    }
}
