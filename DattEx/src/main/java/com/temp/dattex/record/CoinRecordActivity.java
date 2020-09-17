package com.temp.dattex.record;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.common.framework.basic.BaseActivity;
import com.temp.dattex.BR;
import com.temp.dattex.Constants;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityCoinRecordBinding;
import com.temp.dattex.util.Utils;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.record
 * @FileName     : CoinRecordActivity.java
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
public class CoinRecordActivity extends BaseActivity<ActivityCoinRecordBinding, CoinRecordViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_coin_record;
    }

    @Override
    public void initParam() {
        super.initParam();
        String stringExtra = getIntent().getStringExtra(Constants.KEY_COIN_NAME);
        int inorout = getIntent().getIntExtra(Constants.REQUEST_KEY_INOROUT,0);
        if (!TextUtils.isEmpty(stringExtra)) {
            viewModel.setCoinName(stringExtra);
            viewModel.setInorout(inorout);
        } else {
            finish();
        }
    }

    @Override
    public int initVariableId() {
        return BR.coinRecordViewModel;
    }

    @Override
    public void initViewObservable() {
    }
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void initView() {
        super.initView();
        View emptyView = LayoutInflater
                .from(this)
                .inflate(R.layout.order_empty_layout, null);
        TextView tvEmptyName = (TextView)emptyView.findViewById(R.id.tv_empty_name);
        tvEmptyName.setText("暂无记录");
        viewModel.madapter.setUseEmpty(true);
        viewModel.madapter.setEmptyView(emptyView);

        if (viewModel.getInorout()==0){
            binding.tvTitle.setTitleText("充币记录");
        }else {
            binding.tvTitle.setTitleText("提币记录");
        }

    }
}
