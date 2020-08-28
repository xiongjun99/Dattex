package com.temp.dattex.wallet;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.adapters.TextViewBindingAdapter;

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
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.bean.PayTypeBean;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.buy.BuyActivity;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.net.DataService;
import com.temp.dattex.record.CoinRecordActivity;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.Utils;
import com.temp.dattex.util.WalletPayDialogViewModel;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private ObservableField<String> amount = new ObservableField<>("0");
    private ObservableField<String> price = new ObservableField<>("");
    private ObservableField<String> balance = new ObservableField<>("");
    private ObservableField<String> name = new ObservableField<>("");
    private ObservableField<String> payForType = new ObservableField<>("");
    private ObservableField<Integer> payForTypeID = new ObservableField<>(0);
    private ObservableField<Integer> id = new ObservableField<>(0);

    private ObservableField<Boolean> showOtc = new ObservableField<>(true);
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

    public ObservableField<List<OTCcfgBean>> otc = new ObservableField<>();
    public ObservableField<List<PayTypeBean>> paytype = new ObservableField<>();
    public ObservableField<Drawable> getBuyDrawable() {
        return buyDrawable;
    }
    private int payType;

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
    }
    @SingleClick
    public void saveImage() {

    }

    @SingleClick
    public void switchCoin() {

    }
    @SingleClick
    public void changeBalance(float num) {
        balance.set(String.valueOf(num));
        if (price!=null){
            amount.set(String.valueOf(Utils.keepTwo(Float.valueOf(balance.get())/Float.valueOf(price.get()))));
        }else {
            ToastUtil.show(getApplication(),"请选择购买方式");
        }
    }

    @SingleClick
    @Override
    public void rightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_COIN_NAME, rechargeCoin.get());
        startActivity(CoinRecordActivity.class, bundle);
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
        showOtc.set(false);
    }

    @SingleClick
    public void buy() {
        showOtc.set(true);
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

        @Override public void afterTextChanged(Editable s){
            if (!TextUtils.isEmpty(s.toString())){
                amount.set(String.valueOf(Utils.keepTwo(Float.valueOf(s.toString())/Float.valueOf(price.get()))));
                buyDrawable.set(getApplication().getResources().getDrawable(R.drawable.drawable_button_ensure));
                setBuyDrawable(buyDrawable);
            }
     }
};
    @SingleClick
    public void ensureOrder() {
        if (!TextUtils.isEmpty(amount.get())&&!TextUtils.isEmpty(exchangeType.get())&&!TextUtils.isEmpty(balance.get())&& payForTypeID.get()>0){
            getRecharge();
        }else {
            ToastUtil.show(getApplication(),"请填写完整的买入信息");
        }
//        WalletPayDialogViewModel walletPayDialogViewModel = new WalletPayDialogViewModel();
//        DialogUtil.showWallPayDialog(AppManager.getActivityStack().peek(), walletPayDialogViewModel);
    }

    @SingleClick
    public void changeType(float num) {
        balance.set(String.valueOf(num));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(popStaus.get()==0){
            exchangeType.set(String.valueOf(otc.get().get(i).getCurrency()));
            price.set(String.valueOf(otc.get().get(i).getBuyRatio()));
            uc.pop.setValue(false);
        }else {
            payType = i;
            payForTypeID.set(paytype.get().get(i).getId());
            payForType.set(paytype.get().get(i).getName().getZh());
            pay_uc.pay_pop.setValue(false);
        }
    }

    @Override
    public void onDismiss() {
        btState.set(false);
    }

    public static class UIChangeObservable {
        //密码开关观察者
        SingleLiveEvent<Boolean> pop = new SingleLiveEvent<>();
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
                    if(b!=null && b.size() != 0){
                        exchangeType.set(b.get(0).getCurrency());
                        price.set(String.valueOf(b.get(0).getBuyRatio()));
                        otc.set(b);
                        mhandler.sendEmptyMessage(1);
                    }else {
                        ToastUtil.show(getApplication(),"获取OTC配置失败");
                        finish();
                    }
                }, t -> {
                });
    }

    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                getPayTypeData();
            }
        }
    };

    private void getPayTypeData() {
        DataService.getInstance().getPayType().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null && b.size() != 0){
                        payForTypeID.set(b.get(0).getId());
                        payForType.set(b.get(0).getName().getZh());
                        paytype.set(b);
                    }else {
                        ToastUtil.show(getApplication(),"获取支付方式配置失败");
                    }
                }, t -> {
                });
    }

    private void getRecharge() {
        DataService.getInstance().getRecharge(amount.get(),"",exchangeType.get(),balance.get(),payForTypeID.get()).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null){
                        name.set(b.getOtc().getName());
                        id.set(b.getRecord().getId());
                        Bundle bundle = new Bundle();
                        bundle.putString("payForType",payForType.get());
                        bundle.putInt("payType",payType);
                        bundle.putInt("id",id.get());
                        bundle.putString("price",price.get());
                        bundle.putString("num",amount.get());
                        bundle.putString("amount",balance.get());
                        bundle.putString("name",name.get());
                        bundle.putString("ext",b.getOtc().getExt());
                        bundle.putString("card",b.getOtc().getCard());
                        startActivity(BuyActivity.class,bundle);
                    }else {
                        ToastUtil.show(getApplication(),"创建订单失败");
                    }
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                });
    }
}
