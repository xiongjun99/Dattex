package com.temp.dattex.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exchange.utilslib.DisplayUtil;
import com.independ.framework.response.BaseResponse;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.R;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.databinding.DialogChangeLeverageBinding;
import com.temp.dattex.databinding.DialogCoinRecordFilterBinding;
import com.temp.dattex.databinding.DialogCreateContract2Binding;
import com.temp.dattex.databinding.DialogCreateContractBinding;
import com.temp.dattex.databinding.DialogLoadpayBinding;
import com.temp.dattex.databinding.DialogOrderCoverBinding;
import com.temp.dattex.databinding.DialogSwitchCoinBinding;
import com.temp.dattex.databinding.DialogUpdateBinding;
import com.temp.dattex.databinding.ItemTradeSwitchCoinBinding;
import com.temp.dattex.fragments.trade.TradeViewModel;
import com.temp.dattex.net.DataService;
import com.temp.dattex.order.OrderItemViewModel;
import com.temp.dattex.wallet.WalletModel;

import java.util.List;

import io.reactivex.Observable;

public class DialogUtil {

    public static void showSwitchCoinDialog(Context context, SwitchSymbolDialogViewModel viewModel ,List<SymbolConfigBean> symbols) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogSwitchCoinBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_switch_coin, null, false);
        binding.setSwitchSymbolViewModel(viewModel);
        viewModel.setDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, 800);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        binding.recyclerViewDialogSwitchSymbol.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerViewDialogSwitchSymbol.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemTradeSwitchCoinBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_trade_switch_coin, parent, false);
                return new ViewHolder(binding.getRoot(), binding);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                SymbolConfigBean symbolBean = symbols.get(position);
//                SymbolConfigBean symbolBean = viewModel.getSymbolBean(position);
                holder.binding.setSymbolBean(symbolBean);
                holder.binding.getRoot().setOnClickListener(v -> {
                    viewModel.setSymbol(symbolBean.getCoinSymbol(), symbolBean.getBaseSymbol());
                });
            }

            @Override
            public int getItemCount() {
                return viewModel.getSymbolSize();
            }
        });
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context) - DisplayUtil.dp2px(context, 32);
        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTradeSwitchCoinBinding binding;

        public ViewHolder(@NonNull View itemView, ItemTradeSwitchCoinBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }


    public static void createContractDialog(Context context, PlaceAnOrderDialogModel placeAnOrderDialogModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogCreateContractBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_create_contract, null, false);
        binding.setContractModel(placeAnOrderDialogModel);
        placeAnOrderDialogModel.setDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context) - DisplayUtil.dp2px(context, 32);
        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }


    public static void createContractDialog2(Context context, OrderItemViewModel orderItemViewModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogCreateContract2Binding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_create_contract2, null, false);
        binding.setContractModel(orderItemViewModel);
        orderItemViewModel.setDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context) - DisplayUtil.dp2px(context, 32);
        dialog.getWindow().setAttributes(attributes);
        dialog.show();
    }
    public static void showUpdateDialog(Context context, UpdateDialogViewModel viewModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogUpdateBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_update, null, false);
        binding.setViewModel(viewModel);
        viewModel.setDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1400);
        layoutParams.leftMargin = 40;
        layoutParams.rightMargin = 40;
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        dialog.setCancelable(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context) - DisplayUtil.dp2px(context, 32);
        dialog.getWindow().setAttributes(attributes);
        dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
            if (i == keyEvent.KEYCODE_SEARCH){
                return true;
            }else {
                return false;
            }
        });
        dialog.show();
    }


    public static void showCoverDialog(Context context, CoverDialogViewModel viewModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogOrderCoverBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_order_cover, null, false);
        binding.setCoverDialogViewModel(viewModel);
        viewModel.dialog.set(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = DisplayUtil.dp2px(context, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context) - DisplayUtil.dp2px(context, 32);
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public static void showFilterDialog(Context context, CoinRecordFilerViewModel viewModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogCoinRecordFilterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_coin_record_filter, null, false);
        binding.setFilterViewModel(viewModel);
        viewModel.setDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.BOTTOM;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context);
        attributes.height = DisplayUtil.getScreenContentHeight(context) - DisplayUtil.dp2px(context, 44);
        attributes.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dialog1 -> viewModel.setDialog(null));
        dialog.show();
    }

    public static void changeLeverage(Context context, TradeViewModel viewModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogChangeLeverageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_change_leverage, null, false);
        binding.setTradeViewModel(viewModel);
        viewModel.setLeverageDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context);
        attributes.height = DisplayUtil.getScreenContentHeight(context) - DisplayUtil.dp2px(context, 44);
        attributes.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dialog1 -> viewModel.setLeverageDialog(null));
        dialog.show();
    }

    public static void showWallPayDialog(Context context, WalletModel walletModel) {
        Dialog dialog = new Dialog(context, R.style.DialogFullScreen);
        DialogLoadpayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_loadpay, null, false);
        binding.setWalletModel(walletModel);
        walletModel.setPayDialog(dialog);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        dialog.setContentView(binding.getRoot(), layoutParams);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = DisplayUtil.getScreenContentWidth(context)/2 + DisplayUtil.dp2px(context, 80);
        attributes.height = DisplayUtil.getScreenContentHeight(context) - DisplayUtil.dp2px(context, 44);
        attributes.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnDismissListener(dialog1 -> walletModel.setPayDialog(null));
        dialog.show();
    }
}
