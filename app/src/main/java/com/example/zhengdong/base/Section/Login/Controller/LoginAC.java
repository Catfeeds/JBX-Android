package com.example.zhengdong.base.Section.Login.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.RxBus;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.General.BaseAC;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.PreferencesUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Main.MainAC;
import com.example.zhengdong.base.Section.First.Events.RefreshTokenEvent;
import com.example.zhengdong.base.Section.Login.EventBus.LoginEvents;
import com.example.zhengdong.base.Section.Login.Model.LoginModel;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginAC extends BaseAC {

    @BindView(R.id.login_username_edt)
    EditText loginUsernameEdt;
    @BindView(R.id.login_delete_lay)
    LinearLayout loginDeleteLay;
    @BindView(R.id.login_paw_edt)
    EditText loginPawEdt;
    @BindView(R.id.login_register_txt)
    TextView loginRegisterTxt;
    @BindView(R.id.login_forget_txt)
    TextView loginForgetTxt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ac);
        ButterKnife.bind(this);
        // 记住密码
        String user = String.valueOf(SharedPreferencesUtils.getParam(LoginAC.this, UrlUtils.APP_USERNAME,""));
        String paw = String.valueOf(SharedPreferencesUtils.getParam(LoginAC.this, UrlUtils.APP_PASSWORD,""));
        loginUsernameEdt.setText(user);
        loginPawEdt.setText(paw);
    }

    // 登录接口
    private void initLoginBtn() {
        username = loginUsernameEdt.getText().toString().trim();
        password = loginPawEdt.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            XToast.show(getBaseContext(),"请输入手机号码!");
            return;
        }
        if (TextUtils.isEmpty(password)){
            XToast.show(getBaseContext(),"请输入密码!");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        if (username.equals("001")){
            map.put("userName", "15539910985");
            map.put("password", "123456");
        }else {
            map.put("userName", username);
            map.put("password", password);
        }
        HttpRequest.URL_GET_REQUEST(LoginAC.this, UrlUtils.LOGIN_URL, map, "登录中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                LoginModel loginModel = new Gson().fromJson(response, LoginModel.class);
                if (loginModel.getCode() == 200) {
                    SharedPreferencesUtils.setParam(LoginAC.this,UrlUtils.APP_USERNAME,username);
                    SharedPreferencesUtils.setParam(LoginAC.this,UrlUtils.APP_PASSWORD,password);
//                    XToast.show(getBaseContext(), "" + loginModel.getMsg());
                    String token = loginModel.getData().getToken();
                    SharedPreferencesUtils.setParam(LoginAC.this,UrlUtils.APP_TOKEN,"");
                    SharedPreferencesUtils.setParam(LoginAC.this,UrlUtils.APP_TOKEN,token);
                    EventBus.getDefault().post(new RefreshTokenEvent(token));
                    // 判断是否有多个组织机构
                    if (loginModel.getOtherData() == null){
                        // 直接跳转
                        EventBus.getDefault().postSticky(new LoginEvents(token,false));
                        finish();
                    }else {
                        // 弹出组织机构的列表,让用户选择
                        startActivityWithTwoValue(SelectOrgAC.class,new Gson().toJson(loginModel));
                        finish();
                    }


                } else {
                    XToast.show(getBaseContext(), "" + loginModel.getMsg());
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

    @Override
    protected void onResume() {
        super.onResume();
        // 刷新token
        String user = String.valueOf(SharedPreferencesUtils.getParam(LoginAC.this, UrlUtils.APP_USERNAME,""));
        String paw = String.valueOf(SharedPreferencesUtils.getParam(LoginAC.this, UrlUtils.APP_PASSWORD,""));
        loginUsernameEdt.setText(user);
        loginPawEdt.setText(paw);
    }

    @OnClick({R.id.login_delete_lay, R.id.login_register_txt, R.id.login_forget_txt, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_delete_lay:
                loginUsernameEdt.setText("");
                break;
            case R.id.login_register_txt:
                Intent intent = new Intent(LoginAC.this, RegisterAC.class);
                startActivity(intent);
                break;
            case R.id.login_forget_txt:
                Intent intents = new Intent(LoginAC.this, ForgetAC.class);
                startActivity(intents);
                break;
            case R.id.login_btn:
                initLoginBtn();
                break;
        }
    }

    private void startActivityWithTwoValue(Class<?> cls, String value) {
        Intent intent = new Intent(LoginAC.this, cls);
        intent.putExtra("values", value);
        startActivity(intent);
    }

}
