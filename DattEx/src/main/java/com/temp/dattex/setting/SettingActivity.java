package com.temp.dattex.setting;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.framework.basic.BaseActivity;
import com.independ.framework.client.RetrofitClient;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivitySafeBinding;
import com.temp.dattex.util.Utils;
import com.temp.dattex.widget.SettingItemView;

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
    private SettingItemView tvRevise;
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
        tvRevise = findViewById(R.id.tv_revise);
        tvRevise.setOnClickListener(view -> {
            showDialog();
        });
    }
    public  void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_ipadress,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        EditText etIpadress = view.findViewById(R.id.et_ipadress);
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
                RetrofitClient.getInstance().initRetrofit("http://"+etIpadress.getText().toString());
                //... To-do
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
