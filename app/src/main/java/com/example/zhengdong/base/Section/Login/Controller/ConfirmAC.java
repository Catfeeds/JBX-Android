package com.example.zhengdong.base.Section.Login.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmAC extends BaseAC {


    @BindView(R.id.confirm_back_lay)
    LinearLayout confirmBackLay;
    @BindView(R.id.confirm_new_paw_edt)
    EditText confirmNewPawEdt;
    @BindView(R.id.confirm_conform_paw_edt)
    EditText confirmConformPawEdt;
    @BindView(R.id.confirm_btn)
    Button confirmBtn;
    private String phone;
    private String verifyCode;
    private String data = "";
    private String smsCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_ac);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra("phone");
        verifyCode = getIntent().getStringExtra("verifyCode");
        smsCode = getIntent().getStringExtra("smsToken");
    }


    @OnClick({R.id.confirm_back_lay, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.confirm_back_lay:
                finish();
                break;
            case R.id.confirm_btn:
                initForgetPaw();
                break;
        }
    }

    // 忘记密码
    private void initForgetPaw() {
        final String firstPaw = confirmNewPawEdt.getText().toString().trim();
        String secondPaw = confirmConformPawEdt.getText().toString().trim();
        if (TextUtils.isEmpty(firstPaw)){
            XToast.show(getBaseContext(),"请输入新密码!");
            return;
        }
        if (TextUtils.isEmpty(secondPaw)){
            XToast.show(getBaseContext(),"请输入确认密码!");
            return;
        }
        if (!firstPaw.equals(secondPaw)){
            XToast.show(getBaseContext(),"两次密码输入不一致!");
            return;
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("verifyCode",verifyCode);
        map.put("password",firstPaw);
        map.put("smsToken",smsCode);
        HttpRequest.URL_REQUEST(ConfirmAC.this, map, UrlUtils.FORGET_PAW_URL, true, "", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LoginModel loginModel = new Gson().fromJson(response,LoginModel.class);
                if (loginModel.getCode() == 200){
                    SharedPreferencesUtils.setParam(ConfirmAC.this,UrlUtils.APP_USERNAME,phone);
                    SharedPreferencesUtils.setParam(ConfirmAC.this,UrlUtils.APP_PASSWORD,firstPaw);
                    XToast.show(getBaseContext(),"找回密码成功!");
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
}
