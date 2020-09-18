package com.temp.buda.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.Application;
import com.temp.buda.R;
import com.temp.buda.bean.OrdersBean;
import com.temp.buda.databinding.ItemOrderBinding;
import com.temp.buda.order.OrderItemViewModel;

import java.math.BigDecimal;
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
    private Context mContext;
    private OrderItemViewModel orderItemViewModel;
    private int mType ,mOrdertype;
    public CurrentRecyclerAdapter(Context context,int layoutResId, List<OrdersBean.OrderItemBean> data, int type, int ordertype) {
        super(layoutResId, data);
        orderItemViewModel = new OrderItemViewModel(Application.getInstance());
        mType = type;
        mOrdertype= ordertype;
        mContext = context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrdersBean.OrderItemBean currentOrderBean) {
        ItemOrderBinding binding = helper.getBinding();
        binding.setOrderItemViewModel(orderItemViewModel);
        binding.setOrderBean(currentOrderBean);
        TextView tvSet = helper.findView(R.id.tv_set);
        TextView tvMarketprice = helper.findView(R.id.tv_marketprice);
        TextView tvContractType = helper.getView(R.id.tv_contracttype);
        TextView inCreaseValues = helper.getView(R.id.tv_profit);
        TextView TextCurrentPrice = helper.getView(R.id.text_current_price);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvPlace = helper.getView(R.id.tv_place);
        if ( mOrdertype ==0  ){
            tvMarketprice.setText(""+currentOrderBean.getMarketPrice());
            if (currentOrderBean.getProfitType()==0){
                inCreaseValues.setSelected(true);
                inCreaseValues.setText("-"+ new BigDecimal(currentOrderBean.getIncreaseValues()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
            } else {
                inCreaseValues.setSelected(false);
                inCreaseValues.setText("+"+ new BigDecimal(currentOrderBean.getIncreaseValues()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
            }
        }else {
            if (currentOrderBean.getProfitType()==0){
                inCreaseValues.setSelected(true);
                inCreaseValues.setText("-"+ new BigDecimal(currentOrderBean.getFinalAmount()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
            } else {
                inCreaseValues.setSelected(false);
                inCreaseValues.setText("+"+ new BigDecimal(currentOrderBean.getFinalAmount()).setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
            }
            tvMarketprice.setText(""+currentOrderBean.getEndMarketPrice());
        }
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
            orderItemViewModel.getShow().set(true);
        }else {
            orderItemViewModel.getShow().set(true);
        }
        tvTime.setText(currentOrderBean.getBuyTime());
        if (mOrdertype == 1){
            orderItemViewModel.getOrderShow().set(true);
            TextCurrentPrice.setText(mContext.getResources().getString(R.string.text_place_price));
            tvSet.setVisibility(View.GONE);
            tvPlace.setVisibility(View.GONE);
        } else {
            tvSet.setVisibility(View.VISIBLE);
            tvPlace.setVisibility(View.VISIBLE);
            orderItemViewModel.getOrderShow().set(false);
            TextCurrentPrice.setText(mContext.getResources().getString(R.string.text_current_price));
            if (currentOrderBean.getContractType().equals("01")){
                tvPlace.setVisibility(View.VISIBLE);
                tvSet.setVisibility(View.VISIBLE);
            }else {
                tvPlace.setVisibility(View.GONE);
                tvSet.setVisibility(View.GONE);
            }
        }

        if (currentOrderBean.getContractType().equals("01")){
            tvContractType.setText("合约订单");
            tvContractType.setBackground(mContext.getResources().getDrawable(R.drawable.shape_red_round));
        } else {
            tvContractType.setText("申购订单");
            tvContractType.setBackground(mContext.getResources().getDrawable(R.drawable.shape_blue_round));
        }
    }


    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }
}
