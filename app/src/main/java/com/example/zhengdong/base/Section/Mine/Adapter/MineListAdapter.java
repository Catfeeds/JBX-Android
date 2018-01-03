package com.example.zhengdong.base.Section.Mine.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineListAdapter extends RecyclerView.Adapter<MineListAdapter.ViewHolder> {
    public ArrayList<String> datas = null;
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

    String[] mineListTitle = {
            "我的订单", "我的关注", "发票设置", "收货地址", "邀请", "我的客服", "帮助与反馈", "设置"
    };

    int[] mineListPic = {
            R.drawable.my_list_order, R.drawable.my_list_follow, R.drawable.my_list_invoice, R.drawable.my_list_address,
            R.drawable.my_list_invitation, R.drawable.my_list_ser, R.drawable.my_list_help, R.drawable.my_list_set
    };

    @OnClick(R.id.mine_setting_rela)
    public void onViewClicked() {
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public MineListAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 3 || position == 7) {
            viewHolder.mineBottomLay.setVisibility(View.VISIBLE);
            viewHolder.mineLineView.setVisibility(View.GONE);
        } else {
            viewHolder.mineBottomLay.setVisibility(View.GONE);
            viewHolder.mineLineView.setVisibility(View.VISIBLE);
        }


        viewHolder.firstPic.setBackgroundResource(mineListPic[position]);
        viewHolder.firstTxt.setText(mineListTitle[position]);
        viewHolder.mineSettingRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemClick(view, position, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_pic)
        ImageView firstPic;
        @BindView(R.id.first_txt)
        TextView firstTxt;
        @BindView(R.id.mine_setting_rela)
        RelativeLayout mineSettingRela;
        @BindView(R.id.mine_bottom_lay)
        LinearLayout mineBottomLay;
        @BindView(R.id.mine_line_view)
        View mineLineView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
