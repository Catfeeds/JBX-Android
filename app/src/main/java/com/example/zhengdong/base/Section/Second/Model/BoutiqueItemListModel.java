/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/2/2.
 */

public class BoutiqueItemListModel {

    /**
     * success : true
     * code : 200
     * msg : success
     * data : [{"name":"展柜","id":"zg"},{"name":"屏风","id":"pf"},{"name":"制品","id":"zp"},{"name":"配件","id":"pj"},{"name":"雕塑","id":"ds"}]
     * otherData : null
     */

    private boolean success;
    private int code;
    private String msg;
    private Object otherData;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 展柜
         * id : zg
         */

        private String name;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
