package com.temp.dattex.order;

import android.app.Application;
import android.app.Dialog;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.CoverDialogViewModel;
import com.temp.dattex.util.DialogUtil;

/**
 * @Package: com.temp.dattex.order
 * @ClassName: OrderItemViewModel
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/18 21:47
 * @Email: 86152
 */
public class OrderItemViewModel extends BaseViewModel {
    private ObservableField<String> amount = new ObservableField("");
    private ObservableField<Boolean> show = new ObservableField();
    private ObservableField<String> contractType = new ObservableField();
    private ObservableField<Boolean> orderShow = new ObservableField();

    public ObservableField<Boolean> getOrderShow() {
        return orderShow;
    }

    public void setOrderShow(ObservableField<Boolean> orderShow) {
        this.orderShow = orderShow;
    }


    public ObservableField<String> getContractType() {
        return contractType;
    }

    public void setContractType(ObservableField<String> contractType) {
        this.contractType = contractType;
    }



    public ObservableField<Boolean> getShow() {
        return show;
    }

    public void setShow(ObservableField<Boolean> show) {
        this.show = show;
    }

    public ObservableField<String> getAmount() {
        return amount;
    }

    public void setAmount(ObservableField<String> amount) {
        this.amount = amount;
    }

    public OrderItemViewModel(@NonNull Application application) {
        super(application);
    }
    //平仓
    @SingleClick
    public void cover(long orderId) {
        CoverDialogViewModel viewModel = new CoverDialogViewModel(getApplication());
        viewModel.setOrderId(orderId);
        DialogUtil.showCoverDialog(AppManager.getActivityStack().peek(), viewModel);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @SingleClick
    public void set() {
        OrderItemViewModel orderItemViewModel = new OrderItemViewModel(getApplication());
        orderItemViewModel.setPrice(amount.get());
        orderItemViewModel.setPercent(upStopPercent.get(),downStopPercent.get());
        orderItemViewModel.getDirection().set(direction.get());
        orderItemViewModel.getSymbol().set(symbol.get());
        orderItemViewModel.getId().set(id.get());
        orderItemViewModel.getLever().set(lever.get());
        DialogUtil.createContractDialog2(AppManager.getActivityStack().peek(), orderItemViewModel);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    private ObservableField<String> upStopPrice = new ObservableField("");
    private ObservableField<String> downStopPrice = new ObservableField("");
    public ObservableField<String> upStopPercent = new ObservableField("0");
    public ObservableField<String> downStopPercent = new ObservableField("0");
    private ObservableField<String> lever = new ObservableField("");
    private ObservableField<String> id = new ObservableField("");
    private ObservableField<String> symbol = new ObservableField("");
    private ObservableField<String> direction = new ObservableField("");
    private Dialog dialog;

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public ObservableField<String> getUpStopPrice() {
        return upStopPrice;
    }

    public void setUpStopPrice(ObservableField<String> upStopPrice) {
        this.upStopPrice = upStopPrice;
    }

    public ObservableField<String> getDownStopPrice() {
        return downStopPrice;
    }

    public void setDownStopPrice(ObservableField<String> downStopPrice) {
        this.downStopPrice = downStopPrice;
    }

    public ObservableField<String> getUpStopPercent() {
        return upStopPercent;
    }

    public void setUpStopPercent(ObservableField<String> upStopPercent) {
        this.upStopPercent = upStopPercent;
    }

    public ObservableField<String> getDownStopPercent() {
        return downStopPercent;
    }

    public void setDownStopPercent(ObservableField<String> downStopPercent) {
        this.downStopPercent = downStopPercent;
    }

    public ObservableField<String> getLever() {
        return lever;
    }

    public void setLever(ObservableField<String> lever) {
        this.lever = lever;
    }

    public ObservableField<String> getId() {
        return id;
    }

    public void setId(ObservableField<String> id) {
        this.id = id;
    }

    public ObservableField<String> getSymbol() {
        return symbol;
    }

    public void setSymbol(ObservableField<String> symbol) {
        this.symbol = symbol;
    }

    public ObservableField<String> getDirection() {
        return direction;
    }

    public void setDirection(ObservableField<String> direction) {
        this.direction = direction;
    }

    @SingleClick
    public void closeDialog() {
        if (null != getDialog()) {
            getDialog().dismiss();
            setDialog(null);
        }
    }

    @SingleClick
    //设置止盈止损 /app/exchange/setProfitLossRate
    public void ensureOrder() {
            float stopLossRate = Float.parseFloat(downStopPercent.get()) / 100;
            float stopProfitRate = Float.parseFloat(upStopPercent.get()) / 100;
        DataService.getInstance().getProfitLossRate(direction.get(),id.get(),lever.get(),amount.get(),String.valueOf(stopLossRate),String.valueOf(stopProfitRate),symbol.get()).compose(ResponseTransformer.handleResult())
                .subscribe(data->{
                    ToastUtil.show(getApplication(),"设置成功...");
                },t->{
                    ToastUtil.show(getApplication(),"设置失败...");
                });
            if (null != getDialog()) {
                getDialog().dismiss();
                setDialog(null);
            }
    }
    @SingleClick
    public void downStopPercentPlus() {
        String text = downStopPercent.get();
        int i = Integer.parseInt(text) + 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        downStopPrice.set(String.valueOf((100 - i) / 100f * price));
        downStopPercent.set(String.valueOf(i));
    }
    @SingleClick
    public void downStopPercentSub() {
        String text = downStopPercent.get();
        int i = Integer.parseInt(text) - 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        downStopPrice.set(String.valueOf((100 - i) / 100f * price));
        downStopPercent.set(String.valueOf(i));
    }
    @SingleClick
    public void upStopPercentPlus() {
        String text = upStopPercent.get();
        int i = Integer.parseInt(text) + 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        upStopPrice.set(String.valueOf((100 + i) / 100f * price));
        upStopPercent.set(String.valueOf(i));
    }
    @SingleClick
    public void upStopPercentSub() {
        String text = upStopPercent.get();
        int i = Integer.parseInt(text) - 10;
        if (i < 10) {
            i = 10;
        } else if (i > 100) {
            i = 100;
        }
        upStopPrice.set(String.valueOf((100 + i) / 100f * price));
        upStopPercent.set(String.valueOf(i));
    }
    public void setPrice(String s) {
        upStopPrice.set(s);
        downStopPrice.set(s);
        this.price = Float.parseFloat(s);
    }
    public void setPercent(String s,String s1) {
        upStopPercent.set(s);
        downStopPercent.set(s1);
    }

    private float price;

}
