package com.temp.buda.withdraw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseActivity;
import com.common.framework.basic.BaseApplication;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.DisplayUtil;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.bean.WithdrawBean;
import com.temp.buda.buy.BuyActivity;
import com.temp.buda.config.AssetsConfigs;
import com.temp.buda.databinding.ActivityWithdrawBinding;
import com.temp.buda.databinding.DialogLoadpayBinding;
import com.temp.buda.net.DataService;
import com.temp.buda.util.DialogUtil;
import com.temp.buda.util.Utils;
import com.temp.buda.widget.EditPop;
import com.yzq.zxinglibrary.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

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
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class WithdrawActivity extends BaseActivity<ActivityWithdrawBinding, WithdrawViewModel> {
    private EditText et_address,etAdressCode;
    private RadioButton rbUsdt,rbAdress;
    private LinearLayout ll_address,ll_select_address,ll_exchangeType;
    private RelativeLayout rlCollectionCard,rlConfirm,rl_sell_usdt,rl_withdraw;
    private TextView tvAdressCode,tvVerificationCode,tvConfirm,cnySymbol;
    private EditText etVerificationCode,etWithdrawAmount;
    private int reciveItemId,payType;
    private EditPop editPop;
    private AlertDialog dialog;
    private ProgressBar progressBar;
    private ImageView ivOk;
    private TextView tvOrderInfo ;

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
        viewModel.uc.pop.observe(this, isShow -> {
            viewModel.popStaus.set(0);
            LogUtil.e(isShow);
            createPop();
            editPop.popState(isShow);
        });
        viewModel.scanLiveData.observe(this, o -> requestPermission(CAMERA_REQ_CODE));
    }
    public void createPop() {
        if (editPop == null) {
            List<String> listData = new ArrayList<>();
            for (int i = 0; i < viewModel.otc.get().getOtcCfgs().size(); i++) {
                listData.add(viewModel.otc.get().getOtcCfgs().get(i).getCurrency());
            }
            editPop = new EditPop(this,viewModel.pPosition.get());
            editPop.setAdapterData(listData, binding.line);
            editPop.setOnItemClickListener(viewModel);
            editPop.setOnDismissListener(viewModel);
        }
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Bundle bundle = getIntent().getExtras();
        String balance  = bundle.getString("balance");
        viewModel.getBalance().set(balance);
        viewModel.getSellnumber().set(balance +" USDT");
        tvConfirm = (TextView)findViewById(R.id.tv_confirm);
        cnySymbol = (TextView)findViewById(R.id.cny_symbol);
        et_address = (EditText)findViewById(R.id.et_address);
        etWithdrawAmount= (EditText)findViewById(R.id.et_withdrawamount);
        etAdressCode = (EditText)findViewById(R.id.et_adresscode);
        etVerificationCode = (EditText)findViewById(R.id.et_verificationcode);
        ll_address = (LinearLayout)findViewById(R.id.ll_address);
        rl_sell_usdt = (RelativeLayout) findViewById(R.id.rl_sell_usdt);
        ll_select_address = (LinearLayout)findViewById(R.id.ll_select_address);
        ll_exchangeType = (LinearLayout)findViewById(R.id.ll_exchangeType);
        rl_withdraw = (RelativeLayout) findViewById(R.id.rl_withdraw);
        rlConfirm = (RelativeLayout) findViewById(R.id.rl_confirm);
        tvVerificationCode = (TextView)findViewById(R.id.tv_verificationcode);
        tvAdressCode = (TextView)findViewById(R.id.tv_adresscode);
        etWithdrawAmount.setHint(Utils.rvZeroAndDot(viewModel.minAmount.get()));
        tvAdressCode.setOnClickListener(view -> {
            AdresssendPhoneCode();
        });
        rlConfirm.setOnClickListener(view -> {
            if (viewModel.type.get() == 0){
                Confirm_Sell();
            } else {
                doWithdraw();
            }
        });
        tvVerificationCode.setOnClickListener(view -> {
            sendPhoneCode();
        });
        ll_select_address.setOnClickListener(view -> {
        });
        rbUsdt = (RadioButton) findViewById(R.id.rb_usdt);
        rbAdress = (RadioButton) findViewById(R.id.rb_address);
        rbUsdt.setOnClickListener(view -> {
            viewModel.getIsCheck().set(false);
            binding.tvRight.setText("出售记录");
            tvConfirm.setText(getResources().getString(R.string.Confirm_Sell));
            viewModel.type.set(0);
        });
        rbAdress.setOnClickListener(view -> {
            viewModel.getIsCheck().set(true);
            viewModel.type.set(1);
            tvConfirm.setText(getResources().getString(R.string.Confirm_Withdraw));
            binding.tvRight.setText("提币记录");
        });
        rlCollectionCard = (RelativeLayout)findViewById(R.id.rl_collection_card);
        rlCollectionCard.setOnClickListener(view -> {
            Intent it = new Intent(this,NewPayTypeActivity.class);
            startActivityForResult(it,2);
        });
        ll_exchangeType.setOnClickListener(view -> {
//            showPayDialog();
            viewModel.uc.pop.setValue(true);
        });
    }

    private void AdresssendPhoneCode() {
        try {
            final int i = Integer.parseInt(tvAdressCode.getText().toString());
            if (i == 1) {
                tvAdressCode.setText(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                tvAdressCode.setText(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(this::AdresssendPhoneCode, 1000);
            }
        } catch (Exception e) {
            tvAdressCode.setText("60");
            LooperUtil.getHandler().postDelayed(this::AdresssendPhoneCode, 1000);
            DataService.getInstance().sendCodeMessage("04").compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(this,t.getMessage());
            });
        }
    }

    public void Confirm_Sell() {
            if (TextUtils.isEmpty(viewModel.number.get())||Float.valueOf(viewModel.number.get())<Float.valueOf(viewModel.minAmount.get())) {
                viewModel.number.set("");
                viewModel.accountPrice.set("");
                ToastUtil.show(getApplication(),"最低卖出" + viewModel.minAmount.get()+"个，请重新输入");
                return;
            }
            if (AssetsConfigs.getInstance().getCoinInfo("USDT").getCoinId()==null){
                ToastUtil.show(getApplication(),"CoinId错误");
                return;
            }
        if (TextUtils.isEmpty(etVerificationCode.getText().toString())){
            ToastUtil.show(getApplication(),"请填写验证码");
            return;
        }
         DataService.getInstance().inoutWithdraw(etVerificationCode.getText().toString(),viewModel.getWithdrawCoin().get(),viewModel.number.get(),"CNY","",viewModel.accountPrice.get(),payType,reciveItemId).compose(ResponseTransformer.<WithdrawBean>handleResult()).subscribe(
         b -> {
             if (b.getRecord()!=null){
                 showDialog();
                 timer.start();
             }
        }, t -> {
             ToastUtil.show(BaseApplication.getInstance(),"操作失败,"+ t.getMessage());
        });
    }
    @SuppressLint("CheckResult")//钱包提币
    @SingleClick
    public void doWithdraw() {
        if(TextUtils.isEmpty(viewModel.getWithdrawAddress().get())){
            ToastUtil.show(BaseApplication.getInstance(),"请选择提币地址");
            return;
        }
        if(TextUtils.isEmpty(etAdressCode.getText().toString())){
            ToastUtil.show(BaseApplication.getInstance(),"请输入验证码");
            return;
        }
        if(TextUtils.isEmpty(viewModel.getAdressAmount().get())){
            ToastUtil.show(BaseApplication.getInstance(),"请输入提币数量");
            return;
        }
        DataService.getInstance().withdrawCoin(etAdressCode.getText().toString(), viewModel.getWithdrawAmount().get(),viewModel.getWithdrawCoin().get(), viewModel.getWithdrawAddress().get()).compose(ResponseTransformer.handleResult()).<Object>subscribe(
                b -> {
                    finish();
                    ToastUtil.show(BaseApplication.getInstance(),"操作成功");
                }, t -> {
                    ToastUtil.show(BaseApplication.getInstance(),"操作失败,"+ t.getMessage());
                }
        );
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
            DataService.getInstance().sendCodeMessage("04").compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(this,t.getMessage());
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
           if (data !=null&&data.getStringExtra("name")!=null){
            viewModel.WithdrawAddress(data.getStringExtra("name"));
            et_address.setTextSize(12);
           }
           break;
            case  2:
                if (data !=null&&data.getStringExtra("name")!=null){
                    reciveItemId = data.getIntExtra("reciveItemId",0);
                    payType = data.getIntExtra("payType",0);
                    viewModel.getPayType().set(data.getStringExtra("name")+" "+data.getStringExtra("account"));
                }
                break;
        }
    }
    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_withdraw_load,null,false);
        dialog = new AlertDialog.Builder(this).setView(view).create();
         progressBar = view.findViewById(R.id.progress);
         ivOk = view.findViewById(R.id.iv_ok);
         tvOrderInfo = view.findViewById(R.id.tv_order_info);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(this)/2 + DisplayUtil.dp2px(this, 80);
        attributes.height = DisplayUtil.getScreenContentHeight(this) - DisplayUtil.dp2px(this, 44);
        attributes.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    CountDownTimer timer = new CountDownTimer(7000, 1000) {
        public void onTick(long millisUntilFinished) {
            tvOrderInfo.setText("买家已接单 " + ((millisUntilFinished / 1000) - 1) + " s");
            if (millisUntilFinished / 1000 == 2){
                ivOk.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            } else {
                ivOk.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
            if (millisUntilFinished / 1000 == 1) {
                dialog.dismiss();
                dialog = null;
                ToastUtil.show(WithdrawActivity.this,"操作成功");
                finish();
            } else {

            }
        }
        public void onFinish() {
        }
    };
}
