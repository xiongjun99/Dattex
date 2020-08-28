package com.temp.dattex.adapter;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.NewApplyBean;
import java.util.List;

public class ApplyAdapter extends BaseQuickAdapter<NewApplyBean.RowsBean, BaseViewHolder> {

    public ApplyAdapter(List<NewApplyBean.RowsBean> data) {
        super(R.layout.item_apply, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewApplyBean.RowsBean data) {
        TextView tvApplyStatus = helper.getView(R.id.tv_apply_status);
        tvApplyStatus.setText(data.getTypeName());

        TextView tvApplyType = helper.getView(R.id.tv_apply_type);
        tvApplyType.setText(data.getApplyTypeName());

        TextView tvAmountType = helper.getView(R.id.tv_amount_type);
        tvAmountType.setText(data.getCoinId());

        TextView tvUnitPrice = helper.getView(R.id.tv_unit_price);
        tvUnitPrice.setText(""+data.getUnitPrice());

        TextView tvApplyMax = helper.getView(R.id.tv_apply_max);
        tvApplyMax.setText(""+data.getQty());

        TextView tvTransactionPrice = helper.getView(R.id.tv_transaction_price);
        tvTransactionPrice.setText(""+data.getQty() * data.getUnitPrice());

        TextView tvTime = helper.getView(R.id.tv_time);
        tvTime.setText(data.getStartTime() + "  -  " + data.getEndTime());

    }
}
