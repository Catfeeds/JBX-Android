package com.example.zhengdong.base.Section.Three.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailListAdapter extends RecyclerView.Adapter<ShopDetailListAdapter.ViewHolder> {

    private  String[] price;
    private String[] firstTxt = null;
    private int type = -1;
    public int[] datas = null;
    public ArrayList<Integer> picArr = null;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private ArrayList<String> txtArr = null;
    private ArrayList<Integer> srcArr = null;
    private String[] commonTxt;
    private int[] commonPic;

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public ShopDetailListAdapter(Context context, int[] datas, int position, String[] arr, String[] firstArr) {
        mContext = context;
        this.datas = datas;
        this.type = position;
        this.firstTxt = firstArr;
        this.price = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_detail_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        if (type == 1||type == 2) {
            viewHolder.price.setVisibility(View.GONE);
        }else {
            viewHolder.price.setVisibility(View.VISIBLE);
        }

        viewHolder.price.setText(price[position]);
        viewHolder.txt.setText(firstTxt[position]);
        viewHolder.pic.setBackgroundResource(datas[position]);
        viewHolder.contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, position, "");
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contentView)
        LinearLayout contentView;
        @BindView(R.id.pic)
        ImageView pic;
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.price)
        TextView price;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
