/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Five.Model.MineOffCompanyListModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfferSecondListAdapter extends RecyclerView.Adapter<OfferSecondListAdapter.ViewHolder> {
    public MineOffCompanyListModel datas = null;
    public ArrayList<Integer> picArr = null;



    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void OnItemClick(View view, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public OfferSecondListAdapter(Context context, MineOffCompanyListModel datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_second_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.companyNameTxt.setText(""+datas.getData().get(position).getOrg_name());
        viewHolder.orderTimeTxt.setText("报价时间: "+datas.getData().get(position).getCreate_ts());
        viewHolder.totalTxt.setText(""+datas.getData().get(position).getPrice());
        viewHolder.remarkTxt.setText("备注: "+datas.getData().get(position).getRemark());
        viewHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, position, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.company_name_txt)
        TextView companyNameTxt;
        @BindView(R.id.order_time_txt)
        TextView orderTimeTxt;
        @BindView(R.id.total_txt)
        TextView totalTxt;
        @BindView(R.id.remark_txt)
        TextView remarkTxt;
        @BindView(R.id.tran_btn)
        Button tranBtn;
        @BindView(R.id.cell)
        LinearLayout cell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
