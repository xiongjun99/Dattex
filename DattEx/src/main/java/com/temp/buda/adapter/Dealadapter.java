package com.temp.buda.adapter;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.DealItemBean;
import com.temp.buda.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Dealadapter extends BaseQuickAdapter<DealItemBean, BaseViewHolder> {

     private Context mContext;

public Dealadapter(int layoutResId,Context context, List<DealItemBean> data) {
        super(layoutResId, data);
        mContext = context;
  }

@Override
protected void convert(BaseViewHolder helper, DealItemBean dethBean) {
    TextView tvTime = helper.getView(R.id.tv_time);
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    String sd = sdf.format(new Date(Long.parseLong(String.valueOf(dethBean.getTs()))));      // 时间戳转换成时间
    tvTime.setText(""+sd);

    TextView tvNumber = helper.getView(R.id.tv_number);
    tvNumber.setText(""+Utils.keepTwo(dethBean.getAmount()));

    TextView tvPrice = helper.getView(R.id.tv_price);
    tvPrice.setText(Utils.keepTwo(Double.valueOf(dethBean.getPrice())));

    TextView tvBuySell = helper.getView(R.id.tv_buy_sell);
    if (dethBean.getDirection().equals("sell")){
        tvBuySell.setText("做空");
        tvBuySell.setTextColor(mContext.getResources().getColor(R.color.color_FFDC4D4D));
     } else {
        tvBuySell.setText("做多");
        tvBuySell.setTextColor(mContext.getResources().getColor(R.color.color_FF20BC68));
    }
  }
}