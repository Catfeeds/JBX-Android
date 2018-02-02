/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.base.Section.Five.Model.OrderDetailModel;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailListAdapter extends RecyclerView.Adapter<OrderDetailListAdapter.ViewHolder> {
    public OrderDetailModel.DataBean datas = null;


    /**
     * 修改 增加context
     *
     * @param datas
     * @param context
     */
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(View view, int position, String name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public OrderDetailListAdapter(Context context, OrderDetailModel.DataBean datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_detail_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if (position == 0) {
            viewHolder.headerCell.setVisibility(View.VISIBLE);
            viewHolder.dProjectNameTxt.setText("项目名称：" + datas.getArgsMap().getName());
            viewHolder.dMaterialkindTxt.setText("材质种类：" + datas.getArgsMap().getMaterialType());
            viewHolder.dMaterialNumTxt.setText("板材总数量(张)：" + datas.getArgsMap().getMaterialNum());
            viewHolder.dPartsumTxt.setText("部件总面积(㎡)：" + datas.getArgsMap().getTotalGraphicalArea());
            viewHolder.dMaterialsumTxt.setText("板材总面积(㎡)：" + datas.getArgsMap().getConsumMaterialArea());
            viewHolder.dPartnumTxt.setText("部件数量(个)：" + datas.getArgsMap().getGraphical_num());
            String typeList = "";
            for (int i = 0;i<datas.getArgsMap().getByTypeList_2().size();i++){
                typeList +=  "\n"+ (i+1)+"、"+ datas.getArgsMap().getByTypeList_2().get(i);
            }
            viewHolder.dMaterialTxt.setText("" + typeList);
//            viewHolder.dHeightTxt.setText("厚度：");
//            viewHolder.dMaterialColorTxt.setText("板材颜色：");
//            viewHolder.dMaterialGgTxt.setText("材料规格：");
            viewHolder.dCutNumTxt.setText("下料刀数(刀)：" + datas.getArgsMap().getCountcuttingnum());
            viewHolder.dRenderTxt.setText("折弯(刀)：" + datas.getArgsMap().getCountbendnum());
            viewHolder.dRiverTxt.setText("刨槽(m)：" + datas.getArgsMap().getCountgroovingnum());
        } else {
            viewHolder.headerCell.setVisibility(View.GONE);
        }
        viewHolder.iMaterialTxt.setText("材质：" + datas.getTempGraphicalSepcList().get(position).getTypeChina());
        viewHolder.iHeightTxt.setText("厚度：" + datas.getTempGraphicalSepcList().get(position).getThickness_num());
        viewHolder.iColorTxt.setText("颜色：" + datas.getTempGraphicalSepcList().get(position).getColorChina());
        viewHolder.iItchTxt.setText("内外尺：" + datas.getTempGraphicalSepcList().get(position).getIssizeChina());
        viewHolder.iLineTxt.setText("是否拉丝：" + datas.getTempGraphicalSepcList().get(position).getIs_linesChina());
        viewHolder.iTowardTxt.setText("朝向：" + datas.getTempGraphicalSepcList().get(position).getOrientationChina());
        viewHolder.iArtTxt.setText("工艺：" + datas.getTempGraphicalSepcList().get(position).getCraftsChina());
        String gg = "规格：\n";
        for (int i = 0; i < datas.getTempGraphicalSepcList().get(position).getWidthAndHeightList().size(); i++) {
            gg += datas.getTempGraphicalSepcList().get(position).getWidthAndHeightList().get(i) + "   " + datas.getTempGraphicalSepcList().get(position).getGrapNameList().get(i) + "\n";
        }
        viewHolder.iGgTxt.setText(gg);
        GlideApp.with(mContext)
                .load(UrlUtils.BASE_PIC_URL + datas.getTempGraphicalSepcList().get(position).getFilepathList().get(0))
                .placeholder(R.drawable.placerholder)
                .centerCrop()
                .into(viewHolder.firstPic);

    }

    @Override
    public int getItemCount() {
        return datas.getTempGraphicalSepcList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.first_pic)
        ImageView firstPic;
        @BindView(R.id.d_projectName_txt)
        TextView dProjectNameTxt;
        @BindView(R.id.d_materialkind_txt)
        TextView dMaterialkindTxt;
        @BindView(R.id.d_material_num_txt)
        TextView dMaterialNumTxt;
        @BindView(R.id.d_partsum_txt)
        TextView dPartsumTxt;
        @BindView(R.id.d_materialsum_txt)
        TextView dMaterialsumTxt;
        @BindView(R.id.d_partnum_txt)
        TextView dPartnumTxt;
        @BindView(R.id.d_material_txt)
        TextView dMaterialTxt;
        @BindView(R.id.d_height_txt)
        TextView dHeightTxt;
        @BindView(R.id.d_material_color_txt)
        TextView dMaterialColorTxt;
        @BindView(R.id.d_material_gg_txt)
        TextView dMaterialGgTxt;
        @BindView(R.id.d_cut_num_txt)
        TextView dCutNumTxt;
        @BindView(R.id.d_render_txt)
        TextView dRenderTxt;
        @BindView(R.id.d_river_txt)
        TextView dRiverTxt;
        @BindView(R.id.header_cell)
        LinearLayout headerCell;

        @BindView(R.id.i_material_txt)
        TextView iMaterialTxt;
        @BindView(R.id.i_height_txt)
        TextView iHeightTxt;
        @BindView(R.id.i_color_txt)
        TextView iColorTxt;
        @BindView(R.id.i_itch_txt)
        TextView iItchTxt;
        @BindView(R.id.i_toward_txt)
        TextView iTowardTxt;
        @BindView(R.id.i_line_txt)
        TextView iLineTxt;
        @BindView(R.id.i_art_txt)
        TextView iArtTxt;
        @BindView(R.id.i_gg_txt)
        TextView iGgTxt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
