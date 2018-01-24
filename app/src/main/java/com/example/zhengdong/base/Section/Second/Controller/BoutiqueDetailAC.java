/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.zhengdong.base.Macro.SnackbarUtils;
import com.example.zhengdong.base.Macro.popupWindow.CommonPupWindowView;
import com.example.zhengdong.base.Section.Five.View.CircleImageView;
import com.example.zhengdong.base.Section.Second.View.AndroidBug5497Workaround;
import com.example.zhengdong.base.Section.Second.View.HeadZoomScrollView;
import com.example.zhengdong.base.Section.Second.View.StatusBarCompat.StatusBarCompat;
import com.example.zhengdong.jbx.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoutiqueDetailAC extends AppCompatActivity implements CommonPupWindowView.ViewInterface {

    @BindView(R.id.header_pic)
    ImageView headerPic;
    @BindView(R.id.mine_header_pic)
    CircleImageView mineHeaderPic;
    @BindView(R.id.head_sv)
    HeadZoomScrollView headSv;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.share)
    LinearLayout share;
    @BindView(R.id.write)
    ImageView write;
    @BindView(R.id.input)
    RelativeLayout input;
    @BindView(R.id.edt)
    EditText edit;
    @BindView(R.id.comment_list_btn)
    ImageView commentListBtn;
    @BindView(R.id.raise_btn)
    ImageView raiseBtn;
    @BindView(R.id.detail_content)
    RelativeLayout detailContent;
    private CommonPupWindowView popupWindow;
    private UMWeb web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_detail_ac);
        AndroidBug5497Workaround.assistActivity(findViewById(R.id.detail_content));
        ButterKnife.bind(this);
        StatusBarCompat.translucentStatusBar(BoutiqueDetailAC.this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @OnClick({R.id.back, R.id.share, R.id.comment_list_btn, R.id.raise_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                initShareForFriend();
                break;
            case R.id.comment_list_btn:
                Intent intent = new Intent(BoutiqueDetailAC.this,BoutiqueListAC.class);
                startActivity(intent);
                break;
            case R.id.raise_btn:
                break;
        }
    }

    /**
     * 分享给好友
     * */
    private void initShareForFriend() {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popup_share, null);
        //测量View的宽高
        measureWidthAndHeight(upView);
        popupWindow = new CommonPupWindowView.Builder(this)
                .setView(R.layout.popup_share)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(this.findViewById(R.id.detail_content), Gravity.BOTTOM, 0, 0);
    }
    public void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
    /***
     * 分享popwindow点击事件
     * */
    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId){
            case R.layout.popup_share:
                LinearLayout wechat = (LinearLayout)view.findViewById(R.id.share_wechat_lay);
                wechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow!=null){
                            popupWindow.dismiss();
                            // 分享到微信
                            initShareFunction(SHARE_MEDIA.WEIXIN);
                        }
                    }
                });
                LinearLayout moment = (LinearLayout)view.findViewById(R.id.share_moment_lay);
                moment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow!= null){
                            popupWindow.dismiss();
                            // 分享到朋友圈
                            initShareFunction(SHARE_MEDIA.WEIXIN_CIRCLE);
                        }
                    }
                });
                LinearLayout sina = (LinearLayout)view.findViewById(R.id.share_sina_lay);
                sina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow!=null){
                            popupWindow.dismiss();
                            // 分享到新浪
                            initShareFunction(SHARE_MEDIA.QQ);
                        }
                    }
                });

                break;
            default:
                break;
        }
    }


    // 初始化分享函数
    private void initShareFunction(SHARE_MEDIA value) {
//        UMImage image = new UMImage(getActivity(), R.drawable.logox);//资源文件
//        UMImage thumb =  new UMImage(getActivity(), R.drawable.logox);
//        image.setThumb(thumb);
//        UMWeb  web = new UMWeb("www.baidu.com");
//        web.setTitle("This is music title");//标题
//        web.setThumb(image);  //缩略图
//        web.setDescription("my description");//描述

        UMImage image = new UMImage(this, R.drawable.placerholder);
        UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
        image.setThumb(thumb);
        image.compressFormat = Bitmap.CompressFormat.PNG;

        web = new UMWeb("http://www.jubuxiu.com");
        web.setTitle("厦门钢鲸科技有限公司");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("安全高效的智能移动平台!");//描述


        new ShareAction(this).setPlatform(value)
//                .withText("安全高效的智能移动平台!")
//                .withMedia(image)
                .withMedia(web)
                .setCallback(umShareListener)
                .share();
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
//            shareDialog.show();
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Log.d("plat","platform"+platform);
//            shareDialog.dismiss();
            initSnackBar("分享成功!");

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            shareDialog.dismiss();
            initSnackBar("分享失败!");
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            shareDialog.dismiss();
            initSnackBar("取消分享!");
        }

        // 通用SnackBar
        private void initSnackBar(String value) {
            SnackbarUtils.showShortSnackbar(getWindow().getDecorView(), value, Color.WHITE, Color.parseColor("#128CEE"));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
