package com.example.zhengdong.base.Section.Login.Model;

/**
 * Created by zheng.dong on 2017/12/18.
 */

public class OrganListModel {

    Boolean isSelect;



    LoginModel.OtherDataBean otherDataBeanList;

    public OrganListModel(boolean state, LoginModel.OtherDataBean otherDataBean){
        this.isSelect = state;
        this.otherDataBeanList =  otherDataBean;
    }

    public LoginModel.OtherDataBean getOtherDataBeanList() {
        return otherDataBeanList;
    }

    public void setOtherDataBeanList(LoginModel.OtherDataBean otherDataBeanList) {
        this.otherDataBeanList = otherDataBeanList;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }




    public static class OtherDataBean {
        /**
         * tenement : null
         * status : 1
         * px_seq : 0
         * parent_org_id : string
         * org_id : 19fad3f58bd04759944e3862456c914a
         * org_code : 000001
         * name : 钢鲸1
         * modify_ts : 20171216160536
         * modifier : fde6199d294ba1afa95c5acc8817ee78
         * org_level : 1
         * creator : fde6199d294ba1afa95c5acc8817ee78
         * create_ts : 20171216160536
         * address : string
         */

        private Object tenement;
        private String status;
        private int px_seq;
        private String parent_org_id;
        private String org_id;
        private String org_code;
        private String name;
        private String modify_ts;
        private String modifier;
        private String org_level;
        private String creator;
        private String create_ts;
        private String address;

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

        public String getParent_org_id() {
            return parent_org_id;
        }

        public void setParent_org_id(String parent_org_id) {
            this.parent_org_id = parent_org_id;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getOrg_code() {
            return org_code;
        }

        public void setOrg_code(String org_code) {
            this.org_code = org_code;
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

        public String getOrg_level() {
            return org_level;
        }

        public void setOrg_level(String org_level) {
            this.org_level = org_level;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

}
