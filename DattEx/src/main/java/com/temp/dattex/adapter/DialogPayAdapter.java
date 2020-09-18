package com.temp.dattex.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import java.util.List;

public class DialogPayAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DialogPayAdapter(List<String> data) {
        super(R.layout.item_dialog_pay, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String data) {
        TextView tvPayName = helper.getView(R.id.tv_pay_name);
        tvPayName.setText(data);

    }
}
