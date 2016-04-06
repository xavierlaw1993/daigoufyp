package com.xavier.daigoufyp.controller.adapter.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.home.ProductsFragment;
import com.xavier.daigoufyp.controller.page.home.ShopsFragment;

/**
 * Created by zensis on 6/4/16.
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {
    Context context;

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProductsFragment();
            case 1:
                return new ShopsFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.home__product_fragment_title);
            case 1:
                return context.getString(R.string.home__shop_fragment_title);
        }
        return "";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
