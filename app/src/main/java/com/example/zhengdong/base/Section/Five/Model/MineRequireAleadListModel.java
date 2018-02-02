/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/28.
 */

public class MineRequireAleadListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":1,"enquirylist":[{"match_enqu_id":"9bc854dde3cf4725807d54f6f445412a","match_resp_id":"8cb7faac369d49e48396dfdc784db06e","price":1231,"day_limit":5,"remark":"最后一次报价1231","status":"1","create_ts":20180131235011,"creator":"60acb85001774427875ed156758b3807","modify_ts":20180131235011,"modifier":"60acb85001774427875ed156758b3807","resp_code":"4245730592425","enqu_ts":20180131234936,"org_count":1,"phone":"15168273085","user_name":"肖大大"}]}
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
         * total : 1
         * enquirylist : [{"match_enqu_id":"9bc854dde3cf4725807d54f6f445412a","match_resp_id":"8cb7faac369d49e48396dfdc784db06e","price":1231,"day_limit":5,"remark":"最后一次报价1231","status":"1","create_ts":20180131235011,"creator":"60acb85001774427875ed156758b3807","modify_ts":20180131235011,"modifier":"60acb85001774427875ed156758b3807","resp_code":"4245730592425","enqu_ts":20180131234936,"org_count":1,"phone":"15168273085","user_name":"肖大大"}]
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
             * match_enqu_id : 9bc854dde3cf4725807d54f6f445412a
             * match_resp_id : 8cb7faac369d49e48396dfdc784db06e
             * price : 1231.0
             * day_limit : 5
             * remark : 最后一次报价1231
             * status : 1
             * create_ts : 20180131235011
             * creator : 60acb85001774427875ed156758b3807
             * modify_ts : 20180131235011
             * modifier : 60acb85001774427875ed156758b3807
             * resp_code : 4245730592425
             * enqu_ts : 20180131234936
             * org_count : 1
             * phone : 15168273085
             * user_name : 肖大大
             */

            private String match_enqu_id;
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
            private long enqu_ts;
            private int org_count;
            private String phone;
            private String user_name;

            public String getMatch_enqu_id() {
                return match_enqu_id;
            }

            public void setMatch_enqu_id(String match_enqu_id) {
                this.match_enqu_id = match_enqu_id;
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

            public long getEnqu_ts() {
                return enqu_ts;
            }

            public void setEnqu_ts(long enqu_ts) {
                this.enqu_ts = enqu_ts;
            }

            public int getOrg_count() {
                return org_count;
            }

            public void setOrg_count(int org_count) {
                this.org_count = org_count;
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
