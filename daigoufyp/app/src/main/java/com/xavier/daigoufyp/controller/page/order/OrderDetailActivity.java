package com.xavier.daigoufyp.controller.page.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetOrderDetailRequest;
import com.xavier.daigoufyp.controller.network.request.UpdateOrderRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceBackActivity;
import com.xavier.daigoufyp.model.User;
import com.xavier.daigoufyp.model.response.AbsResponse;
import com.xavier.daigoufyp.model.response.MyOrderResponse;
import com.xavier.daigoufyp.utils.Utils;

import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends AbsSpiceBackActivity {
    @Inject
    User user;

    @InjectView(R.id.buyerNameTextView)
    TextView buyerNameTextView;

    @InjectView(R.id.sellerNameTextView)
    TextView sellerNameTextView;

    @InjectView(R.id.totalPriceTextView)
    TextView totalPriceTextView;

    @InjectView(R.id.productNameTextView)
    TextView productNameTextView;

    @InjectView(R.id.productQuantityTextView)
    TextView productQuantityTextView;

    @InjectView(R.id.orderStatusTextView)
    TextView orderStatusTextView;

    @InjectView(R.id.deliveryAddressTextView)
    TextView deliveryAddressTextView;

    @InjectView(R.id.payButton)
    Button payButton;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;


    @InjectExtra(value = "ORDER_ID", optional = false)
    int orderID;

    @InjectExtra(value = "ORDER_TYPE", optional = false)
    String orderType;

    String orderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSpiceManger().execute(
                new GetOrderDetailRequest(this, orderID),
                new GetOrderDetailRequestListener());
    }

    private class GetOrderDetailRequestListener extends AbsRequestListener<MyOrderResponse> {

        @Override
        public void onSuccess(final MyOrderResponse myOrderResponse) {
            progressbar.setVisibility(View.GONE);
            orderStatus = myOrderResponse.order.order_status;
            if (orderType.equals("Buying")) {
                payButton.setVisibility(View.VISIBLE);
                if (orderStatus.equals("Pending")) {
                    payButton.setText("Pay the order");
                } else if (orderStatus.equals("Paid")) {
                    payButton.setText("Finished the order");
                } else if (orderStatus.equals("Finished")) {
                    payButton.setVisibility(View.GONE);
                    payButton.setClickable(false);
                }
                buyerNameTextView.setText("from " + user.user_name);
                sellerNameTextView.setText(myOrderResponse.order.seller_name);
            } else {
                payButton.setVisibility(View.GONE);
                buyerNameTextView.setText("from " + myOrderResponse.order.buyer_name);
                sellerNameTextView.setText("to " + user.user_name);
            }
            totalPriceTextView.setText("total price: " + myOrderResponse.order.order_total_price);
            productNameTextView.setText(myOrderResponse.order.product.product_name);
            productQuantityTextView.setText(myOrderResponse.order.product.quantity);

            orderStatusTextView.setText("status: " + orderStatus);
            deliveryAddressTextView.setText("delivery address: "+myOrderResponse.order.delivery_address);

            payButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressbar.setVisibility(View.VISIBLE);
                    getSpiceManger().execute(
                            new UpdateOrderRequest(OrderDetailActivity.this,
                                    orderID, myOrderResponse.order.order_status),
                            new UpdateOrderRequestListener());
                }
            });
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }

    private class UpdateOrderRequestListener extends AbsRequestListener<AbsResponse> {

        @Override
        public void onSuccess(AbsResponse absResponse) {
            progressbar.setVisibility(View.GONE);
            if (orderStatus.equals("Pending")) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderDetailActivity.this);
                alertDialog.setTitle("Congratulation");
                alertDialog.setMessage("You have paid the transaction. Please finish the order when you the product is delivered to you");
                alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            } else if (orderStatus.equals("Paid")) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderDetailActivity.this);
                alertDialog.setTitle("Congratulation");
                alertDialog.setMessage("You have finished a transaction. Enjoy your product");
                alertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(findViewById(android.R.id.content), msg);
        }
    }
}
