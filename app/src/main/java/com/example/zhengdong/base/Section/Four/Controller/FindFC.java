/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.zhengdong.base.Macro.AlphaViewUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.comView.SoftKeyBoardListener;
import com.example.zhengdong.base.Section.Four.Adapter.FindListAdapter;
import com.example.zhengdong.base.Section.Four.View.FindSearchEdtView;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFC extends Fragment {


    @BindView(R.id.search_view)
    FindSearchEdtView findSearchEdt;
    Unbinder unbinder;
    @BindView(R.id.find_rv)
    RecyclerView findRv;
    private View views;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (views == null) {
            views = inflater.inflate(R.layout.fragment_find_fc, container, false);
            unbinder = ButterKnife.bind(this, views);
            initRecycleView();
            initSearchView();

        }
        return views;
    }

    private void initSearchView() {
        // 监听键盘事件
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                // 设置灰色区域
                AlphaViewUtils.setBackGroundLevel(getActivity(), 0.6f);
            }

            @Override
            public void keyBoardHide(int height) {
                AlphaViewUtils.setBackGroundLevel(getActivity(), 1.0f);
            }
        });
        getActivity().getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.e("点击了Xxx");
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
        });
        findSearchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //完成自己的事件
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                }
                return false;
            }
        });

    }

    /**
     * 初始化RV
     *
     * @param
     */
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findRv.setLayoutManager(linearLayoutManager);
        FindListAdapter findListAdapter = new FindListAdapter(getActivity(), null);
        findRv.setAdapter(findListAdapter);
        findListAdapter.setOnItemClickListener(new FindListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int gridviewPosition) {
                Intent intent = new Intent(getActivity(), FindSecondAC.class);
                intent.putExtra("gridType", gridviewPosition);
                intent.putExtra("gridName",name);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
