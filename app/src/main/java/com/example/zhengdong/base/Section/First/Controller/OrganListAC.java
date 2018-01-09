package com.example.zhengdong.base.Section.First.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.First.Adapter.CommonSecondListAdapter;
import com.example.zhengdong.base.Section.First.Model.SecondOrganListModel;
import com.example.zhengdong.base.Section.First.View.IndexViewPager;
import com.example.zhengdong.base.Section.First.View.SearchEditText;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrganListAC extends AppCompatActivity {


    @BindView(R.id.navi_title_lay)
    LinearLayout naviTitleLay;
    @BindView(R.id.navi_right_txt)
    TextView naviRightTxt;
    @BindView(R.id.navi_right_lay)
    LinearLayout naviRightLay;
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    IndexViewPager vp;
    ArrayList<OrganListFC> mFragment = new ArrayList<>();
    //    public SlidePagerAdapter slidePagerAdapter;
    public String dept_id = "";
    @BindView(R.id.second_rv)
    RecyclerView secondRv;
    @BindView(R.id.add_people_btn)
    Button addPeopleBtn;
    @BindView(R.id.add_partment_btn)
    Button addPartmentBtn;
    @BindView(R.id.navi_title_txt)
    TextView naviTitleTxt;
    @BindView(R.id.search_view)
    SearchEditText searchView;
    @BindView(R.id.navi_back_lay)
    LinearLayout naviBackLay;
    private String token;
    private String org_id;
    private String dept_name = "";
    private SecondOrganListModel secondOrganListModel;
    private String parent_dept_id = "";
    private CommonSecondListAdapter commonSecondListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organ_list_ac);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        EventBus.getDefault().register(this);
        dept_id = getIntent().getStringExtra("dept_id");
        dept_name = getIntent().getStringExtra("title");
        parent_dept_id = getIntent().getStringExtra("parent_dept_id");
//        SharedPreferencesUtils.setParam(OrganListAC.this,UrlUtils.APP_DEPT_ID,dept_id);

        initNavigationView();
        // 初始化数据源
        initVPData(dept_id);
//        initVPview();
    }

    // 初始胡标题栏
    private void initNavigationView() {
        naviBackLay.setVisibility(View.VISIBLE);
        naviTitleTxt.setText(dept_name);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //完成自己的事件
                    initSearchViewData();
                }
                return false;
            }
        });
    }
    // 搜索单个的数据
    private void initSearchViewData() {

    }

//    @Subscribe
//    public void onEventMainThread(PassEvent event) {
//        if (!TextUtils.isEmpty(event.getDept_id())) {
//            naviTitleTxt.setText(event.getName());
//            initVPData(event.getDept_id());
////            addPageItem(new OrganListFC());
//        }
//    }

    public void initVPData(final String dept_ids) {
        HashMap<String, String> map = new HashMap<>();
        map.put("dept_id", dept_ids);
        HttpRequest.URL_JSONGET_REQUEST(this, UrlUtils.QUERY_MENBER_AND_DEPT_LIST_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                secondOrganListModel = new Gson().fromJson(response, SecondOrganListModel.class);
                if (secondOrganListModel.getCode() == 200) {
                    XToast.show(getBaseContext(), "部门人员获取成功!");
                    initRecycleView();
//                    EventBus.getDefault().post(new SecondEvent(parent_dept_id,dept_ids,dept_name,secondOrganListModel.getData().getDept()));
                } else {
                    XToast.show(getBaseContext(), "" + secondOrganListModel.getMsg());
                }
            }

            @Override
            public void NOCONNECTION() {

            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }
        });
    }

    // 初始化rv
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        secondRv.setLayoutManager(linearLayoutManager);
        commonSecondListAdapter = new CommonSecondListAdapter(this, secondOrganListModel.getData().getDept());
        secondRv.setAdapter(commonSecondListAdapter);
        commonSecondListAdapter.setOnItemClickListener(new CommonSecondListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, String deptid, String name) {
                SharedPreferencesUtils.setParam(OrganListAC.this, "second", deptid);
                Intent intent = new Intent(OrganListAC.this, OrganListAC.class);
                intent.putExtra("dept_id", deptid);
                intent.putExtra("title", name);
//                intent.putExtra("parent_dept_id",parent_dept_id);
                startActivity(intent);
            }
        });
    }

//    // 初始化vp
//    public void initVPview() {
//        // 标题若干
//        naviTitleTxt = (TextView) findViewById(R.id.navi_title_txt);
//        naviTitleTxt.setText(dept_name);
//        naviBackLay.setVisibility(View.VISIBLE);
//        naviBackLay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (vp.getCurrentItem() == 0) {
//                    finish();
//                } else {
//                    vp.setCurrentItem(vp.getCurrentItem() - 1);
//                }
//            }
//        });
//
//        vp = (IndexViewPager) findViewById(R.id.vp);
//        OrganListFC fragment = OrganListFC.getInstance();
//        mFragment.add(fragment);
//        slidePagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
//        vp.setAdapter(slidePagerAdapter);
//
//    }
//
//    // 添加新的fragment
//    public void addPageItem(OrganListFC fragment) {
//        mFragment.add(fragment);
//        slidePagerAdapter.notifyDataSetChanged();
//        setPageItem(getCurrentPage()+1);
//    }
//    //指定ViewPager当前页
//    public void setPageItem(int index) {
//        vp.setCurrentItem(index);
//    }
//
//    //获取ViewPager当前页
//    public int getCurrentPage() {
//        return vp.getCurrentItem();
//    }
//
//    public class SlidePagerAdapter extends FragmentPagerAdapter {
//        public SlidePagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragment.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragment.size();
//        }
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //返回
//            if (vp.getCurrentItem() == 0) {
//                finish();
//            } else {
//                // 返回的时候刷新数据
//                int current = vp.getCurrentItem()-1;
//                LogUtil.e("当前的页面为"+current);
//                vp.setCurrentItem(vp.getCurrentItem() - 1);
//
//            }
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.add_people_btn, R.id.add_partment_btn, R.id.navi_back_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_people_btn:
                break;
            case R.id.add_partment_btn:
                Intent intent = new Intent(OrganListAC.this, AddtionalAC.class);
                intent.putExtra("type", 3);
                startActivity(intent);
                break;
            case R.id.navi_back_lay:
                finish();
                break;
            default:
                break;
        }
    }

}
