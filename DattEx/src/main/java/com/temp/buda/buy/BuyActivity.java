package com.temp.buda.buy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.R;
import com.temp.buda.net.DataService;
import com.temp.buda.util.Utils;
import com.temp.buda.widget.TitleBar;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
@RequiresApi(api = Build.VERSION_CODES.M)
public class BuyActivity extends Activity {
    private TextView tvProposal, tvPrice, tvBuyBum, tvAmount, tvSafety, tvPayee, tvOpenbank, tvBankaccount,tvAccount,tvBuyTime,tv_cancel,tvAccountName;
    private int id;
    private RelativeLayout rlTransferAccounts;
    private LinearLayout llBankInfo,llOther;
    private TitleBar titleBar;
    public   int count = 900;
    private  Timer mTimer = null;
    private  TimerTask mTimerTask = null;
    private int dialogType = 0;
    private int payType = 0;
    private TextView tvBankaccountCopy,tvOpenbankCopy,tvAccountCopy,tvPayeeCopy,tvSafetyCopy;
    private TextView tvTitleInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();
        initData();
        startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        payType = getIntent().getIntExtra("payType", 0);
        id = getIntent().getIntExtra("id", 0);
        tvAccountName = (TextView) findViewById(R.id.tv_account_name);
        tvProposal = (TextView) findViewById(R.id.tv_proposal);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvBuyBum = (TextView) findViewById(R.id.tv_buy_num);
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        tvSafety = (TextView) findViewById(R.id.tv_safety);
        tvPayee = (TextView) findViewById(R.id.tv_payee);
        tvOpenbank = (TextView) findViewById(R.id.tv_openbank);
        tvBankaccount = (TextView) findViewById(R.id.tv_bankaccount);
        tvBuyTime = (TextView)  findViewById(R.id.tv_buy_time);
        tv_cancel = (TextView)  findViewById(R.id.tv_cancel);
        rlTransferAccounts = (RelativeLayout) findViewById(R.id.rl_transfer_accounts);
        llBankInfo = (LinearLayout)findViewById(R.id.ll_bank_info);
        llOther = (LinearLayout)findViewById(R.id.ll_other);
        tvAccount = (TextView) findViewById(R.id.tv_account);
        tvBankaccountCopy = (TextView) findViewById(R.id.tv_bankaccount_copy);
        tvOpenbankCopy = (TextView) findViewById(R.id.tv_openbank_copy);
        tvAccountCopy = (TextView) findViewById(R.id.tv_account_copy);
        tvPayeeCopy = (TextView) findViewById(R.id.tv_payeecopy);
        tvSafetyCopy = (TextView) findViewById(R.id.tv_safety_copy);
        tvTitleInfo = (TextView) findViewById(R.id.tv_title_info);
        rlTransferAccounts.setOnClickListener(view -> {
            dialogType = 0;
            showBuyDialog();
        });
        tv_cancel.setOnClickListener(view -> {
            dialogType = 1;
            showBuyDialog();
        });
        if (payType==0){
            tvTitleInfo.setText(this.getResources().getString(R.string.text_pay_mode));
            llBankInfo.setVisibility(View.VISIBLE);
            llOther.setVisibility(View.GONE);
        }else {
            tvTitleInfo.setText("");
            llBankInfo.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
        }
        tvBankaccountCopy.setOnClickListener(view -> {
            Utils.setClipboard(this,tvBankaccount.getText().toString());
            ToastUtil.show(this,"已复制");
        });
        tvOpenbankCopy.setOnClickListener(view -> {
            Utils.setClipboard(this,tvOpenbank.getText().toString());
            ToastUtil.show(this,"已复制");
        });
        tvAccountCopy.setOnClickListener(view -> {
            Utils.setClipboard(this,tvAccount.getText().toString());
            ToastUtil.show(this,"已复制");
        });
        tvPayeeCopy.setOnClickListener(view -> {
            Utils.setClipboard(this,tvPayee.getText().toString());
            ToastUtil.show(this,"已复制");
        });
        tvSafetyCopy.setOnClickListener(view -> {
            Utils.setClipboard(this,tvSafety.getText().toString());
            ToastUtil.show(this,"已复制");
        });
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
    }

    private void initData() {
        int fstart = tvProposal.getText().toString().indexOf("支付方");
        int fend = fstart + "支付方".length();
        SpannableStringBuilder style = new SpannableStringBuilder(tvProposal.getText().toString());
        style.setSpan(new ForegroundColorSpan(Color.WHITE), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tvProposal.setText(style);
        if (getIntent().getStringExtra("payForType") != null) {
            tvAccountName.setText(getIntent().getStringExtra("payForType") + "收款账号");
        }
        if (getIntent().getStringExtra("price") != null) {
            tvPrice.setText( getIntent().getStringExtra("unit") + getIntent().getStringExtra("price") + "");
        }
        if (getIntent().getStringExtra("num") != null) {
            tvBuyBum.setText(getIntent().getStringExtra("num"));
        }
        if (getIntent().getStringExtra("amount") != null) {
            tvAmount.setText(getIntent().getStringExtra("unit") + Utils.format4(Utils.multiply(getIntent().getStringExtra("price"),getIntent().getStringExtra("num"))) + "");
        }
        if (getIntent().getStringExtra("name") != null) {
            tvPayee.setText(getIntent().getStringExtra("name"));
        }
        if (getIntent().getStringExtra("ext") != null) {
            tvOpenbank.setText(getIntent().getStringExtra("ext"));
        }
        if (getIntent().getStringExtra("card") != null) {
            tvBankaccount.setText(getIntent().getStringExtra("card"));
            tvAccount.setText(getIntent().getStringExtra("card"));
        }
        if (id > 0) {
            tvSafety.setText(id + "");
        }
    }
    private void getTransferred() {
        DataService.getInstance().getTransferred(id).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null){
                        Intent it = new Intent(this,BuyDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("price",tvPrice.getText().toString());
                        bundle.putString("num",tvBuyBum.getText().toString());
                        bundle.putString("amount",tvAmount.getText().toString());
                        bundle.putInt("id",id);
                        it.putExtras(bundle);//将bundle传入intent中。
                        startActivity(it);
                        finish();
                    }
                }, t -> {
                    ToastUtil.show(getApplicationContext(),t.getMessage());
                });
    }
    private void Cancel() {
        DataService.getInstance().Cancel(id).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null){
                        ToastUtil.show(BuyActivity.this,"取消成功");
                        finish();
                    }
                }, t -> {
                    ToastUtil.show(getApplicationContext(),t.getMessage());
                });
    }
    public  void startTimer(){
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    mhandler.sendEmptyMessage(1);
                    count --;
                }
            };
        }

        if(mTimer != null && mTimerTask != null )
            mTimer.schedule(mTimerTask, 1000, 1000);
    }

    public  void stopTimer(){

        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        count = 0;
    }
    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                tvBuyTime.setText("倒计时:"+getTime(count));
                if (count==0){
                    stopTimer();
                }
            }
        }
    };
    public static String getTime(int seconds) {
        String standardTime;
        if (seconds <= 0){
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }
    public  void showBuyDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        TextView tv_pricr = view.findViewById(R.id.tv_price);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        TextView tvDialogTitle = view.findViewById(R.id.tv_dialog_title);
        if (dialogType == 0 ){
            tvDialogTitle.setText("您是否已完成转账？");
            tvConfirm.setText("我已转账");
            tvCancel.setText("重新转账");
        } else {
            tvDialogTitle.setText("您是否取消转账？");
            tvConfirm.setText("确定");
            tvCancel.setText("取消");
        }
        tv_pricr.setText(tvAmount.getText().toString());
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                if (dialogType == 0 ){
                    getTransferred();
                    dialog.dismiss();
                } else {
                    Cancel();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
