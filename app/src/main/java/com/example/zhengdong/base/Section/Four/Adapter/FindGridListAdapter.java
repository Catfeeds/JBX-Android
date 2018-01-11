/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindGridListAdapter extends RecyclerView.Adapter<FindGridListAdapter.ViewHolder> {
    public List<Map<String, Object>> datas = null;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, String name, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public FindGridListAdapter(Context context, List<Map<String, Object>> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.find_job_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0){
            viewHolder.image.setVisibility(View.VISIBLE);
            viewHolder.txt.setTextColor(Color.RED);
        }else {
            viewHolder.image.setVisibility(View.GONE);
        }
        viewHolder.image.setBackgroundResource(Integer.parseInt(datas.get(position).get("image").toString()));
        viewHolder.txt.setText(datas.get(position).get("text").toString());
        viewHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,datas.get(position).get("text").toString(),position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.cell)
        RelativeLayout cell;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
