package com.temp.dattex.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.InviteBean;
import com.temp.dattex.bean.NewApplyBean;
import com.temp.dattex.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InviteListAdapter extends BaseQuickAdapter<InviteBean, BaseViewHolder> {

    public InviteListAdapter(List<InviteBean> data) {
        super(R.layout.item_invite_list, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, InviteBean data) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(""+data.getAccount());
        TextView tvTime = helper.getView(R.id.tv_time);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setLenient(false);
        try {
            Date newDate= formatter.parse(data.getInviteTime());
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            tvTime.setText(""+formatter.format(newDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView tvNumber = helper.getView(R.id.tv_number);
        tvNumber.setText(""+data.getTradeCount());

        TextView tvAmount = helper.getView(R.id.tv_amount);
        tvAmount.setText(""+ data.getReturnAmount() + " USDT");
    }
}
