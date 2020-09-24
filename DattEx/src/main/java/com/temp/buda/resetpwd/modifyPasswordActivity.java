package com.temp.buda.resetpwd;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.base.BaseActivity;
import com.temp.buda.R;
import com.temp.buda.database.LoginInfo;
import com.temp.buda.net.DataService;
import com.temp.buda.widget.TitleBar;

public class modifyPasswordActivity extends BaseActivity {
   private TitleBar titleBar;
   private EditText etOldPassword,etNewPassword,etAgainPassword;
   private TextView tvConfirm;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_modify_password);
        initView();
    }

    private void initView() {
        titleBar = (TitleBar)findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);

        etOldPassword = (EditText)findViewById(R.id.et_old_password);
        etNewPassword = (EditText)findViewById(R.id.et_new_password);
        etAgainPassword = (EditText)findViewById(R.id.et_again_password);

        tvConfirm = (TextView)findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(view -> {
            resetDone();
        });
    }
    public void resetDone() {
        if (!etOldPassword.getText().toString().equals(LoginInfo.getPassWord())){
            ToastUtil.show(getApplication(),"请输入正确的密码");
            return;
        }
        if (!etNewPassword.getText().toString().equals(etAgainPassword.getText().toString())){
            ToastUtil.show(getApplication(),"请输入相同的新密码");
            return;
        }
        DataService.getInstance().updatePwd(etOldPassword.getText().toString(),etNewPassword.getText().toString()).
                compose(ResponseTransformer.handleResult())
                .subscribe(s -> {
                    finish();
                    ToastUtil.show(getApplication(), getApplication().getResources().getString(R.string.text_reset_password_success));
                }, throwable -> ToastUtil.show(getApplication(), throwable.getMessage()));
    }
}
