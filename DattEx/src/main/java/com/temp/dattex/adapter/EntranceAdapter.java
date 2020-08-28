package com.temp.dattex.adapter;

import android.widget.TextView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.HomeViewPagerBean;
import com.temp.dattex.bean.MarketListBean;

import java.util.List;

public class EntranceAdapter extends BaseQuickAdapter<MarketListBean, BaseViewHolder> {
    public EntranceAdapter(@Nullable List<MarketListBean> data) {
        super(R.layout.item_home_entrance, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketListBean item) {
        TextView tvCurrencyType = helper.getView(R.id.tv_currencytype);
        tvCurrencyType.setText(item.getCoinId());
        TextView tvCurrentindex = helper.getView(R.id.tv_currentindex);
        tvCurrentindex.setText(item.getPrice());
        TextView tvPercentage = helper.getView(R.id.tv_percentage);
//        TextView tvCNY = helper.getView(R.id.tv_cny);
//        tvCNY.setText(item.getCNY());

        if (Float.valueOf(item.getChanges()) < 0.0){
            tvPercentage.setText(item.getChanges()+"%");
            tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
            tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
        }else if (Float.valueOf(item.getChanges()) > 0.0){
            tvPercentage.setText("+"+item.getChanges()+"%");
            tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
            tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
        }
    }
}