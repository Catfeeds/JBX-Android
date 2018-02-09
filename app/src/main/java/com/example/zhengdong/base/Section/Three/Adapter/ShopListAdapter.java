package com.example.zhengdong.base.Section.Three.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {
    public ArrayList<String> datas = null;
    public ArrayList<Integer> picArr = null;
    String [] shopTitleArr = {
            "今日现货","优质供应商","优质加工商"
    };
    int [] shopPicArr = {
            R.drawable.shop_tit_spot,R.drawable.shop_tit_sup,R.drawable.shop_tab_con
    };

    int[] shopPic = {
            R.drawable.shangjia1, R.drawable.shangjia2, R.drawable.shangjia3,
            R.drawable.shangjia4, R.drawable.shangjia5, R.drawable.shangjia6
    };
    int[] xianPic = {
            R.drawable.xianhuo1, R.drawable.xianhuo2, R.drawable.xianhuo3,
            R.drawable.xianhuo4, R.drawable.xianhuo5, R.drawable.xianhuo6
    };
    int[] shang3Pic = {
            R.drawable.shang1, R.drawable.shang2, R.drawable.shang3,
            R.drawable.shang4, R.drawable.shang5, R.drawable.shang6
    };

    String [] firstArr = {
            "201不锈钢板","不锈钢方管","304不锈钢板",
            "不锈钢圆管","不锈钢圆管","不锈钢卷"
    };
    String [] priceArr = {
            "¥ 16.8/kg","¥ 10.0/kg","¥ 16.9/kg",
            "¥ 18.7/kg","¥ 16.0/kg","¥ 16.8/kg"
    };
    String [] name = {
            "金龙不锈钢","双兴隆顺不锈钢","鑫利恒不锈钢","宏昌烨不锈钢",
            "兴伟企业","鑫晨不锈钢"
    };

    String [] name1 = {
            "金顺不锈钢","奥胜火不锈钢","华业不锈钢","金润德不锈钢",
            "铭一不锈钢","天仁不锈钢"
    };


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


    public ShopListAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0){
            viewHolder.shopHeaderLay.setVisibility(View.VISIBLE);
        }else {
            viewHolder.shopHeaderLay.setVisibility(View.GONE);
        }
        viewHolder.titleFirstPic.setBackgroundResource(shopPicArr[position]);
        viewHolder.firstTitleTxt.setText(shopTitleArr[position]);
        viewHolder.shopClassRv.setNestedScrollingEnabled(false);
        if (position == 0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.shopClassRv.setLayoutManager(linearLayoutManager);
            ShopDetailListAdapter shopDetailListAdapter = new ShopDetailListAdapter(mContext,xianPic,position,priceArr,firstArr);
            viewHolder.shopClassRv.setAdapter(shopDetailListAdapter);
            shopDetailListAdapter.setOnItemClickListener(new ShopDetailListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, String name) {

                }
            });
        }else if (position == 1){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.shopClassRv.setLayoutManager(linearLayoutManager);
            ShopDetailListAdapter shopDetailListAdapter = new ShopDetailListAdapter(mContext,shopPic, position, firstArr, name);
            viewHolder.shopClassRv.setAdapter(shopDetailListAdapter);
            shopDetailListAdapter.setOnItemClickListener(new ShopDetailListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, String name) {

                }
            });
        }else if (position == 2){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            viewHolder.shopClassRv.setLayoutManager(linearLayoutManager);
            ShopDetailListAdapter shopDetailListAdapter = new ShopDetailListAdapter(mContext,shang3Pic, position, firstArr, name1);
            viewHolder.shopClassRv.setAdapter(shopDetailListAdapter);
            shopDetailListAdapter.setOnItemClickListener(new ShopDetailListAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position, String name) {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_find_goods_lay)
        LinearLayout shopFindGoodsLay;
        @BindView(R.id.shop_find_supply_lay)
        LinearLayout shopFindSupplyLay;
        @BindView(R.id.shop_find_make_lay)
        LinearLayout shopFindMakeLay;
        @BindView(R.id.shop_header_lay)
        LinearLayout shopHeaderLay;
        @BindView(R.id.title_first_pic)
        ImageView titleFirstPic;
        @BindView(R.id.first_title_txt)
        TextView firstTitleTxt;
        @BindView(R.id.first_go_lay)
        LinearLayout firstGoLay;
        @BindView(R.id.shop_class_rv)
        RecyclerView shopClassRv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
