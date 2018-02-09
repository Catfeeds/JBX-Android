/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/2/3.
 */

public class CommnetListModel {

    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":5,"fanCommentsList":[{"comm_id":"17ae6e57b597462e91c7f655a43b581c","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"yyyyyyy","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"5492e5378306449ba0204095399075ae","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"yyyyyyyhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"7fb01472d5244428855375f809c1086d","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"juju juju hu","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"8ea59bec8bc046ec998f6aa32bec52e6","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"asdfasdfasdfsadfae3435344253","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"1dfdfcae4333457da51d32dd1886883e","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"ddfgdfgdg","comm_owner":"b2c9be7ecc174140976572465097fa0f","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.120","termi_device":"null","phone":"17505926763","user_name":"游明威"}]}
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
         * total : 5
         * fanCommentsList : [{"comm_id":"17ae6e57b597462e91c7f655a43b581c","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"yyyyyyy","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"5492e5378306449ba0204095399075ae","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"yyyyyyyhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"7fb01472d5244428855375f809c1086d","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"juju juju hu","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"8ea59bec8bc046ec998f6aa32bec52e6","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"asdfasdfasdfsadfae3435344253","comm_owner":"cb77000e3722459b84578437f0833049","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.191","termi_device":"null","phone":"15539910985","user_name":"董证"},{"comm_id":"1dfdfcae4333457da51d32dd1886883e","fanc_id":"03d8110d00b540728ed60a0ab623eb0e","comm_type":"1","commnents":"ddfgdfgdg","comm_owner":"b2c9be7ecc174140976572465097fa0f","comm_ts":"201-02-03","status":"1","ip_owner":"172.16.45.120","termi_device":"null","phone":"17505926763","user_name":"游明威"}]
         */

        private int total;
        private List<FanCommentsListBean> fanCommentsList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<FanCommentsListBean> getFanCommentsList() {
            return fanCommentsList;
        }

        public void setFanCommentsList(List<FanCommentsListBean> fanCommentsList) {
            this.fanCommentsList = fanCommentsList;
        }

        public static class FanCommentsListBean {
            /**
             * comm_id : 17ae6e57b597462e91c7f655a43b581c
             * fanc_id : 03d8110d00b540728ed60a0ab623eb0e
             * comm_type : 1
             * commnents : yyyyyyy
             * comm_owner : cb77000e3722459b84578437f0833049
             * comm_ts : 201-02-03
             * status : 1
             * ip_owner : 172.16.45.191
             * termi_device : null
             * phone : 15539910985
             * user_name : 董证
             */

            private String comm_id;
            private String fanc_id;
            private String comm_type;
            private String commnents;
            private String comm_owner;
            private String comm_ts;
            private String status;
            private String ip_owner;
            private String termi_device;
            private String phone;
            private String user_name;

            public String getComm_id() {
                return comm_id;
            }

            public void setComm_id(String comm_id) {
                this.comm_id = comm_id;
            }

            public String getFanc_id() {
                return fanc_id;
            }

            public void setFanc_id(String fanc_id) {
                this.fanc_id = fanc_id;
            }

            public String getComm_type() {
                return comm_type;
            }

            public void setComm_type(String comm_type) {
                this.comm_type = comm_type;
            }

            public String getCommnents() {
                return commnents;
            }

            public void setCommnents(String commnents) {
                this.commnents = commnents;
            }

            public String getComm_owner() {
                return comm_owner;
            }

            public void setComm_owner(String comm_owner) {
                this.comm_owner = comm_owner;
            }

            public String getComm_ts() {
                return comm_ts;
            }

            public void setComm_ts(String comm_ts) {
                this.comm_ts = comm_ts;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIp_owner() {
                return ip_owner;
            }

            public void setIp_owner(String ip_owner) {
                this.ip_owner = ip_owner;
            }

            public String getTermi_device() {
                return termi_device;
            }

            public void setTermi_device(String termi_device) {
                this.termi_device = termi_device;
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
