package com.temp.buda.withdraw;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.base.BaseActivity;
import com.temp.buda.R;
import com.temp.buda.adapter.WithdrawListAdapter;
import com.temp.buda.bean.NewPayTypeBean;
import com.temp.buda.bean.OTCcfgBean;
import com.temp.buda.net.DataService;
import com.temp.buda.widget.TitleBar;

import java.util.List;

public class NewPayTypeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private WithdrawListAdapter withdrawListAdapter;
    private TextView tvBottom;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpaytype);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        recyclerView= (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.clearFocus();
        recyclerView.setFocusable(false);
        withdrawListAdapter = new WithdrawListAdapter(this,null);
        recyclerView.setAdapter(withdrawListAdapter);
        tvBottom = (TextView)findViewById(R.id.tv_bottom);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        tvBottom.setOnClickListener(view -> {
            Intent it = new Intent(NewPayTypeActivity.this,AddPayTypeActivity.class);
            startActivity(it);
        });
        withdrawListAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent it = new Intent(this,WithdrawActivity.class);
            it.putExtra("name",withdrawListAdapter.getData().get(position).getBankName());
            it.putExtra("account",withdrawListAdapter.getData().get(position).getReceivingAccount());
            it.putExtra("reciveItemId",withdrawListAdapter.getData().get(position).getId());
            it.putExtra("payType",withdrawListAdapter.getData().get(position).getType());
            setResult(RESULT_OK, it);
            finish();
        });
    }
    private void initData() {
        DataService.getInstance().getMemberReciveItem("0").compose(ResponseTransformer.<List<NewPayTypeBean>>handleResult()).subscribe(
                bean -> {
                    withdrawListAdapter.setNewData(bean);
                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
        );
    }
}
