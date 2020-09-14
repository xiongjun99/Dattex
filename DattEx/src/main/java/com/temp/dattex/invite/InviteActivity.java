package com.temp.dattex.invite;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.ApplyAdapter;
import com.temp.dattex.bean.NewApplyBean;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.M)
public class InviteActivity extends BaseActivity {
    private TextView tvInviteCode, tvCodeCopy, tvLinkCopy, tvInviteLink;
    private RecyclerView recyclerView;
    private ApplyAdapter applyAdapter;
    private List<NewApplyBean.RowsBean> list = new ArrayList();
    int page = 0;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_invite);
        initView();
        getData();
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
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        applyAdapter = new ApplyAdapter(list);
        recyclerView.setAdapter(applyAdapter);
    }
    private void getData() {
        DataService.getInstance().getFindByPageApplyCoin(page).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    list = b.getRows();
                    applyAdapter.setNewData(list);
                }, t -> {
                    ToastUtil.show(getApplicationContext(),"获取申购信息失败"+t.getMessage());
                });
    }
}
