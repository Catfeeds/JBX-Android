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
import android.widget.TextView;

import com.example.zhengdong.base.Macro.RvAnimationUtil;
import com.example.zhengdong.base.Section.First.Model.AdvertiseListModel;
import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BussListAdapter extends RecyclerView.Adapter<BussListAdapter.ViewHolder> {


    private Context mContext;
    private AdvertiseListModel.DataBean mDatas;
    private LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String name, int brandid);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public BussListAdapter(Context mContext, AdvertiseListModel.DataBean mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }


//    public BussListAdapter setDatas(List<CityBean> datas) {
//        mDatas = datas;
//        return this;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_buss, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RvAnimationUtil.runEnterAnimation(holder.itemView, position);
        holder.nameTxt.setText("" + mDatas.getOrgList().get(position).getName());
        holder.descriptTxt.setText("经营地址: " + mDatas.getOrgList().get(position).getAddress());
        if (mDatas.getOrgList().get(position).getContact_person() == null){
            holder.phoneTxt.setText("联系人: "+"暂无");
        }else {
            holder.phoneTxt.setText("联系人: "+mDatas.getOrgList().get(position).getContact_person()+"   "+mDatas.getOrgList().get(position).getShop_phone());
        }
        GlideApp.with(mContext)
                .load(mDatas.getOrgList().get(position).getLogo())
                .placeholder(R.drawable.placerholder)
                .into(holder.avatar);

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.getOrgList().size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bus_pic)
        ImageView avatar;
        @BindView(R.id.name_txt)
        TextView nameTxt;
        @BindView(R.id.descript_txt)
        TextView descriptTxt;
        @BindView(R.id.phone_txt)
        TextView phoneTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
