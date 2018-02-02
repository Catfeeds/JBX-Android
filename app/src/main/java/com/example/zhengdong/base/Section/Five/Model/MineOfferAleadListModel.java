/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/28.
 */

public class MineOfferAleadListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":3,"enquirylist":[{"match_resp_id":"df2a3e7c81a24e7e95e0f5c6ff32aa54","price":9696,"day_limit":7,"remark":"东征第三次报价9696","status":"1","create_ts":20180131234323,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131234538,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245330592410","phone":"15539910985","user_name":"董证"},{"match_resp_id":"5b7930c8c7de40e69cd96261a6011d18","price":8888,"day_limit":8,"remark":"东征第二期8888","status":"1","create_ts":20180131231807,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131232128,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245930592694","phone":"15539910985","user_name":"董证"},{"match_resp_id":"c1d446755cf0474189a8c1acce13067a","price":7890,"day_limit":3,"remark":"东征第一次报价7890","status":"1","create_ts":20180131231729,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131231940,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245830592110","phone":"15539910985","user_name":"董证"}]}
     * otherData : null
     */

    private boolean success;
    private int code;
    private String msg;
    private DataBean data;
    private Object otherData;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    public static class DataBean {
        /**
         * total : 3
         * enquirylist : [{"match_resp_id":"df2a3e7c81a24e7e95e0f5c6ff32aa54","price":9696,"day_limit":7,"remark":"东征第三次报价9696","status":"1","create_ts":20180131234323,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131234538,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245330592410","phone":"15539910985","user_name":"董证"},{"match_resp_id":"5b7930c8c7de40e69cd96261a6011d18","price":8888,"day_limit":8,"remark":"东征第二期8888","status":"1","create_ts":20180131231807,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131232128,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245930592694","phone":"15539910985","user_name":"董证"},{"match_resp_id":"c1d446755cf0474189a8c1acce13067a","price":7890,"day_limit":3,"remark":"东征第一次报价7890","status":"1","create_ts":20180131231729,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180131231940,"modifier":"cb77000e3722459b84578437f0833049","resp_code":"4245830592110","phone":"15539910985","user_name":"董证"}]
         */

        private int total;
        private List<EnquirylistBean> enquirylist;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<EnquirylistBean> getEnquirylist() {
            return enquirylist;
        }

        public void setEnquirylist(List<EnquirylistBean> enquirylist) {
            this.enquirylist = enquirylist;
        }

        public static class EnquirylistBean {
            /**
             * match_resp_id : df2a3e7c81a24e7e95e0f5c6ff32aa54
             * price : 9696.0
             * day_limit : 7
             * remark : 东征第三次报价9696
             * status : 1
             * create_ts : 20180131234323
             * creator : cb77000e3722459b84578437f0833049
             * modify_ts : 20180131234538
             * modifier : cb77000e3722459b84578437f0833049
             * resp_code : 4245330592410
             * phone : 15539910985
             * user_name : 董证
             */

            private String match_resp_id;
            private double price;
            private int day_limit;
            private String remark;
            private String status;
            private long create_ts;
            private String creator;
            private long modify_ts;
            private String modifier;
            private String resp_code;
            private String phone;
            private String user_name;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public long getCreate_ts() {
                return create_ts;
            }

            public void setCreate_ts(long create_ts) {
                this.create_ts = create_ts;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public long getModify_ts() {
                return modify_ts;
            }

            public void setModify_ts(long modify_ts) {
                this.modify_ts = modify_ts;
            }

            public String getModifier() {
                return modifier;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public String getResp_code() {
                return resp_code;
            }

            public void setResp_code(String resp_code) {
                this.resp_code = resp_code;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
