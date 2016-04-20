package com.xavier.daigoufyp.controller.page.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xavier.daigoufyp.R;
import com.xavier.daigoufyp.controller.page.abs.AbsSpiceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuyingOrderFragment extends AbsSpiceFragment {


    public BuyingOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buying_order, container, false);
    }

}
