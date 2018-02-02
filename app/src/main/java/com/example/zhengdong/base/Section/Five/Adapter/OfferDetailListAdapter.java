/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.base.Section.Login.Model.RequireOrderListModel;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferDetailListAdapter extends RecyclerView.Adapter<OfferDetailListAdapter.ViewHolder> {
    public RequireOrderListModel datas = null;

    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public OfferDetailListAdapter(Context context, RequireOrderListModel datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_detail_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.headerCell.setVisibility(View.VISIBLE);
            viewHolder.orderServiceTxt.setText("" + datas.getData().getEnquiryMap().getDemands());
            viewHolder.worjTimeTxt.setText("" + datas.getData().getEnquiryMap().getDay_limit() + "天");
            viewHolder.uploadDayTxt.setText("" + datas.getData().getEnquiryMap().getPickup_day());
            viewHolder.remarkTxt.setText("" + datas.getData().getEnquiryMap().getRemark());
        } else {
            viewHolder.headerCell.setVisibility(View.GONE);
        }
        if (datas.getData().getItemmap().size() == 0) {
            viewHolder.orderCell.setVisibility(View.GONE);
        } else {
            viewHolder.orderCell.setVisibility(View.VISIBLE);
            viewHolder.cOrderNumberTxt.setText("订货单号: " + datas.getData().getItemmap().get(position).getItem_id() + "");
            viewHolder.cPartNumTxt.setText("部件个数: " + datas.getData().getItemmap().get(position).getCount());
            viewHolder.cOrderStatueTxt.setText("定制状态: " + datas.getData().getItemmap().get(position).getRespStatus());
            GlideApp.with(mContext)
                    .load(datas.getData().getItemmap().get(position).getFile_path())
                    .placeholder(R.drawable.placerholder)
                    .centerCrop()
                    .into(viewHolder.cHeaderPic);
            viewHolder.orderCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view, datas.getData().getItemmap().get(position).getItem_id());
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return datas.getData().getItemmap().size() == 0 ? 1 : datas.getData().getItemmap().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_cell)
        LinearLayout headerCell;
        @BindView(R.id.order_cell)
        RelativeLayout orderCell;

        @BindView(R.id.order_service_txt)
        TextView orderServiceTxt;
        @BindView(R.id.worj_time_txt)
        TextView worjTimeTxt;
        @BindView(R.id.upload_day_txt)
        TextView uploadDayTxt;
        @BindView(R.id.remark_txt)
        TextView remarkTxt;
        @BindView(R.id.c_order_number_txt)
        TextView cOrderNumberTxt;
        @BindView(R.id.c_part_num_txt)
        TextView cPartNumTxt;
        @BindView(R.id.c_order_statue_txt)
        TextView cOrderStatueTxt;
        @BindView(R.id.c_header_pic)
        ImageView cHeaderPic;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
