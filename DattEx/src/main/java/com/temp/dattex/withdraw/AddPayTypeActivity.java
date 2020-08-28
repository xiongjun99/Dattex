package com.temp.dattex.withdraw;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.DialogPayAdapter;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;
import java.util.ArrayList;
import java.util.List;

public class AddPayTypeActivity extends BaseActivity {
    private List<String> list = new ArrayList<>();
    private TextView tvPay;
    private String exchangeType,price;
    private List<OTCcfgBean> otc = new ArrayList<>();
    private LinearLayout llBankCardPay,llOther;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpaytype);
        initData();
        initView();
    }

    private void initView() {
        llBankCardPay = (LinearLayout)findViewById(R.id.ll_bankpay);
        llOther = (LinearLayout)findViewById(R.id.ll_otherpay);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.line);
        linearLayout.setOnClickListener(view -> {
            showPayDialog();
        });
        tvPay = (TextView)findViewById(R.id.tv_pay);
    }
    private void initData() {
        getPayTypeData();
    }


    public  void showPayDialog() {
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
                tvPay.setText(list.get(position));
                if (list.get(position).contains("银行卡")){
                    llBankCardPay.setVisibility(View.VISIBLE);
                    llOther.setVisibility(View.GONE);
                }else {
                    llBankCardPay.setVisibility(View.GONE);
                    llOther.setVisibility(View.VISIBLE);
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
    private void getPayTypeData() {
        DataService.getInstance().getPayType().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null && b.size() != 0){
                        for (int i = 0; i < b.size(); i++) {
                            list.add(b.get(i).getName().getZh());
                            tvPay.setText(list.get(0));
                        }
                    }else {
                        ToastUtil.show(getApplication(),"获取支付方式配置失败");
                    }
                }, t -> {
                });
    }
}
