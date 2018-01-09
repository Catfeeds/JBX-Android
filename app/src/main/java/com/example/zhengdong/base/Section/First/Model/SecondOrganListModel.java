/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.First.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2017/12/25.
 */

public class SecondOrganListModel {

    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"member":[],"total_member":0,"dept":[{"tenement":null,"status":"1","px_seq":0,"parent_dept_id":"2939b9a945d44772a2d3aead883f8171","org_id":"19fad3f58bd04759944e3862456c914a","name":"sdsd","modify_ts":"20171225213111","modifier":"fde6199d294ba1afa95c5acc8817ee78","dept_level":2,"dept_id":"172b1083db924320a27eae0b82c5508a","dept_code":"4102","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171225213111","address":null},{"tenement":null,"status":"1","px_seq":0,"parent_dept_id":"2939b9a945d44772a2d3aead883f8171","org_id":"19fad3f58bd04759944e3862456c914a","name":"bbb","modify_ts":"20171225212710","modifier":"fde6199d294ba1afa95c5acc8817ee78","dept_level":2,"dept_id":"834186ae0bb748d086c29a1b6b4532d5","dept_code":"4101","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171225212710","address":null}]}
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
         * member : []
         * total_member : 0
         * dept : [{"tenement":null,"status":"1","px_seq":0,"parent_dept_id":"2939b9a945d44772a2d3aead883f8171","org_id":"19fad3f58bd04759944e3862456c914a","name":"sdsd","modify_ts":"20171225213111","modifier":"fde6199d294ba1afa95c5acc8817ee78","dept_level":2,"dept_id":"172b1083db924320a27eae0b82c5508a","dept_code":"4102","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171225213111","address":null},{"tenement":null,"status":"1","px_seq":0,"parent_dept_id":"2939b9a945d44772a2d3aead883f8171","org_id":"19fad3f58bd04759944e3862456c914a","name":"bbb","modify_ts":"20171225212710","modifier":"fde6199d294ba1afa95c5acc8817ee78","dept_level":2,"dept_id":"834186ae0bb748d086c29a1b6b4532d5","dept_code":"4101","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171225212710","address":null}]
         */

        private int total_member;
        private List<?> member;
        private List<DeptBean> dept;

        public int getTotal_member() {
            return total_member;
        }

        public void setTotal_member(int total_member) {
            this.total_member = total_member;
        }

        public List<?> getMember() {
            return member;
        }

        public void setMember(List<?> member) {
            this.member = member;
        }

        public List<DeptBean> getDept() {
            return dept;
        }

        public void setDept(List<DeptBean> dept) {
            this.dept = dept;
        }

        public static class DeptBean {
            /**
             * tenement : null
             * status : 1
             * px_seq : 0
             * parent_dept_id : 2939b9a945d44772a2d3aead883f8171
             * org_id : 19fad3f58bd04759944e3862456c914a
             * name : sdsd
             * modify_ts : 20171225213111
             * modifier : fde6199d294ba1afa95c5acc8817ee78
             * dept_level : 2
             * dept_id : 172b1083db924320a27eae0b82c5508a
             * dept_code : 4102
             * creator : fde6199d294ba1afa95c5acc8817ee78
             * create_ts : 20171225213111
             * address : null
             */

            private Object tenement;
            private String status;
            private int px_seq;
            private String parent_dept_id;
            private String org_id;
            private String name;
            private String modify_ts;
            private String modifier;
            private int dept_level;
            private String dept_id;
            private String dept_code;
            private String creator;
            private String create_ts;
            private Object address;

            public Object getTenement() {
                return tenement;
            }

            public void setTenement(Object tenement) {
                this.tenement = tenement;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getPx_seq() {
                return px_seq;
            }

            public void setPx_seq(int px_seq) {
                this.px_seq = px_seq;
            }

            public String getParent_dept_id() {
                return parent_dept_id;
            }

            public void setParent_dept_id(String parent_dept_id) {
                this.parent_dept_id = parent_dept_id;
            }

            public String getOrg_id() {
                return org_id;
            }

            public void setOrg_id(String org_id) {
                this.org_id = org_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getModify_ts() {
                return modify_ts;
            }

            public void setModify_ts(String modify_ts) {
                this.modify_ts = modify_ts;
            }

            public String getModifier() {
                return modifier;
            }

            public void setModifier(String modifier) {
                this.modifier = modifier;
            }

            public int getDept_level() {
                return dept_level;
            }

            public void setDept_level(int dept_level) {
                this.dept_level = dept_level;
            }

            public String getDept_id() {
                return dept_id;
            }

            public void setDept_id(String dept_id) {
                this.dept_id = dept_id;
            }

            public String getDept_code() {
                return dept_code;
            }

            public void setDept_code(String dept_code) {
                this.dept_code = dept_code;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public String getCreate_ts() {
                return create_ts;
            }

            public void setCreate_ts(String create_ts) {
                this.create_ts = create_ts;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }
        }
    }
}
