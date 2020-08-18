package com.temp.dattex.auth;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LogUtil;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.databinding.ActivityAuthBinding;
import com.temp.dattex.widget.EditPop;
import com.temp.dattex.widget.FleXoPopWindow;

import java.util.ArrayList;
import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthViewModel> {

    List<String> listData = new ArrayList<>();

    private EditPop editPop;
    private FleXoPopWindow fleXoPopWindow;


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auth;
    }

    @Override
    public int initVariableId() {
        return BR.authViewModle;
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.pop.observe(this, isShow -> {
            LogUtil.e(isShow);
            createPop();
            editPop.popState(isShow);
        });
    }


    public void createPop() {
        if (editPop == null) {
            listData.add("身份证");
            listData.add("驾照");
            listData.add("护照");
            editPop = new EditPop(this,viewModel.pPosition.get());
            editPop.setAdapterData(listData, binding.line);
            editPop.setOnItemClickListener(viewModel);
            editPop.setOnDismissListener(viewModel);
        }
    }
}
