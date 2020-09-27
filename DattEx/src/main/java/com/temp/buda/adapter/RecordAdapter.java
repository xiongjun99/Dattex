package com.temp.buda.adapter;

import android.app.Activity;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.RecordBean;

import java.util.List;

public class RecordAdapter extends BaseQuickAdapter<RecordBean.RowsBean, BaseViewHolder> {
    Activity mActivity;
    public RecordAdapter( Activity activity,List<RecordBean.RowsBean> data) {
        super(R.layout.item_record, data);
        mActivity = activity;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecordBean.RowsBean data) {
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvAmount = helper.getView(R.id.tv_amount);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvStatus = helper.getView(R.id.tv_status);
        if (data.getInorout()==0){
            tvName.setText("USDT购买");
            tvAmount.setText("+"+data.getAmount());
            tvAmount.setTextColor(mActivity.getResources().getColor(R.color.color_FF20BC68));
        }else {
            tvName.setText("USDT出售");
            tvAmount.setText("-"+data.getAmount());
            tvAmount.setTextColor(mActivity.getResources().getColor(R.color.color_FFDC4D4D));
        }
        tvTime.setText(data.getCreated());
        if (data.getState()==0){
            if (data.getInorout()==1){
            tvStatus.setText("买家确认中");
            }else {
            tvStatus.setText("待转账");
            }
        }else if (data.getState()==1){
            tvStatus.setText("已取消");
        }else if (data.getState()==2){
            tvStatus.setText("失败");
        }else if (data.getState()==3){
            if (data.getInorout()==0){
                tvStatus.setText("卖家确认中");
            }
        }else if (data.getState()==4){
            tvStatus.setText("已入账");
        }
    }
}
