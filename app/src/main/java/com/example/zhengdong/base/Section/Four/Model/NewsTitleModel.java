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
     * data : [{"tenement":null,"id":33,"uuid":"a03391f485c646f2979e59cf3bdf8116","add_time":1501723947000,"add_user_id":"1","update_time":1506412942000,"update_user_id":"1","key_name":"最新热点","delete_flag":null},{"tenement":null,"id":34,"uuid":"a4f13179550440c488b6f86962981280","add_time":1501723960000,"add_user_id":"1","update_time":null,"update_user_id":null,"key_name":"行业新闻","delete_flag":null},{"tenement":null,"id":35,"uuid":"6fad8e95fbc24927a48c2f6ef3463737","add_time":1501723969000,"add_user_id":"1","update_time":null,"update_user_id":null,"key_name":"市场动态","delete_flag":null},{"tenement":null,"id":37,"uuid":"fdc8a67b93fc4a19a21445b040126280","add_time":1501723988000,"add_user_id":"1","update_time":1507617500000,"update_user_id":"1","key_name":"不锈精英","delete_flag":null},{"tenement":null,"id":39,"uuid":"03637382978640ccb0e26bd22bc2dae3","add_time":1506412968000,"add_user_id":"1","update_time":null,"update_user_id":null,"key_name":"不锈学院","delete_flag":null}]
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
         * tenement : null
         * id : 33
         * uuid : a03391f485c646f2979e59cf3bdf8116
         * add_time : 1501723947000
         * add_user_id : 1
         * update_time : 1506412942000
         * update_user_id : 1
         * key_name : 最新热点
         * delete_flag : null
         */

        private Object tenement;
        private int id;
        private String uuid;
        private long add_time;
        private String add_user_id;
        private long update_time;
        private String update_user_id;
        private String key_name;
        private Object delete_flag;

        public Object getTenement() {
            return tenement;
        }

        public void setTenement(Object tenement) {
            this.tenement = tenement;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getAdd_user_id() {
            return add_user_id;
        }

        public void setAdd_user_id(String add_user_id) {
            this.add_user_id = add_user_id;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public String getUpdate_user_id() {
            return update_user_id;
        }

        public void setUpdate_user_id(String update_user_id) {
            this.update_user_id = update_user_id;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }

        public Object getDelete_flag() {
            return delete_flag;
        }

        public void setDelete_flag(Object delete_flag) {
            this.delete_flag = delete_flag;
        }
    }
}
