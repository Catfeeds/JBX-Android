/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/2/2.
 */

public class BoutiqueListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":1,"fanexList":[{"fanc_id":"03d8110d00b540728ed60a0ab623eb0e","graphical_id":"d2f0935cc70e4bb5b55055369e12eca5","item_id":"a6320fbcf17a4856aaf8d7dd9ae58476","fan_title":"测试111111","fan_type":"zg","apply_ts":"02-02","owner_id":"60acb85001774427875ed156758b3807","presentation":"100000000","pic_path":"/group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png","fanc_flag":["最新"],"phone":"15168273085","user_name":"肖大大"}]}
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
         * fanexList : [{"fanc_id":"03d8110d00b540728ed60a0ab623eb0e","graphical_id":"d2f0935cc70e4bb5b55055369e12eca5","item_id":"a6320fbcf17a4856aaf8d7dd9ae58476","fan_title":"测试111111","fan_type":"zg","apply_ts":"02-02","owner_id":"60acb85001774427875ed156758b3807","presentation":"100000000","pic_path":"/group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png","fanc_flag":["最新"],"phone":"15168273085","user_name":"肖大大"}]
         */

        private int total;
        private List<FanexListBean> fanexList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<FanexListBean> getFanexList() {
            return fanexList;
        }

        public void setFanexList(List<FanexListBean> fanexList) {
            this.fanexList = fanexList;
        }

        public static class FanexListBean {
            /**
             * fanc_id : 03d8110d00b540728ed60a0ab623eb0e
             * graphical_id : d2f0935cc70e4bb5b55055369e12eca5
             * item_id : a6320fbcf17a4856aaf8d7dd9ae58476
             * fan_title : 测试111111
             * fan_type : zg
             * apply_ts : 02-02
             * owner_id : 60acb85001774427875ed156758b3807
             * presentation : 100000000
             * pic_path : /group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png
             * fanc_flag : ["最新"]
             * phone : 15168273085
             * user_name : 肖大大
             */

            private String fanc_id;
            private String graphical_id;
            private String item_id;
            private String fan_title;
            private String fan_type;
            private String apply_ts;
            private String owner_id;
            private String presentation;
            private String pic_path;
            private String phone;
            private String user_name;
            private List<String> fanc_flag;

            public String getFanc_id() {
                return fanc_id;
            }

            public void setFanc_id(String fanc_id) {
                this.fanc_id = fanc_id;
            }

            public String getGraphical_id() {
                return graphical_id;
            }

            public void setGraphical_id(String graphical_id) {
                this.graphical_id = graphical_id;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getFan_title() {
                return fan_title;
            }

            public void setFan_title(String fan_title) {
                this.fan_title = fan_title;
            }

            public String getFan_type() {
                return fan_type;
            }

            public void setFan_type(String fan_type) {
                this.fan_type = fan_type;
            }

            public String getApply_ts() {
                return apply_ts;
            }

            public void setApply_ts(String apply_ts) {
                this.apply_ts = apply_ts;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getPresentation() {
                return presentation;
            }

            public void setPresentation(String presentation) {
                this.presentation = presentation;
            }

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
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

            public List<String> getFanc_flag() {
                return fanc_flag;
            }

            public void setFanc_flag(List<String> fanc_flag) {
                this.fanc_flag = fanc_flag;
            }
        }
    }
}
