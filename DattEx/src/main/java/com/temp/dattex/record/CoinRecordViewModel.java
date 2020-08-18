package com.temp.dattex.record;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.independ.framework.response.ResponseTransformer;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.temp.dattex.R;
import com.temp.dattex.bean.AssetsRecordBean;
import com.temp.dattex.binding.adapter.CommonViewBinding;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.databinding.ItemCoinRecordBinding;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.DialogUtil;

import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.record
 * @FileName     : CoinRecordViewModel.java
 * @Author       : chao
 * @Date         : 2020/5/20
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
public class CoinRecordViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, CommonViewBinding.SmartViewListener {

    private String coinName;
    private int page;


    public CoinRecordViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void rightClick() {
        CoinRecordFilerViewModel coinRecordFilerViewModel = new CoinRecordFilerViewModel();
        Activity peek = AppManager.getActivityStack().peek();
        DialogUtil.showFilterDialog(peek, coinRecordFilerViewModel);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        getData();
    }

    @SuppressLint("CheckResult")
    private void getData() {
        DataService.getInstance().assetsRecorde(coinName, page, "").compose(ResponseTransformer.<AssetsRecordBean>handleResult()).subscribe(
                bean -> {
                    List<AssetsRecordBean.AssetsItemBean> rows = bean.getRows();
                    if (null != rows) {
                        page++;
                        adapter.addData(rows);
                        adapter.notifyDataSetChanged();
                    }
                }, t -> {

                }
        );
    }


    public BaseQuickAdapter adapter = new BaseQuickAdapter<AssetsRecordBean.AssetsItemBean, BaseViewHolder>(R.layout.item_coin_record) {


        @Override
        protected void convert(BaseViewHolder viewHolder, AssetsRecordBean.AssetsItemBean assetsItemBean) {
            ItemCoinRecordBinding binding = viewHolder.getBinding();
            binding.setDataBean(assetsItemBean);
        }

        @Override
        protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            DataBindingUtil.bind(viewHolder.itemView);
        }
    };


}
