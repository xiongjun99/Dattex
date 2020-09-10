package com.temp.dattex.setting;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.Application;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivitySafeBinding;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.UpdateDialogViewModel;
import com.temp.dattex.util.Utils;
import com.temp.dattex.widget.SettingItemView;
import com.temp.dattex.widget.TitleBar;

import org.w3c.dom.Attr;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.safe
 * @FileName     : SafeActivity.java
 * @Author       : chao
 * @Date         : 2020/6/20
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
public class SettingActivity extends BaseActivity<ActivitySafeBinding, SettingViewModel> {
    private SettingItemView tvRevise,tvUpload,tvClean,tvSwitch;
    private TextView ip,tvUploadText;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    public int initVariableId() {
        return BR.safeViewModel;
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initView() {
        super.initView();
        TitleBar titleBar =(TitleBar)findViewById(R.id.titlebar);
        titleBar.setLeftTwoClick(this);

        tvRevise =(SettingItemView) findViewById(R.id.tv_revise);
        tvUpload =(SettingItemView) findViewById(R.id.tv_upload);
        tvClean =(SettingItemView) findViewById(R.id.tv_clean);
        tvSwitch =(SettingItemView) findViewById(R.id.tv_switch);
        ip =(TextView) findViewById(R.id.ip);
        tvUploadText =(TextView) findViewById(R.id.tv_upload_text);
        tvRevise.setOnClickListener(view -> {
            showDialog();
        });
        tvUpload.setOnClickListener(view -> {
            Upload();
        });
        tvUploadText.setText("V"+Utils.getVersion(this));
        tvClean.setOnClickListener(view -> {
            ToastUtil.show(this,"暂未开放");
        });
        tvSwitch.setOnClickListener(view -> {
            ToastUtil.show(this,"暂未开放");
        });
        if (TextUtils.isEmpty(Application.URL)){
            Application.URL = ApiAddress.BASE_URL;
            ip.setText(Application.URL);
        }else {
            ip.setText(Application.URL);
        }
    }
    public  void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_ipadress,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        EditText etIpadress = view.findViewById(R.id.et_ipadress);
        etIpadress.setText(ApiAddress.BASE_URL);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvCancel.setText("取消");
        tvConfirm.setText("确定");
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetrofitClient.getInstance().initRetrofit(ApiAddress.BASE_URL);
                RetrofitClient.getInstance().initRetrofit(etIpadress.getText().toString());
                Application.URL = etIpadress.getText().toString();
                ip.setText(Application.URL);
                //... To-do
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    public void Upload(){
        DataService.getInstance().UpDate().compose(ResponseTransformer.handleResult()).subscribe(
                d -> {
                    if (!d.getAndroid().getVersion().equals(Utils.getVersion(this))){
                        UpdateDialogViewModel updateDialogViewModel = new UpdateDialogViewModel();
                        updateDialogViewModel.setNewVersionInfo(d.getAndroid().getMemo().replace("\\n", "\n"));
                        updateDialogViewModel.setNewVersionName("V"+d.getAndroid().getVersion());
                        updateDialogViewModel.setLinkUrl(d.getAndroid().getDownloadLink());
                        DialogUtil.showUpdateDialog(this,updateDialogViewModel);
                        String[] sourceStrArray = d.getAndroid().getForcedUpdateVer().split(",");
                        for (int i = 0; i < sourceStrArray.length; i++) {
                            if (sourceStrArray[i].equals(Utils.getVersion(this))){
                                System.out.println("------强制更新");
                                updateDialogViewModel.setOpen(true);
                            } else {
                                updateDialogViewModel.setOpen(false);
                                System.out.println("------可以关闭");
                            }
                        }
                    } else {
                        ToastUtil.show(this,"最新版本无需更新");
                        System.out.println("-------最新版本无需更新");
                    }
                }, t -> {
//                    ToastUtil.show(this,"获取版本更新失败...");
                }
        );
    }
}
