package com.example.zhengdong.base.Section.Login.Model;

import java.util.List;

/**
 * Created by zheng.dong on 2017/12/11.
 */

public class LoginModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : {"token":"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiZmRlNjE5OWQyOTRiYTFhZmE5NWM1YWNjODgxN2VlNzgiLCJpc3MiOiJqeWIiLCJleHAiOjE1MTM0MjI0OTIsInZlcnNpb24iOiIyMDE3MTIxNjE3MDgxMiIsImlhdCI6MTUxMzQxNTI5MiwiY2xpZW50X2lkIjoiMTAwMDAwMDAwMSJ9.jyOXJfa8OSKavDA0F2rrD-IQCtehTdvNwP2VZiaqL-k","expires_in":"1513422492612","nc":"admin","userName":"admin","userId":"fde6199d294ba1afa95c5acc8817ee78","last_time":"20171216165302","last_ip":"0:0:0:0:0:0:0:1","cur_time":"20171216170812","cur_ip":"172.16.33.8","status":"1","redirect_uri":null,"onlyCookies":false}
     * otherData : [{"tenement":null,"status":"1","px_seq":0,"parent_org_id":"string","org_id":"19fad3f58bd04759944e3862456c914a","org_code":"000001","name":"钢鲸1","modify_ts":"20171216160536","modifier":"fde6199d294ba1afa95c5acc8817ee78","org_level":"1","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171216160536","address":"string"},{"tenement":null,"status":"1","px_seq":0,"parent_org_id":"string","org_id":"797174e585f64e29a896cb02c1256d1d","org_code":"000002","name":"钢鲸2","modify_ts":"20171216160557","modifier":"fde6199d294ba1afa95c5acc8817ee78","org_level":"1","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171216160557","address":"string"},{"tenement":null,"status":"1","px_seq":0,"parent_org_id":"string","org_id":"a89330602a9d40bbacfe2c75f0b8b9bd","org_code":"string","name":"钢鲸","modify_ts":"20171216144347","modifier":"fde6199d294ba1afa95c5acc8817ee78","org_level":"1","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171216144347","address":"string"},{"tenement":null,"status":"1","px_seq":0,"parent_org_id":"string","org_id":"cbbc133749f343b49c5afc2bb5969891","org_code":"string","name":"钢鲸","modify_ts":"20171216144107","modifier":"fde6199d294ba1afa95c5acc8817ee78","org_level":"1","creator":"fde6199d294ba1afa95c5acc8817ee78","create_ts":"20171216144107","address":"string"}]
     */

    private boolean success;
    private int code;
    private String msg;
    private DataBean data;
    private List<OtherDataBean> otherData;

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

    public List<OtherDataBean> getOtherData() {
        return otherData;
    }

    public void setOtherData(List<OtherDataBean> otherData) {
        this.otherData = otherData;
    }

    public static class DataBean {
        /**
         * token : eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiZmRlNjE5OWQyOTRiYTFhZmE5NWM1YWNjODgxN2VlNzgiLCJpc3MiOiJqeWIiLCJleHAiOjE1MTM0MjI0OTIsInZlcnNpb24iOiIyMDE3MTIxNjE3MDgxMiIsImlhdCI6MTUxMzQxNTI5MiwiY2xpZW50X2lkIjoiMTAwMDAwMDAwMSJ9.jyOXJfa8OSKavDA0F2rrD-IQCtehTdvNwP2VZiaqL-k
         * expires_in : 1513422492612
         * nc : admin
         * userName : admin
         * userId : fde6199d294ba1afa95c5acc8817ee78
         * last_time : 20171216165302
         * last_ip : 0:0:0:0:0:0:0:1
         * cur_time : 20171216170812
         * cur_ip : 172.16.33.8
         * status : 1
         * redirect_uri : null
         * onlyCookies : false
         */

        private String token;
        private String expires_in;
        private String nc;
        private String userName;
        private String userId;
        private String last_time;
        private String last_ip;
        private String cur_time;
        private String cur_ip;
        private String status;
        private Object redirect_uri;
        private boolean onlyCookies;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public String getNc() {
            return nc;
        }

        public void setNc(String nc) {
            this.nc = nc;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLast_time() {
            return last_time;
        }

        public void setLast_time(String last_time) {
            this.last_time = last_time;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getCur_time() {
            return cur_time;
        }

        public void setCur_time(String cur_time) {
            this.cur_time = cur_time;
        }

        public String getCur_ip() {
            return cur_ip;
        }

        public void setCur_ip(String cur_ip) {
            this.cur_ip = cur_ip;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getRedirect_uri() {
            return redirect_uri;
        }

        public void setRedirect_uri(Object redirect_uri) {
            this.redirect_uri = redirect_uri;
        }

        public boolean isOnlyCookies() {
            return onlyCookies;
        }

        public void setOnlyCookies(boolean onlyCookies) {
            this.onlyCookies = onlyCookies;
        }
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
