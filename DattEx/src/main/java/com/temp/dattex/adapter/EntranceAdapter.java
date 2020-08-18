package com.temp.dattex.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.HomeViewPagerBean;
import java.util.List;

public class EntranceAdapter extends BaseQuickAdapter<HomeViewPagerBean, BaseViewHolder> {
    public EntranceAdapter(@Nullable List<HomeViewPagerBean> data) {
        super(R.layout.item_home_entrance, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeViewPagerBean item) {
        TextView tvCurrencyType = helper.getView(R.id.tv_currencytype);
        tvCurrencyType.setText(item.getCurrencyType());
        TextView tvCurrentindex = helper.getView(R.id.tv_currentindex);
        tvCurrentindex.setText(item.getCurrentIndex());
        TextView tvPercentage = helper.getView(R.id.tv_percentage);
        tvPercentage.setText(item.getPercentage());
        TextView tvCNY = helper.getView(R.id.tv_cny);
        tvCNY.setText(item.getCNY());
        if (item.getPercentage().contains("-")){
            System.out.println("-----aaaaaaa"+item.getCurrencyType());
            tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
            tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
        }else {
            System.out.println("-----bbbbbbb"+item.getCurrencyType());
            tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
            tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
        }
    }
}