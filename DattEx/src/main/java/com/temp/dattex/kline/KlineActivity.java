package com.temp.dattex.kline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.databinding.library.baseAdapters.BR;

import com.common.framework.basic.BaseActivity;
import com.google.zxing.common.StringUtils;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityKlineBinding;
import com.temp.dattex.home.HomeActivity;

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
public class KlineActivity extends BaseActivity<ActivityKlineBinding, KlineViewModel> {
   private String Symbol;
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
//  binding.recyclerViewKlineBottomList
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

    }
}
