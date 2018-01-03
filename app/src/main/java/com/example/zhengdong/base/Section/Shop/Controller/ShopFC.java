package com.example.zhengdong.base.Section.Shop.Controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhengdong.base.Section.Shop.Adapter.ShopListAdapter;
import com.example.zhengdong.jbx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFC extends Fragment {


    @BindView(R.id.shop_search_edt)
    EditText shopSearchEdt;
    @BindView(R.id.shop_navi_cart_img)
    ImageView shopNaviCartImg;
    @BindView(R.id.shop_navi_cart_lay)
    LinearLayout shopNaviCartLay;
    @BindView(R.id.shop_rv)
    RecyclerView shopRv;
    Unbinder unbinder;
    private View view;
    private ShopListAdapter shopListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_shop_fc, container, false);
            unbinder = ButterKnife.bind(this, view);

            initThreePartClassRV();
        }
        return view;
    }

    private void initThreePartClassRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shopRv.setLayoutManager(linearLayoutManager);
        shopListAdapter = new ShopListAdapter(getActivity(),null);
        shopRv.setAdapter(shopListAdapter);
        shopListAdapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, String name) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.shop_navi_cart_lay)
    public void onViewClicked() {
        switch (view.getId()){
            case R.id.shop_navi_cart_lay:
                break;
            default:
                break;
        }
    }
}
