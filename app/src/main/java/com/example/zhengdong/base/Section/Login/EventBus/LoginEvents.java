/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Login.EventBus;

/**
 * Created by zheng.dong on 2018/2/1.
 */

public class LoginEvents {
    private String message;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin;
    public LoginEvents(String message,boolean isLogin){
        this.message = message;
        this.isLogin = isLogin;
    }

    public String getMessage(){
        return message;
    }


}
