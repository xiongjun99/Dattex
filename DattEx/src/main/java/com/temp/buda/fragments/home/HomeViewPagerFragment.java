package com.temp.buda.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseApplication;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.buda.Constants;
import com.temp.buda.R;
import com.temp.buda.adapter.EntranceAdapter;
import com.temp.buda.bean.MarketListBean;
import com.temp.buda.kline.KlineActivity;
import com.temp.buda.net.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeViewPagerFragment extends Fragment {
    private int processStatus;
    List<MarketListBean> list = new ArrayList<>();;
    EntranceAdapter entranceAdapter;
    RecyclerView recyclerView;
    private Timer timer;

    public HomeViewPagerFragment(int processStatus) {
        this.processStatus = processStatus;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_viewpager, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutParams(layoutParams12);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        entranceAdapter = new EntranceAdapter(getActivity(),list);
        recyclerView.setAdapter(entranceAdapter);
        initData(processStatus);
        entranceAdapter.setOnItemClickListener((adapter, view1, position) -> {
            Intent it = new Intent(getActivity(),KlineActivity.class);
            it.putExtra(Constants.REQUEST_KEY_COIN_ID,  entranceAdapter.getData().get(position).getCoinId());
            startActivity(it);
        });
    }
    private void initData(int processStatus) {
        getMarketList();
//        if (processStatus==0){
//            homeEntrances.add(new HomeViewPagerBean("BTC/USDT", "100.00","+5.32%","600.01"));
//            homeEntrances.add(new HomeViewPagerBean("ETH/USDT", "286.00","-1.32%","1300.01"));
//            homeEntrances.add(new HomeViewPagerBean("HT/USDT", "398.00","-0.32%","120.00"));
//            entranceAdapter.notifyDataSetChanged();
//        } else {
//            homeEntrances.add(new HomeViewPagerBean("CNY/USDT", "323.00","+2.32%","510.00"));
//            homeEntrances.add(new HomeViewPagerBean("UPC/USDT", "112.00","+1.32%","888.00"));
//            homeEntrances.add(new HomeViewPagerBean("WWE/USDT", "31.00","-0.42%","1999.00"));
//            entranceAdapter.notifyDataSetChanged();
//        }
    }
    @Override
    public void onResume() {
        super.onResume();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getMarketList();
            }
        }, 1000, 1000);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void getMarketList() {
        DataService.getInstance().getMarketList().compose(ResponseTransformer.<List<MarketListBean>>handleResult()).subscribe(
                l -> {
                    if (list!=null&&list.size()>0){
                      list.clear();
                     }
                    for (int i = 0; i < l.size(); i++) {
                        if (i<3){
                            entranceAdapter.addData(i,l.get(i));
                        }
                    }
//                    entranceAdapter.notifyDataSetChanged();
//                    if (entranceAdapter.getData()==null||entranceAdapter.getData().size()==0){
//                        entranceAdapter.addData(l);
//                    }else {
//                        entranceAdapter.setNewData(l);
//                    }
                }, t -> {
                    ToastUtil.show(BaseApplication.getInstance(), t.getMessage());}
        );
    }

}