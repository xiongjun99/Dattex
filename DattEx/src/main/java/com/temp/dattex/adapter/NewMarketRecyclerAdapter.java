package com.temp.dattex.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.util.Utils;

import java.util.List;

public class NewMarketRecyclerAdapter extends BaseQuickAdapter<MarketListBean, BaseViewHolder> {
    private Context mContext;

    public NewMarketRecyclerAdapter(int layoutResId,Context context, List<MarketListBean> data) {
        super(layoutResId, data);
        mContext= context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketListBean marketBean) {
        TextView tvCoinId = helper.getView(R.id.tv_coinid);
        tvCoinId.setText(marketBean.getCoinId());

        TextView tvChanges = helper.getView(R.id.tv_changes);
        float changes = Float.valueOf(marketBean.getChanges());
        if (changes == 0.0){
            tvChanges.setText(marketBean.getChanges());
            tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_normal));
        } else if (changes >0){
            tvChanges.setText("+"+marketBean.getChanges()+"%");
            tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_green));
        }else if (changes <0){
            tvChanges.setText(marketBean.getChanges()+"%");
            tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_red));
        }
        TextView tvCny = helper.getView(R.id.tv_cny);
        tvCny.setText(""+ Utils.keepTwo(Double.valueOf(marketBean.getPrice())*SymbolConfigs.getInstance().getCnyRate()));

        TextView tvPrice = helper.getView(R.id.tv_price);
        tvPrice.setText(marketBean.getPrice());
    }

}