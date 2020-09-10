package com.temp.dattex.withdraw;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exchange.utilslib.LooperUtil;
import com.exchange.utilslib.ToastUtil;
import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.BaseActivity;
import com.temp.dattex.R;
import com.temp.dattex.adapter.DialogPayAdapter;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.net.DataService;
import com.temp.dattex.widget.EditPop;
import com.temp.dattex.widget.TitleBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AddPayTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    private List<String> list = new ArrayList<>();
    private TextView tvPay;
    private String exchangeType,price;
    private List<OTCcfgBean> otc = new ArrayList<>();
    private LinearLayout llBankCardPay,llOther,llProvince,llCity;
    private TextView tvPayInfo,tvGetCode;
    private List<String> provinceList = new ArrayList<>();
    private List<String> cityList = new ArrayList<>();
    private TextView tvProvince , tvCity;
    private TitleBar title_bar;
    private EditText etBankname,etSurName,etName,etCardNumber,etBranchBank,etBankGetCode,etNobankAccount;
    private int payType;
    private RelativeLayout rlConfirm;
    private EditPop popwindow;
    private int pos = 0;
    private List<String> listData;
    private boolean isShow = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpaytype);
        initData();
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        title_bar = (TitleBar)findViewById(R.id.title_bar);
        tvProvince = (TextView)findViewById(R.id.tv_province);
        tvCity = (TextView)findViewById(R.id.tv_city);
        tvPayInfo = (TextView)findViewById(R.id.tv_payinfo);
        tvGetCode = (TextView)findViewById(R.id.tv_get_code);
        etBankname = (EditText) findViewById(R.id.et_bankname);
        etSurName = (EditText) findViewById(R.id.et_surname);
        etNobankAccount = (EditText) findViewById(R.id.et_nobank_account);
        etName = (EditText) findViewById(R.id.et_name);
        etBankGetCode = (EditText) findViewById(R.id.et_bank_get_code);
        etCardNumber = (EditText) findViewById(R.id.et_cardnumber);
        etBranchBank = (EditText) findViewById(R.id.et_branchbank);
        rlConfirm = (RelativeLayout) findViewById(R.id.rl_confirm);
        llBankCardPay = (LinearLayout)findViewById(R.id.ll_bankpay);
        llOther = (LinearLayout)findViewById(R.id.ll_otherpay);
        llProvince = (LinearLayout)findViewById(R.id.ll_province);
        llCity = (LinearLayout)findViewById(R.id.ll_city);
        title_bar.setLeftTwoClick(this);
        rlConfirm.setOnClickListener(view -> {
            if (isSubmit()){
                addMemberPayType(payType);
            }
        });
        tvGetCode.setOnClickListener(view -> {
            sendPhoneCode();
        });
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.line);
        linearLayout.setOnClickListener(view -> {
            showPayDialog();
        });
        tvPay = (TextView)findViewById(R.id.tv_pay);

        llProvince.setOnClickListener(view -> {
            getAddressData("");
            shoAddressDialog(0,provinceList);
        });
        llCity.setOnClickListener(view -> {
            if (tvProvince.getText().length()>0&&!tvProvince.getText().equals("请选择")){
                getAddressData(tvProvince.getText().toString());
                shoAddressDialog(1,cityList);
            }
        });
        etBankname.setOnClickListener(view -> {
            createPop();
            popwindow.popState(isShow);
        });
    }
    public void createPop() {
        if (popwindow == null) {
            listData = new ArrayList<>();
            listData.add("中国银行");
            listData.add("中国工商银行");
            listData.add("中国农业银行");
            listData.add("中国邮政储蓄银行");
            listData.add("中国建设银行");
            listData.add("交通银行");
            listData.add("招商银行");
            listData.add("中信银行");
            listData.add("平安银行");
            listData.add("光大银行");
            listData.add("浦发银行");

            popwindow = new EditPop(this,pos);
            popwindow.setAdapterData(listData,etBankname);
            popwindow.setOnItemClickListener(this);
            popwindow.setOnDismissListener(this);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        etBankname.setText(listData.get(i));
        popwindow.dismiss();
    }
    private void initData() {
        getPayTypeData();
    }

    private boolean isSubmit(){
        if (TextUtils.isEmpty(etSurName.getText().toString())){
            ToastUtil.show(this,"请输入姓");
            return false;
        }
        if (TextUtils.isEmpty(etName.getText().toString())){
            ToastUtil.show(this,"请输入名");
            return false;
        }
        if (TextUtils.isEmpty(etBankGetCode.getText().toString())){
            ToastUtil.show(this,"请输入验证码");
            return false;
        }
        if (payType ==1){
            if (tvProvince.getText().equals("请选择")){
                ToastUtil.show(this,"请选择省");
                return false;
            }
            if (tvCity.getText().equals("请选择")){
                ToastUtil.show(this,"请选择市");
                return false;
            }
            if (TextUtils.isEmpty(etBankname.getText().toString())){
                ToastUtil.show(this,"请输入开户银行");
                return false;
            }
            if (TextUtils.isEmpty(etCardNumber.getText().toString())){
                ToastUtil.show(this,"请输入银行卡号");
                return false;
            }
            if (TextUtils.isEmpty(etBranchBank.getText().toString())){
                ToastUtil.show(this,"请输入开户支行名称");
                return false;
            }
        } else {
            if (TextUtils.isEmpty(etNobankAccount.getText().toString())){
                ToastUtil.show(this,"请输入支付账号");
                return false;
            }
        }
        return true;
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
                    payType =1;
                    llBankCardPay.setVisibility(View.VISIBLE);
                    llOther.setVisibility(View.GONE);
                    tvPayInfo.setText("请确认"+list.get(position)+"与姓名一致");
                } else if (list.get(position).contains("支付宝")){
                    payType =2;
                    llBankCardPay.setVisibility(View.GONE);
                    llOther.setVisibility(View.VISIBLE);
                    tvPayInfo.setText("请确认"+list.get(position)+"与姓名一致");
                }else if (list.get(position).contains("微信")){
                    payType =3;
                    llBankCardPay.setVisibility(View.GONE);
                    llOther.setVisibility(View.VISIBLE);
                    tvPayInfo.setText("请确认"+list.get(position)+"与姓名一致");
                }
            }
            dialog.dismiss();
        });
        ivCancel.setOnClickListener(view1 -> {
        dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawableResource(R.color.color_282C42);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog); // 添加动画
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        WindowManager.LayoutParams params =
//                dialog.getWindow().getAttributes();
//        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
//        params.height = 800 ;
//        dialog.getWindow().setAttributes(params);
    }
    private void getPayTypeData() {
        DataService.getInstance().getPayType().compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    if(b!=null && b.size() != 0){
                        for (int i = 0; i < b.size(); i++) {
                            payType =1;
                            list.add(b.get(i).getName().getZh());
                            tvPay.setText(list.get(0));
                            tvPayInfo.setText("只能添加与该姓名一致的"+list.get(0));
                        }
                    }else {
                        ToastUtil.show(getApplication(),"获取支付方式配置失败");
                    }
                }, t -> {
                    ToastUtil.show(getApplication(),t.getMessage());
                });
    }
//    type (1银行 2支付宝 3微信 4数字钱包)
    public  void addMemberPayType( int type){
        String   account = "";
        if (type ==1 ){
            account = etCardNumber.getText().toString();
        }else {
            account = etNobankAccount.getText().toString();
        }
            DataService.getInstance().addMemberPayType("",etBankname.getText().toString(),tvProvince.getText().toString(),tvCity.getText().toString(),"0",etSurName.getText().toString(),etName.getText().toString(),etBranchBank.getText().toString(),account,type,etBankGetCode.getText().toString()).compose(ResponseTransformer.handleResult()).subscribe(
                b -> {
                    ToastUtil.show(this,"添加成功");
                    finish();
                }, t -> {
                        ToastUtil.show(this,t.getMessage());
                    });
    }
    public void sendPhoneCode() {
        try {
            final int i = Integer.parseInt(tvGetCode.getText().toString());
            if (i == 1) {
                tvGetCode.setText(getApplication().getResources().getString(R.string.text_send_code));
            } else {
                tvGetCode.setText(String.valueOf(i - 1));
                LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            }
        } catch (Exception e) {
            tvGetCode.setText("60");
            LooperUtil.getHandler().postDelayed(this::sendPhoneCode, 1000);
            DataService.getInstance().sendCodeMessage("05").compose(ResponseTransformer.<Object>handleResult()).subscribe(o -> {
            }, t -> {
                ToastUtil.show(this,t.getMessage());
            });
        }
    }
    private void getAddressData(String provinceName) {
     if (provinceList!=null&&provinceList.size()>0){
         provinceList.clear();
     }
        if (cityList!=null&&cityList.size()>0){
            cityList.clear();
        }
        try {
            JSONObject data = new JSONObject(getJson("city.json",this));
            JSONObject  jsonObject = data.getJSONObject("data");
            JSONArray jsonArray = jsonObject.getJSONArray("areas");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.optString("name");
                provinceList.add(name);
                JSONArray array = obj.getJSONArray("children");
                for (int j = 0; j < array.length(); j++) {
                    if (name.equals(provinceName)){
                        JSONObject childrenObj = array.getJSONObject(j);
                        String childreNname = childrenObj.optString("name");
                        cityList.add(childreNname);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static String getJson(String fileName, Context context) {
                //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
                    //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
                //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public  void shoAddressDialog(int type,List<String> mlist) {
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
        DialogPayAdapter applyAdapter = new DialogPayAdapter(mlist);
        recyclerView.setAdapter(applyAdapter);
        applyAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if (mlist!=null) {
              if (type==0){
                  tvProvince.setText(mlist.get(position));
              }else {
                  tvCity.setText(mlist.get(position));
              }
            }
            dialog.dismiss();
        });
        ivCancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawableResource(R.color.color_282C42);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog); // 添加动画
        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4  注意一定要在show方法调用后再写设置窗口大小的代码，否则不起效果会
//        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams params =
                dialog.getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = 800 ;
        dialog.getWindow().setAttributes(params);
    }

    @Override
    public void onDismiss() {

    }
}
