package com.temp.dattex.withdraw;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.ToastUtil;
import com.temp.dattex.BR;
import com.temp.dattex.R;
import com.temp.dattex.adapter.ApplyAdapter;
import com.temp.dattex.adapter.DialogPayAdapter;
import com.temp.dattex.apply.ApplyActivity;
import com.temp.dattex.databinding.ActivityWithdrawBinding;
import com.temp.dattex.util.Utils;
import com.yzq.zxinglibrary.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.withdraw
 * @FileName     : WithdrawActivity.java
 * @Author       : chao
 * @Date         : 2020/5/19
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
public class WithdrawActivity extends BaseActivity<ActivityWithdrawBinding, WithdrawViewModel> {
    private EditText et_address;
    private RadioButton rbUsdt,rbAdress;
    private LinearLayout ll_address,ll_sell_usdt,ll_select_address,ll_exchangeType;
    private RelativeLayout rlCollectionCard;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_withdraw;
    }

    @Override
    public int initVariableId() {
        return BR.withdrawViewModel;
    }

    public int CAMERA_REQ_CODE = 0x1110;

    @Override
    public void initViewObservable() {
        viewModel.scanLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                requestPermission(CAMERA_REQ_CODE);
            }
        });
    }

    private void requestPermission(int requestCode) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                    requestCode);
        } else {
            //扫码
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, CAMERA_REQ_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (permissions == null || grantResults == null) {
            return;
        }
        if (grantResults.length < 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (requestCode == CAMERA_REQ_CODE) {
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, CAMERA_REQ_CODE);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        et_address = (EditText)findViewById(R.id.et_address);
        ll_address = (LinearLayout)findViewById(R.id.ll_address);
        ll_sell_usdt = (LinearLayout)findViewById(R.id.ll_sell_usdt);
        ll_select_address = (LinearLayout)findViewById(R.id.ll_select_address);
        ll_exchangeType = (LinearLayout)findViewById(R.id.ll_exchangeType);
        ll_select_address.setOnClickListener(view -> {
            Intent intent = new Intent(WithdrawActivity.this,WithdrawListActivity.class);
            startActivityForResult(intent,1);
        });
        rbUsdt = (RadioButton) findViewById(R.id.rb_usdt);
        rbAdress = (RadioButton) findViewById(R.id.rb_address);
        rbUsdt.setOnClickListener(view -> {
            ll_address.setVisibility(View.GONE);
            ll_sell_usdt.setVisibility(View.VISIBLE);
        });
        rbAdress.setOnClickListener(view -> {
            ll_address.setVisibility(View.VISIBLE);
            ll_sell_usdt.setVisibility(View.GONE);
        });
        rlCollectionCard = (RelativeLayout)findViewById(R.id.rl_collection_card);
        rlCollectionCard.setOnClickListener(view -> {
          Intent it = new Intent(this,NewPayTypeActivity.class);
          startActivity(it);
        });
        ll_exchangeType.setOnClickListener(view -> {
            showPayDialog();
        });
//        DataService.getInstance().checkWithdraw().compose(ResponseTransformer.<Boolean>handleResult()).subscribe(b -> {
//            if (!b) {
//                finish();
//            }
//        }, t -> {
//            ToastUtil.show(BaseApplication.getInstance(), t.getMessage());
//            finish();
//        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
           if (data !=null&&data.getStringExtra("str")!=null){
            viewModel.WithdrawAddress(data.getStringExtra("str")+"\n"+data.getStringExtra("str1"));
            et_address.setTextSize(12);
           }
           break;
        }
    }
    public  void showPayDialog() {
         List<String> list = new ArrayList<>();
         for (int i = 0; i < viewModel.otc.get().size(); i++) {
             list.add(viewModel.otc.get().get(i).getCurrency());
        }
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_withdrawpay,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        ImageView ivCancel = view .findViewById(R.id.iv_cancel);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_country_list_item_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DialogPayAdapter applyAdapter = new DialogPayAdapter(list);
        recyclerView.setAdapter(applyAdapter);
        applyAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if (list!=null) {
                if (!viewModel.getExchangeType().get().equals(list.get(position))){
                    viewModel.getAccountPrice().set("");
                    viewModel.getNumber().set("");
                } else {
                    viewModel.getExchangeType().set(String.valueOf(viewModel.otc.get().get(position).getCurrency()));
                    viewModel.getPrice().set(String.valueOf(viewModel.otc.get().get(position).getSellRatio()));

                }
            }
            dialog.dismiss();
        });
        ivCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog); // 添加动画
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
