package com.temp.dattex.withdraworwallet;

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
import com.temp.dattex.bean.CoinRecordBean;
import com.temp.dattex.binding.adapter.CommonViewBinding;
import com.temp.dattex.binding.adapter.TitleBarClickBindingAdapter;
import com.temp.dattex.databinding.ItemWithWalletBinding;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.DialogUtil;

import java.util.List;

public class WithDrawWalletViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, CommonViewBinding.SmartViewListener {

    private String coinName;
    private int page;


    public WithDrawWalletViewModel(@NonNull Application application) {
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
                    List<AssetsRecordBean.RowsBean> rows = bean.getRows();
                    if (null != rows) {
                        page++;
                        adapter.addData(rows);
                        adapter.notifyDataSetChanged();
                    }
                }, t -> {

                }
        );
    }

    public BaseQuickAdapter adapter = new BaseQuickAdapter<AssetsRecordBean.RowsBean, BaseViewHolder>(R.layout.item_with_wallet) {


        @Override
        protected void convert(BaseViewHolder viewHolder,AssetsRecordBean.RowsBean assetsItemBean) {
            ItemWithWalletBinding binding = viewHolder.getBinding();
            binding.setDataBean(assetsItemBean);
        }

        @Override
        protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            DataBindingUtil.bind(viewHolder.itemView);
        }
    };
}
