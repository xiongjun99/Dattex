package com.temp.dattex.auth;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.Observable;

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

    private EditPop editPop;

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
        viewModel.identityType.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                viewModel.onDismiss();
            }
        });
    }


    public void createPop() {
        if (editPop == null) {
            editPop = new EditPop(this,viewModel.pPosition.get());
            editPop.setAdapterData(viewModel.mlist.get(), binding.line);
            editPop.setOnItemClickListener(viewModel);
            editPop.setOnDismissListener(viewModel);
        }
    }
}
