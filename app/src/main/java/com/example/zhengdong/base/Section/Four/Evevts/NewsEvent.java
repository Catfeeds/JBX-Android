/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Evevts;

/**
 * Created by zheng.dong on 2017/12/27.
 */

public class NewsEvent {
    String newsID;
    String searchTxt;
    public NewsEvent(String newsID,String searchTxt){
        this.newsID = newsID;
        this.searchTxt = searchTxt;
    }
    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
}
