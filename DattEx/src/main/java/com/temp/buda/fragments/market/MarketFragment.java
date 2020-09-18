package com.temp.buda.fragments.market;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseApplication;
import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.BR;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.adapter.NewMarketRecyclerAdapter;
import com.temp.buda.bean.MarketListBean;
import com.temp.buda.config.SymbolConfigs;
import com.temp.buda.databinding.FragmentMarketLayoutBinding;
import com.temp.buda.kline.KlineActivity;
import com.temp.buda.net.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Package: com.temp.dattex.market
 * @ClassName: MarketFragment
 * @Description: java&#x7c7b;&#x4f5c;&#x7528;&#x63cf;&#x8ff0;
 * @Author: &#x674e;&#x5609;&#x4f26;
 * @CreateDate: 2020/5/17 21:19
 * @Email: 86152
 */

public class MarketFragment extends BaseFragment<FragmentMarketLayoutBinding, MarketViewModel> {
    private Timer timer;
    private List<MarketListBean> listBeans = new ArrayList<>();
    private NewMarketRecyclerAdapter marketRecyclerAdapter;
    private RecyclerView recycler_view;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_market_layout;
    }

    @Override
    public int initVariableId() {
        return BR.marketViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView() {
        getUsdtcny();
        getMarketList();
        recycler_view = getActivity().findViewById(R.id.recycler_view);
        marketRecyclerAdapter = new NewMarketRecyclerAdapter(R.layout.item_newmarket_layout,getActivity(),listBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setAdapter(marketRecyclerAdapter);
        marketRecyclerAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(getActivity(), KlineActivity.class);
            intent.putExtra(Constants.REQUEST_KEY_COIN_ID, marketRecyclerAdapter.getData().get(position).getCoinId());
            startActivity(intent);
        });
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onResume() {
        super.onResume();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getUsdtcny();
                getMarketList();
            }
        }, 1000, 1000);
    }

    private void getMarketList() {
        DataService.getInstance().getMarketList().compose(ResponseTransformer.<List<MarketListBean>>handleResult()).subscribe(
                list -> {
                    if (marketRecyclerAdapter.getData().size()>0){
                        marketRecyclerAdapter.setNewData(list);
                    }else {
                        marketRecyclerAdapter.addData(list);
                    }
                }, t -> {
                    ToastUtil.show(BaseApplication.getInstance(), t.getMessage());}
        );
    }
    private void getUsdtcny() {
        DataService.getInstance().getUsdtRate().compose(ResponseTransformer.<Double>handleResult()).subscribe(
                d -> {
                    SymbolConfigs.getInstance().setCnyRate(d.floatValue());
                }, t -> {

                }
        );
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }
}
