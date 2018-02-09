/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Five.View.CircleImageView;
import com.example.zhengdong.base.Section.Second.Model.CommnetListModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoutiqueCommentListAdapter extends RecyclerView.Adapter<BoutiqueCommentListAdapter.ViewHolder> {



    private int types = 0;
    public List<CommnetListModel.DataBean.FanCommentsListBean> datas = null;

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


    public BoutiqueCommentListAdapter(Context context, List<CommnetListModel.DataBean.FanCommentsListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.boutique_comment_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.nameTxt.setText(datas.get(position).getUser_name());
        viewHolder.timeTxt.setText(datas.get(position).getComm_ts());
        viewHolder.commentTxt.setText(datas.get(position).getCommnents());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mine_header_pic)
        CircleImageView mineHeaderPic;
        @BindView(R.id.name_txt)
        TextView nameTxt;
        @BindView(R.id.time_txt)
        TextView timeTxt;
        @BindView(R.id.comment_txt)
        TextView commentTxt;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
