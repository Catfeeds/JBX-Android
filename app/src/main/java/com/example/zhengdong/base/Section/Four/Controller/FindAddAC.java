/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.zhengdong.jbx.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindAddAC extends AppCompatActivity {

    @BindView(R.id.add_word_view)
    ScrollView addWordView;
    @BindView(R.id.add_hr_view)
    ScrollView addHrView;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    @BindView(R.id.right_pic)
    ImageView rightPic;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.add_word_lay)
    LinearLayout addWordLay;
    @BindView(R.id.aw_title_edt)
    EditText awTitleEdt;
    @BindView(R.id.aw_name_edt)
    EditText awNameEdt;
    @BindView(R.id.aw_sex_rel)
    RelativeLayout awSexRel;
    @BindView(R.id.aw_birthday_edt)
    RelativeLayout awBirthdayEdt;
    @BindView(R.id.aw_salary_rel)
    RelativeLayout awSalaryRel;
    @BindView(R.id.aw_year_rel)
    RelativeLayout awYearRel;
    @BindView(R.id.aw_phone_edt)
    EditText awPhoneEdt;
    @BindView(R.id.aw_offer_rel)
    RelativeLayout awOfferRel;
    @BindView(R.id.aw_intro_edt)
    EditText awIntroEdt;
    @BindView(R.id.aw_save_btn)
    Button awSaveBtn;
    @BindView(R.id.ah_zhiwei_rel)
    RelativeLayout ahZhiweiRel;
    @BindView(R.id.ah_salary_rel)
    RelativeLayout ahSalaryRel;
    @BindView(R.id.ah_fl_rel)
    RelativeLayout ahFlRel;
    @BindView(R.id.ah_workyear_rel)
    RelativeLayout ahWorkyearRel;
    @BindView(R.id.ah_people_rel)
    RelativeLayout ahPeopleRel;
    @BindView(R.id.ah_descript_edt)
    EditText ahDescriptEdt;
    @BindView(R.id.ah_release_btn)
    Button ahReleaseBtn;
    private int currentID = -1;
    private ArrayList<String> options1Items = null;
    private ArrayList<String> salaryFirstOptionsItems=null;
    private ArrayList<List<String>> salarySecondOptionsItems=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_add_ac);
        ButterKnife.bind(this);
        currentID = getIntent().getIntExtra("currentID", -1);
        initNavigationView();
    }

    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        if (currentID == 1) {
            naviTitleTxt.setText("添加简历");
            addWordView.setVisibility(View.VISIBLE);
        } else if (currentID == 3) {
            naviTitleTxt.setText("发布招聘");
            addHrView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.navi_back_lay)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.aw_sex_rel, R.id.aw_birthday_edt, R.id.aw_salary_rel, R.id.aw_year_rel, R.id.aw_offer_rel, R.id.aw_save_btn, R.id.ah_zhiwei_rel, R.id.ah_salary_rel, R.id.ah_fl_rel, R.id.ah_workyear_rel, R.id.ah_people_rel, R.id.ah_release_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aw_sex_rel:
                options1Items = new ArrayList<>();
                options1Items.add("请选择性别");
                options1Items.add("男");
                options1Items.add("女");
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1);
                    }
                }).setContentTextSize(15)
                        .setSubCalSize(15)
                        .build();
                pvOptions.setPicker(options1Items);
                pvOptions.show();

                break;
            case R.id.aw_birthday_edt:
                //时间选择器
                TimePickerView pvTime = new TimePickerView
                        .Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
//                        tvTime.setText(getTime(date));
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setContentSize(15)
                        .setSubCalSize(15)
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();

                // 可选择的样式
//                 .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
//                    .setCancelText("Cancel")//取消按钮文字
//                    .setSubmitText("Sure")//确认按钮文字
//                    .setContentSize(18)//滚轮文字大小
//                    .setTitleSize(20)//标题文字大小
//                    .setTitleText("Title")//标题文字
//                    .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                    .isCyclic(true)//是否循环滚动
//                    .setTitleColor(Color.BLACK)//标题文字颜色
//                    .setSubmitColor(Color.BLUE)//确定按钮文字颜色
//                    .setCancelColor(Color.BLUE)//取消按钮文字颜色
//                    .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                    .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
//                    .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                    .setRangDate(startDate,endDate)//起始终止年月日设定
//                    .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
//                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                    .isDialog(true)//是否显示为对话框样式
//                    .build();

//                //条件选择器
//                OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//                    @Override
//                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
//                        //返回的分别是三个级别的选中位置
//                        String tx = options1Items.get(options1).getPickerViewText()
//                                + options2Items.get(options1).get(option2)
//                                + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                        tvOptions.setText(tx);
//                    }
//                }).build();
//                pvOptions.setPicker(options1Items, options2Items, options3Items);
//                pvOptions.show();

                break;
            case R.id.aw_salary_rel:
                salaryFirstOptionsItems = new ArrayList<>();
                salarySecondOptionsItems = new ArrayList<>();
                salaryFirstOptionsItems.add("面议");
                ArrayList<String> list = new ArrayList<>();
                for (int i = 1;i<50;i++){
                    salaryFirstOptionsItems.add(i+"");
                    for (int k = 2;k<51;k++){
                        list.add(k+"");
                    }
                    salarySecondOptionsItems.add(list);
                }
                OptionsPickerView salaryOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1);
                    }
                }).setContentTextSize(15)
                        .setSubCalSize(15)
                        .setLabels("K","K","")
                        .setLinkage(true)
                        .build();
                salaryOptions.setPicker(salaryFirstOptionsItems,salarySecondOptionsItems);
                salaryOptions.show();
                break;
            case R.id.aw_year_rel:
                options1Items = new ArrayList<>();
                options1Items.add("请选择工作年限");
                options1Items.add("应届生");
                for (int i = 1;i<20;i++){
                    options1Items.add(i+"年");
                }
                options1Items.add("其他");
                //条件选择器
                OptionsPickerView yearOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1);
                    }
                }).setContentTextSize(15)
                        .setSubCalSize(15)
                        .build();
                yearOptions.setPicker(options1Items);
                yearOptions.show();
                break;
            case R.id.aw_offer_rel:
                break;
            case R.id.aw_save_btn:
                break;
            case R.id.ah_zhiwei_rel:
                break;
            case R.id.ah_salary_rel:
                break;
            case R.id.ah_fl_rel:
                break;
            case R.id.ah_workyear_rel:
                break;
            case R.id.ah_people_rel:
                break;
            case R.id.ah_release_btn:
                break;
            default:
                break;
        }
    }
}
