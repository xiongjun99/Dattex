package com.temp.dattex.fragments.market;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.common.framework.basic.BaseFragment;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.MarketRecyclerAdapter;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.databinding.FragmentMarketLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.temp.dattex.market
 * @ClassName: MarketFragment
 * @Description: java&#x7c7b;&#x4f5c;&#x7528;&#x63cf;&#x8ff0;
 * @Author: &#x674e;&#x5609;&#x4f26;
 * @CreateDate: 2020/5/17 21:19
 * @Email: 86152
 */

public class MarketFragment extends BaseFragment<FragmentMarketLayoutBinding, MarketViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_market_layout;
    }

    @Override
    public int initVariableId() {
        return BR.marketViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView() {
        List<SymbolConfigBean> symbols = SymbolConfigs.getInstance().getSymbols();
        MarketRecyclerAdapter marketRecyclerAdapter = new MarketRecyclerAdapter(R.layout.item_market_layout, symbols);
        viewModel.adapter.set(marketRecyclerAdapter);
    }

    @Override
    public void initViewObservable() {

    }
}
