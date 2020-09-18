package com.temp.buda.binding.adapter;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.common.framework.basic.BaseViewModel;
import com.temp.buda.widget.ScrollViewPager;
import com.temp.buda.widget.TitleBar;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.binding.adapter
 * @FileName     : TitleBarClickBindingAdapter.java
 * @Author       : chao
 * @Date         : 2020/5/14
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

public class TitleBarClickBindingAdapter {


    @BindingAdapter(value = {"leftClick"}, requireAll = true)
    public static <T extends BaseViewModel> void setOnLeftClickListener(TitleBar view, T viewModel) {
        view.setLeftClick(viewModel);
    }

    @BindingAdapter(value = {"rightClick"}, requireAll = true)
    public static void setRightClickListener(TitleBar view, TitleRightClickListener rightClickListener) {
        view.setRightClick(rightClickListener);
    }

    public interface TitleRightClickListener {
        void rightClick();
    }


    @BindingAdapter(value = {"titleText"}, requireAll = true)
    public static void setTitleText(TitleBar titleBar, String title) {
        titleBar.setTitleText(title);
    }

    @BindingAdapter("pagerAdapter")
    public static void setPagerAdapter(ScrollViewPager adapter, FragmentStatePagerAdapter pagerAdapter) {
        adapter.setAdapter(pagerAdapter);
    }

    @BindingAdapter("pagerCurrentItem")
    public static void setPageCurrentItem(ScrollViewPager pager, int index) {
        pager.setCurrentItem(index, false);
    }

    @BindingAdapter("pagerTouch")
    public static void setPagerTouch(ScrollViewPager pager, boolean touch) {
        pager.setTouch(touch);
    }
}
