package com.temp.dattex.apply;

import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.ApplyAdapter;
import com.temp.dattex.bean.NewApplyBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.util.Utils;
import com.temp.dattex.widget.TitleBar;
import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends BaseActivity{
    private RecyclerView recyclerView;
    private ApplyAdapter applyAdapter;
    private List<NewApplyBean.RowsBean> list = new ArrayList();
    private TitleBar titleBar;
    private int id;
    private int UnitPrice,ApplyNumber,TotalPrice;
    int page = 0;
    private TextView tvApplyCode;
    private EditText etApplyCode,etApplyNumber;
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_apply);
        initView();
        getData();
    }

    private void getData() {
        DataService.getInstance().getFindByPageApplyCoin(page).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    list = b.getRows();
                    applyAdapter.setNewData(list);
                }, t -> {
                    ToastUtil.show(getApplicationContext(),"获取申购信息失败..."+t.getMessage());
                });
    }

    private void initView() {
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftTwoClick(this);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        applyAdapter = new ApplyAdapter(list);
        recyclerView.setAdapter(applyAdapter);
        applyAdapter.setOnItemClickListener((adapter, view, position) -> {
            if ("未中签".equals(list.get(position).getApplyTypeName())||"可申购".equals(list.get(position).getApplyTypeName())){
                id = list.get(position).getId();
                UnitPrice = list.get(position).getUnitPrice();
                ApplyNumber = list.get(position).getQty();
                showApplyDialog();
            }
        });
    }
    public  void showApplyDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_apply,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this,R.style.DialogFullScreen).setView(view).create();
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);

        TextView tvUnitPrice = view.findViewById(R.id.tv_unitprice);
        tvUnitPrice.setText(String.valueOf(UnitPrice));
        etApplyNumber = view.findViewById(R.id.et_apply_number);
        TextView tvTotalPrice = view.findViewById(R.id.tv_total_price);
        etApplyNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            if (editable.length()>0){
                tvTotalPrice.setText(""+Integer.valueOf(editable.toString())*UnitPrice);
           }
        }
      });
        etApplyCode = view.findViewById(R.id.et_apply_code);
        tvApplyCode = view.findViewById(R.id.tv_apply_code);
        tvApplyCode.setOnClickListener(v -> {
            sendPhoneCode();
        });
        tvCancel.setOnClickListener(v -> {
            //... To-do
            dialog.dismiss();
        });

        tvConfirm.setOnClickListener(v -> {
            //... To-do
            if (Integer.valueOf(etApplyNumber.getText().toString()) > ApplyNumber){
                ToastUtil.show(ApplyActivity.this,"申购数量大于最大申购数量,请重新输入...");
            } else {
                applyCoin();
            }
            dialog.dismiss();
        });
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout((Utils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    public void sendPhoneCode() {
        try {
            final int i = Integer.parseInt(tvApplyCode.getText().toString());
            if (i == 1) {
                tvApplyCode.setText(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                tvApplyCode.setText(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            }
        } catch (Exception e) {
            tvApplyCode.setText("60");
            LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            DataService.getInstance().sendCodeMessage("06").compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
              ToastUtil.show(this,t.getMessage());
            });
        }

    }
    public void applyCoin() {
        DataService.getInstance().getApplyCoin(id,Integer.valueOf(etApplyNumber.getText().toString()),etApplyCode.getText().toString()).compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            ToastUtil.show(this,"申购成功,请在订单中查看申购信息...");
            finish();
        }, t -> {
            ToastUtil.show(this,t.getMessage());
        });
    }
}
