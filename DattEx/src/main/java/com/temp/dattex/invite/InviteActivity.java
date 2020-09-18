package com.temp.dattex.invite;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.InviteListAdapter;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;

@RequiresApi(api = Build.VERSION_CODES.M)
public class InviteActivity extends BaseActivity {
    private TextView tvInviteCode, tvCodeCopy, tvLinkCopy, tvInviteLink;
    private RecyclerView recyclerView;
    private InviteListAdapter adapter;
    private ImageView ivCancel;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_invite);
        initView();
        getData();
    }

    private void initView() {
        ivCancel = (ImageView) findViewById(R.id.iv_cancel);
        tvInviteCode = (TextView) findViewById(R.id.tv_invite_code);
        tvInviteLink = (TextView) findViewById(R.id.tv_invite_link);
        tvLinkCopy = (TextView) findViewById(R.id.tv_link_copy);
        tvCodeCopy = (TextView) findViewById(R.id.tv_code_copy);
        ivCancel.setOnClickListener(view -> {
           finish();
        });
        if (!TextUtils.isEmpty(LoginInfo.getRecode())) {
            tvInviteCode.setText(LoginInfo.getRecode());
            tvInviteLink.setText("http://reg.dattex.cc/#/invitation?invite=" + LoginInfo.getRecode());
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_country_list_item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InviteListAdapter(null);
        recyclerView.setAdapter(adapter);
    }
    private void getData() {
        DataService.getInstance().getInviteRecord().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    adapter.setNewData(b);
                }, t -> {
                    ToastUtil.show(getApplicationContext(),""+t.getMessage());
                });
    }
}
