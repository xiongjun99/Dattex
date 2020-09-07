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
        tvApplyStatus.setText(data.getApplyTypeName());

        TextView tvApplyType = helper.getView(R.id.tv_apply_type);
        tvApplyType.setText(data.getTypeName());

        TextView tvAmountType = helper.getView(R.id.tv_amount_type);
        tvAmountType.setText(data.getCoinId());

        TextView tvUnitPrice = helper.getView(R.id.tv_unit_price);
        tvUnitPrice.setText(""+data.getUnitPrice());

        TextView UnitPrice = helper.getView(R.id.unit_price);
        UnitPrice.setText("单价 " + data.getSymbol().substring(data.getSymbol().lastIndexOf("/")+1));

        TextView tvApplyMax = helper.getView(R.id.tv_apply_max);
        tvApplyMax.setText("" + data.getQty());

        TextView applyMax = helper.getView(R.id.apply_max);
        applyMax.setText("最大申购 " + data.getSymbol().substring(0,  data.getSymbol().indexOf("/")));

        TextView tvTransactionPrice = helper.getView(R.id.tv_transaction_price);
        tvTransactionPrice.setText(""+ data.getTradingPrice());

        TextView TransactionPrice = helper.getView(R.id.transaction_price);
        TransactionPrice.setText("成交总额 "+ data.getSymbol().substring(0,  data.getSymbol().indexOf("/")));

        TextView tvTime = helper.getView(R.id.tv_time);
        tvTime.setText(data.getStartTime() + "  -  " + data.getEndTime());

        if (!"可申购".equals(data.getApplyTypeName())==true&&!"未中签".equals(data.getApplyTypeName())==true) {
            applyMax.setText("申购数量 "+ data.getSymbol().substring(0,  data.getSymbol().indexOf("/")));
            tvApplyMax.setText(""+data.getSubscribeQty());
            TransactionPrice.setText("申购总额 "+ data.getSymbol().substring(data.getSymbol().lastIndexOf("/")+1));
            tvTransactionPrice.setText(""+data.getSubscribePrice());
        }
    }
}
