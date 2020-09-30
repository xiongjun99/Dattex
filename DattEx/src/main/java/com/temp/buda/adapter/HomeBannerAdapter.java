package com.temp.buda.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.temp.buda.R;
import com.temp.buda.bean.BannerItemBean;
import com.temp.buda.net.ApiAddress;

import java.util.ArrayList;
import java.util.List;

public class HomeBannerAdapter extends LoopPagerAdapter {
    private Context context;
    private List<BannerItemBean.RowsBean> domainLists = new ArrayList<>();
    public HomeBannerAdapter(RollPagerView viewPager, Context context, List<BannerItemBean.RowsBean> domainLists) {
        super(viewPager);
        this.context = context;
        this.domainLists=domainLists;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View view=View.inflate(context, R.layout.banner_item,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.iv);
         Glide.with(context).load(ApiAddress.WEB_URL + domainLists.get(position).getUrl()).into(imageView);
        return view;
    }

    @Override
    public int getRealCount() {
        return domainLists==null?0:domainLists.size();
    }
}

