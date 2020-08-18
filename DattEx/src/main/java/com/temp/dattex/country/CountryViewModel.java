package com.temp.dattex.country;

import android.app.Application;

import androidx.annotation.NonNull;

import com.common.framework.basic.BaseViewModel;
import com.common.framework.click.SingleClick;
import com.temp.dattex.Constants;

import java.util.HashMap;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.country
 * @FileName     : CountryViewModel.java
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

public class CountryViewModel extends BaseViewModel {

    public CountryViewModel(@NonNull Application application) {
        super(application);
    }


    @SingleClick
    public void chooseCountryItem(String countryName, String countryCode) {
        HashMap<String, String> params = new HashMap<>();
        params.put(Constants.KEY_COUNTRY_CODE, countryCode);
        params.put(Constants.KEY_COUNTRY_NAME, countryName);
        getUC().getSetResultEvent().postValue(params);
        finish();
    }
}
