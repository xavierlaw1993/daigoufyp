package com.xavier.daigoufyp.controller.adapter.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.model.ProductPicture;
import com.xavier.daigoufyp.view.product.ProductPictureItemView;
import com.xavier.daigoufyp.view.product.ProductPictureView;

import java.util.List;

/**
 * Created by zensis on 11/4/16.
 */
public class ProductPicturePagerAdapter extends PagerAdapter {
    Context context;
    List<ProductPicture> productPictureList;
    LayoutInflater layoutInflater;
    ProductPictureView.ProductPictureListener listener;

    public ProductPicturePagerAdapter(Context context,
                                      List<ProductPicture> productPictureList,
                                      ProductPictureView.ProductPictureListener listener) {
        this.productPictureList = productPictureList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return productPictureList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ProductPictureItemView view = new ProductPictureItemView(container.getContext());
        view.bindModel(productPictureList.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPictureClick(productPictureList.get(position));
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
