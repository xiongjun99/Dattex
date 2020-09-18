package com.temp.buda.web;

import android.app.Application;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseViewModel;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.web
 * @FileName     : WebViewModel.java
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

public class WebViewModel extends BaseViewModel implements WebView.FindListener {

    public WebViewModel(@NonNull Application application) {
        super(application);
    }

    private ObservableField<String> Text = new ObservableField<>("");
    private ObservableField<String> loadUrl = new ObservableField<>();

    public ObservableField<String> getText() {
        return Text;
    }

    public void setTitleText(ObservableField<String> titleText) {
        this.Text = titleText;
    }

    public ObservableField<String> getLoadUrl() {

        return loadUrl;

    }

    public void setLoadUrl(ObservableField<String> loadUrl) {
        this.loadUrl = loadUrl;
    }

    @Override
    public void onFindResultReceived(int i, int i1, boolean b) {

    }
}
