package com.temp.buda.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.common.framework.basic.AppManager;
import com.common.framework.click.SingleClick;

public class UpdateDialogViewModel {

    private Dialog dialog;

    private String newVersionName;

    private String newVersionInfo;

    private String LinkUrl;

    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }


    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public String getNewVersionName() {
        return newVersionName;
    }

    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }

    public String getNewVersionInfo() {
        return newVersionInfo;
    }

    public void setNewVersionInfo(String newVersionInfo) {
        this.newVersionInfo = newVersionInfo;
    }


    @SingleClick
    public void updateApp() {
        if (!TextUtils.isEmpty(getLinkUrl())){
            Activity peek = AppManager.getActivityStack().peek();
            openBrowser(peek,getLinkUrl());
        }
    }

    public static void openBrowser(Context context, String url){
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
// 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
// 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager()); // 打印Log   ComponentName到底是什么 L.d("componentName = " + componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
        }
    }
    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
                getDialog().dismiss();
                setDialog(null);
        }
    }
}
