/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Work.Events;

/**
 * Created by zheng.dong on 2017/12/26.
 */

public class PassEvent {
    String name;
    String dept_id;
    public PassEvent(String name,String dept_id){
        this.name = name;
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }



}
