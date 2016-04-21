package com.xavier.daigoufyp.view.listcell;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.model.Order;

import roboguice.RoboGuice;
import roboguice.inject.InjectView;

/**
 * Created by xavier on 21/4/16.
 */
public class OrderListCellView extends LinearLayout {
    Context context;

    @InjectView(R.id.NameTextView)
    TextView NameTextView;

    @InjectView(R.id.totalPriceTextView)
    TextView totalPriceTextView;

    @InjectView(R.id.orderStatusTextView)
    TextView orderStatusTextView;

    public OrderListCellView(Context context) {
        super(context);
        init(context);
    }

    public OrderListCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OrderListCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OrderListCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_order_item, this);
        RoboGuice.getInjector(getContext()).injectMembers(this);
        RoboGuice.getInjector(getContext()).injectViewMembers(this);
    }

    public void bindModel(Order order, String type) {
        if (type.equals("Buying"))
            NameTextView.setText("Order made to " + order.seller_name);
        else
            NameTextView.setText("Order made from" + order.buyer_name);
        orderStatusTextView.setText(order.order_status);
        totalPriceTextView.setText("total price: HKD " + order.order_total_price);
    }
}
