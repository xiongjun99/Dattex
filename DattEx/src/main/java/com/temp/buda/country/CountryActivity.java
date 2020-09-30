package com.temp.buda.country;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LogUtil;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.adapter.ApplyAdapter;
import com.temp.buda.adapter.CountryAdapter;
import com.temp.buda.bean.CountryBean;
import com.temp.buda.databinding.ActivityCountryBinding;
import com.temp.buda.databinding.ItemCountryInfoBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.country
 * @FileName     : CountryActivity.java
 * @Author       : chao
 * @Date         : 2020/5/14
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
public class CountryActivity extends BaseActivity<ActivityCountryBinding, CountryViewModel> {

    private String[] countryNameList;
    private String[] countryCodeList;
    private EditText et_Search;
    private List<CountryBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_country;
    }

    @Override
    public int initVariableId() {
        return BR.countryViewModel;
    }

    @Override
    public void initViewObservable() {

    }
    public void initData(){
        if (list!=null&&list.size()>0){
            list.clear();
        }
        countryNameList = getResources().getStringArray(R.array.country_name);
        countryCodeList = getResources().getStringArray(R.array.country_code);
        for (int i = 0; i < countryNameList.length; i++) {
            CountryBean  data = new CountryBean();
            data.setCountryCode(countryCodeList[i]);
            data.setCountryName(countryNameList[i]);
            list.add(data);
        }
        adapter.setNewData(list);
   }

    @SuppressLint("NewApi")
    @Override
    public void initView() {
        et_Search = (EditText)findViewById(R.id.et_search);
        et_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               if (TextUtils.isEmpty(s)){
                   initData();
               }
            }
        });
        recyclerView = findViewById(R.id.recyclerViewCountryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CountryAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            viewModel.chooseCountryItem(adapter.getData().get(position).getCountryName(),adapter.getData().get(position).getCountryCode());
        });
        et_Search.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(et_Search.getText().toString())){
                 List<CountryBean> newlist = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).getCountryName();
                    int result = str.indexOf(et_Search.getText().toString());
                    if(result != -1){
                        newlist.add(list.get(i));
                    }
                }
                adapter.setNewData(newlist);
            }
        });
        initData();
    }
}
