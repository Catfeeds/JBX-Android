package com.example.zhengdong.base.Section.News.Controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.News.Evevts.NewsEvent;
import com.example.zhengdong.base.Section.News.Model.NewsTitleModel;
import com.example.zhengdong.jbx.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFC extends Fragment implements OnTabSelectListener {


    @BindView(R.id.news_search_edt)
    EditText newsSearchEdt;
    @BindView(R.id.tab_lay)
    SlidingTabLayout tab_lay;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private View view;
    private Context mContext = getActivity();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final ArrayList<String> mTitles = new ArrayList();
    ArrayList<String> mNewsIDs = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private String currentNewsID = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news_fc, container, false);
            unbinder = ButterKnife.bind(this, view);
            // 初始化TabLayout
            initTabLayData();
            initSearchEdtView();
        }
        return view;
    }

    // 初始化标题栏数据
    private void initTabLayData() {
        HttpRequest.URL_JSONGETNOPARAM_REQUEST(getActivity(), UrlUtils.NEWS_TITLE_LIST, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                NewsTitleModel newsTitleModel = new Gson().fromJson(response, NewsTitleModel.class);
                if (newsTitleModel.getCode() == 200) {
                    for (int i = 0; i < newsTitleModel.getData().size(); i++) {
                        mTitles.add(newsTitleModel.getData().get(i).getKey_name());
                        mNewsIDs.add(String.valueOf(newsTitleModel.getData().get(i).getId()));
                    }
                    initTabLayView();
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

    private void initSearchEdtView() {
        newsSearchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //完成自己的事件
                    LogUtil.e("搜索了" + newsSearchEdt.getText().toString());
                    String searchTxt = newsSearchEdt.getText().toString();
                    EventBus.getDefault().post(new NewsEvent(currentNewsID, searchTxt));
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    private void initTabLayView() {
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(NewsListFC.getInstance(mNewsIDs.get(i)));
        }
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        tab_lay.setViewPager(vp);
        currentNewsID = mNewsIDs.get(0);
        tab_lay.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                LogUtil.e("选中的页面为",mTitles.get(position));
                currentNewsID = mNewsIDs.get(position);
            }

            @Override
            public void onTabReselect(int position) {
//                LogUtil.e("选中两次的页面为",mTitles.get(position));
                currentNewsID = mNewsIDs.get(position);
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
                currentNewsID = mNewsIDs.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        vp.setCurrentItem(4);
//        tab_lay.showDot(4);
    }

    @Override
    public void onTabSelect(int position) {
//        XToast.show(getActivity(),"选中了第"+position+"个!");
    }

    @Override
    public void onTabReselect(int position) {
//        XToast.show(getActivity(),"选中了第"+position+"个!");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

}
