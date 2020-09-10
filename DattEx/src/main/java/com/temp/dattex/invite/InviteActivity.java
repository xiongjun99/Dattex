package com.temp.dattex.invite;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.exchange.utilslib.ToastUtil;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.util.Utils;

@RequiresApi(api = Build.VERSION_CODES.M)
public class InviteActivity extends BaseActivity {
    TextView tvInviteCode, tvCodeCopy, tvLinkCopy, tvInviteLink;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_invite);
        initView();
    }

    private void initView() {
        tvInviteCode = (TextView) findViewById(R.id.tv_invite_code);
        tvInviteLink = (TextView) findViewById(R.id.tv_invite_link);
        tvLinkCopy = (TextView) findViewById(R.id.tv_link_copy);
        tvCodeCopy = (TextView) findViewById(R.id.tv_code_copy);
        if (!TextUtils.isEmpty(LoginInfo.getRecode())) {
            tvInviteCode.setText(LoginInfo.getRecode());
            tvInviteLink.setText("http://reg.buda.tc/#/invitation?invite=" + LoginInfo.getRecode());
        }
        tvCodeCopy.setOnClickListener(view -> {
            Utils.setClipboard(this, tvInviteCode.getText().toString());
            ToastUtil.show(this, "复制成功");
        });
        tvLinkCopy.setOnClickListener(view -> {
            Utils.setClipboard(this, tvInviteLink.getText().toString());
            ToastUtil.show(this, "复制成功");
        });
    }
}
