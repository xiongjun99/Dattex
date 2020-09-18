package com.temp.dattex.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.NoticeAdapter;
import com.temp.dattex.bean.NoticeBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.web.WebViewActivity;
import com.temp.dattex.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends BaseActivity {
    private TitleBar titleBar;
    private RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;
    private List<NoticeBean.RowsBean> list = new ArrayList();
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_notice);
        initView();
        getData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        titleBar.setTitleText("帮助");
        titleBar.setLeftTwoClick(this);
        recyclerView = findViewById(R.id.recycler_view);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_country_list_item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        noticeAdapter = new NoticeAdapter(list);
        recyclerView.setAdapter(noticeAdapter);
        noticeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent it = new Intent(HelpActivity.this, WebViewActivity.class);
                it.putExtra(WebViewActivity.KEY_PARAM_TITLE, "帮助");
                it.putExtra(WebViewActivity.KEY_PARAM_URL, "http://45.132.238.178/#/article?id="+noticeAdapter.getData().get(position).getId());
                startActivity(it);
            }
        });
    }
    private void getData() {
        DataService.getInstance().getNotice(1,3,1,0).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if (b.getRows()==null||b.getRows().size()<=0){
                        Toast.makeText(this,"暂无帮助信息",Toast.LENGTH_LONG).show();
                    } else {
                        noticeAdapter.setNewData(b.getRows());
                    }
                }, t -> {
                    ToastUtil.show(this,"获取帮助信息失败"+t.getMessage());
                });
    }
}
