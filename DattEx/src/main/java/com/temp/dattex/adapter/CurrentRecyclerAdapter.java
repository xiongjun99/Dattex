package com.temp.dattex.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.dattex.Application;
import com.temp.dattex.R;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.databinding.ItemOrderBinding;
import com.temp.dattex.order.OrderItemViewModel;

import org.w3c.dom.Text;

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
    private int mType ,mOrdertype;
    public CurrentRecyclerAdapter(int layoutResId, List<OrdersBean.OrderItemBean> data,int type,int ordertype) {
        super(layoutResId, data);
        orderItemViewModel = new OrderItemViewModel(Application.getInstance());
        mType = type;
        mOrdertype= ordertype;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrdersBean.OrderItemBean currentOrderBean) {
        ItemOrderBinding binding = helper.getBinding();
        binding.setOrderItemViewModel(orderItemViewModel);
        binding.setOrderBean(currentOrderBean);
        TextView tvSet = helper.findView(R.id.tv_set);
        tvSet.setOnClickListener(view -> {
            orderItemViewModel.getDirection().set(String.valueOf(currentOrderBean.getDirection()));
            orderItemViewModel.getSymbol().set(String.valueOf(currentOrderBean.getSymbol()));
            orderItemViewModel.getId().set(String.valueOf(currentOrderBean.getId()));
            orderItemViewModel.getLever().set(String.valueOf(currentOrderBean.getLever()));
            orderItemViewModel.getAmount().set(String.valueOf(currentOrderBean.getPrice()));
            orderItemViewModel.getUpStopPercent().set(String.valueOf((int)(currentOrderBean.getStopProfitRates()*100f)));
            orderItemViewModel.getDownStopPercent().set(String.valueOf((int)(currentOrderBean.getStopLossRates()*100f)));
            orderItemViewModel.set();
        });
        if (mType ==0){
            orderItemViewModel.getShow().set(false);
        }else {
            orderItemViewModel.getShow().set(true);
        }
        if (currentOrderBean.getContractType().equals("01")){
            orderItemViewModel.getContractType().set("合约订单");
        }else {
            orderItemViewModel.getContractType().set("新币订单");
        }
        TextView inCreaseValues = helper.getView(R.id.tv_profit);
        if (mOrdertype == 1){
            if (currentOrderBean.getProfitType()==0){
                inCreaseValues.setSelected(false);
                inCreaseValues.setText("-"+currentOrderBean.getFinalAmount());
            } else {
                inCreaseValues.setSelected(true);
                inCreaseValues.setText("+"+currentOrderBean.getFinalAmount());
            }
            orderItemViewModel.getOrderShow().set(true);
        }else {
            if (currentOrderBean.getProfitType()==0){
                inCreaseValues.setSelected(false);
                inCreaseValues.setText("-"+currentOrderBean.getIncreaseValues());
            } else {
                inCreaseValues.setSelected(true);
                inCreaseValues.setText("+"+currentOrderBean.getIncreaseValues());
            }
            orderItemViewModel.getOrderShow().set(false);
        }
    }


    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }
}
