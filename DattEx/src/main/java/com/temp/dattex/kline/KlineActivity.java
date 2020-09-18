package com.temp.dattex.kline;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.databinding.library.baseAdapters.BR;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LogUtil;
import com.icechao.klinelib.utils.Status;
import com.icechao.klinelib.view.KLineChartView;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityKlineBinding;
import com.temp.dattex.home.HomeActivity;
import com.temp.dattex.widget.EditPop;

import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.kline
 * @FileName     : KlineActivity.java
 * @Author       : chao
 * @Date         : 2020/5/15
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
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class KlineActivity extends BaseActivity<ActivityKlineBinding, KlineViewModel> {
    private String Symbol;
    private EditPop editPop;
    private KLineChartView kLineChartView;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_kline;
    }

    @Override
    public int initVariableId() {
        return BR.klineViewModel;
    }

    @Override
    public void initViewObservable() {
//      binding.recyclerViewKlineBottomList
        viewModel.uc.pop.observe(this, isShow -> {
            LogUtil.e(isShow);
            createPop();
            editPop.popState(isShow);
        });
        viewModel.getPopType().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.getPopType().get()!=null){
                    if (viewModel.getPopType().get().equals("4")){
                        kLineChartView.setKlineState(Status.KlineStatus.TIME_LINE);
                        if (editPop !=null){
                            editPop.dismiss();
                        }
                    } else {
                        kLineChartView.setKlineState(Status.KlineStatus.K_LINE);
                        if (editPop !=null){
                            editPop.dismiss();
                        }
                    }
                }
            }
        });
    }
    public void createPop() {
        if (editPop == null) {
            editPop = new EditPop(this,viewModel.pPosition.get());
            editPop.setAdapterData(viewModel.getListData().get(), binding.more);
            editPop.setOnItemClickListener(viewModel);
            editPop.setOnDismissListener(viewModel);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<String> list = new ArrayList<>();
        list.add("分时");
        list.add("30分");
        list.add("4小时");
        list.add("1周");
        viewModel.getListData().set(list);
    }

    @Override
    public void initView() {
        super.initView();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        binding.klineChartView.setOverScrollRange(width / 5 + 1);
        Symbol = getIntent().getStringExtra(Constants.REQUEST_KEY_COIN_ID);
        viewModel.getTitleText().set(Symbol);
        viewModel.getLeftCoin().set(Symbol.substring(0,Symbol.indexOf("/")));
        viewModel.getRightCoin().set(Symbol.substring(Symbol.indexOf("/")+1));
        RelativeLayout rlBuyMore =  this.findViewById(R.id.rl_buy_more);
        rlBuyMore.setOnClickListener(view -> {
            Intent it = new Intent(KlineActivity.this, HomeActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("many","0");
            it.putExtra("leftCoin",viewModel.getLeftCoin().get());
            it.putExtra("rightCoin",viewModel.getRightCoin().get());
            startActivity(it);
        });
        RelativeLayout rlShort =  this.findViewById(R.id.rl_short);
        rlShort.setOnClickListener(view -> {
            Intent it = new Intent(KlineActivity.this, HomeActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("many","1");
            it.putExtra("leftCoin",viewModel.getLeftCoin().get());
            it.putExtra("rightCoin",viewModel.getRightCoin().get());
            startActivity(it);
        });
        kLineChartView = (KLineChartView)findViewById(R.id.kline_chart_view);
        kLineChartView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //      kLineChartView.setKlineState(Status.KlineStatus.TIME_LINE);
    }
}
