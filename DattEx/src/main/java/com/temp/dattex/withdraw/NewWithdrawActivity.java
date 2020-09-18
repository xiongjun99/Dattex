package com.temp.dattex.withdraw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.net.DataService;
import com.temp.dattex.widget.EditPop;
import com.temp.dattex.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

//新增提币地址
public class NewWithdrawActivity extends Activity implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    private TitleBar titleBar;
    private RelativeLayout rlCoin;
    private EditPop popwindow;
    private int pos = 0;
    private boolean isShow = true;
    private TextView tvCoinId , tvVerificationCode;
    private List<String> listData;
    private RelativeLayout rlConfirm;
    private EditText etVerificationcode,etAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newwithdraw);
        initView();
    }

    @SuppressLint("NewApi")
    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        tvCoinId = findViewById(R.id.tv_coinid);
        rlCoin = findViewById(R.id.rl_coin);
        tvVerificationCode = findViewById(R.id.tv_verificationcode);
        rlConfirm  = findViewById(R.id.rl_confirm);
        etVerificationcode = findViewById(R.id.et_verificationcode);
        etAddress = findViewById(R.id.et_address);
        rlConfirm.setOnClickListener(view -> {
            addMemberPayType();
        });
        tvVerificationCode.setOnClickListener(view -> {
            sendPhoneCode();
        });
        rlCoin.setOnClickListener(view -> {
            createPop();
            popwindow.popState(isShow);
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void createPop() {
        if (popwindow == null) {
             listData = new ArrayList<>();
             listData.add("USDT");
             popwindow = new EditPop(NewWithdrawActivity.this,pos);
             popwindow.setAdapterData(listData,rlCoin);
             popwindow.setOnItemClickListener(this);
             popwindow.setOnDismissListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tvCoinId.setText(listData.get(i));
        popwindow.dismiss();
    }

    @Override
    public void onDismiss() {
    }
    public void sendPhoneCode() {
        try {
            final int i = Integer.parseInt(tvVerificationCode.getText().toString());
            if (i == 1) {
                tvVerificationCode.setText(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                tvVerificationCode.setText(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            }
        } catch (Exception e) {
            tvVerificationCode.setText("60");
            LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            DataService.getInstance().sendCodeMessage("05").compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(this,t.getMessage());
            });
        }
    }
    //    type (1银行 2支付宝 3微信 4数字钱包)
    public  void addMemberPayType(){
        DataService.getInstance().addMemberPayType(etAddress.getText().toString(),"","","","0","","","","",4,etVerificationcode.getText().toString()).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    ToastUtil.show(this,"添加成功");
                    finish();
                }, t -> {
                    ToastUtil.show(this,t.getMessage());
                });
    }
}
