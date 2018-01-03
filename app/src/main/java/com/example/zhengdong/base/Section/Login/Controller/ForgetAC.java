package com.example.zhengdong.base.Section.Login.Controller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Section.Login.Model.GetVerifyCodeModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetAC extends AppCompatActivity {

    @BindView(R.id.forget_phone_edt)
    EditText forgetPhoneEdt;
    @BindView(R.id.forget_verify_code_edt)
    EditText forgetVerifyCodeEdt;
    @BindView(R.id.forget_get_verify_code_txt)
    TextView forgetGetVerifyCodeTxt;
    @BindView(R.id.forget_get_verify_code_lay)
    LinearLayout forgetGetVerifyCodeLay;
    @BindView(R.id.forget_btn)
    Button forgetBtn;
    @BindView(R.id.forget_back_lay)
    LinearLayout forgetBackLay;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_ac);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.forget_get_verify_code_lay, R.id.forget_btn, R.id.forget_back_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_get_verify_code_lay:
                initGetVerifyCode();
                break;
            case R.id.forget_btn:
                String phone = forgetPhoneEdt.getText().toString().trim();
                String verifyCode = forgetVerifyCodeEdt.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    XToast.show(getBaseContext(),"请输入手机号码!");
                    return;
                }
                if (TextUtils.isEmpty(verifyCode)){
                    XToast.show(getBaseContext(),"请输入验证码!");
                    return;
                }
                Intent intent = new Intent(ForgetAC.this, ConfirmAC.class);
                intent.putExtra("phone",phone);
                intent.putExtra("verifyCode",verifyCode);
                startActivity(intent);
                break;
            case R.id.forget_back_lay:
                finish();
                break;
            default:
                break;
        }
    }

    // 获取验证码
    private void initGetVerifyCode() {
        phoneNumber = forgetPhoneEdt.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            XToast.show(getBaseContext(), "手机号不能为空!");
            return;
        }
        new CountDownTimer(30 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                forgetGetVerifyCodeTxt.setText(l / 1000 + "s后重新发送");
                forgetGetVerifyCodeTxt.setTextColor(Color.parseColor("#307FDE"));
                forgetGetVerifyCodeLay.setClickable(false);
            }

            @Override
            public void onFinish() {
                forgetGetVerifyCodeTxt.setText("重新获取验证码");
                forgetGetVerifyCodeLay.setClickable(true);
            }
        }.start();
        HashMap<String, String> map = new HashMap<>();
        map.put("toPhone", phoneNumber);
        HttpRequest.URL_GET_REQUEST(ForgetAC.this, UrlUtils.GET_VERIFY_CODE, map, "", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                GetVerifyCodeModel getVerifyCodeModel = new Gson().fromJson(response, GetVerifyCodeModel.class);
                if (getVerifyCodeModel.getCode() == 200) {
                    XToast.show(getBaseContext(), "" + getVerifyCodeModel.getMsg());

                } else {
                    XToast.show(getBaseContext(), "" + getVerifyCodeModel.getMsg());
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
