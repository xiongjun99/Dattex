package com.temp.dattex.notice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.net.DataService;
import com.temp.dattex.widget.TitleBar;

public class NoticeInfoActivity extends BaseActivity {
  private  int id = 0;
  private String content,Time,Title;
  private TextView tvTitle,tvName,tvTime;
    private TitleBar titleBar;
    private LinearLayout ll;
    WebView webView;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_noticeinfo);
        id = getIntent().getIntExtra("id",0);
        Time = getIntent().getStringExtra("time");
        Title = getIntent().getStringExtra("title");
        initView();
//        initData();
    }

    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
//        tvName = (TextView)findViewById(R.id.tv_name);
//        tvName.setText("buda");
//        tvTitle = (TextView)findViewById(R.id.tv_title);
//        tvTitle.setText(Title);
//        tvTime = (TextView)findViewById(R.id.tv_time);
//        tvTime.setText(Time);
        webView = (WebView)findViewById(R.id.web);
//        webView.clearCache(true);
//        webView.clearHistory();
//        webView.requestFocus();
        WebSettings webSettings = webView.getSettings();
//        webSettings.setDatabaseEnabled(true);
//// 缓存白屏
//        String appCachePath = getApplicationContext().getCacheDir()
//                .getAbsolutePath() + "/webcache";
//// 设置 Application Caches 缓存目录
//        webSettings.setAppCachePath(appCachePath);
//        webSettings.setDatabasePath(appCachePath);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSavePassword(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webView.loadUrl("www.sina.com");
         ll = (LinearLayout)findViewById(R.id.ll);
         if (id == 1 ){
             ll.setVisibility(View.GONE);
         }
    }
    private void initData() {
        DataService.getInstance().getNoticeListInfo(id,1,1,1,1).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
//                    content = b.getContent();
                    content = "http://45.132.238.178/#/article?id=" + id;
                    WebSettings settings = webView.getSettings();
                    settings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
                    webView.setVerticalScrollBarEnabled(false);//不能垂直滑动
                    webView.setHorizontalScrollBarEnabled(false);//不能水平滑动
                    settings.setTextSize(WebSettings.TextSize.NORMAL);//通过设置WebSettings，改变HTML中文字的大小
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
                    settings.setAllowUniversalAccessFromFileURLs(true);
                    settings.setAllowFileAccess(true);
//                    settings.setUseWideViewPort(true);
//                    settings.setLoadWithOverviewMode(true);
                    settings.setAllowFileAccessFromFileURLs(true);
//设置WebView属性，能够执行Javascript脚本
                    webView.getSettings().setJavaScriptEnabled(true);//设置js可用
                    webView.setWebViewClient(new WebViewClient());
                    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
//                    content = "<html><style>*,body,html,div,p,img{border:0;margin:0;padding:0;} </style> <body>" + content + "</body></html>";
//                    String js = "<script type=\"text/javascript\">" +
//                            "var imgs = document.getElementsByTagName('img');" + // 找到img标签
//                            "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
//                            "imgs[i].style.width = '100%';" +  // 宽度改为100%
//                            "imgs[i].style.height = 'auto';" +
//                            "}" +
//                            "</script>";
//                    webView.loadUrl("http://45.132.238.178/#/article?id=2");
//                    webView.loadDataWithBaseURL(null, "<p style='font-size:18px;text-align:center;'>"+ "</p>" + content + js, "text/html; charset=UTF-8", "utf-8", null);
                }, t -> {
                    ToastUtil.show(this,"获取公告失败"+t.getMessage());
                });
    }
 }
