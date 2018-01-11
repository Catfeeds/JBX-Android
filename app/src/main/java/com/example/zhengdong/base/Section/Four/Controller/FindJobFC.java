/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhengdong.base.Section.Four.Adapter.FindJobListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindJobFC extends Fragment {


    @BindView(R.id.find_job_rv)
    RecyclerView findJobRv;
    Unbinder unbinder;

    public FindJobFC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_job_fc, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRV();
        return view;
    }

    private void initRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        findJobRv.setLayoutManager(linearLayoutManager);
        FindJobListAdapter findJobListAdapter = new FindJobListAdapter(getActivity(),null);
        findJobRv.setAdapter(findJobListAdapter);
        findJobListAdapter.setOnItemClickListener(new FindJobListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String name, int i) {
                Intent intent = new Intent(getActivity(),FindDetailAC.class);
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
