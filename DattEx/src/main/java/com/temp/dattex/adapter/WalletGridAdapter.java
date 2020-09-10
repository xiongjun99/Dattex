package com.temp.dattex.adapter;

import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;

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
