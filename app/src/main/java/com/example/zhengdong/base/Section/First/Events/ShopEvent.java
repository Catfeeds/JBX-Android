/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Events;

/**
 * Created by zheng.dong on 2018/2/4.
 */

public class ShopEvent {
    String name;
    boolean market;
    public ShopEvent(String name,boolean market){
        this.name = name;
        this.market = market;
    }
    public boolean isMarket() {
        return market;
    }

    public void setMarket(boolean market) {
        this.market = market;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
