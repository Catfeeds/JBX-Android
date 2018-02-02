/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.Model.OrganPartmentListModel;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradeListAdapter extends RecyclerView.Adapter<TradeListAdapter.ViewHolder> {
    public OrganPartmentListModel datas = null;
    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String i, String position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public TradeListAdapter(Context context, OrganPartmentListModel datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trade_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0){
            viewHolder.firstTxt.setVisibility(View.VISIBLE);
            viewHolder.secondTxt.setVisibility(View.VISIBLE);
            viewHolder.threeTxt.setVisibility(View.VISIBLE);
            viewHolder.fourTxt.setVisibility(View.VISIBLE);
        }else {
            viewHolder.firstTxt.setVisibility(View.GONE);
            viewHolder.secondTxt.setVisibility(View.GONE);
            viewHolder.threeTxt.setVisibility(View.GONE);
            viewHolder.fourTxt.setVisibility(View.GONE);
        }
        if (position%2 == 1){
            viewHolder.rFirstTxt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.rSecondTxt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.rThreeTxt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            viewHolder.rFourTxt.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            viewHolder.rFirstTxt.setBackgroundColor(mContext.getResources().getColor(R.color.light_grays));
            viewHolder.rSecondTxt.setBackgroundColor(mContext.getResources().getColor(R.color.light_grays));
            viewHolder.rThreeTxt.setBackgroundColor(mContext.getResources().getColor(R.color.light_grays));
            viewHolder.rFourTxt.setBackgroundColor(mContext.getResources().getColor(R.color.light_grays));
        }


    }


    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_txt)
        TextView firstTxt;
        @BindView(R.id.r_first_txt)
        TextView rFirstTxt;
        @BindView(R.id.second_txt)
        TextView secondTxt;
        @BindView(R.id.r_second_txt)
        TextView rSecondTxt;
        @BindView(R.id.three_txt)
        TextView threeTxt;
        @BindView(R.id.r_three_txt)
        TextView rThreeTxt;
        @BindView(R.id.four_txt)
        TextView fourTxt;
        @BindView(R.id.r_four_txt)
        TextView rFourTxt;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
