package com.xavier.daigoufyp.controller.page.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.list.AbsListAdapter;
import com.xavier.daigoufyp.controller.adapter.list.ProductListAdapter;
import com.xavier.daigoufyp.controller.adapter.list.ShopListAdapter;
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetAllProductsRequest;
import com.xavier.daigoufyp.controller.network.request.GetAllShopsRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.controller.page.product.ProductDetailActivity;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.Shop;
import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.model.response.ShopListResponse;
import com.xavier.daigoufyp.utils.Constants;
import com.xavier.daigoufyp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;


public class ShopsFragment extends AbsSpiceFragment {

    @InjectView(R.id.shopListRecyclerView)
    RecyclerView shopListRecyclerView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @InjectView(R.id.categorySpinner)
    Spinner categorySpinner;

    List<Shop> shopList = new ArrayList<>();

    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(getActivity(), R.layout.view_spinner_item, Constants.getCategoryList());
        categorySpinner.setAdapter(categoriesAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String category = categorySpinner.getSelectedItem().toString();
                List<Shop> newShopList = new ArrayList<>();
                if (i > 0) {
                    for (int x = 0; x < shopList.size(); x++) {
                        if (shopList.get(x).category.equals(category))
                            newShopList.add(shopList.get(x));
                    }
                    shopList.clear();
                    shopList.addAll(newShopList);
                    shopListRecyclerView.getAdapter().notifyDataSetChanged();
                } else{
                    requestAllShopsAPI();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ShopListAdapter adapter = new ShopListAdapter(shopList);
        adapter.setOnItemClickListener(new AbsListAdapter.OnItemClickListener<Shop>() {
            @Override
            public void onItemClick(Shop item) {
                Intent i = new Intent(getActivity(), ShopDetailActivity.class);
                i.putExtra("SHOP_ID", Integer.parseInt(item.shop_id));
                startActivity(i);
            }
        });
        shopListRecyclerView.setLayoutManager(manager);
        shopListRecyclerView.setAdapter(adapter);
        requestAllShopsAPI();
    }

    private void requestAllShopsAPI() {
        progressbar.setVisibility(View.VISIBLE);
        getSpiceManager().execute(
                new GetAllShopsRequest(getActivity()),
                new GetAllShopsRequestListener());
    }

    public class GetAllShopsRequestListener extends AbsRequestListener<ShopListResponse> {

        @Override
        public void onSuccess(ShopListResponse shopListResponse) {
            shopList.clear();
            shopList.addAll(shopListResponse.shops);
            shopListRecyclerView.getAdapter().notifyDataSetChanged();
            progressbar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(String msg) {
            Utils.showFailureSnackbar(getView(), msg);
            progressbar.setVisibility(View.GONE);
        }
    }

}
