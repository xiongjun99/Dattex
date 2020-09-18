package com.temp.dattex.binding.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icechao.klinelib.adapter.KLineChartAdapter;
import com.icechao.klinelib.formatter.IDateTimeFormatter;
import com.icechao.klinelib.formatter.IValueFormatter;
import com.icechao.klinelib.view.KLineChartView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.temp.dattex.Application;
import com.temp.dattex.widget.ProgressBar;
import com.temp.dattex.widget.sort.MarketSortView;
import com.temp.dattex.widget.view.CustomSeekBar;

/**
 * @Package: com.temp.dattex.binding.adapter
 * @ClassName: CommonViewBinding
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/17 16:33
 * @Email: 86152
 */
public class CommonViewBinding {

    @BindingAdapter("rcvAdapter")
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("rcvLayoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LinearLayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }


    @BindingAdapter("imgResource")
    public static void setImageResource(TextView textView, int imageResource) {
        Drawable drawable = Application.getInstance().getResources().getDrawable(imageResource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    @BindingAdapter("onSortChangeListener")
    public static void setSortChangeListener(MarketSortView marketSortView, MarketSortView.OnSortChangeListener onSortChangeListener) {
        marketSortView.setOnSortChangeListener(onSortChangeListener);
    }

    @BindingAdapter("onProgressChangeListener")
    public static void setOnProgressBarChangeListener(ProgressBar progressBar, ProgressBar.OnProgressChangeListener onProgressChangeListener) {
        progressBar.setOnProgressChangeListener(onProgressChangeListener);
    }

    @BindingAdapter("src")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter("listener")
    public static void setSmartViewListener(SmartRefreshLayout refreshLayout, SmartViewListener smartRefreshListener) {
        refreshLayout.setOnRefreshListener(smartRefreshListener);
        refreshLayout.setOnLoadMoreListener(smartRefreshListener);
    }

    @BindingAdapter("adapter")
    public static void setKLineAdapter(KLineChartView kLineChartView, KLineChartAdapter adapter) {
        kLineChartView.setAdapter(adapter);
    }

    @BindingAdapter("dateFormatter")
    public static void setKLineAdapter(KLineChartView kLineChartView, IDateTimeFormatter formatter) {
        kLineChartView.setDateTimeFormatter(formatter);
    }

    @BindingAdapter("valueFormatter")
    public static void setKLineValueFormatter(KLineChartView kLineChartView, IValueFormatter formatter) {
        kLineChartView.setValueFormatter(formatter);
    }


    @BindingAdapter("volValueFormatter")
    public static void setKLineVolValueFormatter(KLineChartView kLineChartView, IValueFormatter formatter) {
        kLineChartView.setVolFormatter(formatter);
    }

    @BindingAdapter("overRange")
    public static void setKlineOverRange(KLineChartView kLineChartView, int range) {
        int measuredWidth = kLineChartView.getMeasuredWidth();
        kLineChartView.setOverScrollRange(measuredWidth / 5);
    }

    public interface SmartViewListener extends OnRefreshListener, OnLoadMoreListener {

    }

    @BindingAdapter("setMaxProgress")
    public static void setMaxProgress(CustomSeekBar customSeekBar , int maxProgress) {
        customSeekBar.setMaxProgress(maxProgress);
    }
}
