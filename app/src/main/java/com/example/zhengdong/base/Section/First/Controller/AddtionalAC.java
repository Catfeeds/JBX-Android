package com.example.zhengdong.base.Section.First.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.base.Section.First.Events.PassEvent;
import com.example.zhengdong.base.Section.First.View.CustomSwitchView;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddtionalAC extends AppCompatActivity {

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
    @BindView(R.id.navi_right_pic_lay)
    LinearLayout naviRightPicLay;
    @BindView(R.id.title_bg)
    RelativeLayout titleBg;
    @BindView(R.id.partment_name_edt)
    EditText partmentNameEdt;
    @BindView(R.id.partment_lay)
    LinearLayout partmentLay;
    @BindView(R.id.people_name_edt)
    EditText peopleNameEdt;
    @BindView(R.id.people_phone_edt)
    EditText peoplePhoneEdt;
    @BindView(R.id.people_jobname_edt)
    EditText peopleJobnameEdt;
    @BindView(R.id.people_email_edt)
    EditText peopleEmailEdt;
    @BindView(R.id.enter_partment_rel)
    RelativeLayout enterPartmentRel;
    @BindView(R.id.switch_btn)
    CustomSwitchView switchBtn;
    @BindView(R.id.people_lay)
    LinearLayout peopleLay;
    private int type;
    private String token;
    private String organID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtional_ac);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
//        token = String.valueOf(SharedPreferencesUtils.getParam(this, UrlUtils.APP_TOKEN, ""));
//        organID = String.valueOf(SharedPreferencesUtils.getParam(this, UrlUtils.APP_ORANGE_ID, ""));
        initNavigationView(type);
    }

    private void initNavigationView(int type) {
        naviBackLay.setVisibility(View.VISIBLE);
        if (type == 1) {
            naviTitleTxt.setText("添加员工");
            peopleLay.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            naviTitleTxt.setText("添加部门");
            partmentLay.setVisibility(View.VISIBLE);
        } else if (type ==3 ){
            naviTitleTxt.setText("添加子部门");
            partmentLay.setVisibility(View.VISIBLE);
        }
        naviRightTxt.setText("提交");
    }


    // 添加部门
    public void initAddPartmentData() {
        String partmentName = partmentNameEdt.getText().toString();
        if (TextUtils.isEmpty(partmentName)) {
            XToast.show(getBaseContext(), "请输入部门名称!");
            return;
        }
        JSONObject map = new JSONObject();
        try {
            map.put("name", partmentName);
            if (type == 3){
//                String dept = getIntent().getStringExtra("dept_id");
                String dept = String.valueOf(SharedPreferencesUtils.getParam(AddtionalAC.this, "second",""));
//                String parentDept = String.valueOf(SharedPreferencesUtils.getParam(AddtionalAC.this, UrlUtils.APP_PARENT_DEPT_ID,""));
//                map.put("dept_id",dept);
                map.put("parent_dept_id",dept);
            }else {
                map.put("parent_dept_id","");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpRequest.URL_JSONPOSTREQUEST(this, map.toString(), UrlUtils.ADD_PARTMENT_URL, true, "", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LoginModel loginModel = new Gson().fromJson(response,LoginModel.class);
                if (loginModel.getCode() == 200){
                    XToast.show(getBaseContext(),"部门添加成功!");
                    EventBus.getDefault().post(new PassEvent("1","1"));
                    finish();
                }else {
                    XToast.show(getBaseContext(),""+loginModel.getMsg());
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

    // 添加员工
    private void initAddPeopleData() {
        // 记着传递org_id
        HashMap<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("org_id",organID);
        map.put("dept_id","");


        HttpRequest.URL_REQUEST(this, map, UrlUtils.ADD_PARTMENT_URL, true, "添加中...", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {

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

    @OnClick({R.id.navi_back_lay, R.id.navi_right_lay, R.id.enter_partment_rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.navi_back_lay:
                finish();
                break;
            case R.id.navi_right_lay:
                if (type == 2) {
                    initAddPartmentData();
                } else if (type == 1) {
                    initAddPeopleData();
                } else if (type == 3){
                    initAddPartmentData();
                }
                break;
            case R.id.enter_partment_rel:
                break;
            default:
                break;
        }
    }


}
