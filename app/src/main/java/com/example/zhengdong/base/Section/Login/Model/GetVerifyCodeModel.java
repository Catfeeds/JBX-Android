package com.example.zhengdong.base.Section.Login.Model;

/**
 * Created by zheng.dong on 2017/12/11.
 */

public class GetVerifyCodeModel {


    /**
     * success : true
     * code : 200
     * msg : success
     * data : a4818713257a4c74a2d71f26f1d335ff
     * otherData : null
     */

    private boolean success;
    private int code;
    private String msg;
    private String data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }
}
