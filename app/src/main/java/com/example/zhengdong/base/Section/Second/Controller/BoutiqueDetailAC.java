/*
 * Copyright (c) create by ki1lt0 .
 */

package com.example.zhengdong.base.Section.Second.Controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.example.zhengdong.base.APIManager.HttpInterFace;
import com.example.zhengdong.base.APIManager.HttpRequest;
import com.example.zhengdong.base.APIManager.UrlUtils;
import com.example.zhengdong.base.Macro.LogUtil;
import com.example.zhengdong.base.Macro.SharedPreferencesUtils;
import com.example.zhengdong.base.Macro.XToast;
import com.example.zhengdong.base.Macro.popupWindow.CommonPupWindowView;
import com.example.zhengdong.base.Section.First.View.GlideApp;
import com.example.zhengdong.base.Section.First.View.RecyclerBanner;
import com.example.zhengdong.base.Section.Five.Model.RequestModel;
import com.example.zhengdong.base.Section.Five.View.CircleImageView;
import com.example.zhengdong.base.Section.Login.Controller.LoginAC;
import com.example.zhengdong.base.Section.Second.Model.BoutiqueDetailModel;
import com.example.zhengdong.base.Section.Second.Model.IsRaiseModel;
import com.example.zhengdong.base.Section.Second.View.AndroidBug5497Workaround;
import com.example.zhengdong.base.Section.Second.View.StatusBarCompat.StatusBarCompat;
import com.example.zhengdong.jbx.R;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoutiqueDetailAC extends AppCompatActivity implements CommonPupWindowView.ViewInterface {

    @BindView(R.id.header_pic)
    RecyclerBanner headerPic;
    @BindView(R.id.mine_header_pic)
    CircleImageView mineHeaderPic;
    @BindView(R.id.head_sv)
    ScrollView headSv;
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
    @BindView(R.id.name_txt)
    TextView nameTxt;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.time_txt)
    TextView timeTxt;
    @BindView(R.id.use_txt)
    TextView useTxt;
    @BindView(R.id.raise_txt)
    TextView raiseTxt;
    @BindView(R.id.comment_txt)
    TextView commentTxt;
    @BindView(R.id.material_txt)
    TextView materialTxt;
    @BindView(R.id.height_txt)
    TextView heightTxt;
    @BindView(R.id.color_txt)
    TextView colorTxt;
    @BindView(R.id.length_txt)
    TextView lengthTxt;
    @BindView(R.id.width_txt)
    TextView widthTxt;
    @BindView(R.id.total_sum_txt)
    TextView totalSumTxt;
    @BindView(R.id.descript_txt)
    TextView descriptTxt;
    @BindView(R.id.online_view)
    RelativeLayout onlineView;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.no_net_view)
    LinearLayout noNetView;
    @BindView(R.id.pic_header)
    ImageView picHeader;
    private CommonPupWindowView popupWindow;
    private UMWeb web;
    private LoadingDailog shareDialog;
    private BoutiqueDetailModel.DataBean dataSource;
    private List<RecyclerBanner.BannerEntity> urls = new ArrayList<>();
    private String Fanc_id = "";
    private boolean isRaise = false;
    private String Graphical_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_detail_ac);
        AndroidBug5497Workaround.assistActivity(findViewById(R.id.detail_content));
        ButterKnife.bind(this);
        StatusBarCompat.translucentStatusBar(BoutiqueDetailAC.this, true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("分享中...")
                .setCancelable(true)
                .setCancelOutside(true);
        shareDialog = loadBuilder.create();
        Graphical_id = getIntent().getStringExtra("Graphical_id");
        Fanc_id = getIntent().getStringExtra("Fanc_id");
        initBoutiqueDetailData(Graphical_id, Fanc_id, 0);
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                boolean isOK = false;
                //目前输入框需要支持多行输入，此时enter键的内容不会更改，且按下时actionId为0；
                // 注意不同的手机可能有兼容性问题，此时只监听enter键的按下
                //当actionId == XX_SEND
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件=
                    initCommentData("1");
                    isOK = true;
                }
                return isOK;
            }
        });

        // 查看接口
        initCommentData("0");
        // 查询是否点赞
        initQueryIsRaiseData();
    }

    private void initQueryIsRaiseData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("fanc_id", Fanc_id);
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.BOUTIQUE_QUERY_ISRAISE_URL, map, "", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                IsRaiseModel requestModel = new Gson().fromJson(response, IsRaiseModel.class);
                if (requestModel.getCode() == 200) {
                    if (requestModel.isData()) {
                        raiseBtn.setBackgroundResource(R.drawable.icon_tab_fab_blue);
                        isRaise = true;
                    } else {
                        raiseBtn.setBackgroundResource(R.drawable.icon_tab_fab);
                        isRaise = false;
                    }
                } else {
                }
            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {

            }
        });
    }

    /**
     * 添加详情
     */
    private void initCommentData(final String type) {

        // 要验证是否登录
        String token = String.valueOf(SharedPreferencesUtils.getParam(this, UrlUtils.APP_TOKEN, ""));
        if (TextUtils.isEmpty(token)) {
            if (type.equals("1") || type.equals("2") || type.equals("3")) {
                XToast.show(getBaseContext(), "你还没有登录!");
                Intent intent = new Intent(BoutiqueDetailAC.this, LoginAC.class);
                startActivity(intent);
                return;
            }
        }
        String commment = "";
        if (type.equals("1")) {
            commment = edit.getText().toString().trim();
            if (TextUtils.isEmpty(commment)) {
                XToast.show(getBaseContext(), "评论不能为空!");
                return;
            }
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("fanc_id", Fanc_id);
        map.put("comm_type", type);
        if (type.equals("1")) {
            map.put("commnents", commment);
        }
        HttpRequest.URL_GET_REQUEST(this, UrlUtils.BOUTIQUE_COMMENT_URL, map, "", false, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                RequestModel requestModel = new Gson().fromJson(response, RequestModel.class);
                if (type.equals("1")) {
                    if (requestModel.getCode() == 200) {
                        XToast.show(getBaseContext(), "评论成功!");
                        Intent intent = new Intent(BoutiqueDetailAC.this, BoutiqueListAC.class);
                        intent.putExtra("Fancid", Fanc_id);
                        startActivity(intent);
                        edit.setText("");
                    } else {
                        XToast.show(getBaseContext(), "评论失败!");
                    }
                }
                if (type.equals("2")) {
                    if (requestModel.getCode() == 200) {
                        XToast.show(getBaseContext(), "点赞成功!");
                        raiseBtn.setBackgroundResource(R.drawable.icon_tab_fab_blue);
                        isRaise = true;
                    } else {
                        XToast.show(getBaseContext(), "点赞失败!");
                        isRaise = false;
                    }
                }
                if (type.equals("3")) {
                    if (requestModel.getCode() == 200) {
                        XToast.show(getBaseContext(), "取消成功!");
                        raiseBtn.setBackgroundResource(R.drawable.icon_tab_fab);
                        isRaise = false;
                    } else {
                        XToast.show(getBaseContext(), "取消失败!");
                        isRaise = false;
                    }
                }

            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {

            }
        });
    }

    /**
     * 精品详情
     */
    private void initBoutiqueDetailData(String graphical_id, String Fanc_id, int type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("graphical_id", graphical_id);
        map.put("fanc_id", Fanc_id);
        map.put("comm_type", String.valueOf(type));
        HttpRequest.URL_JSONGET_REQUEST(this, UrlUtils.BOUTIQUE_DETAIL_URL, map, "加载中...", true, new HttpInterFace() {
            @Override
            public void URL_REQUEST(String response) {
                BoutiqueDetailModel boutiqueDetailModel = new Gson().fromJson(response, BoutiqueDetailModel.class);
                if (boutiqueDetailModel.getCode() == 200) {
                    onlineView.setVisibility(View.VISIBLE);
                    noNetView.setVisibility(View.GONE);
                    dataSource = boutiqueDetailModel.getData();
                    initValueForView();
                } else {
                    XToast.show(getBaseContext(), "" + boutiqueDetailModel.getMsg());
                }
            }

            @Override
            public void BEFORE() {

            }

            @Override
            public void AFTER() {

            }

            @Override
            public void NOCONNECTION() {
                onlineView.setVisibility(View.GONE);
                noNetView.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 赋值方法
     */
    private void initValueForView() {
//        for (int i = 0; i < dataSource.getPic_list().size(); i++) {
//            urls.add(new Entity(UrlUtils.BASE_PIC_URL + dataSource.getPic_list().get(i).getPic_path(), dataSource.getPic_list().get(i).getPic_des()));
//        }
//        headerPic.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
//            @Override
//            public void onClick(RecyclerBanner.BannerEntity entity) {
//                LogUtil.e("网址为" + entity.getLink());
//
//            }
//        });
//        headerPic.setDatas(urls);
        GlideApp.with(this)
                .load(UrlUtils.BASE_PIC_URL + dataSource.getPic_list().get(0).getPic_path())
                .placeholder(R.drawable.placerholder)
                .fitCenter()
                .into(picHeader);
        nameTxt.setText(dataSource.getUser_name());
        titleTxt.setText(dataSource.getFan_title());
        useTxt.setText(dataSource.getRead_num() + "");
        raiseTxt.setText(dataSource.getPraise_num() + "");
        commentTxt.setText(dataSource.getComment_num() + "");
        materialTxt.setText("材质：" + dataSource.getType());
        heightTxt.setText("厚度：" + dataSource.getThickness_num());
        colorTxt.setText("板材颜色：" + dataSource.getColor());
        lengthTxt.setText("长度：" + dataSource.getLength_num());
        widthTxt.setText("宽度：" + dataSource.getWidth_num());
        totalSumTxt.setText("总面积m²：" + dataSource.getAllArgs());
        descriptTxt.setText(dataSource.getPresentation());
    }


    private class Entity implements RecyclerBanner.BannerEntity {

        String url;
        String link;

        public Entity(String url, String link) {
            this.url = url;
            this.link = link;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getLink() {
            return link;
        }
    }

    @OnClick({R.id.back, R.id.share, R.id.comment_list_btn, R.id.raise_btn, R.id.no_net_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                initShareForFriend();
                break;
            case R.id.comment_list_btn:
                Intent intent = new Intent(BoutiqueDetailAC.this, BoutiqueListAC.class);
                intent.putExtra("Fancid", Fanc_id);
                startActivity(intent);
                break;
            case R.id.raise_btn:
                if (isRaise) {
                    initCommentData("3");
                } else {
                    initCommentData("2");
                }
                break;
            case R.id.no_net_view:
                initBoutiqueDetailData(Graphical_id, Fanc_id, 0);
                break;
            default:
                break;
        }
    }

    /**
     * 分享给好友
     */
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
        switch (layoutResId) {
            case R.layout.popup_share:
                LinearLayout wechat = (LinearLayout) view.findViewById(R.id.share_wechat_lay);
                wechat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            // 分享到微信
                            initShareFunction(SHARE_MEDIA.WEIXIN);
                        }
                    }
                });
                LinearLayout moment = (LinearLayout) view.findViewById(R.id.share_moment_lay);
                moment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            // 分享到朋友圈
                            initShareFunction(SHARE_MEDIA.WEIXIN_CIRCLE);
                        }
                    }
                });
                LinearLayout sina = (LinearLayout) view.findViewById(R.id.share_sina_lay);
                sina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            // 分享到qq
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
//        UMImage image = new UMImage(this, R.drawable.placerholder);//资源文件
//        UMImage thumb =  new UMImage(this, R.mipmap.ic_launcher);
//        image.setThumb(thumb);
//        UMWeb  web = new UMWeb("www.baidu.com");
//        web.setTitle("This is music title");//标题
//        web.setThumb(image);  //缩略图
//        web.setDescription("my description");//描述

        UMImage image = new UMImage(this, UrlUtils.BASE_PIC_URL + dataSource.getPic_path());
        UMImage thumb = new UMImage(this, R.mipmap.ic_launcher);
        image.setThumb(thumb);
        image.compressFormat = Bitmap.CompressFormat.PNG;

        web = new UMWeb("http://www.jubuxiu.com");
        web.setTitle(dataSource.getFan_title());//标题
        web.setThumb(image);  //缩略图
        web.setDescription(dataSource.getPresentation());//描述


        new ShareAction(BoutiqueDetailAC.this).setPlatform(value)
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
            shareDialog.show();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            shareDialog.dismiss();
            XToast.show(getBaseContext(), "分享成功!");
            initCommentData("4");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            shareDialog.dismiss();
            XToast.show(getBaseContext(), "分享失败!");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            shareDialog.dismiss();
            XToast.show(getBaseContext(), "取消分享!");
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (shareDialog != null) {
            shareDialog.dismiss();
        }
    }

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
