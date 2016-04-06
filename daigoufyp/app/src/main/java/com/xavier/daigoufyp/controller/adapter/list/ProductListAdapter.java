package com.xavier.daigoufyp.controller.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.view.listcell.ProductListCellView;

import java.util.List;

/**
 * Created by zensis on 6/4/16.
 */
public class ProductListAdapter extends AbsListAdapter<ProductListAdapter.ProductViewHolder> {
    List<Product> productList;

    public ProductListAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductListCellView view = new ProductListCellView(parent.getContext());
        ProductViewHolder holder = new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        holder.view.bindModel(productList.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null)
                    onItemClickListener.onItemClick(productList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductListCellView view;

        public ProductViewHolder(ProductListCellView view) {
            super(view);
            this.view = view;
        }
    }
}
