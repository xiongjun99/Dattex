package com.temp.dattex.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.adapter.WithdrawBtcAdapter;
import com.temp.dattex.bean.NewPayTypeBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class WithdrawListActivity extends Activity {
    private RecyclerView recyclerView;
    private WithdrawBtcAdapter withdrawListAdapter;
    private List<NewPayTypeBean> list = new ArrayList<>();
    private TextView tvBottom;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawlist);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        DataService.getInstance().getMemberReciveItem("1").compose(ResponseTransformer.<List<NewPayTypeBean>>handleResult()).subscribe(
                bean -> {
                    withdrawListAdapter.setNewData(bean);
                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
        );
    }

    private void initView() {
        recyclerView= (RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.clearFocus();
        recyclerView.setFocusable(false);
        withdrawListAdapter = new WithdrawBtcAdapter(WithdrawListActivity.this,list);
        recyclerView.setAdapter(withdrawListAdapter);
        tvBottom = (TextView)findViewById(R.id.tv_bottom);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        tvBottom.setOnClickListener(view -> {
            Intent it = new Intent(this,NewWithdrawActivity.class);
            startActivity(it);
        });
        withdrawListAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent it = new Intent(this,WithdrawActivity.class);
            it.putExtra("name",withdrawListAdapter.getData().get(position).getAddr());
            it.putExtra("reciveItemId",withdrawListAdapter.getData().get(position).getId());
            setResult(RESULT_OK, it);
            finish();
        });
    }
}
