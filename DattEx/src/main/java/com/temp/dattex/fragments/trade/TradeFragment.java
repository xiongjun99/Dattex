package com.temp.dattex.fragments.trade;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.FragmentTradeBinding;
import com.temp.dattex.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


public class TradeFragment extends BaseFragment<FragmentTradeBinding, TradeViewModel> {
    private ProgressBar progressBar;
    private List<Integer> list = new ArrayList<>();
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_trade;
    }

    @Override
    public int initVariableId() {
        return BR.tradeViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {
        LogUtil.d("lazyLoad");
    }


    @Override
    public void initView() {
        list.add(0);
        list.add(200);
        list.add(400);
        list.add(600);
        list.add(800);
        list.add(1000);

        View emptyView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.order_empty_layout, null);
        viewModel.adapter.setUseEmpty(true);
        viewModel.adapter.setEmptyView(emptyView);
        progressBar = getActivity().findViewById(R.id.progressbar);
        progressBar.setList(list);
        progressBar.setPointCount(5);
    }

    @Override
    public void initViewObservable() {

    }
}
