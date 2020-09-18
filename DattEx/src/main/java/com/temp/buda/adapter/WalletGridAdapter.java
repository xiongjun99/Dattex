package com.temp.buda.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;

import java.util.List;

public class WalletGridAdapter  extends BaseQuickAdapter<Integer, BaseViewHolder>{


    public WalletGridAdapter(List<Integer> data) {
        super(R.layout.item_wallet_price,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer data) {
        TextView textView = helper.getView(R.id.text);
        textView.setText(""+data);
    }
}
