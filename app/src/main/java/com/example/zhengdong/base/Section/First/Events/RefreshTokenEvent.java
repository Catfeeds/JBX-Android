/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Events;

/**
 * Created by zheng.dong on 2018/2/4.
 */

public class RefreshTokenEvent {
    public RefreshTokenEvent(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token;
}
