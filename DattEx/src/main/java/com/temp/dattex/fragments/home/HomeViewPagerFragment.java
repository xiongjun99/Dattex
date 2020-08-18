package com.temp.dattex.fragments.home;

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
import com.temp.dattex.R;
import com.temp.dattex.adapter.EntranceAdapter;
import com.temp.dattex.bean.HomeViewPagerBean;
import java.util.ArrayList;

public class HomeViewPagerFragment extends Fragment {
    private String processStatus;
    ArrayList<HomeViewPagerBean> homeEntrances = new ArrayList<>();;
    EntranceAdapter entranceAdapter;
    RecyclerView recyclerView;
    public HomeViewPagerFragment(String processStatus) {
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
        entranceAdapter = new EntranceAdapter(homeEntrances);
        recyclerView.setAdapter(entranceAdapter);
        initData(processStatus);
    }
    private void initData(String processStatus) {
        if (processStatus.equals("0")){
            homeEntrances.add(new HomeViewPagerBean("BTC/USDT", "100.00","+5.32%","600.01"));
            homeEntrances.add(new HomeViewPagerBean("ETH/USDT", "286.00","-1.32%","1300.01"));
            homeEntrances.add(new HomeViewPagerBean("HT/USDT", "398.00","-0.32%","120.00"));
            entranceAdapter.notifyDataSetChanged();
        } else {
            homeEntrances.add(new HomeViewPagerBean("CNY/USDT", "323.00","+2.32%","510.00"));
            homeEntrances.add(new HomeViewPagerBean("UPC/USDT", "112.00","+1.32%","888.00"));
            homeEntrances.add(new HomeViewPagerBean("WWE/USDT", "31.00","-0.42%","1999.00"));
            entranceAdapter.notifyDataSetChanged();
        }
    }
}