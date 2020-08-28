package com.temp.dattex.order.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.common.framework.basic.BaseFragment;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.CurrentRecyclerAdapter;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.databinding.FragmentHistoryLayoutBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.temp.dattex.order.fragment
 * @ClassName: HistoryOrderFragment
 * @Description: java&#x7c7b;&#x4f5c;&#x7528;&#x63cf;&#x8ff0;
 * @Author: &#x674e;&#x5609;&#x4f26;
 * @CreateDate: 2020/5/18 21:17
 * @Email: 86152
 */
public class HistoryOrderFragment extends BaseFragment<FragmentHistoryLayoutBinding, HistoryOrderViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_history_layout;
    }

    @Override
    public int initVariableId() {
        return BR.historyOrderViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView() {
        List<OrdersBean.OrderItemBean> list = new ArrayList<>();
        CurrentRecyclerAdapter currentRecyclerAdapter = new CurrentRecyclerAdapter(R.layout.item_order, list,1,1);
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
