package com.temp.buda.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.DethBean;
import com.temp.buda.util.Utils;

import java.util.List;

public class DethAdapter extends BaseQuickAdapter<DethBean, BaseViewHolder> {
   private Context mContext;
public DethAdapter(int layoutResId,Context context, List<DethBean> data) {
        super(layoutResId, data);
        mContext = context;
     }

@Override
protected void convert(BaseViewHolder helper, DethBean dethBean) {
        TextView tvNumber = helper.getView(R.id.tv_number);
        tvNumber.setText(Utils.keepTwo(Double.valueOf(dethBean.getNum())));
        TextView tvPrice = helper.getView(R.id.tv_price);
        tvPrice.setText(Utils.keepTwo(Double.valueOf(dethBean.getPrice())));
        TextView tvTotailprice = helper.getView(R.id.tv_totailprice);
        tvTotailprice.setText(dethBean.getTotalNum());
        if (dethBean.getName().contains("asks")){
        tvPrice.setTextColor(mContext.getResources().getColor(R.color.color_FFDC4D4D));
        } else {
        tvPrice.setTextColor(mContext.getResources().getColor(R.color.color_FF20BC68));
       }
    }
 }