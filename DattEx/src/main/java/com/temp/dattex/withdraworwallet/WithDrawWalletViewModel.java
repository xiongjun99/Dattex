package com.temp.dattex.withdraworwallet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

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
import com.temp.dattex.databinding.ItemWithWalletBinding;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoinRecordFilerViewModel;
import com.temp.dattex.util.DialogUtil;

import java.util.List;

public class WithDrawWalletViewModel extends BaseViewModel implements TitleBarClickBindingAdapter.TitleRightClickListener, CommonViewBinding.SmartViewListener, CoinRecordFilerViewModel.OnEnsureListener {

    private String coinName;
    private int page;

    public ObservableField<Integer> getType() {
        return type;
    }

    public void setType(ObservableField<Integer> type) {
        this.type = type;
    }

    private ObservableField<Integer> type = new ObservableField<>(-1);

    public WithDrawWalletViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    public void rightClick() {
        CoinRecordFilerViewModel coinRecordFilerViewModel = new CoinRecordFilerViewModel(getApplication());
        Activity peek = AppManager.getActivityStack().peek();
        coinRecordFilerViewModel.setOnEnsureListener(this);
        DialogUtil.showFilterDialog(peek, coinRecordFilerViewModel);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page ++;
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
    public void getData() {
        DataService.getInstance().getFindMemberBill(type.get(),coinName, page, "").compose(ResponseTransformer.<AssetsRecordBean>handleResult()).subscribe(
                bean -> {
                    List<AssetsRecordBean.RowsBean> rows = bean.getRows();
                    if (null != rows && rows.size() > 0) {
                        adapter.setNewData(bean.getRows());
                    } else {
                        adapter.setNewData(null);
                    }
                }, t -> {

                }
        );
    }
    @Override
    public void onEnsure(int value) {
        type.set(value);
        getData();
    }
    public BaseQuickAdapter adapter = new BaseQuickAdapter<AssetsRecordBean.RowsBean, BaseViewHolder>(R.layout.item_with_wallet) {
        @Override
        protected void convert(BaseViewHolder viewHolder,AssetsRecordBean.RowsBean assetsItemBean) {
            ItemWithWalletBinding binding = viewHolder.getBinding();
            binding.setDataBean(assetsItemBean);

            switch (assetsItemBean.getType()){
                case 0:
                    binding.tvTitle.setText("充币");
                    break;
                case 1:
                    binding.tvTitle.setText("提币");
                    break;
                case 2:
                    binding.tvTitle.setText("手续费");
                    break;
                case 3:
                    binding.tvTitle.setText("交易返佣");
                    break;
                case 4:
                    binding.tvTitle.setText("邀请返佣");
                    break;
                case 5:
                    binding.tvTitle.setText("注册奖励");
                    break;
                case 6:
                    binding.tvTitle.setText("充值赠送");
                    break;
                case 99:
                    binding.tvTitle.setText("其它");
                    break;
            }

        }

        @Override
        protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
            DataBindingUtil.bind(viewHolder.itemView);
        }
    };
}
