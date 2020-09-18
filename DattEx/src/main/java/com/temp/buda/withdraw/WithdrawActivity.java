package com.temp.buda.withdraw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.common.framework.basic.BaseActivity;
import com.common.framework.basic.BaseApplication;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.bean.WithdrawBean;
import com.temp.buda.config.AssetsConfigs;
import com.temp.buda.databinding.ActivityWithdrawBinding;
import com.temp.buda.net.DataService;
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
    private LinearLayout ll_address,ll_sell_usdt,ll_select_address,ll_exchangeType,ll_withdraw;
    private RelativeLayout rlCollectionCard,rlConfirm;
    private TextView tvAdressCode,tvVerificationCode,tvConfirm,cnySymbol;
    private EditText etVerificationCode,etWithdrawAmount;
    private int reciveItemId,payType;
    private int type = 1;
    private EditPop editPop;
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
        Bundle bundle = getIntent().getExtras();
        String balance  = bundle.getString("balance");
        viewModel.getBalance().set(balance);
        System.out.println("----------"+balance);
        viewModel.getSellnumber().set(balance +" USDT");
        tvConfirm = (TextView)findViewById(R.id.tv_confirm);
        cnySymbol = (TextView)findViewById(R.id.cny_symbol);
        et_address = (EditText)findViewById(R.id.et_address);
        etWithdrawAmount= (EditText)findViewById(R.id.et_withdrawamount);
        etAdressCode = (EditText)findViewById(R.id.et_adresscode);
        etVerificationCode = (EditText)findViewById(R.id.et_verificationcode);
        ll_address = (LinearLayout)findViewById(R.id.ll_address);
        ll_sell_usdt = (LinearLayout)findViewById(R.id.ll_sell_usdt);
        ll_select_address = (LinearLayout)findViewById(R.id.ll_select_address);
        ll_exchangeType = (LinearLayout)findViewById(R.id.ll_exchangeType);
        ll_withdraw = (LinearLayout)findViewById(R.id.ll_withdraw);
        rlConfirm = (RelativeLayout) findViewById(R.id.rl_confirm);
        tvVerificationCode = (TextView)findViewById(R.id.tv_verificationcode);
        tvAdressCode = (TextView)findViewById(R.id.tv_adresscode);
        etWithdrawAmount.setHint(Utils.rvZeroAndDot(viewModel.minAmount.get()));
        tvAdressCode.setOnClickListener(view -> {
            AdresssendPhoneCode();
        });
        rlConfirm.setOnClickListener(view -> {
            if (type ==0){
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
            ll_address.setVisibility(View.GONE);
            ll_sell_usdt.setVisibility(View.VISIBLE);
            tvConfirm.setText(getResources().getString(R.string.Confirm_Sell));
            type= 0;
            ll_withdraw.setBackgroundColor(getResources().getColor(R.color.color_1A1C29));
        });
        rbAdress.setOnClickListener(view -> {

            ll_withdraw.setBackgroundColor(getResources().getColor(R.color.color_282C42));
            type = 1;
            ll_address.setVisibility(View.VISIBLE);
            ll_sell_usdt.setVisibility(View.GONE);
            tvConfirm.setText(getResources().getString(R.string.Confirm_Withdraw));
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
              ToastUtil.show(BaseApplication.getInstance(),"操作成功");
              finish();
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
}
