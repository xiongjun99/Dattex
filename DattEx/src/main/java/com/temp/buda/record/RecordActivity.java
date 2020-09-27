package com.temp.buda.record;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.adapter.RecordAdapter;
import com.temp.buda.base.BaseActivity;
import com.temp.buda.bean.RecordBean;
import com.temp.buda.buy.BuyDetailActivity;
import com.temp.buda.net.DataService;
import com.temp.buda.util.Utils;
import com.temp.buda.widget.TitleBar;
import java.util.List;

public class RecordActivity extends BaseActivity {
   private TitleBar titleBar;
   private int type = -1;
   private int page = 1;
   private String coinName;
    private RecyclerView recyclerView;
    private RecordAdapter adapter;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_record);
        initView();
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void initView() {
        titleBar = (TitleBar)findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        type = getIntent().getIntExtra(Constants.REQUEST_KEY_TYPE,-1);
        if (!TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY_COIN_NAME))) {
            coinName = getIntent().getStringExtra(Constants.KEY_COIN_NAME);
        } else {
            finish();
        }
        if (type!=-1){
            if (type==0){
                titleBar.setTitleText("购买记录");
            }else {
                titleBar.setTitleText("出售记录");
            }
        }
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecordAdapter(this,null);
        View emptyView = LayoutInflater
                .from(this)
                .inflate(R.layout.order_empty_layout, null);
        TextView tvEmptyName = (TextView)emptyView.findViewById(R.id.tv_empty_name);
        tvEmptyName.setText("暂无记录");
        adapter.setUseEmpty(true);
        adapter.setEmptyView(emptyView);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Intent it = new Intent(this, BuyDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("otcType",type + 1);
            bundle.putInt("id",adapter.getData().get(position).getId());
            it.putExtras(bundle);//将bundle传入intent中。
            startActivity(it);
        });
    }

    private void getData() {
        DataService.getInstance().assetsRecorde(type,coinName, page, "").compose(ResponseTransformer.<RecordBean>handleResult()).subscribe(
                bean -> {
                    List<RecordBean.RowsBean> rows = bean.getRows();
                    if (null != rows) {
                        adapter.setNewData(rows);
                    }
                }, t -> {
                    ToastUtil.show(this,t.getMessage());
                }
        );
    }
}
