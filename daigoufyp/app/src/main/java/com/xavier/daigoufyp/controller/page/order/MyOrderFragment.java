package com.xavier.daigoufyp.controller.page.order;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.pager.OrderPagerAdapter;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;

import roboguice.inject.InjectView;

public class MyOrderFragment extends AbsSpiceFragment {

    @InjectView(R.id.orderViewPager)
    ViewPager orderViewPager;

    @InjectView(R.id.orderTabs)
    TabLayout orderTabs;

    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OrderPagerAdapter adapter = new OrderPagerAdapter(getActivity(), getChildFragmentManager());
        orderViewPager.setAdapter(adapter);
        orderTabs.setupWithViewPager(orderViewPager);
    }
}
