/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/28.
 */

public class MineOffCompanyListModel {

    /**
     * success : true
     * code : 200
     * msg : success
     * data : [{"org_name":"沈阳鑫利恒不锈钢有限公司","match_resp_id":"4fd2bb809d0a46a2847117924960796e","price":1200,"day_limit":11,"remark":"hjgfutftkfy","create_ts":20180128165654}]
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
         * org_name : 沈阳鑫利恒不锈钢有限公司
         * match_resp_id : 4fd2bb809d0a46a2847117924960796e
         * price : 1200.0
         * day_limit : 11
         * remark : hjgfutftkfy
         * create_ts : 20180128165654
         */

        private String org_name;
        private String match_resp_id;
        private double price;
        private int day_limit;
        private String remark;
        private long create_ts;

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getMatch_resp_id() {
            return match_resp_id;
        }

        public void setMatch_resp_id(String match_resp_id) {
            this.match_resp_id = match_resp_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getDay_limit() {
            return day_limit;
        }

        public void setDay_limit(int day_limit) {
            this.day_limit = day_limit;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getCreate_ts() {
            return create_ts;
        }

        public void setCreate_ts(long create_ts) {
            this.create_ts = create_ts;
        }
    }
}
