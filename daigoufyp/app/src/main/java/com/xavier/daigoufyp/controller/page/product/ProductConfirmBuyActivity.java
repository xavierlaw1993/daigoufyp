package com.xavier.daigoufyp.controller.page.product;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.AbsRequest;
import com.xavier.daigoufyp.controller.network.request.CreateOrderRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.controller.page.home.MainActivity;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.User;
import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.utils.Utils;
import com.xavier.daigoufyp.view.CustomSpinnerView;
import com.xavier.daigoufyp.view.IntentActionDialog;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

/**
 * Created by zensis on 15/4/16.
 */
@ContentView(R.layout.activity_product_confirm_buy)
public class ProductConfirmBuyActivity extends AbsSpiceBackActivity {
    @Inject
    User user;

    @InjectView(R.id.quantitySpinnerView)
    CustomSpinnerView quantitySpinnerView;

    @InjectView(R.id.totalPriceTextView)
    TextView totalPriceTextView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @InjectExtra(value = "PRODUCT_OBJ", optional = false)
    Product product;

    int total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalPriceTextView.setText(getString(R.string.product__price_unit) + " " + product.product_price);
        total_price = Integer.parseInt(product.product_price);
        quantitySpinnerView.bindModel(1, Integer.parseInt(product.quantity), true);
        quantitySpinnerView.setOnQuantityChangeListener(new CustomSpinnerView.OnQuantityChangeListener() {
            @Override
            public void onQuantityChange(int value) {
                total_price = value * Integer.parseInt(product.product_price);
                totalPriceTextView.setText(getString(R.string.product__price_unit) + " " + value * Integer.parseInt(product.product_price));
            }
        });
    }

    public void onConfirmToBuyClick(View v) {
        enterDeliveryAddressDialog();
    }

    private void enterDeliveryAddressDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(8, 8, 8, 8);
        TextView textView = new TextView(this);
        textView.setText("Please enter a delivery address");
        final EditText input = new EditText(this);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParamsyoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        linearLayout.setLayoutParams(lp);
        linearLayout.addView(textView);
        linearLayout.addView(input);
        alertDialog.setView(linearLayout);
        alertDialog.setPositiveButton("Make an order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                progressbar.setVisibility(View.VISIBLE);
                getSpiceManger().execute(
                        new CreateOrderRequest(
                                ProductConfirmBuyActivity.this, product.user_id,
                                user.user_id, input.getEditableText().toString(),
                                Integer.parseInt(product.product_id), total_price),
                        new CreateOrderRequestListener());
                dialogInterface.dismiss();

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void createOrderSuccessDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setPositiveButton("Go to My Order", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent i1 = new Intent(ProductConfirmBuyActivity.this, MainActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i1.putExtra("TO_FRAGMENT", R.id.nav_order);
                startActivity(i1);
                dialogInterface.dismiss();
            }
        });
        alertDialog.setNegativeButton("Continue Shopping", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent i2 = new Intent(ProductConfirmBuyActivity.this, MainActivity.class);
                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i2);
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private class CreateOrderRequestListener extends AbsRequestListener<AbsResponse> {

        @Override
        public void onSuccess(AbsResponse absResponse) {
            progressbar.setVisibility(View.GONE);
            createOrderSuccessDialog();
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }
}
