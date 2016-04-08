package com.xavier.daigoufyp.controller.page.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.adapter.pager.HomePagerAdapter;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;
import com.xavier.daigoufyp.model.User;

import roboguice.inject.InjectView;

public class HomeFragment extends AbsSpiceFragment {

    @Inject
    User user;

    @InjectView(R.id.homeViewPager)
    ViewPager homeViewPager;

    @InjectView(R.id.homeTabs)
    TabLayout homeTabs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomePagerAdapter adapter = new HomePagerAdapter(getActivity(), getChildFragmentManager());
        homeViewPager.setAdapter(adapter);
        homeTabs.setupWithViewPager(homeViewPager);
    }

//    public void onLogoutClick(View v) {
//        LoginManager.getInstance().logOut();
//        user.clearUser().commit();
//        Intent i = new Intent(getActivity(), LoginActivity.class);
//        startActivity(i);
//        getActivity().finish();
//    }
}
