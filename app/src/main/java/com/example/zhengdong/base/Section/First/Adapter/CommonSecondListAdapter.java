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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Five.View.CircleImageView;
import com.example.zhengdong.base.Section.First.Model.SecondOrganListModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommonSecondListAdapter extends RecyclerView.Adapter<CommonSecondListAdapter.ViewHolder> {
    public List<SecondOrganListModel.DataBean.DeptBean> datas = null;
    public ArrayList<Integer> picArr = null;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    @OnClick(R.id.content_rel)
    public void onViewClicked() {
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int i, String position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public CommonSecondListAdapter(Context context, List<SecondOrganListModel.DataBean.DeptBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.common_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
//        if (position == 0) {
//            viewHolder.rvHeader.setVisibility(View.VISIBLE);
//            // 头部rv
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//            viewHolder.headerRv.setLayoutManager(linearLayoutManager);
//            ComHeaderListAdapter comHeaderListAdapter = new ComHeaderListAdapter(mContext, datas.getData().getContent());
//            viewHolder.headerRv.setAdapter(comHeaderListAdapter);
//            comHeaderListAdapter.setOnItemClickListener(new ComHeaderListAdapter.OnItemClickListener() {
//                @Override
//                public void OnItemClick(View view, int i, int position, String name) {
//                    mOnItemClickListener.OnItemClick(view,position,position,name);
//                }
//            });
//
//        } else {
//            viewHolder.rvHeader.setVisibility(View.GONE);
//        }
        viewHolder.contentRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,position,datas.get(position).getDept_id(),datas.get(position).getName());
            }
        });
        viewHolder.rvHeader.setVisibility(View.GONE);
        viewHolder.nameTxt.setText(datas.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.header_rv)
        RecyclerView headerRv;
        @BindView(R.id.rv_header)
        LinearLayout rvHeader;
        @BindView(R.id.header_pic)
        CircleImageView headerPic;
        @BindView(R.id.name_txt)
        TextView nameTxt;
        @BindView(R.id.subname_txt)
        TextView subnameTxt;
        @BindView(R.id.go_pic)
        ImageView goPic;
        @BindView(R.id.content_rel)
        RelativeLayout contentRel;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
