package com.xavier.daigoufyp.controller.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.order.BuyingOrderFragment;
import com.xavier.daigoufyp.controller.page.order.SellingOrderFragment;

/**
 * Created by xavier on 21/4/16.
 */
public class OrderPagerAdapter extends FragmentStatePagerAdapter{
    Context context;

    public OrderPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BuyingOrderFragment();
            case 1:
                return new SellingOrderFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Buying";
            case 1:
                return "Selling";
        }
        return "";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
