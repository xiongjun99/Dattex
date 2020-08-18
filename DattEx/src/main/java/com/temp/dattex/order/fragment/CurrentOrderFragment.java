package com.temp.dattex.order.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.CurrentRecyclerAdapter;
import com.temp.dattex.databinding.FragmentCurrentLayoutBinding;

/**
 * @Package: com.temp.dattex.order.fragment
 * @ClassName: CurrentOrderFragment
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 16:29
 * @Email: 86152
 */


public class CurrentOrderFragment extends BaseFragment<FragmentCurrentLayoutBinding, CurrentOrderViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_current_layout;
    }

    @Override
    public int initVariableId() {
        return BR.currentViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void initView() {
        LogUtil.d("init view " + System.currentTimeMillis());
//        List<CurrentOrderBean> list = new ArrayList<>();
//        list.add(new CurrentOrderBean());
//        list.add(new CurrentOrderBean());
        CurrentRecyclerAdapter currentRecyclerAdapter = new CurrentRecyclerAdapter(R.layout.item_order, null);
        View emptyView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.order_empty_layout, null);
        currentRecyclerAdapter.setUseEmpty(true);
        currentRecyclerAdapter.setEmptyView(emptyView);
        viewModel.rcyAdapter.set(currentRecyclerAdapter);
    }

    @Override
    public void initViewObservable() {

    }
}
