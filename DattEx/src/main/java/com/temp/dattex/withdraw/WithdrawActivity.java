package com.temp.dattex.withdraw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityWithdrawBinding;
import com.yzq.zxinglibrary.android.CaptureActivity;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.withdraw
 * @FileName     : WithdrawActivity.java
 * @Author       : chao
 * @Date         : 2020/5/19
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
public class WithdrawActivity extends BaseActivity<ActivityWithdrawBinding, WithdrawViewModel> {
    private EditText et_address;
    private RadioButton rbUsdt,rbAdress;
    private LinearLayout ll_address,ll_sell_usdt,ll_select_address;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_withdraw;
    }

    @Override
    public int initVariableId() {
        return BR.withdrawViewModel;
    }

    public int CAMERA_REQ_CODE = 0x1110;

    @Override
    public void initViewObservable() {
        viewModel.scanLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                requestPermission(CAMERA_REQ_CODE);
            }
        });
    }

    private void requestPermission(int requestCode) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
        } else {
            //扫码
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, CAMERA_REQ_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions == null || grantResults == null) {
            return;
        }
        if (grantResults.length < 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (requestCode == CAMERA_REQ_CODE) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, CAMERA_REQ_CODE);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        et_address = (EditText)findViewById(R.id.et_address);
        ll_address = (LinearLayout)findViewById(R.id.ll_address);
        ll_sell_usdt = (LinearLayout)findViewById(R.id.ll_sell_usdt);
        ll_select_address = (LinearLayout)findViewById(R.id.ll_select_address);
        ll_select_address.setOnClickListener(view -> {
            Intent intent = new Intent(WithdrawActivity.this,WithdrawListActivity.class);
            startActivityForResult(intent,1);
        });
        rbUsdt = (RadioButton) findViewById(R.id.rb_usdt);
        rbAdress = (RadioButton) findViewById(R.id.rb_address);
        rbUsdt.setOnClickListener(view -> {
            ll_address.setVisibility(View.GONE);
            ll_sell_usdt.setVisibility(View.VISIBLE);
        });
        rbAdress.setOnClickListener(view -> {
            ll_address.setVisibility(View.VISIBLE);
            ll_sell_usdt.setVisibility(View.GONE);
        });

//        DataService.getInstance().checkWithdraw().compose(ResponseTransformer.<Boolean>handleResult()).subscribe(b -> {
//            if (!b) {
//                finish();
//            }
//        }, t -> {
//            ToastUtil.show(BaseApplication.getInstance(), t.getMessage());
//            finish();
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
           if (data !=null&&data.getStringExtra("str")!=null){
            viewModel.WithdrawAddress(data.getStringExtra("str")+"\n"+data.getStringExtra("str1"));
            et_address.setTextSize(12);
           }
           break;
        }
    }
}
