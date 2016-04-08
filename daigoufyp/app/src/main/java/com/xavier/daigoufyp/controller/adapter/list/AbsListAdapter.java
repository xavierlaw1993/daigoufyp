package com.xavier.daigoufyp.controller.adapter.list;

import android.support.v7.widget.RecyclerView;

/**
 * Created by xavier on 6/4/16.
 */
public abstract class AbsListAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener<I> {
        void onItemClick(I item);
    }
}