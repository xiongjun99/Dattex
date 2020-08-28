package com.temp.dattex.fragments.home;

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
import com.temp.dattex.R;
import com.temp.dattex.adapter.HomeFunctionAdapter;
import com.temp.dattex.apply.ApplyActivity;
import com.temp.dattex.bean.HomeFunctionBean;
import com.temp.dattex.net.ApiAddress;
import com.temp.dattex.web.WebViewActivity;
import java.util.ArrayList;

public class HomeFunctionFragment  extends Fragment {
private int processStatus;
private ArrayList<HomeFunctionBean> homeFunctionList = new ArrayList<>();;
private HomeFunctionAdapter homeFunctionAdapter;
private RecyclerView recyclerView;
public HomeFunctionFragment(int processStatus) {
        this.processStatus = processStatus;
        }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        homeFunctionAdapter = new HomeFunctionAdapter(homeFunctionList);
        recyclerView.setAdapter(homeFunctionAdapter);
        homeFunctionAdapter.setOnItemClickListener((adapter, view1, position) -> {
               if (homeFunctionList.get(position).isWeb()==true && "帮助中心".equals(homeFunctionList.get(position).getmFunName())){
                   Bundle bundle = new Bundle();
                   bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.HELP_CENTER_URL);
                   Intent it = new Intent(getActivity(),WebViewActivity.class);
                   it.putExtras(bundle);
                   startActivity(it);
               } else if (homeFunctionList.get(position).isWeb()==true && "新闻公告".equals(homeFunctionList.get(position).getmFunName())){
                    Bundle bundle = new Bundle();
                    bundle.putString(WebViewActivity.KEY_PARAM_URL, ApiAddress.NEWS_NOTICE_URL);
                    Intent it = new Intent(getActivity(),WebViewActivity.class);
                    it.putExtras(bundle);
                    startActivity(it);
                }else if (homeFunctionList.get(position).isWeb()==false && "新币申购".equals(homeFunctionList.get(position).getmFunName())){
                   Intent it = new Intent(getActivity(), ApplyActivity.class);
                   startActivity(it);
               }
        });
        initData(processStatus);
}
private void initData(int processStatus) {
        if (processStatus==0){
        homeFunctionList.add(new HomeFunctionBean("帮助中心", "问题/指南/资料",R.mipmap.icon_help_center,ApiAddress.HELP_CENTER_URL,true));
        homeFunctionList.add(new HomeFunctionBean("新闻公告", "快讯/文章/公告",R.mipmap.icon_news,ApiAddress.NEWS_NOTICE_URL,true));
        homeFunctionAdapter.notifyDataSetChanged();
        } else {
        homeFunctionList.add(new HomeFunctionBean("新币申购", "购买新币涨不停",R.mipmap.apply,"Test",false));
        homeFunctionAdapter.notifyDataSetChanged();
        }
    }
 }