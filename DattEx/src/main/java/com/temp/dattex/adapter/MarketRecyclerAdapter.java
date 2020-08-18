package com.temp.dattex.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.Constants;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.databinding.ItemMarketLayoutBinding;
import com.temp.dattex.kline.KlineActivity;

import java.util.List;

/**
 * @Package: com.temp.dattex.adapter
 * @ClassName: MarketRecyclerAdapter
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 22:11
 * @Email: 86152
 */
public class MarketRecyclerAdapter extends BaseQuickAdapter<SymbolConfigBean, BaseViewHolder> {

    public MarketRecyclerAdapter(int layoutResId, List<SymbolConfigBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SymbolConfigBean marketBean) {
        ItemMarketLayoutBinding binding = baseViewHolder.getBinding();
        binding.setSymbol(marketBean);
        binding.getRoot().setOnClickListener(v -> {
            Context context = getContext();
            Intent intent = new Intent(context, KlineActivity.class);
            intent.putExtra(Constants.KEY_LEFT_COIN, marketBean.getCoinSymbol());
            intent.putExtra(Constants.KEY_RIGHT_COIN, marketBean.getBaseSymbol());
            context.startActivity(intent);
        });
    }

    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
        super.onItemViewHolderCreated(viewHolder, viewType);
    }
}
