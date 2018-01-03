package com.example.zhengdong.base.Section.Login.Controller;

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

public class RegisterAC extends AppCompatActivity {

    @BindView(R.id.register_phone_edt)
    EditText registerPhoneEdt;
    @BindView(R.id.register_verify_code_edt)
    EditText registerVerifyCodeEdt;
    @BindView(R.id.register_get_verify_code_txt)
    TextView registerGetVerifyCodeTxt;
    @BindView(R.id.register_get_verify_code_lay)
    LinearLayout registerGetVerifyCodeLay;
    @BindView(R.id.register_paw_edt)
    EditText registerPawEdt;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.regsiter_agree_txt)
    TextView regsiterAgreeTxt;
    @BindView(R.id.register_back_lay)
    LinearLayout registerBackLay;
    private String phoneNumber;
    private String verifyCode;
    private String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ac);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.register_get_verify_code_lay, R.id.register_btn, R.id.regsiter_agree_txt, R.id.register_back_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_get_verify_code_lay:
                // 获取验证码
                getVerifyCodeBtn();
                break;
            case R.id.register_btn:
                initRegisterAction();
                break;
            case R.id.regsiter_agree_txt:
                break;
            case R.id.register_back_lay:
                finish();
                break;
            default:
                break;
        }
    }

    // 注册方法
    private void initRegisterAction() {
        codes = registerVerifyCodeEdt.getText().toString().trim();
        if (TextUtils.isEmpty(codes)) {
            XToast.show(getBaseContext(), "请输入验证码!");
            return;
        }
        String phone = registerPhoneEdt.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            XToast.show(getBaseContext(), "手机号码不能为空!");
            return;
        }
        String password = registerPawEdt.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            XToast.show(getBaseContext(), "请输入密码!");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("verifyCode", codes);
        HttpRequest.URL_REQUEST(RegisterAC.this, map, UrlUtils.REGISTER_URL, true, "注册中...", new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                GetVerifyCodeModel getVerifyCodeModel = new Gson().fromJson(response,GetVerifyCodeModel.class);
                if (getVerifyCodeModel.getCode() == 200){
                    XToast.show(getBaseContext(),""+getVerifyCodeModel.getMsg());
                }else {
                    XToast.show(getBaseContext(),""+getVerifyCodeModel.getMsg());
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


    // 获取手机验证码
    private void getVerifyCodeBtn() {
        phoneNumber = registerPhoneEdt.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            XToast.show(getBaseContext(), "手机号不能为空!");
            return;
        }
        new CountDownTimer(30 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                registerGetVerifyCodeTxt.setText(l / 1000 + "s后重新发送");
                registerGetVerifyCodeTxt.setTextColor(Color.parseColor("#307FDE"));
                registerGetVerifyCodeLay.setClickable(false);
            }

            @Override
            public void onFinish() {
                registerGetVerifyCodeTxt.setText("重新获取验证码");
                registerGetVerifyCodeLay.setClickable(true);
            }
        }.start();
        HashMap<String, String> map = new HashMap<>();
        map.put("toPhone", phoneNumber);
        HttpRequest.URL_GET_REQUEST(RegisterAC.this, UrlUtils.GET_VERIFY_CODE, map, "", false, new HttpInterFace() {
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
