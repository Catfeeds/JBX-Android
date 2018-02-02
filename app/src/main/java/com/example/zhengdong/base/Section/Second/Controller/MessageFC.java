package com.example.zhengdong.base.Section.Second.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Second.Adapter.BoutiqueListAdapter;
import com.example.zhengdong.base.Section.Second.Model.BoutiqueItemListModel;
import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFC extends Fragment {


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
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    Unbinder unbinder;

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab_lay)
    SlidingTabLayout tab_lay;
    private View view;
    private ArrayList<BoutiqueListFC> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private BoutiqueItemListModel boutiqueItemListModel;
    private List<BoutiqueItemListModel.DataBean> dataSource;
    private ArrayList<String> mTitles;
    private ArrayList<String> mID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_message_fc, container, false);
            unbinder = ButterKnife.bind(this, view);
            initNavigationView();
            initItemDataSource();
        }
        return view;
    }

    private void initItemDataSource() {
        HttpRequest.URL_JSONGETNOPARAM_REQUEST(getActivity(), UrlUtils.BOUTIQUE_ITEM_LIST_URL, "加载中...", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                mTitles = new ArrayList<>();
                mID = new ArrayList<>();
                boutiqueItemListModel = new Gson().fromJson(response,BoutiqueItemListModel.class);
                if (boutiqueItemListModel.getCode() == 200){
                    dataSource = boutiqueItemListModel.getData();
                    for (int i = 0;i<dataSource.size();i++){
                        mTitles.add(dataSource.get(i).getName());
                        mID.add(dataSource.get(i).getId());
                    }
                    initTabLayView();
                }else {
                    XToast.show(getContext(),""+boutiqueItemListModel.getMsg());
                }
            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {

            }
        });
    }

    private void initTabLayView() {
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(BoutiqueListFC.getInstance(mID.get(i)));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tab_lay.setViewPager(vp);
        tab_lay.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.e("当前滚动的页面为"+position);
            }

            @Override
            public void onPageSelected(int position) {
//                LogUtil.e("当前选中的页面为"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        vp.setCurrentItem(4);
//        tab_lay.showDot(4);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }



    private void initNavigationView() {
        naviTitleTxt.setText("精品加工图");
        naviRightTxt.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
