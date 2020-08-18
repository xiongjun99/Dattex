package com.temp.dattex.withdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.temp.dattex.R;
import com.temp.dattex.adapter.WithdrawListAdapter;
import com.temp.dattex.bean.WithDrawListBean;
import com.temp.dattex.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class WithdrawListActivity extends Activity {
    private RecyclerView recyclerView;
    private WithdrawListAdapter withdrawListAdapter;
    private List<WithDrawListBean> list = new ArrayList<>();
    private TextView tvBottom;
    private TitleBar titleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawlist);
        initView();
        initData();
    }

    private void initData() {
        String [] strings1 = {"我的地址1","我的地址2"};
        String [] strings2 = {"test11111111111111","test2222222222222"};
        for (int i = 0; i < strings1.length; i++) {
            WithDrawListBean data = new WithDrawListBean();
            data.setString1(strings1[i]);
            data.setString2(strings2[i]);
            list.add(data);
        }
        withdrawListAdapter.setDiffNewData(list);
    }

    private void initView() {
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
            Intent it = new Intent(this,NewWithdrawActivity.class);
            startActivity(it);
            finish();
        });
        withdrawListAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent it = new Intent(this,WithdrawActivity.class);
            it.putExtra("str",withdrawListAdapter.getData().get(position).getString1());
            it.putExtra("str1",withdrawListAdapter.getData().get(position).getString2());
            setResult(RESULT_OK, it);
            finish();
        });
    }
}
