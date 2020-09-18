package com.temp.dattex.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.MarketListBean;

import java.util.List;

public class TradeSymbolAdapter extends BaseQuickAdapter<MarketListBean, BaseViewHolder> {
    public TradeSymbolAdapter(@Nullable List<MarketListBean> data) {
        super(R.layout.item_tradesymbol, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MarketListBean item) {
        TextView tvCoinid = helper.getView(R.id.tv_coinid);
        tvCoinid.setText(item.getCoinId());
        TextView tvPrice = helper.getView(R.id.tv_price);
        tvPrice.setText(item.getPrice());

    }
}