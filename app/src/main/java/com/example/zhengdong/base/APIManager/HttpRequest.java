/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.APIManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;

import com.android.tu.loadingdialog.LoadingDailog;
import com.example.zhengdong.base.Macro.LoadingUtils;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.XToast;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

public class HttpRequest {

    public static Activity activity = new Activity();
    private static LoadingDailog dialog;

    public static void URL_REQUEST(final Context context, HashMap<String, String> hashMap, String url, final boolean isLoading, String loadString, final HttpInterFace httpInterFace) {
        LogUtil.e("HasMap参数" + hashMap.toString());
        LogUtil.e("HasMapURL" + url);

        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        OkHttpUtils.post().url(url)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .params(hashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！" + id);
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        // try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                if (!activity.isFinishing()) {
                                    dialog.show();
                                }
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();

                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

    public static void URL_JSONREQUEST(final Context context, String hashMap, String url, final boolean isLoading, String loadString, String token, final HttpInterFace httpInterFace) {
        LogUtil.e("HasMap参数" + hashMap.toString());
        LogUtil.e("HasMapURL" + url);

        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        OkHttpUtils.postString().url(url)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .addHeader("Authorization", "bearer;" + token)
                .content(hashMap)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！" + id);
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
//                         try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                if (!activity.isFinishing()) {
                                    dialog.show();
                                }
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();

                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

    public static void URL_GET_REQUEST(final Context context, String url, HashMap<String, String> map, String loadString, final boolean isLoading, final HttpInterFace httpInterFace) {
        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        LogUtil.e("HasMap参数" + map.toString());
        LogUtil.e("HasMap参数" + url.toString());
        String token = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_TOKEN, ""));
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_ORANGE_ID, ""));
        OkHttpUtils.get().url(url)
                .addHeader("x-application-context", "application:8085")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .addHeader("transfer-encoding", "Identity")
                .addHeader("Authorization", "bearer;" + token)
                .addHeader("org_id", org_id)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！");
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        // try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                dialog.show();
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

    /***
     *
     * 带header的post请求   json/application 格式
     * */
    public static void URL_JSONPOSTREQUEST(final Context context, String hashMap, String url, final boolean isLoading, String loadString, final HttpInterFace httpInterFace) {
        LogUtil.e("HasMap参数" + hashMap.toString());
        LogUtil.e("HasMapURL" + url);

        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        String token = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_TOKEN, ""));
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_ORANGE_ID, ""));

        OkHttpUtils.postString().url(url)
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json;chartset=utf-8")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))// 解析成为application/json格式
                .addHeader("Authorization", "bearer;" + token)
                .addHeader("org_id", org_id)
                .content(hashMap.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！" + id);
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
//                         try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                if (!activity.isFinishing()) {
                                    dialog.show();
                                }
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();

                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }


    /**
     * 带header的GET请求
     */
    public static void URL_JSONGET_REQUEST(final Context context, String url, HashMap<String, String> map, String loadString, final boolean isLoading, final HttpInterFace httpInterFace) {
        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();

        }

        String token = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_TOKEN, ""));
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_ORANGE_ID, ""));

        LogUtil.e("HasMap参数" + map.toString());
        LogUtil.e("HasMap参数" + url.toString());
        OkHttpUtils.get().url(url)
//                .addHeader("x-application-context", "application:8085")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .addHeader("transfer-encoding", "Identity")
                .addHeader("Authorization", "bearer;" + token)
                .addHeader("org_id", org_id)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！");
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        // try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                dialog.show();
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

    /**
     * 带header的GET请求,不带参数
     */
    public static void URL_JSONGETNOPARAM_REQUEST(final Context context, String url, String loadString, final boolean isLoading, final HttpInterFace httpInterFace) {
        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();

        }
        String token = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_TOKEN, ""));
        String org_id = String.valueOf(SharedPreferencesUtils.getParam(context, UrlUtils.APP_ORANGE_ID, ""));

        LogUtil.e("HasMap参数" + url.toString());
        OkHttpUtils.get().url(url)
                .addHeader("x-application-context", "application:8085")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .addHeader("transfer-encoding", "Identity")
                .addHeader("Authorization", "bearer;" + token)
                .addHeader("org_id", org_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！");
                        httpInterFace.NOCONNECTION();
                        if (isLoading) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (isLoading) {
                            dialog.dismiss();
                        }

                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        // try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                dialog.show();
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

    public static void URL_GETJSON_REQUEST(final Context context, String url, String loadString, final boolean isLoading, final HttpInterFace httpInterFace) {
        if (isLoading) {
//            dialog = LoadingUtils.createLoadingDialog(context, loadString);
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(context)
                    .setMessage(loadString)
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();

        }
        LogUtil.e("HasMap参数" + url.toString());
        OkHttpUtils.get().url(url)

                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        XToast.show(context, "网络出错！");
                        httpInterFace.NOCONNECTION();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("打印的JSON:" + response);
                        if (TextUtils.isEmpty(response)) {
                            XToast.show(context, "请重试!");
                            return;
                        }
                        httpInterFace.URL_REQUEST(response);
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                        // try catch 防止在刷新列表的时候立即点击返回时间崩溃!!!!
                        if (isLoading) {
                            try {
                                dialog.show();
                            } catch (Exception e) {

                            }
                        }
                        httpInterFace.BEFORE();
                    }

                    @Override
                    public void onAfter(int id) {
                        super.onAfter(id);
                        if (isLoading) {
                            dialog.dismiss();
                        }
                        httpInterFace.AFTER();
                    }
                });

    }

}
