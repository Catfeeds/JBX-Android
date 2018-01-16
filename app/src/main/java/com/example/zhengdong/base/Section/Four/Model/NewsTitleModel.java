/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Four.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2017/12/27.
 */

public class NewsTitleModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"EcInformationCat":[{"id":33,"key_name":"最新热点"},{"id":34,"key_name":"行业新闻"},{"id":35,"key_name":"市场动态"},{"id":37,"key_name":"不锈精英"},{"id":39,"key_name":"不锈学院"}]}
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
        private List<EcInformationCatBean> EcInformationCat;

        public List<EcInformationCatBean> getEcInformationCat() {
            return EcInformationCat;
        }

        public void setEcInformationCat(List<EcInformationCatBean> EcInformationCat) {
            this.EcInformationCat = EcInformationCat;
        }

        public static class EcInformationCatBean {
            /**
             * id : 33
             * key_name : 最新热点
             */

            private int id;
            private String key_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }
        }
    }
}
