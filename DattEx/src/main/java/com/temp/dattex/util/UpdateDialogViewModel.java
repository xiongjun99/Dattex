package com.temp.dattex.util;

import android.app.Activity;
import android.app.Dialog;

import com.common.framework.basic.AppManager;
import com.common.framework.click.SingleClick;

public class UpdateDialogViewModel {

    private Dialog dialog;

    private String newVersionName;

    private String newVersionInfo;

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
        Activity peek = AppManager.getActivityStack().peek();
//        Utils.DownLoadAPK(peek,"https://study.163.com/pub/study-android-official.apk");
    }

    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
    }
}
