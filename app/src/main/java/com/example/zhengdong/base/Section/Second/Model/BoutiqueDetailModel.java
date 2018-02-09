/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/2/2.
 */

public class BoutiqueDetailModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"type":"304L","color":"红铜拉丝","thickness_num":0.6,"length_num":100,"width_num":800,"fanc_id":"03d8110d00b540728ed60a0ab623eb0e","graphical_id":"d2f0935cc70e4bb5b55055369e12eca5","item_id":"a6320fbcf17a4856aaf8d7dd9ae58476","fan_title":"测试111111","fan_type":"展柜","apply_ts":"02-02","owner_id":"60acb85001774427875ed156758b3807","presentation":"100000000","pic_path":"/group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png","case_desc1":"","pic_path1":"","case_desc2":"","pic_path2":"","case_desc3":"","pic_path3":"","up_seq":1,"status":"1","status_ts":1,"check_status":"1","checker":"60acb85001774427875ed156758b3807","check_ts":20180202194426,"fanc_flag":["最新","热门","流行"],"read_num":1,"read_ts":20180203132318,"praise_num":1,"praise_ts":20180202235620,"comment_num":1,"comment_ts":20180203001952,"use_num":0,"use_ts":20180202194426,"forwarded_num":0,"forwarded_ts":20180202194426,"allArgs":0.8,"phone":"15168273085","user_name":"肖大大","pic_list":[{"pic_path":"/group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png","pic_des":"100000000"}]}
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
         * type : 304L
         * color : 红铜拉丝
         * thickness_num : 0.6
         * length_num : 100.0
         * width_num : 800.0
         * fanc_id : 03d8110d00b540728ed60a0ab623eb0e
         * graphical_id : d2f0935cc70e4bb5b55055369e12eca5
         * item_id : a6320fbcf17a4856aaf8d7dd9ae58476
         * fan_title : 测试111111
         * fan_type : 展柜
         * apply_ts : 02-02
         * owner_id : 60acb85001774427875ed156758b3807
         * presentation : 100000000
         * pic_path : /group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png
         * case_desc1 :
         * pic_path1 :
         * case_desc2 :
         * pic_path2 :
         * case_desc3 :
         * pic_path3 :
         * up_seq : 1
         * status : 1
         * status_ts : 1
         * check_status : 1
         * checker : 60acb85001774427875ed156758b3807
         * check_ts : 20180202194426
         * fanc_flag : ["最新","热门","流行"]
         * read_num : 1
         * read_ts : 20180203132318
         * praise_num : 1
         * praise_ts : 20180202235620
         * comment_num : 1
         * comment_ts : 20180203001952
         * use_num : 0
         * use_ts : 20180202194426
         * forwarded_num : 0
         * forwarded_ts : 20180202194426
         * allArgs : 0.8
         * phone : 15168273085
         * user_name : 肖大大
         * pic_list : [{"pic_path":"/group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png","pic_des":"100000000"}]
         */

        private String type;
        private String color;
        private double thickness_num;
        private double length_num;
        private double width_num;
        private String fanc_id;
        private String graphical_id;
        private String item_id;
        private String fan_title;
        private String fan_type;
        private String apply_ts;
        private String owner_id;
        private String presentation;
        private String pic_path;
        private String case_desc1;
        private String pic_path1;
        private String case_desc2;
        private String pic_path2;
        private String case_desc3;
        private String pic_path3;
        private int up_seq;
        private String status;
        private int status_ts;
        private String check_status;
        private String checker;
        private long check_ts;
        private int read_num;
        private long read_ts;
        private int praise_num;
        private long praise_ts;
        private int comment_num;
        private long comment_ts;
        private int use_num;
        private long use_ts;
        private int forwarded_num;
        private long forwarded_ts;
        private double allArgs;
        private String phone;
        private String user_name;
        private List<String> fanc_flag;
        private List<PicListBean> pic_list;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getThickness_num() {
            return thickness_num;
        }

        public void setThickness_num(double thickness_num) {
            this.thickness_num = thickness_num;
        }

        public double getLength_num() {
            return length_num;
        }

        public void setLength_num(double length_num) {
            this.length_num = length_num;
        }

        public double getWidth_num() {
            return width_num;
        }

        public void setWidth_num(double width_num) {
            this.width_num = width_num;
        }

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

        public String getCase_desc1() {
            return case_desc1;
        }

        public void setCase_desc1(String case_desc1) {
            this.case_desc1 = case_desc1;
        }

        public String getPic_path1() {
            return pic_path1;
        }

        public void setPic_path1(String pic_path1) {
            this.pic_path1 = pic_path1;
        }

        public String getCase_desc2() {
            return case_desc2;
        }

        public void setCase_desc2(String case_desc2) {
            this.case_desc2 = case_desc2;
        }

        public String getPic_path2() {
            return pic_path2;
        }

        public void setPic_path2(String pic_path2) {
            this.pic_path2 = pic_path2;
        }

        public String getCase_desc3() {
            return case_desc3;
        }

        public void setCase_desc3(String case_desc3) {
            this.case_desc3 = case_desc3;
        }

        public String getPic_path3() {
            return pic_path3;
        }

        public void setPic_path3(String pic_path3) {
            this.pic_path3 = pic_path3;
        }

        public int getUp_seq() {
            return up_seq;
        }

        public void setUp_seq(int up_seq) {
            this.up_seq = up_seq;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStatus_ts() {
            return status_ts;
        }

        public void setStatus_ts(int status_ts) {
            this.status_ts = status_ts;
        }

        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }

        public String getChecker() {
            return checker;
        }

        public void setChecker(String checker) {
            this.checker = checker;
        }

        public long getCheck_ts() {
            return check_ts;
        }

        public void setCheck_ts(long check_ts) {
            this.check_ts = check_ts;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }

        public long getRead_ts() {
            return read_ts;
        }

        public void setRead_ts(long read_ts) {
            this.read_ts = read_ts;
        }

        public int getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(int praise_num) {
            this.praise_num = praise_num;
        }

        public long getPraise_ts() {
            return praise_ts;
        }

        public void setPraise_ts(long praise_ts) {
            this.praise_ts = praise_ts;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public long getComment_ts() {
            return comment_ts;
        }

        public void setComment_ts(long comment_ts) {
            this.comment_ts = comment_ts;
        }

        public int getUse_num() {
            return use_num;
        }

        public void setUse_num(int use_num) {
            this.use_num = use_num;
        }

        public long getUse_ts() {
            return use_ts;
        }

        public void setUse_ts(long use_ts) {
            this.use_ts = use_ts;
        }

        public int getForwarded_num() {
            return forwarded_num;
        }

        public void setForwarded_num(int forwarded_num) {
            this.forwarded_num = forwarded_num;
        }

        public long getForwarded_ts() {
            return forwarded_ts;
        }

        public void setForwarded_ts(long forwarded_ts) {
            this.forwarded_ts = forwarded_ts;
        }

        public double getAllArgs() {
            return allArgs;
        }

        public void setAllArgs(double allArgs) {
            this.allArgs = allArgs;
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

        public List<PicListBean> getPic_list() {
            return pic_list;
        }

        public void setPic_list(List<PicListBean> pic_list) {
            this.pic_list = pic_list;
        }

        public static class PicListBean {
            /**
             * pic_path : /group1/M00/00/04/wKgBNlpwFaGATvCMAAAdo1AWcO4184.png
             * pic_des : 100000000
             */

            private String pic_path;
            private String pic_des;

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
            }

            public String getPic_des() {
                return pic_des;
            }

            public void setPic_des(String pic_des) {
                this.pic_des = pic_des;
            }
        }
    }
}
