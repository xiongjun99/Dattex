package com.temp.dattex.fragments.trade;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import com.common.framework.basic.AppManager;
import com.common.framework.basic.BaseFragment;
import com.exchange.utilslib.LogUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.bean.InfoBySymbolBean;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.config.SymbolConfigs;
import com.temp.dattex.databinding.FragmentTradeBinding;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.DialogUtil;
import com.temp.dattex.util.SwitchSymbolDialogViewModel;
import com.temp.dattex.widget.SeekbarWithIntervals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TradeFragment extends BaseFragment<FragmentTradeBinding, TradeViewModel> implements SwitchSymbolDialogViewModel.OnSymbolSet {
    private com.temp.dattex.widget.SeekbarWithIntervals SeekbarWithIntervals = null;
    private List<String> seekbarIntervals = new ArrayList<>();

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_trade;
    }

    @Override
    public int initVariableId() {
        return BR.tradeViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void lazyLoad() {
        LogUtil.d("lazyLoad");
    }


    @Override
    public void initView() {
        View emptyView = LayoutInflater
                .from(getActivity())
                .inflate(R.layout.order_empty_layout, null);
        viewModel.adapter.setUseEmpty(true);
        viewModel.adapter.setEmptyView(emptyView);
        initData();
        SeekbarWithIntervals = (SeekbarWithIntervals)getActivity(). findViewById(R.id.seekbarWithIntervals);
        String[] array = {"0","0"};
        List<String> list = Arrays.asList(array);
        SeekbarWithIntervals.setIntervals(list);
        LinearLayout llSwitchSymbol = getActivity().findViewById(R.id.ll_switchsymbol);
        llSwitchSymbol.setOnClickListener(view -> {
            SeekbarWithIntervals.setProgress(0);
            viewModel.getPositionPercent().set(0);
            viewModel.getTradeAmount().set("0");
            SwitchSymbolDialogViewModel switchSymbolDialogViewModel = new SwitchSymbolDialogViewModel();
            switchSymbolDialogViewModel.setOnSymbolSet(this);
            DialogUtil.showSwitchCoinDialog(AppManager.getActivityStack().peek(), switchSymbolDialogViewModel);
        });
//      getSeekbarWithIntervals().setIntervals(getIntervals());
        SeekbarWithIntervals.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    viewModel.getPositionPercent().set(Integer.valueOf(seekbarIntervals.get(seekBar.getProgress())));
                    viewModel.getTradeAmount().set(seekbarIntervals.get(seekBar.getProgress()));
                }
            });
        if (null!=getActivity().getIntent().getStringExtra("many")){
            if (getActivity().getIntent().getStringExtra("many").equals("0")){
                viewModel.tradeBuy.set(true);
            }else {
                viewModel.tradeBuy.set(false);
            }
        }
    }

   public void initData(){
       DataService.getInstance().getInfoBySymbol(viewModel.getLeftCoin().get() + "/" + viewModel.getRightCoin().get()).compose(ResponseTransformer.<InfoBySymbolBean>handleResult()).subscribe(
               o -> {
                   viewModel.leverageAdapter.addData(Arrays.asList(o.getExchangeLevers().replaceAll(" ","").split(",")));
                   seekbarIntervals = Arrays.asList(o.getExchangePrincipalPrice().replaceAll(" ","").split(","));
                   SeekbarWithIntervals.setIntervals(seekbarIntervals);
               }, t -> ToastUtil.show(getActivity(), t.getMessage())
       );
   }
    public void onSymbolSet(String coinSymbol, String baseSymbol) {
        viewModel.getLeftCoin().set(coinSymbol);
        viewModel.getRightCoin().set(baseSymbol);
        viewModel.tradeSymbolConfig = SymbolConfigs.getInstance().getSymbol(viewModel.getLeftCoin().get() + "/" + viewModel.getRightCoin().get());
        initData();
    }
    @Override
    public void initViewObservable() {
    }

//    private List<String> getIntervals() {
//        return new ArrayList<String>() {{
//            add("0");
//            add("5");
//        }};
//    }
//    private SeekbarWithIntervals getSeekbarWithIntervals() {
//        if (SeekbarWithIntervals == null) {
//            SeekbarWithIntervals = (SeekbarWithIntervals)getActivity(). findViewById(R.id.seekbarWithIntervals);
//        }
//        return SeekbarWithIntervals;
//    }
}
