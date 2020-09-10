package com.temp.dattex.wallet;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.WalletGridAdapter;
import com.temp.dattex.databinding.ActivityWalletBinding;
import com.temp.dattex.widget.EditPop;
import com.temp.dattex.widget.FleXoPopWindow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.splash
 * @FileName     : SplashActivity.java
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
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class WalletActivity extends BaseActivity<ActivityWalletBinding, WalletModel> {
    private FleXoPopWindow fleXoPopWindow;
    private EditPop editPop,pay_Pop;
    private RadioButton rbBuy,rbWallet;
    private EditText tvWithdrawAddress;
    private RecyclerView recyclerView;
    private WalletGridAdapter walletGridAdapter;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_wallet;
    }

    @Override
    public int initVariableId() {
        return BR.walletModel;
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.pop.observe(this, isShow -> {
            viewModel.popStaus.set(0);
            LogUtil.e(isShow);
            createPop();
            editPop.popState(isShow);
        });
        viewModel.pay_uc.pay_pop.observe(this, isShow -> {
            viewModel.popStaus.set(1);
            LogUtil.e(isShow);
            createPayPop();
            pay_Pop.popState(isShow);
            walletGridAdapter.setNewData(viewModel.otc.get().getPayTypes().get(viewModel.pPosition.get()).getInDefault());
        });
        viewModel.otc.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                walletGridAdapter.setNewData(viewModel.otc.get().getPayTypes().get(0).getInDefault());
            }
        });
    }
    public void createPop() {
        if (editPop == null) {
            List<String> listData = new ArrayList<>();
            for (int i = 0; i < viewModel.otc.get().getOtcCfgs().size(); i++) {
                listData.add(viewModel.otc.get().getOtcCfgs().get(i).getCurrency());
            }
            editPop = new EditPop(this,viewModel.pPosition.get());
            editPop.setAdapterData(listData, binding.line);
            editPop.setOnItemClickListener(viewModel);
            editPop.setOnDismissListener(viewModel);
        }
    }
    public void createPayPop() {
        if (pay_Pop == null) {
            List<String> list = new ArrayList<>();
            if (viewModel.otc.get()!=null&&viewModel.otc.get().getPayTypes().size()>0){
                for (int i = 0; i < viewModel.otc.get().getPayTypes().size(); i++) {
                    list.add(viewModel.otc.get().getPayTypes().get(i).getName());
                }
            }
            pay_Pop = new EditPop(this,viewModel.pPosition.get());
            pay_Pop.setAdapterData(list, binding.pay);
            pay_Pop.setOnItemClickListener(viewModel);
            pay_Pop.setOnDismissListener(viewModel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rbBuy = (RadioButton) findViewById(R.id.rb_buy);
        rbWallet = (RadioButton) findViewById(R.id.rb_wallet);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        walletGridAdapter = new WalletGridAdapter(null);
        recyclerView.setAdapter(walletGridAdapter);
        walletGridAdapter.setOnItemClickListener((adapter, view, position) ->
                        viewModel.changeBalance(walletGridAdapter.getData().get(position))
               );
        rbBuy.setOnClickListener(view -> {
            rbBuy.setChecked(true);
            rbWallet.setChecked(false);
        });
        rbWallet.setOnClickListener(view -> {
            rbBuy.setChecked(false);
            rbWallet.setChecked(true);
        });
    }
}
