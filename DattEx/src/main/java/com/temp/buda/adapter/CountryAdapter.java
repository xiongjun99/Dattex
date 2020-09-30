package com.temp.buda.adapter;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.temp.buda.R;
import com.temp.buda.bean.CountryBean;
import com.temp.buda.bean.NewApplyBean;

import java.util.List;

public class CountryAdapter extends BaseQuickAdapter<CountryBean, BaseViewHolder> {

    public CountryAdapter(List<CountryBean> data) {
        super(R.layout.item_country_info, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CountryBean data) {

        TextView tvCountryName = helper.getView(R.id.countryname);
        tvCountryName.setText(data.getCountryName());

        TextView tvCountryCode = helper.getView(R.id.countrycode);
        tvCountryCode.setText(data.getCountryCode());
    }
}
