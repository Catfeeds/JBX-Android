/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.RvAnimationUtil;
import com.example.zhengdong.base.Section.First.Model.CityBean;
import com.example.zhengdong.jbx.R;


import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private Context mContext;
    private List<CityBean> mDatas;
    private LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public interface  OnItemClickListener{
        void OnItemClick(View view, String name, int brandid);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public CityListAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public CityListAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RvAnimationUtil.runEnterAnimation(holder.itemView,position);

        final CityBean brandBean = mDatas.get(position);
        holder.tvCity.setText(mDatas.get(position).getCity());
        holder.avatar.setBackgroundResource(R.drawable.placerholder);
        if (mOnItemClickListener != null){
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(v,mDatas.get(position).getCity(),position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.brand_text) TextView tvCity;
        @BindView(R.id.brand_pic) ImageView avatar;
        @BindView(R.id.brand_cell) LinearLayout frameLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
