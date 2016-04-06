package com.xavier.daigoufyp.controller.page.home;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.list.ProductListAdapter;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.model.Product;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;


public class ProductsFragment extends AbsSpiceFragment {

    @InjectView(R.id.productListRecyclerView)
    RecyclerView productListRecyclerView;

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
        productListRecyclerView.setLayoutManager(manager);
        productListRecyclerView.setAdapter(adapter);
    }
}
