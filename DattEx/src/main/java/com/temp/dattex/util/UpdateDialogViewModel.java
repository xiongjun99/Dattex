package com.temp.dattex.util;

import android.app.Dialog;

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

    }

    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
    }
}
