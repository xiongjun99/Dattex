package com.temp.buda;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewGroup;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

public abstract class BaseActivity extends RxFragmentActivity {

    public ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        progressDialog = new ProgressDialog(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showLoading(boolean cancelable) {
        if (this.isFinishing()) return;

        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.setCancelable(cancelable);
            progressDialog.show();
        }

    }

    public void showLoading() {
        showLoading(true);
    }

    public void showLoading(String message, boolean cancelable) {
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(cancelable);
            progressDialog.show();
        }
    }
    /**
     *
     */
    public void dismissLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0).setBackground(null);
        super.onDestroy();
    }

    protected String getTag(){
        return "sssss";
    }
    }
