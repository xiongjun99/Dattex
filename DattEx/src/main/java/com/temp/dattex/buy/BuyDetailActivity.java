package com.temp.dattex.buy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;
import com.temp.dattex.widget.TitleBar;

public class
BuyDetailActivity extends Activity {
    private TextView tvPrice, tvBuyBum, tvAmount, tvStarTime, tvEndTime, tvOrderStatus;
    private int id;
    private static boolean flag = true;
    private static final int TIMER = 999;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buydetail);
        initView();
        initData();
        setTimer();
    }
    private void setTimer(){
        new Thread(() -> {
            while (flag){
                try {
                    Thread.sleep(5000); //休眠一秒
                    mHanler.sendEmptyMessage(TIMER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void initData() {
        id = getIntent().getIntExtra("id", 0);
        if (getIntent().getStringExtra("price") != null) {
            tvPrice.setText(getIntent().getStringExtra("price") + "元");
        }
        if (getIntent().getStringExtra("num") != null) {
            tvBuyBum.setText(getIntent().getStringExtra("num"));
        }
        if (getIntent().getStringExtra("amount") != null) {
            tvAmount.setText(getIntent().getStringExtra("amount") + "元");
        }
    }

    private void initView() {
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvBuyBum = (TextView) findViewById(R.id.tv_buy_num);
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        tvStarTime = (TextView) findViewById(R.id.tv_startime);
        tvEndTime = (TextView) findViewById(R.id.tv_endtime);
        tvOrderStatus = (TextView) findViewById(R.id.tv_orderstatus);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
    }
    private Handler mHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TIMER:
                    getBuyDetail();
                    break;
                default:
                    break;
            }
        }
    };

    private void stopTimer(){
        flag = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
    private void getBuyDetail() {
        DataService.getInstance().getOtcDetail(id).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if (b!=null){
                        switch (b.getRecord().getState()){
                            case 0:
                                tvOrderStatus.setText("待转账");
                                break;
                            case 1:
                                tvOrderStatus.setText("已取消");
                                break;
                            case 3:
                                stopTimer();
                                tvOrderStatus.setText("已转账");
                                break;
                        }
                        tvStarTime.setText(Utils.dateTostring(b.getRecord().getCreated()));
                        tvEndTime.setText(Utils.dateTostring(b.getRecord().getUpdated()));
                    }
                }, t -> {
                });
    }
}
