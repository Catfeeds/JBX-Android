package com.example.zhengdong.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.jbx.R;

import java.util.HashMap;

import com.example.zhengdong.base.Section.Message.Controller.MessageFC;
import com.example.zhengdong.base.Section.Mine.Controller.MineFC;
import com.example.zhengdong.base.Section.News.Controller.NewsFC;
import com.example.zhengdong.base.Section.Shop.Controller.ShopFC;
import com.example.zhengdong.base.Section.Work.Controller.WorkFC;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAC extends AppCompatActivity implements View.OnClickListener {


    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    public static final int PAGE_FIVE = 4;

    HashMap<Integer, Fragment> fragments = new HashMap<>();
    @BindView(R.id.first_pic)
    ImageView firstPic;
    @BindView(R.id.first_txt)
    TextView firstTxt;
    @BindView(R.id.first_lay)
    LinearLayout firstLay;
    @BindView(R.id.second_pic)
    ImageView secondPic;
    @BindView(R.id.second_txt)
    TextView secondTxt;
    @BindView(R.id.second_lay)
    LinearLayout secondLay;
    @BindView(R.id.three_pic)
    ImageView threePic;
    @BindView(R.id.three_txt)
    TextView threeTxt;
    @BindView(R.id.three_lay)
    LinearLayout threeLay;
    @BindView(R.id.four_pic)
    ImageView fourPic;
    @BindView(R.id.four_txt)
    TextView fourTxt;
    @BindView(R.id.four_lay)
    LinearLayout fourLay;
    @BindView(R.id.five_pic)
    ImageView fivePic;
    @BindView(R.id.five_txt)
    TextView fiveTxt;
    @BindView(R.id.five_lay)
    LinearLayout fiveLay;
    private int fragmentContentId = R.id.container;
    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main_ac);
        ButterKnife.bind(this);
        initTabView();
    }

    private void initTabView() {
        fragments.put(PAGE_ONE, new WorkFC());
        fragments.put(PAGE_TWO, new NewsFC());
        fragments.put(PAGE_THREE, new ShopFC());
        fragments.put(PAGE_FOUR, new MessageFC());
        fragments.put(PAGE_FIVE, new MineFC());
        firstLay.setOnClickListener(this);
        secondLay.setOnClickListener(this);
        threeLay.setOnClickListener(this);
        fourLay.setOnClickListener(this);
        fiveLay.setOnClickListener(this);
        FragmentTransaction ft = MainAC.this.getSupportFragmentManager().beginTransaction();
        ft.add(fragmentContentId, fragments.get(PAGE_ONE));
        currentTab = PAGE_ONE;
        firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
        firstPic.setBackgroundResource(R.drawable.icon_job);
        ft.commit();
    }

    private void changeTab(int page) {
        if (currentTab == page) {
            return;
        }
        Fragment fragment = fragments.get(page);
        FragmentTransaction ft = MainAC.this.getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            ft.add(fragmentContentId, fragment);
        }
        ft.hide(fragments.get(currentTab));
        ft.show(fragments.get(page));
        changeButtonStatus(currentTab, false);
        currentTab = page;
        changeButtonStatus(currentTab, true);
        if (!this.isFinishing()) {
            ft.commitAllowingStateLoss();
        }
    }

    private void changeButtonStatus(int index, boolean check) {
        switch (index) {
            case PAGE_ONE:
                if (check) {
                    firstPic.setBackgroundResource(R.drawable.icon_job);
                    firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    firstPic.setBackgroundResource(R.drawable.icon_jobt);
                    firstTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_TWO:
                if (check) {
                    secondPic.setBackgroundResource(R.drawable.icon_new);
                    secondTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    secondPic.setBackgroundResource(R.drawable.icon_newt);
                    secondTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_THREE:
                if (check) {
                    threePic.setBackgroundResource(R.drawable.icon_shop);
                    threeTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    threePic.setBackgroundResource(R.drawable.icon_shopt);
                    threeTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_FOUR:
                if (check) {
                    fourPic.setBackgroundResource(R.drawable.icon_infor);
                    fourTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    fourPic.setBackgroundResource(R.drawable.icon_infort);
                    fourTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            case PAGE_FIVE:
                if (check) {
                    fivePic.setBackgroundResource(R.drawable.icon_my);
                    fiveTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
                } else {
                    fivePic.setBackgroundResource(R.drawable.icon_myt);
                    fiveTxt.setTextColor(ContextCompat.getColor(this, R.color.gray_40));
                }
                break;
            default:
                firstPic.setBackgroundResource(R.drawable.icon_job);
                firstTxt.setTextColor(ContextCompat.getColor(this, R.color.skyblue));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_lay:
                changeTab(PAGE_ONE);
                break;
            case R.id.second_lay:
                changeTab(PAGE_TWO);
                break;
            case R.id.three_lay:
                changeTab(PAGE_THREE);
                break;
            case R.id.four_lay:
                changeTab(PAGE_FOUR);
                break;
            case R.id.five_lay:
                changeTab(PAGE_FIVE);
                break;
            default:
                break;
        }
    }
}
