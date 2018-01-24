/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhengdong.base.Section.Four.Model.NewsListModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineOfferListAdapter extends RecyclerView.Adapter<MineOfferListAdapter.ViewHolder> {
    private int types = 0;
    public List<NewsListModel.DataBean.EcInformationBean> datas = null;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, int name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public MineOfferListAdapter(Context context, List<NewsListModel.DataBean.EcInformationBean> datas, int types) {
        mContext = context;
        this.datas = datas;
        this.types = types;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_offer_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        if (types == 1){
            viewHolder.firstCell.setVisibility(View.VISIBLE);
            viewHolder.firstCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,types);
                }
            });
        }else {
            viewHolder.firstCell.setVisibility(View.GONE);
        }
        if (types == 2){
            viewHolder.secondCell.setVisibility(View.VISIBLE);
            viewHolder.secondCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,types);
                }
            });
        }else {
            viewHolder.secondCell.setVisibility(View.GONE);
        }
        if (types == 3){
            viewHolder.threeCell.setVisibility(View.VISIBLE);
            viewHolder.threeCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,types);
                }
            });
        }else {
            viewHolder.threeCell.setVisibility(View.GONE);
        }
        if (types == 4){
            viewHolder.fourCell.setVisibility(View.VISIBLE);
            viewHolder.fourCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.OnItemClick(view,types);
                }
            });
        }else {
            viewHolder.fourCell.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_cell)
        LinearLayout firstCell;
        @BindView(R.id.second_cell)
        LinearLayout secondCell;
        @BindView(R.id.three_cell)
        LinearLayout threeCell;
        @BindView(R.id.four_cell)
        LinearLayout fourCell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
