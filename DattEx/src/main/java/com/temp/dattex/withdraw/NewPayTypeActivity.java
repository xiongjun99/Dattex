package com.temp.dattex.withdraw;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.WithdrawListAdapter;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.bean.WithDrawListBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class NewPayTypeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private WithdrawListAdapter withdrawListAdapter;
    private List<WithDrawListBean> list = new ArrayList<>();
    private TextView tvBottom;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpaytype);
        initView();
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
        withdrawListAdapter = new WithdrawListAdapter(list);
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
            it.putExtra("str",withdrawListAdapter.getData().get(position).getString1());
            it.putExtra("str1",withdrawListAdapter.getData().get(position).getString2());
            setResult(RESULT_OK, it);
            finish();
        });
    }
    private void initData() {
        DataService.getInstance().getMemberReciveItem("").compose(ResponseTransformer.<Object>handleResult()).subscribe(
                bean -> {
                }, t -> ToastUtil.show(BaseApplication.getInstance(), t.getMessage())
        );
        String [] strings1 = {"建设银行","支付宝"};
        String [] strings2 = {"*********4342","*********3289"};
        for (int i = 0; i < strings1.length; i++) {
            WithDrawListBean data = new WithDrawListBean();
            data.setString1(strings1[i]);
            data.setString2(strings2[i]);
            list.add(data);
        }
        withdrawListAdapter.setDiffNewData(list);
    }
}
