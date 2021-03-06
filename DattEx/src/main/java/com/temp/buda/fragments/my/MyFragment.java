package com.temp.buda.fragments.my;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.common.framework.basic.BaseFragment;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.database.LoginInfo;
import com.temp.buda.databinding.FragmentMyBinding;
import com.temp.buda.util.Utils;

/**
 * @Package: com.temp.dattex.my
 * @ClassName: MyFragment
 * @Description: java类作用描述
 * @Author: 李嘉伦
 * @CreateDate: 2020/5/16 18:52
 * @Email: 86152
 */
public class MyFragment extends BaseFragment<FragmentMyBinding, MyViewModel> {
    private LinearLayout llExit;
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_my;
    }

    @Override
    public int initVariableId() {
        return BR.myViewModel;
    }

    @Override
    public void stopLoad() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void lazyLoad() {

    }

    @Override
    public void initView() {
        llExit =  (LinearLayout) getActivity().findViewById(R.id.ll_exit);
        llExit.setOnClickListener(view -> {
            showIsExitDialog();
        });
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  void showIsExitDialog() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_isexit,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                if (LoginInfo.isSign()) {
                    viewModel.loginOut();
//                    LoginInfo.signOut();
//                    startActivity(LoginActivity.class);
                    llExit.setVisibility(View.GONE);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(getActivity())/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginInfo.isSign()) {
            viewModel.balance.set("0.0000000");
            llExit.setVisibility(View.VISIBLE);
        } else {
            llExit.setVisibility(View.GONE);
        }
    }
}
