package com.example.zhengdong.base.Section.Work.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Work.Adapter.CommonSecondListAdapter;
import com.example.zhengdong.base.Section.Work.Events.PassEvent;
import com.example.zhengdong.base.Section.Work.Events.SecondEvent;
import com.example.zhengdong.base.Section.Work.Model.SecondOrganListModel;
import com.example.zhengdong.jbx.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrganListFC extends Fragment {


    String str;
    @BindView(R.id.second_rv)
    RecyclerView secondRv;
    @BindView(R.id.add_people_btn)
    Button addPeopleBtn;
    @BindView(R.id.add_partment_btn)
    Button addPartmentBtn;
    private SecondOrganListModel secondOrganListModel;
    private CommonSecondListAdapter commonSecondListAdapter;
    private int index;
    private String parent_dept_id;
//    private String dept_id="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organ_list_fc, container, false);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        EventBus.getDefault().register(this);
        // 获取数据
        return view;
    }
    @Subscribe //这个必须存在,不然程序会蹦
    public void onEventMainThread(SecondEvent event) {
        parent_dept_id = event.getParent_dept_id();
        initCommonRV(event.getDeptBean());
    }

    // 初始化界面
    private void initCommonRV(List<SecondOrganListModel.DataBean.DeptBean> deptBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        secondRv.setLayoutManager(linearLayoutManager);
        commonSecondListAdapter = new CommonSecondListAdapter(getActivity(), deptBean);
        secondRv.setAdapter(commonSecondListAdapter);
        commonSecondListAdapter.setOnItemClickListener(new CommonSecondListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int i, String deptid, String deptname) {
                SharedPreferencesUtils.setParam(getActivity(), "second",deptid);
                EventBus.getDefault().post(new PassEvent(deptname,deptid));
                Intent intent = new Intent(getActivity(),getActivity().getClass());
                startActivity(intent);
            }
        });
    }

    //设置一下测试数据做测试
    public void setTestData(String str) {
        this.str = str;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 传值
    public static OrganListFC getInstance() {
        OrganListFC mf = new OrganListFC();
        return mf;
    }


    @OnClick({R.id.add_people_btn, R.id.add_partment_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_people_btn:
                break;
            case R.id.add_partment_btn:
                Intent intent = new Intent(getActivity(),AddtionalAC.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
