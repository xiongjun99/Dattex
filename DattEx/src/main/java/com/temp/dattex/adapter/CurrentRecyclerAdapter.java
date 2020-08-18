package com.temp.dattex.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.Application;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.databinding.ItemOrderBinding;
import com.temp.dattex.order.OrderItemViewModel;

import java.util.List;

/**
 * @Package: com.temp.dattex.adapter
 * @ClassName: CurrentRecyclerAdapter
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 16:42
 * @Email: 86152
 */
public class CurrentRecyclerAdapter extends BaseQuickAdapter<OrdersBean.OrderItemBean, BaseViewHolder> {


    private OrderItemViewModel orderItemViewModel;

    public CurrentRecyclerAdapter(int layoutResId, List<OrdersBean.OrderItemBean> data) {
        super(layoutResId, data);
        orderItemViewModel = new OrderItemViewModel(Application.getInstance());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrdersBean.OrderItemBean currentOrderBean) {
        ItemOrderBinding binding = helper.getBinding();
        binding.setOrderItemViewModel(orderItemViewModel);
        binding.setOrderBean(currentOrderBean);
    }


    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }
}
