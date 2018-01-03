package com.example.zhengdong.base.Section.Work.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.ImageAsset;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Work.View.RecyclerBanner;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.ViewHolder> {
    public ArrayList<String> datas = null;
    public ArrayList<Integer> picArr = null;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();


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
        void OnItemClick(View view, int i, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public WorkListAdapter(Context context, ArrayList<String> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.work_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.banner.setVisibility(View.VISIBLE);
            urls.add(new Entity("",""));
            urls.add(new Entity("",""));
            urls.add(new Entity("",""));
            viewHolder.bannerRv.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
                @Override
                public void onClick(RecyclerBanner.BannerEntity entity) {
                    LogUtil.e("网址为" + entity.getLink());

                }
            });
            viewHolder.bannerRv.setDatas(urls);
        } else {
            viewHolder.banner.setVisibility(View.GONE);
        }
        viewHolder.firstTxt.setText(datas.get(position));
        txtArr = new ArrayList<>();
        srcArr = new ArrayList<>();
        if (position == 0) {
            commonTxt = ImageAsset.FIRST_TXT;
            commonPic = ImageAsset.FIRST_PIC;
        } else if (position == 1) {
            commonTxt = ImageAsset.SECOND_TXT;
            commonPic = ImageAsset.SECOND_PIC;
        } else if (position == 2) {
            commonTxt = ImageAsset.THREE_TXT;
            commonPic = ImageAsset.THREE_PIC;
        } else if (position == 3) {
            commonTxt = ImageAsset.FOUR_TXT;
            commonPic = ImageAsset.FOUR_PIC;
        } else if (position == 4) {
            commonTxt = ImageAsset.FIVE_TXT;
            commonPic = ImageAsset.FIVE_PIC;
        }
        for (int i = 0; i < commonTxt.length; i++) {
            txtArr.add(commonTxt[i]);
        }
        for (int i = 0; i < commonPic.length; i++) {
            srcArr.add(commonPic[i]);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        viewHolder.workItemRv.setLayoutManager(gridLayoutManager);
        WorkItemAdapter workItemAdapter = new WorkItemAdapter(mContext, txtArr, srcArr);
        viewHolder.workItemRv.setAdapter(workItemAdapter);
        workItemAdapter.setOnItemClickListener(new WorkItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int positionx, String name) {
                mOnItemClickListener.OnItemClick(view, position,positionx, name);
            }
        });





    }

    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;

        public Entity(String url, String link) {
            this.url = url;
            this.link = link;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getLink() {
            return link;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        LinearLayout banner;
        @BindView(R.id.first_txt)
        TextView firstTxt;
        @BindView(R.id.work_item_rv)
        RecyclerView workItemRv;
        @BindView(R.id.banner_rv)
        RecyclerBanner bannerRv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
