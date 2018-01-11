/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindThreeListAdapter extends RecyclerView.Adapter<FindThreeListAdapter.ViewHolder> {

    private int currentID = -1;
    public List<Object> datas = null;


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


    public FindThreeListAdapter(Context context, List<Object> datas, int type) {
        mContext = context;
        this.datas = datas;
        this.currentID = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.find_three_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (currentID == 1) {
            // 我的简历
            viewHolder.mineWordLay.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mineWordLay.setVisibility(View.GONE);
        }
        if (currentID == 2) {
            viewHolder.minWorkLay.setVisibility(View.VISIBLE);
        } else {
            viewHolder.minWorkLay.setVisibility(View.GONE);
        }
        if (currentID == 3) {
            viewHolder.minHrLay.setVisibility(View.VISIBLE);
        } else {
            viewHolder.minHrLay.setVisibility(View.GONE);
        }
        viewHolder.mineWordLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.minWorkLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.minHrLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mine_word_lay)
        LinearLayout mineWordLay;
        @BindView(R.id.min_work_lay)
        LinearLayout minWorkLay;
        @BindView(R.id.min_hr_lay)
        LinearLayout minHrLay;
        

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
