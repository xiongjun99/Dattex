package com.temp.dattex.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.NewApplyBean;

import java.util.List;

public class InviteListAdapter extends BaseQuickAdapter<NewApplyBean.RowsBean, BaseViewHolder> {

    public InviteListAdapter(List<NewApplyBean.RowsBean> data) {
        super(R.layout.item_invite_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewApplyBean.RowsBean data) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(data.getApplyTypeName());

        TextView tvTime = helper.getView(R.id.tv_time);
        tvTime.setText(data.getApplyTypeName());

        TextView tvNumber = helper.getView(R.id.tv_number);
        tvNumber.setText(data.getApplyTypeName());

        TextView tvAmount = helper.getView(R.id.tv_amount);
        tvAmount.setText(data.getApplyTypeName());
    }
}
