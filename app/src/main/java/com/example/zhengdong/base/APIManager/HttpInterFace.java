package com.example.zhengdong.base.APIManager;

public interface HttpInterFace {
    void URL_REQUEST(String response);
    void BEFORE();
    void AFTER();
    void NOCONNECTION();
}
