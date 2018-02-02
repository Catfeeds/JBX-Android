/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Login.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/26.
 */

public class RequireOrderListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"enquiryMap":{"match_enqu_id":"7c080d72769542aaafb7ba23bef82e2a","demands":"激光刨槽","day_limit":1,"remark":"enquire_code","pickup_day":20180208,"pickup_type":"送货","to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201103447,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201103447,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534620592587"},"itemmap":[{"item_id":"4f9ad6d81cfb4e0389c43ff4c3fffded","name":"0#","file_path":"/group1/M00/00/3B/wKgBNlpx5xaAD-ppAAAOeRtxoU4730.png","count":2,"respStatus":"未报价"}],"enquiry_id":"7c080d72769542aaafb7ba23bef82e2a"}
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
         * enquiryMap : {"match_enqu_id":"7c080d72769542aaafb7ba23bef82e2a","demands":"激光刨槽","day_limit":1,"remark":"enquire_code","pickup_day":20180208,"pickup_type":"送货","to_where":"2","status":"2","owner_order":"cb77000e3722459b84578437f0833049","create_ts":20180201103447,"creator":"cb77000e3722459b84578437f0833049","modify_ts":20180201103447,"modifier":"cb77000e3722459b84578437f0833049","enquire_status":0,"enqu_code":"9534620592587"}
         * itemmap : [{"item_id":"4f9ad6d81cfb4e0389c43ff4c3fffded","name":"0#","file_path":"/group1/M00/00/3B/wKgBNlpx5xaAD-ppAAAOeRtxoU4730.png","count":2,"respStatus":"未报价"}]
         * enquiry_id : 7c080d72769542aaafb7ba23bef82e2a
         */

        private EnquiryMapBean enquiryMap;
        private String enquiry_id;
        private List<ItemmapBean> itemmap;

        public EnquiryMapBean getEnquiryMap() {
            return enquiryMap;
        }

        public void setEnquiryMap(EnquiryMapBean enquiryMap) {
            this.enquiryMap = enquiryMap;
        }

        public String getEnquiry_id() {
            return enquiry_id;
        }

        public void setEnquiry_id(String enquiry_id) {
            this.enquiry_id = enquiry_id;
        }

        public List<ItemmapBean> getItemmap() {
            return itemmap;
        }

        public void setItemmap(List<ItemmapBean> itemmap) {
            this.itemmap = itemmap;
        }

        public static class EnquiryMapBean {
            /**
             * match_enqu_id : 7c080d72769542aaafb7ba23bef82e2a
             * demands : 激光刨槽
             * day_limit : 1
             * remark : enquire_code
             * pickup_day : 20180208
             * pickup_type : 送货
             * to_where : 2
             * status : 2
             * owner_order : cb77000e3722459b84578437f0833049
             * create_ts : 20180201103447
             * creator : cb77000e3722459b84578437f0833049
             * modify_ts : 20180201103447
             * modifier : cb77000e3722459b84578437f0833049
             * enquire_status : 0
             * enqu_code : 9534620592587
             */

            private String match_enqu_id;
            private String demands;
            private int day_limit;
            private String remark;
            private int pickup_day;
            private String pickup_type;
            private String to_where;
            private String status;
            private String owner_order;
            private long create_ts;
            private String creator;
            private long modify_ts;
            private String modifier;
            private int enquire_status;
            private String enqu_code;

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

            public String getPickup_type() {
                return pickup_type;
            }

            public void setPickup_type(String pickup_type) {
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
        }

        public static class ItemmapBean {
            /**
             * item_id : 4f9ad6d81cfb4e0389c43ff4c3fffded
             * name : 0#
             * file_path : /group1/M00/00/3B/wKgBNlpx5xaAD-ppAAAOeRtxoU4730.png
             * count : 2
             * respStatus : 未报价
             */

            private String item_id;
            private String name;
            private String file_path;
            private int count;
            private String respStatus;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getRespStatus() {
                return respStatus;
            }

            public void setRespStatus(String respStatus) {
                this.respStatus = respStatus;
            }
        }
    }
}
