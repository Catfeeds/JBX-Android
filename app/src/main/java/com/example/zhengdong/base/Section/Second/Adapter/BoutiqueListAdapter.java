/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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

public class BoutiqueListAdapter extends RecyclerView.Adapter<BoutiqueListAdapter.ViewHolder> {


    private int types = 0;
    public String[] datas = null;
    int[] picArrs = {
            R.drawable.test1, R.drawable.test19, R.drawable.test2, R.drawable.test3, R.drawable.test4, R.drawable.test5, R.drawable.test6,
            R.drawable.test7, R.drawable.test8, R.drawable.test9, R.drawable.test10, R.drawable.test11, R.drawable.test12,
            R.drawable.test13, R.drawable.test14, R.drawable.test16, R.drawable.test17, R.drawable.test18 ,R.drawable.test15,
    };
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


    public BoutiqueListAdapter(Context context, String[] datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.boutique_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        GlideApp.with(mContext)
                .load(picArrs[position])
                .placeholder(R.drawable.placerholder)
                .into(viewHolder.pic);
//        viewHolder.pic.setBackgroundResource(picArrs[position]);
        viewHolder.txt.setText(nameStr[position]);
        viewHolder.cardCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return picArrs.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.card_cell)
        LinearLayout cardCell;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
//            int width = ((Activity) pic.getContext()).getWindowManager().getDefaultDisplay().getWidth();
//            ViewGroup.LayoutParams params = pic.getLayoutParams();
            //设置图片的相对于屏幕的宽高比
//            params.width = (width-40)/2;
//            params.height =  (int) (300 + Math.random() * 400) ;
//            pic.setLayoutParams(params);
        }
    }
}
