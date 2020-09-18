package com.temp.buda.country;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.common.framework.basic.BaseActivity;
import com.exchange.utilslib.LogUtil;
import com.temp.buda.BR;
import com.temp.buda.R;
import com.temp.buda.databinding.ActivityCountryBinding;
import com.temp.buda.databinding.ItemCountryInfoBinding;

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

    @Override
    public void initView() {

        countryNameList = getResources().getStringArray(R.array.country_name);
        countryCodeList = getResources().getStringArray(R.array.country_code);

        binding.recyclerViewCountryList.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCountryList.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemCountryInfoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_country_info, null, false);
                binding.setCountryViewModel(viewModel);
                return new ViewHolder(binding);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.binding.setCountryName(countryNameList[position]);
                holder.binding.setCountryCode(countryCodeList[position]);
            }

            @Override
            public int getItemCount() {
                if (null != countryNameList && countryNameList.length == countryCodeList.length) {
                    return countryNameList.length;
                } else {
                    LogUtil.e("国家名称和国家编码不匹配");
                    return 0;
                }
            }
        });

    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemCountryInfoBinding binding;

        ViewHolder(ItemCountryInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
