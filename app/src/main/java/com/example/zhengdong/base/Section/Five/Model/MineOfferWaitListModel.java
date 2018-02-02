/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Five.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2018/1/25.
 */

public class MineOfferWaitListModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"total":2,"enquirylist":[{"enq_org_id":"d74f9578d59d4470bb8ca46f545a6cea","match_enqu_id":"0b665ecbfcfb47bfb5248233900ae573","organization_id":"5e782b8e1ecb4777878713963f8bc203","from_where":"1","create_ts":20180201091453,"creator":"b2c9be7ecc174140976572465097fa0f","respond_status":"0","enqu_code":"9534110592852","phone":"17505926763","user_name":"游明威"},{"enq_org_id":"687b377eaa444d4cac9728d19de396b5","match_enqu_id":"6d4388f078874b2b9edd059e812f3177","organization_id":"5e782b8e1ecb4777878713963f8bc203","from_where":"1","create_ts":20180201000141,"creator":"60acb85001774427875ed156758b3807","respond_status":"0","enqu_code":"9534720592958","phone":"15168273085","user_name":"肖大大"}]}
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
         * enquirylist : [{"enq_org_id":"d74f9578d59d4470bb8ca46f545a6cea","match_enqu_id":"0b665ecbfcfb47bfb5248233900ae573","organization_id":"5e782b8e1ecb4777878713963f8bc203","from_where":"1","create_ts":20180201091453,"creator":"b2c9be7ecc174140976572465097fa0f","respond_status":"0","enqu_code":"9534110592852","phone":"17505926763","user_name":"游明威"},{"enq_org_id":"687b377eaa444d4cac9728d19de396b5","match_enqu_id":"6d4388f078874b2b9edd059e812f3177","organization_id":"5e782b8e1ecb4777878713963f8bc203","from_where":"1","create_ts":20180201000141,"creator":"60acb85001774427875ed156758b3807","respond_status":"0","enqu_code":"9534720592958","phone":"15168273085","user_name":"肖大大"}]
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
             * enq_org_id : d74f9578d59d4470bb8ca46f545a6cea
             * match_enqu_id : 0b665ecbfcfb47bfb5248233900ae573
             * organization_id : 5e782b8e1ecb4777878713963f8bc203
             * from_where : 1
             * create_ts : 20180201091453
             * creator : b2c9be7ecc174140976572465097fa0f
             * respond_status : 0
             * enqu_code : 9534110592852
             * phone : 17505926763
             * user_name : 游明威
             */

            private String enq_org_id;
            private String match_enqu_id;
            private String organization_id;
            private String from_where;
            private long create_ts;
            private String creator;
            private String respond_status;
            private String enqu_code;
            private String phone;
            private String user_name;

            public String getEnq_org_id() {
                return enq_org_id;
            }

            public void setEnq_org_id(String enq_org_id) {
                this.enq_org_id = enq_org_id;
            }

            public String getMatch_enqu_id() {
                return match_enqu_id;
            }

            public void setMatch_enqu_id(String match_enqu_id) {
                this.match_enqu_id = match_enqu_id;
            }

            public String getOrganization_id() {
                return organization_id;
            }

            public void setOrganization_id(String organization_id) {
                this.organization_id = organization_id;
            }

            public String getFrom_where() {
                return from_where;
            }

            public void setFrom_where(String from_where) {
                this.from_where = from_where;
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

            public String getRespond_status() {
                return respond_status;
            }

            public void setRespond_status(String respond_status) {
                this.respond_status = respond_status;
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
