package com.temp.dattex.widget.sort;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.temp.dattex.R;
import com.temp.dattex.databinding.ViewMarketSortBinding;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.widget
 * @FileName     : MarketSortView.java
 * @Author       : chao
 * @Date         : 2020/5/18
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class MarketSortView extends RelativeLayout {

    private MarketSortViewModel viewModel;

    public MarketSortView(Context context) {
        super(context);
        initView(context);
    }

    public MarketSortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MarketSortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MarketSortView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        viewModel = new MarketSortViewModel();
        ViewMarketSortBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_market_sort, this, true);
        binding.setMarketSortViewModel(viewModel);
    }


    public void setOnSortChangeListener(OnSortChangeListener onSortChangeListener) {
        if (null != viewModel) {
            viewModel.setOnSortChangeListener(onSortChangeListener);
        }
    }

    public interface OnSortChangeListener {

        void sortNormal();

        void priceAscending();

        void priceDescending();

        void changeAscending();

        void changeDescending();
    }


}
