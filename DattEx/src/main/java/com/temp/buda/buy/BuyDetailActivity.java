package com.temp.buda.buy;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.R;
import com.temp.buda.net.DataService;
import com.temp.buda.util.Utils;
import com.temp.buda.widget.TitleBar;

public class BuyDetailActivity extends Activity {
    private TextView tvPrice, tvBuyBum, tvAmount, tvStarTime, tvEndTime, tvOrderStatus;
    private int id = -1 , otcType = -1;
    private static boolean flag = true;
    private static final int TIMER = 1000;
    private TitleBar titleBar;
    private RelativeLayout rlConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buydetail);
        initView();
        initData();
    }
    private void setTimer(){
        new Thread(() -> {
            while (flag){
                try {
                    Thread.sleep(5000); //休眠一秒
//                    mHanler.sendEmptyMessage(TIMER);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void initData() {
          if (getIntent().getIntExtra("id", -1) !=-1){
              id = getIntent().getIntExtra("id", -1);
          }
        if (getIntent().getIntExtra("otcType", -1) !=-1){
            otcType = getIntent().getIntExtra("otcType", -1);
        }
        if (getIntent().getStringExtra("price") != null) {
            tvPrice.setText(getIntent().getStringExtra("price"));
        }
        if (getIntent().getStringExtra("num") != null) {
            tvBuyBum.setText(getIntent().getStringExtra("num"));
        }
        if (getIntent().getStringExtra("amount") != null) {
            tvAmount.setText(getIntent().getStringExtra("amount"));
        }
        getBuyDetail();
    }

    private void initView() {
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvBuyBum = (TextView) findViewById(R.id.tv_buy_num);
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        tvStarTime = (TextView) findViewById(R.id.tv_startime);
        tvEndTime = (TextView) findViewById(R.id.tv_endtime);
        tvOrderStatus = (TextView) findViewById(R.id.tv_orderstatus);
        rlConfirm = (RelativeLayout) findViewById(R.id.rl_confirm);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        rlConfirm.setOnClickListener(view -> {
            showDialog();
        });
    }

    public  void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        TextView tv_pricr = view.findViewById(R.id.tv_price);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tvDialogTitle = view.findViewById(R.id.tv_dialog_title);
            tvDialogTitle.setText("您是否取消订单？");
            tvConfirm.setText("确定");
            tvCancel.setText("取消");
        tv_pricr.setText(tvAmount.getText().toString());
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
         Cancel();
         dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
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
                        String symbol = "";
                        if (b.getRecord().getCurrency().contains("CNY")){
                            symbol = "¥";
                        }else {
                            symbol = "$";
                        }
                        if (otcType!=0){
                            tvPrice.setText(symbol+b.getRecord().getRatio());
                            tvBuyBum.setText(""+Utils.format8(b.getRecord().getActualAmount()));
                            tvAmount.setText(symbol+Utils.format8(b.getRecord().getMoney()));
                        }
                        switch (b.getRecord().getState()){
                            case 0:
                                if (b.getRecord().getInorout()==1){
                                    tvOrderStatus.setText("买家确认中");
                                    rlConfirm.setVisibility(View.VISIBLE);
                            }else {
                                    tvOrderStatus.setText("待转账");
                                    rlConfirm.setVisibility(View.VISIBLE);
                            }

                                break;
                            case 1:
                                tvOrderStatus.setText("已取消");
                                break;
                            case 2:
                                tvOrderStatus.setText("失败");
                                break;
                            case 3:
                                stopTimer();
                                if(otcType!=-1 && otcType == 1){
                                        tvOrderStatus.setText("卖家确认中");
                                }else if(otcType!=-1 && otcType == 2){
                                        tvOrderStatus.setText("买家确认中");
                                } else {
                                        tvOrderStatus.setText("卖家确认中");
                                }
                                break;
                            case 4:
                                tvOrderStatus.setText("已入账");
                                break;
                        }
                        tvStarTime.setText(b.getRecord().getCreated());
                        tvEndTime.setText(b.getRecord().getEndTime());
                    }
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                });
    }
    private void Cancel() {
        DataService.getInstance().Cancel(id).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null){
                        ToastUtil.show(BuyDetailActivity.this,"取消成功");
                        finish();
                    }
                }, t -> {
                    ToastUtil.show(getApplicationContext(),t.getMessage());
                });
    }
}
