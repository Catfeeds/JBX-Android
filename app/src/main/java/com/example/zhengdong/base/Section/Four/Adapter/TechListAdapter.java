/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Four.Model.NewsTitleModel;
import com.example.zhengdong.jbx.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TechListAdapter extends RecyclerView.Adapter<TechListAdapter.ViewHolder> {
    public List<NewsTitleModel.DataBean.EcInformationCatBean> datas = null;
    int[] picArr = {
            R.drawable.de, R.drawable.xianhuo2, R.drawable.xianhuo3, R.drawable.xianhuo4, R.drawable.xianhuo5,
            R.drawable.xianhuo6, R.drawable.xianhuo1, R.drawable.de, R.drawable.xianhuo2, R.drawable.xianhuo3
    };
    String[] txtArr = {
            "提升不锈钢拉丝工艺的秘诀",
            "一种含铝奥氏体不锈钢的高温氧化行为",
            "不锈钢线材的优劣判断",
            "304不锈钢早期点蚀的检测方法",
            "不锈钢花纹板类型及应用",
            "自主研发的热轧不锈钢双面复合板",
            "304不锈钢带的表面加工方法及应用",
            "不锈钢表面处理的六大工艺",
            "不锈钢选择点解抛光的原因",
            "304不锈钢在钻井废弃夜中的腐蚀行为"
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
        void OnItemClick(View view, String name, int position, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public TechListAdapter(Context context, List<NewsTitleModel.DataBean.EcInformationCatBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tech_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.pic.setBackgroundResource(picArr[position]);
        viewHolder.txt.setText(txtArr[position]);
        viewHolder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, "", position, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cell)
        LinearLayout cell;
        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.txt)
        TextView txt;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
