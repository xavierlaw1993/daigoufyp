package com.xavier.daigoufyp.controller.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.model.Order;
import com.xavier.daigoufyp.view.listcell.OrderListCellView;

import java.util.List;

/**
 * Created by xavier on 21/4/16.
 */
public class OrderListAdapter extends AbsListAdapter<OrderListAdapter.OrderViewHolder>{
    List<Order> orderList;
    String type = "Buying";

    public OrderListAdapter(List<Order> orderList, String type) {
        this.orderList = orderList;
        this.type = type;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderListCellView view = new OrderListCellView(parent.getContext());
        OrderViewHolder holder = new OrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, final int position) {
        holder.view.bindModel(orderList.get(position), type);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null)
                    onItemClickListener.onItemClick(orderList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        OrderListCellView view;

        public OrderViewHolder(OrderListCellView view) {
            super(view);
            this.view = view;
        }
    }
}
