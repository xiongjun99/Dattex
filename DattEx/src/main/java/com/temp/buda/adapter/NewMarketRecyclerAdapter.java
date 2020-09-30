package com.temp.buda.adapter;

import android.content.Context;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.MarketListBean;
import com.temp.buda.config.SymbolConfigs;
import com.temp.buda.util.Utils;

import java.util.List;

public class NewMarketRecyclerAdapter extends BaseQuickAdapter<MarketListBean, BaseViewHolder> {
    private Context mContext;
   private  int POS;
    public NewMarketRecyclerAdapter(int layoutResId,Context context, List<MarketListBean> data) {
        super(layoutResId, data);
        mContext= context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketListBean marketBean) {
        TextView tvCoinId = helper.getView(R.id.tv_coinid);
        tvCoinId.setText(marketBean.getCoinId());
        TextView tvChanges = helper.getView(R.id.tv_changes);
        if (getPos()==3) {
            float changes = Float.valueOf(marketBean.getDealCount());
            tvChanges.setText(""+Utils.keepTwo(changes)+"亿");
            tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_blue));
        }else {
            float changes = Float.valueOf(marketBean.getChanges());
            if (changes == 0.0){
                tvChanges.setText(" 0.00");
                tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_normal));
            } else if (changes >0){
                tvChanges.setText("+"+Utils.FloatkeepTwo(changes)+"%");
                tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_green));
            }else if (changes <0){
                tvChanges.setText(Utils.FloatkeepTwo(changes)+"%");
                tvChanges.setBackground(mContext.getResources().getDrawable(R.drawable.shape_market_item_red));
            }
        }
        TextView tvCny = helper.getView(R.id.tv_cny);
        tvCny.setText(""+ Utils.keepTwo(Double.valueOf(marketBean.getPrice())*SymbolConfigs.getInstance().getCnyRate()));


        TextView tvPrice = helper.getView(R.id.tv_price);
        tvPrice.setText(""+ Utils.keepTwo(Double.valueOf(marketBean.getPrice())));
    }
    public  int getPos() {
        return POS;
    }

    public  void setPos(int pos) {
        POS = pos;
    }
}