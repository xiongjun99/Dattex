package com.temp.dattex.recharge;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.temp.dattex.Constants;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.config.AssetsConfigs;
import com.temp.dattex.record.CoinRecordActivity;

import java.util.Hashtable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.recharge
 * @FileName     : RechargeViewModel.java
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
public class RechargeViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener {
    public RechargeViewModel(@NonNull Application application) {
        super(application);
    }

     private ObservableField<Bitmap> qrBitmap = new ObservableField<>();
    private ObservableField<String> rechargeCoin = new ObservableField<>("USDT");
    private ObservableField<String> address = new ObservableField<>("");
    private ObservableField<String> minRecharge = new ObservableField<>("");
    private ObservableField<String> tips = new ObservableField<>("• 请勿向上述地址充值任何非" + rechargeCoin.get() + "资产，否则资产将不可找回。\n" +
            "• 您充值至上述地址后，需要整个网络节点的确认，2次网络确认后到账，6次网络确认后可提币。\n" +
            "• 最小充值金额： " + minRecharge.get() + " " + rechargeCoin.get() + "，小于最小金额的充值将不会上账。");


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
    @Override
    public void rightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_COIN_NAME, rechargeCoin.get());
        startActivity(CoinRecordActivity.class, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        AssetsBean.AssetsItemBean coinInfo = AssetsConfigs.getInstance().getCoinInfo(rechargeCoin.get());
        if (null != coinInfo) {
            qrBitmap.set(createQRcodeImage(coinInfo.getAddr()));
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
}
