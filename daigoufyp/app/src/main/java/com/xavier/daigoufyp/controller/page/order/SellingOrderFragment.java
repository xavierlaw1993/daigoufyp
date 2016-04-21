package com.xavier.daigoufyp.controller.page.order;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.list.AbsListAdapter;
import com.xavier.daigoufyp.controller.adapter.list.OrderListAdapter;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetMyOrderListRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.model.Order;
import com.xavier.daigoufyp.model.response.MyOrderListResponse;
import com.xavier.daigoufyp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

public class SellingOrderFragment extends AbsSpiceFragment {

    @InjectView(R.id.orderListRecyclerView)
    RecyclerView orderListRecyclerView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    List<Order> orderList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        OrderListAdapter adapter = new OrderListAdapter(orderList, "Selling");
        adapter.setOnItemClickListener(new AbsListAdapter.OnItemClickListener<Order>() {
            @Override
            public void onItemClick(Order item) {
                Intent i = new Intent(getActivity(), OrderDetailActivity.class);
                i.putExtra("ORDER_ID", item.order_id);
                i.putExtra("ORDER_TYPE", "Selling");
                startActivity(i);
            }
        });
        orderListRecyclerView.setLayoutManager(manager);
        orderListRecyclerView.setAdapter(adapter);
        getSpiceManager().execute(
                new GetMyOrderListRequest(getActivity(), "Selling"),
                new GetMyOrderListRequestListener());
    }


    private class GetMyOrderListRequestListener extends AbsRequestListener<MyOrderListResponse> {

        @Override
        public void onSuccess(MyOrderListResponse myOrderListResponse) {
            orderList.addAll(myOrderListResponse.orders);
            orderListRecyclerView.getAdapter().notifyDataSetChanged();
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(String msg) {
            progressbar.setVisibility(View.GONE);
            Utils.showFailureSnackbar(getView(), msg);
        }
    }

    public SellingOrderFragment() {
        // Required empty public constructor
    }
}
