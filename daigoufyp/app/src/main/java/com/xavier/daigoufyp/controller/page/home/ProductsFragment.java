package com.xavier.daigoufyp.controller.page.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.list.AbsListAdapter;
import com.xavier.daigoufyp.controller.adapter.list.ProductListAdapter;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetAllProductsRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.controller.page.detail.ProductDetailActivity;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;


public class ProductsFragment extends AbsSpiceFragment {

    @InjectView(R.id.productListRecyclerView)
    RecyclerView productListRecyclerView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    List<Product> productList = new ArrayList<>();

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ProductListAdapter adapter = new ProductListAdapter(productList);
        adapter.setOnItemClickListener(new AbsListAdapter.OnItemClickListener<Product>() {
            @Override
            public void onItemClick(Product item) {
                Intent i = new Intent(getActivity(), ProductDetailActivity.class);
                i.putExtra("product_id", item.product_id);
                startActivity(i);
            }
        });
        productListRecyclerView.setLayoutManager(manager);
        productListRecyclerView.setAdapter(adapter);
        getSpiceManager().execute(
                new GetAllProductsRequest(getActivity()),
                new GetAllProductsRequestListener());
    }

    public class GetAllProductsRequestListener extends AbsRequestListener<ProductListResponse> {

        @Override
        public void onSuccess(ProductListResponse productListResponse) {
            productList.addAll(productListResponse.products);
            productListRecyclerView.getAdapter().notifyDataSetChanged();
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(String msg) {
            Utils.showFailureSnackbar(getView(), msg);
            progressbar.setVisibility(View.GONE);
        }
    }
}
