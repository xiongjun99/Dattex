package com.temp.dattex.binding.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;

import com.exchange.utilslib.LogUtil;
import com.temp.dattex.web.WebViewModel;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.binding.adapter
 * @FileName     : WebViewAdatper.java
 * @Author       : chao
 * @Date         : 2020/5/15
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class WebViewAdapter {

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter(value = {"webViewInit"})
    public static void setTitleText(WebView webView, WebViewModel viewModel) {

        WebSettings settings = webView.getSettings();
        settings.setAppCacheEnabled(false);
        settings.setSupportZoom(false);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                viewModel.getTitleText().set(title);
            }
        };
        webView.setWebChromeClient(webChromeClient);

        WebViewClient webViewClient = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        };
        webView.setWebViewClient(webViewClient);

        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                loadUrl(webView, viewModel);
                viewModel.getLoadUrl().removeOnPropertyChangedCallback(this);
            }
        };

        viewModel.getLoadUrl().addOnPropertyChangedCallback(callback);

        webView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                viewModel.getLoadUrl().removeOnPropertyChangedCallback(callback);
            }
        });
        viewModel.getLoadUrl().notifyChange();
    }

    private static void loadUrl(WebView webView, WebViewModel viewModel) {
        String url = viewModel.getLoadUrl().get();
        if (TextUtils.isEmpty(url) || !url.startsWith("http")) {
            LogUtil.e("无效的加载地址");
        } else {
            webView.loadUrl(url);
        }
    }
}
