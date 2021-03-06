package com.temp.buda.wallet;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.bus.SingleLiveEvent;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.bean.NewAssetsBean;
import com.temp.buda.bean.OTCcfgBean;
import com.temp.buda.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.buda.buy.BuyActivity;
import com.temp.buda.config.AssetsConfigs;
import com.temp.buda.databinding.DialogLoadpayBinding;
import com.temp.buda.net.DataService;
import com.temp.buda.record.CoinRecordActivity;
import com.temp.buda.record.RecordActivity;
import com.temp.buda.util.DialogUtil;
import com.temp.buda.util.Utils;
import com.temp.buda.withdraworwallet.WithdrawOrWalletActivity;

import java.util.Hashtable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.splash
 * @FileName     : SplashViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/20
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
public class WalletModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {

    //封装一个界面发生改变的观察者
    public WalletModel.UIChangeObservable pay_uc = new WalletModel.UIChangeObservable();
    public WalletModel.UIChangeObservable uc = new WalletModel.UIChangeObservable();
    private ObservableField<String> exchangeType = new ObservableField<>("");
    private ObservableField<String> amount = new ObservableField<>("0.0000000");
    private ObservableField<String> price = new ObservableField<>("");
    private ObservableField<String> balance = new ObservableField<>("");
    private ObservableField<String> name = new ObservableField<>("");
    private ObservableField<String> payForType = new ObservableField<>("");
    private ObservableField<Integer> payForTypeID = new ObservableField<>(0);
    private ObservableField<Integer> id = new ObservableField<>(0);
    public ObservableField<String> OtcminAmount = new ObservableField<>("");
    public ObservableField<String> OtcMaxAmount = new ObservableField<>("");
    private ObservableField<Boolean> showOtc = new ObservableField<>(true);
    private ObservableField<Boolean> isCheck = new ObservableField<>(true);

    private ObservableField<Bitmap> qrBitmap = new ObservableField<>();
    private ObservableField<Drawable> buyDrawable = new ObservableField<>(getApplication().getResources().getDrawable(R.drawable.drawable_button_cancel));
    private ObservableField<String> rechargeCoin = new ObservableField<>("USDT");
    private ObservableField<String> address = new ObservableField<>("加载中...");
    private ObservableField<String> minRecharge = new ObservableField<>("");
    private ObservableField<String> tips = new ObservableField<>("• 请勿向上述地址充值任何非" + rechargeCoin.get() + "资产，否则资产将不可找回。\n" +
            "• 您充值至上述地址后，需要整个网络节点的确认，2次网络确认后到账，6次网络确认后可提币。\n" +
            "• 最小充值金额： " + minRecharge.get() + " " + rechargeCoin.get() + "，小于最小金额的充值将不会上账。");
    /**
     * pop 默认选中的Position
     */
    public ObservableField<Integer> pPosition = new ObservableField<>(0);
    public ObservableField<Integer> popStaus = new ObservableField<>(0);
    public ObservableField<OTCcfgBean> otc = new ObservableField<>();
    private ObservableField<String> unit = new ObservableField<>("");
    private ObservableField<Boolean> isOk = new ObservableField<>(false);

    public ObservableField<Boolean> getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(ObservableField<Boolean> isCheck) {
        this.isCheck = isCheck;
    }
   private  String ext,card;
   private  float money;



    public ObservableField<Boolean> getIsOk() {
        return isOk;
    }

    public void setIsOk(ObservableField<Boolean> isOk) {
        this.isOk = isOk;
    }
    public ObservableField<String> getCount() {
        return count;
    }

    public void setCount(ObservableField<String> count) {
        this.count = count;
    }

    private ObservableField<String> count = new ObservableField<>("正在通知卖家");

    public ObservableField<String> getUnit() {
        return unit;
    }

    public void setUnit(ObservableField<String> unit) {
        this.unit = unit;
    }


    public ObservableField<String> getOtcminAmount() {
        return OtcminAmount;
    }

    public void setOtcminAmount(ObservableField<String> otcminAmount) {
        OtcminAmount = otcminAmount;
    }

    public ObservableField<String> getOtcMaxAmount() {
        return OtcMaxAmount;
    }

    public void setOtcMaxAmount(ObservableField<String> otcMaxAmount) {
        OtcMaxAmount = otcMaxAmount;
    }

    public ObservableField<Drawable> getBuyDrawable() {
        return buyDrawable;
    }

    public void setBuyDrawable(ObservableField<Drawable> buyDrawable) {
        this.buyDrawable = buyDrawable;
    }

    public ObservableField<Bitmap> getQrBitmap() {
        return qrBitmap;
    }

    public void setQrBitmap(ObservableField<Bitmap> qrBitmap) {
        this.qrBitmap = qrBitmap;
    }

    public ObservableField<String> getRechargeCoin() {
        return rechargeCoin;
    }

    public void setRechargeCoin(ObservableField<String> rechargeCoin) {
        this.rechargeCoin = rechargeCoin;
    }

    public ObservableField<String> getAddress() {
        return address;
    }

    public void setAddress(ObservableField<String> address) {
        this.address = address;
    }

    public ObservableField<String> getMinRecharge() {
        return minRecharge;
    }

    public void setMinRecharge(ObservableField<String> minRecharge) {
        this.minRecharge = minRecharge;
    }

    public ObservableField<String> getTips() {
        return tips;
    }

    public void setTips(ObservableField<String> tips) {
        this.tips = tips;
    }

    /**
     * pop按钮状态
     */
    public ObservableField<Boolean> btState = new ObservableField<>(false);

    @SingleClick
    public void copyAddress() {
        ClipboardManager cm = (ClipboardManager) BaseApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(address.get());
        ToastUtil.show(getApplication(), "复制成功");
    }

    @SingleClick
    public void saveImage() {

    }

    @SingleClick
    public void switchCoin() {

    }

    @SingleClick
    public void changeBalance(float num) {
        balance.set(Utils.format0(num));
        if (price != null) {
            amount.set(Utils.format8(balance.get()));
        } else {
            ToastUtil.show(getApplication(), "请选择购买方式");
        }
    }

    @SingleClick
    @Override
    public void rightClick() {
        if (isCheck.get()==true){
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.REQUEST_KEY_INOROUT, 0);
            bundle.putString(Constants.KEY_COIN_NAME, rechargeCoin.get());
            startActivity(CoinRecordActivity.class, bundle);
        }else {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.KEY_COIN_NAME, "USDT");
            bundle.putInt(Constants.REQUEST_KEY_TYPE, 0);
            startActivity(RecordActivity.class, bundle);
        }
    }


    public ObservableField<String> getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(ObservableField<String> exchangeType) {
        this.exchangeType = exchangeType;
    }

    public ObservableField<String> getAmount() {
        return amount;
    }

    public void setAmount(ObservableField<String> amount) {
        this.amount = amount;
    }

    public ObservableField<String> getPrice() {
        return price;
    }

    public void setPrice(ObservableField<String> price) {
        this.price = price;
    }

    public ObservableField<String> getBalance() {
        return balance;
    }

    public void setBalance(ObservableField<String> balance) {
        this.balance = balance;
    }

    public ObservableField<String> getPayForType() {
        return payForType;
    }

    public void setPayForType(ObservableField<String> payForType) {
        this.payForType = payForType;
    }


    public ObservableField<Boolean> getShowOtc() {
        return showOtc;
    }

    public void setShowOtc(ObservableField<Boolean> showOtc) {
        this.showOtc = showOtc;
    }

    public WalletModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void recharge() {
        isCheck.set(true);
    }

    @SingleClick
    public void buy() {
       isCheck.set(false);
    }

    @SingleClick
    public void changeType() {

    }

    @SingleClick
    public void changePayForType() {
        btState.set(!btState.get());
        pay_uc.pay_pop.setValue(btState.get());
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString())) {
                amount.set(Utils.format8(s.toString()));
//              amount.set(Utils.format8(Utils.multiply(s.toString(),price.get())));
                buyDrawable.set(getApplication().getResources().getDrawable(R.drawable.drawable_button_ensure));
                setBuyDrawable(buyDrawable);

            } else {
                amount.set("0.0000000");
            }
        }
    };
    @SingleClick
    public void ensureOrder() {
        if (!TextUtils.isEmpty(amount.get()) && !TextUtils.isEmpty(exchangeType.get()) && !TextUtils.isEmpty(balance.get()) && payForTypeID.get() > 0) {
            getRecharge();
        } else {
            ToastUtil.show(getApplication(), "请填写完整的买入信息");
        }
    }

    CountDownTimer timer = new CountDownTimer(7000, 1000) {
        public void onTick(long millisUntilFinished) {
            count.set("卖家已接单 " + ((millisUntilFinished / 1000) - 1) + " s");
            if (millisUntilFinished / 1000 == 2){
                isOk.set(true);
            } else {
                isOk.set(false);
            }
            if (millisUntilFinished / 1000 == 1) {
                PayDialog.dismiss();
                PayDialog = null;
                Bundle bundle = new Bundle();
                bundle.putString("payForType", payForType.get());
                bundle.putInt("payType", pPosition.get());
                bundle.putInt("id", id.get());
                bundle.putString("price", price.get());
                bundle.putString("num", amount.get());
                bundle.putString("amount", Utils.format8(money));
                bundle.putString("name", name.get());
                bundle.putString("ext", ext);
                bundle.putString("card", card);
                bundle.putString("unit", unit.get());
                startActivity(BuyActivity.class, bundle);
            }else {
            }
        }
        public void onFinish() {
        }
    };

    @SingleClick
    public void changeType(float num) {
        balance.set(String.valueOf(num));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (popStaus.get() == 0) {
            pPosition.set(i);
            exchangeType.set(String.valueOf(otc.get().getOtcCfgs().get(i).getCurrency()));
            price.set(String.valueOf(otc.get().getOtcCfgs().get(i).getBuyRatio()));
            uc.pop.setValue(false);
            unit.set(otc.get().getOtcCfgs().get(i).getSymbol());
        } else {
            balance.set("");
            amount.set("");
            OtcminAmount.set(Utils.format0(otc.get().getPayTypes().get(i).getMinIn()));
            OtcMaxAmount.set(Utils.format0(otc.get().getPayTypes().get(i).getMaxIn()));
            pPosition.set(i);
            payForTypeID.set(otc.get().getPayTypes().get(i).getId());
            payForType.set(otc.get().getPayTypes().get(i).getName());
            pay_uc.pay_pop.setValue(false);
        }
    }

    @Override
    public void onDismiss() {
        btState.set(false);
    }

    public static class UIChangeObservable {
        //密码开关观察者
        public SingleLiveEvent<Boolean> pop = new SingleLiveEvent<>();
        SingleLiveEvent<Boolean> pay_pop = new SingleLiveEvent<>();
    }

    @SingleClick
    public void popDown() {
        btState.set(!btState.get());
        uc.pop.setValue(btState.get());
    }

    @SingleClick
    public void PayPopDown() {
        btState.set(!btState.get());
        pay_uc.pay_pop.setValue(btState.get());
    }

    @Override
    public void onResume() {
        super.onResume();
        NewAssetsBean coinInfo = AssetsConfigs.getInstance().getCoinInfo(rechargeCoin.get());
        if (null != coinInfo) {
            qrBitmap.set(createQRcodeImage(coinInfo.getAddr()));
            address.set(coinInfo.getAddr());
        } else {
        }
    }

    public Bitmap createQRcodeImage(String url) {
        int w = 500, h = 500;
        try {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1) {
                return null;
            }
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * w + x] = 0xff000000;
                    } else {
                        pixels[y * w + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            //显示到我们的ImageView上面
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        getOtcData();
    }

    @SuppressLint("CheckResult")
    private void getOtcData() {
        DataService.getInstance().getOtcCfg().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if (b != null) {
                        exchangeType.set(b.getOtcCfgs().get(0).getCurrency());
                        price.set(String.valueOf(b.getOtcCfgs().get(0).getBuyRatio()));
                        OtcminAmount.set(Utils.format0(b.getPayTypes().get(0).getMinIn()));
                        OtcMaxAmount.set(Utils.format0(b.getPayTypes().get(0).getMaxIn()));
                        unit.set(b.getOtcCfgs().get(0).getSymbol());
                        payForTypeID.set(b.getPayTypes().get(0).getId());
                        payForType.set(b.getPayTypes().get(0).getName());
                        otc.set(b);
                    } else {
                        ToastUtil.show(getApplication(), "获取OTC配置失败");
                    }
                }, t -> {
                    ToastUtil.show(getApplication(), t.getMessage());
                });
    }

    private void getRecharge() {
        DataService.getInstance().getRecharge(amount.get(), "", exchangeType.get(), Integer.valueOf(balance.get()), payForTypeID.get()).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if (b != null) {
                        name.set(b.getOtc().getLastName()+b.getOtc().getFirstName());
                        id.set(b.getRecord().getId());
                        ext = b.getOtc().getExt();
                        card = b.getOtc().getCard();
                        money = b.getRecord().getMoney();
                        timer.start();
                        DialogUtil.showWallPayDialog(AppManager.getActivityStack().peek(), this);
                    } else {
                        ToastUtil.show(getApplication(), "创建订单失败");
                    }
                }, t -> {
                    ToastUtil.show(getApplication(), t.getMessage());
                });
    }

    private Dialog PayDialog;

    public void setPayDialog(Dialog payDialog) {
        this.PayDialog = payDialog;
    }
}

