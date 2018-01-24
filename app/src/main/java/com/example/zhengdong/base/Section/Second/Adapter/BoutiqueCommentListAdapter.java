/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoutiqueCommentListAdapter extends RecyclerView.Adapter<BoutiqueCommentListAdapter.ViewHolder> {


    private int types = 0;
    public String[] datas = null;
    String[] nameStr = {
            "弧形精品#1测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本", "弧形精品#1测试文本测试文本测试文本测试文本测试文本", "框类精品#1测试文本测试文本", "框类精品#2测试文本测试文本", "斜边精品#1测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本",
            "斜边精品#2测试文本测试文本测试文本测试文本测试文本",
            "弧形精品#2测试文本测试文本测试文本测试文本测试文本", "盒子精品#1测试文本测试文本测试文本测试文本测试文本",
            "盒子精品#2测试文本测试文本测试文本", "盒子精品#3测试文本", "U形精品#1测试文本测试文本测试文本测试文本测试文本", "弧形精品#3",
            "框类精品#3测试文本测试文本测试文本测试文本", "U形精品#2测试文本", "方形精品#1测试文本测试文本测试文本测试文本", "盒子精品#4测试文本", "弧形精品#4测试文本测试文本测试文本测试文本测试文本测试文本", "异形精品#2测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本测试文本","异形精品#3"
    };

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


    public BoutiqueCommentListAdapter(Context context, String[] datas) {
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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
