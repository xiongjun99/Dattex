package com.temp.dattex.adapter;

import android.content.Context;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.R;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.util.Utils;

import java.util.List;

public class EntranceAdapter extends BaseQuickAdapter<MarketListBean, BaseViewHolder> {
    Context mContext;
    public EntranceAdapter(Context context, @Nullable List<MarketListBean> data) {
        super(R.layout.item_home_entrance, data);
        mContext = context;
    }

    @Override
    protected int getDefItemCount() {
        return super.getDefItemCount();
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketListBean item) {
            TextView tvCurrencyType = helper.getView(R.id.tv_currencytype);
            tvCurrencyType.setText(item.getCoinId());
            TextView tvCurrentindex = helper.getView(R.id.tv_currentindex);
            tvCurrentindex.setText(item.getPrice());
            TextView tvPercentage = helper.getView(R.id.tv_percentage);
            TextView tvCNY = helper.getView(R.id.tv_cny);
             tvCNY.setText(mContext.getResources().getString(R.string.About_Symbol)+mContext.getResources().getString(R.string.xxx) + Utils.FloatkeepTwo(SymbolConfigs.getInstance().getCnyRate() * Float.valueOf(item.getPrice()))+mContext.getResources().getString(R.string.xxx)+mContext.getString(R.string.CNY));

        if (Float.valueOf(item.getChanges()) < 0.0){
                tvPercentage.setText(Utils.FloatkeepTwo(Float.valueOf(item.getChanges()))+"%");
                tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
                tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FFDC4D4D));
            }else if (Float.valueOf(item.getChanges()) > 0.0){
                tvPercentage.setText("+"+Utils.FloatkeepTwo(Float.valueOf((item.getChanges())))+"%");
                tvCurrentindex.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
                tvPercentage.setTextColor(getContext().getResources().getColor(R.color.color_FF20BC68));
        }
    }
}