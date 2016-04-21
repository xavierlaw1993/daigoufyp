package com.xavier.daigoufyp.controller.page.product;


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
import com.xavier.daigoufyp.controller.network.AbsRequestListener;
import com.xavier.daigoufyp.controller.network.request.GetAllProductsRequest;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.model.Product;
import com.xavier.daigoufyp.model.response.ProductListResponse;
import com.xavier.daigoufyp.utils.Constants;
import com.xavier.daigoufyp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;


public class ProductsFragment extends AbsSpiceFragment {

    @InjectView(R.id.productListRecyclerView)
    RecyclerView productListRecyclerView;

    @InjectView(R.id.progressbar)
    ProgressBar progressbar;

    @InjectView(R.id.categorySpinner)
    Spinner categorySpinner;

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
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(getActivity(), R.layout.view_spinner_item, Constants.getCategoryList());
        categorySpinner.setAdapter(categoriesAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String category = categorySpinner.getSelectedItem().toString();
                List<Product> newProductList = new ArrayList<>();
                if (i > 0) {
                    for (int x = 0; x < productList.size(); x++) {
                        if (productList.get(x).category.equals(category))
                            newProductList.add(productList.get(x));
                    }
                    productList.clear();
                    productList.addAll(newProductList);
                    productListRecyclerView.getAdapter().notifyDataSetChanged();
                } else{
                    requestAllProductsAPI();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ProductListAdapter adapter = new ProductListAdapter(productList);
        adapter.setOnItemClickListener(new AbsListAdapter.OnItemClickListener<Product>() {
            @Override
            public void onItemClick(Product item) {
                Intent i = new Intent(getActivity(), ProductDetailActivity.class);
                i.putExtra("product_id", Integer.parseInt(item.product_id));
                startActivity(i);
            }
        });
        productListRecyclerView.setLayoutManager(manager);
        productListRecyclerView.setAdapter(adapter);
        requestAllProductsAPI();
    }

    private void requestAllProductsAPI() {
        progressbar.setVisibility(View.VISIBLE);
        getSpiceManager().execute(
                new GetAllProductsRequest(getActivity()),
                new GetAllProductsRequestListener());
    }

    public class GetAllProductsRequestListener extends AbsRequestListener<ProductListResponse> {

        @Override
        public void onSuccess(ProductListResponse productListResponse) {
            productList.clear();
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
