/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/25.
 */

public class MineRequireWaitListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":2,"enquirylist":[{"match_enqu_id":"8500cbfab581470da87ade71c54289ce","demands":"水磨,冲压","day_limit":5,"remark":"remark   ","pickup_day":20180223,"pickup_type":2,"to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201104429,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201104429,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534720592197","phone":"15539910985","user_name":"董证"},{"match_enqu_id":"7c080d72769542aaafb7ba23bef82e2a","demands":"激光刨槽","day_limit":1,"remark":"enquire_code","pickup_day":20180208,"pickup_type":2,"to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201103447,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201103447,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534620592587","phone":"15539910985","user_name":"董证"}]}
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
         * total : 2
         * enquirylist : [{"match_enqu_id":"8500cbfab581470da87ade71c54289ce","demands":"水磨,冲压","day_limit":5,"remark":"remark   ","pickup_day":20180223,"pickup_type":2,"to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201104429,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201104429,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534720592197","phone":"15539910985","user_name":"董证"},{"match_enqu_id":"7c080d72769542aaafb7ba23bef82e2a","demands":"激光刨槽","day_limit":1,"remark":"enquire_code","pickup_day":20180208,"pickup_type":2,"to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201103447,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201103447,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534620592587","phone":"15539910985","user_name":"董证"}]
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
             * match_enqu_id : 8500cbfab581470da87ade71c54289ce
             * demands : 水磨,冲压
             * day_limit : 5
             * remark : remark
             * pickup_day : 20180223
             * pickup_type : 2
             * to_where : 2
             * status : 2
             * owner_order : cb77000e3722459b84578437f0833049
             * create_ts : 20180201104429
             * creator : cb77000e3722459b84578437f0833049
             * modify_ts : 20180201104429
             * modifier : cb77000e3722459b84578437f0833049
             * enquire_status : 0
             * enqu_code : 9534720592197
             * phone : 15539910985
             * user_name : 董证
             */

            private String match_enqu_id;
            private String demands;
            private int day_limit;
            private String remark;
            private int pickup_day;
            private int pickup_type;
            private String to_where;
            private String status;
            private String owner_order;
            private long create_ts;
            private String creator;
            private long modify_ts;
            private String modifier;
            private int enquire_status;
            private String enqu_code;
            private String phone;
            private String user_name;

            public String getMatch_enqu_id() {
                return match_enqu_id;
            }

            public void setMatch_enqu_id(String match_enqu_id) {
                this.match_enqu_id = match_enqu_id;
            }

            public String getDemands() {
                return demands;
            }

            public void setDemands(String demands) {
                this.demands = demands;
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

            public int getPickup_day() {
                return pickup_day;
            }

            public void setPickup_day(int pickup_day) {
                this.pickup_day = pickup_day;
            }

            public int getPickup_type() {
                return pickup_type;
            }

            public void setPickup_type(int pickup_type) {
                this.pickup_type = pickup_type;
            }

            public String getTo_where() {
                return to_where;
            }

            public void setTo_where(String to_where) {
                this.to_where = to_where;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getOwner_order() {
                return owner_order;
            }

            public void setOwner_order(String owner_order) {
                this.owner_order = owner_order;
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

            public int getEnquire_status() {
                return enquire_status;
            }

            public void setEnquire_status(int enquire_status) {
                this.enquire_status = enquire_status;
            }

            public String getEnqu_code() {
                return enqu_code;
            }

            public void setEnqu_code(String enqu_code) {
                this.enqu_code = enqu_code;
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
