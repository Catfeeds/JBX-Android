package com.example.zhengdong.base.Section.Login.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.Section.Login.Model.OrganListModel;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrganListAdapter extends RecyclerView.Adapter<OrganListAdapter.ViewHolder> {
    public ArrayList<OrganListModel> datas = null;



    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    @OnClick(R.id.check_yes_no_pic)
    public void onViewClicked() {
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int i, String position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public OrganListAdapter(Context context, ArrayList<OrganListModel> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.organ_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (datas.get(position).getSelect()) {
            viewHolder.checkYesNoPic.setBackgroundResource(R.drawable.organ_yes);
        } else {
            viewHolder.checkYesNoPic.setBackgroundResource(R.drawable.organ_no);
        }
        viewHolder.organNameTxt.setText(datas.get(position).getOtherDataBeanList().getName());
        viewHolder.checkYesNoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datas.get(position).getSelect()) {
                    datas.get(position).setSelect(false);
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setSelect(false);
                    }
                    datas.get(position).setSelect(true);
                }
                notifyDataSetChanged();

                mOnItemClickListener.OnItemClick(view, position, datas.get(position).getOtherDataBeanList().getName(), datas.get(position).getOtherDataBeanList().getOrg_id());

            }
        });
        viewHolder.bgLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datas.get(position).getSelect()) {
                    datas.get(position).setSelect(false);
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        datas.get(i).setSelect(false);
                    }
                    datas.get(position).setSelect(true);
                }
                notifyDataSetChanged();
                mOnItemClickListener.OnItemClick(view, position, datas.get(position).getOtherDataBeanList().getName(), datas.get(position).getOtherDataBeanList().getOrg_id());

            }
        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.check_yes_no_pic)
        ImageView checkYesNoPic;
        @BindView(R.id.organ_name_txt)
        TextView organNameTxt;
        @BindView(R.id.bg_lay)
        RelativeLayout bgLay;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
