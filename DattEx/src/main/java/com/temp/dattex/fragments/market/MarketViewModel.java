package com.temp.dattex.fragments.market;

import android.app.Application;

import androidx.annotation.NonNull;

import com.common.framework.basic.BaseViewModel;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.widget.sort.MarketSortView;

/**
 * @Package: com.temp.dattex.market
 * @ClassName: MarketViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 21:20
 * @Email: 86152
 */

public class MarketViewModel extends BaseViewModel implements MarketSortView.OnSortChangeListener {
//    public ObservableField<MarketRecyclerAdapter> adapter = new ObservableField<>();
//    public ObservableField<NewMarketRecyclerAdapter> adapter = new ObservableField<>();
    public MarketViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void sortNormal() {
        LogUtil.e("默认排序");
    }

    @Override
    public void priceAscending() {
        LogUtil.e("价格升序");
    }

    @Override
    public void priceDescending() {
        LogUtil.e("价格降序");
    }

    @Override
    public void changeAscending() {
        LogUtil.e("涨跌幅升序");
    }

    @Override
    public void changeDescending() {
        LogUtil.e("涨跌幅降序");
    }
}
