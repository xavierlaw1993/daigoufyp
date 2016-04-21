package com.xavier.daigoufyp.controller.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.Shop;
import com.xavier.daigoufyp.view.listcell.ProductListCellView;
import com.xavier.daigoufyp.view.listcell.ShopListCellView;

import java.util.List;

/**
 * Created by xavier on 6/4/16.
 */
public class ShopListAdapter extends AbsListAdapter<ShopListAdapter.ShopViewHolder> {
    List<Shop> shopList;

    public ShopListAdapter(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ShopListCellView view = new ShopListCellView(parent.getContext());
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, final int position) {
        holder.view.bindModel(shopList.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null)
                    onItemClickListener.onItemClick(shopList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        ShopListCellView view;

        public ShopViewHolder(ShopListCellView view) {
            super(view);
            this.view = view;
        }
    }
}
