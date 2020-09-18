package com.temp.dattex.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.NoticeBean;

import java.util.List;

public class NoticeAdapter extends BaseQuickAdapter<NoticeBean.RowsBean, BaseViewHolder> {

    public NoticeAdapter(List<NoticeBean.RowsBean> data) {
        super(R.layout.item_notice, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NoticeBean.RowsBean data) {
        TextView tvTitle = helper.getView(R.id.tv_title);
        tvTitle.setText(data.getTitle());
        TextView tvTime = helper.getView(R.id.tv_time);
        tvTime.setText(data.getPublishTime());
    }
}