package com.temp.dattex.config;

import android.annotation.SuppressLint;

import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.net.DataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.config
 * @FileName     : AssetsConfigs.java
 * @Author       : chao
 * @Date         : 2020/6/4
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
public class AssetsConfigs {

    private Map<String, AssetsBean.AssetsItemBean> assetsItemBeanMap = new HashMap<>();

    private static volatile AssetsConfigs assetsConfigs;

    private AssetsConfigs() {

    }

    public static AssetsConfigs getInstance() {
        if (null == assetsConfigs) {
            synchronized (AssetsConfigs.class) {
                if (null == assetsConfigs) {
                    assetsConfigs = new AssetsConfigs();
                }
            }
        }
        return assetsConfigs;
    }

    @SuppressLint("CheckResult")
    public void freshAssets() {
        DataService.getInstance().userAssets().compose(ResponseTransformer.<AssetsBean>handleResult()).subscribe(
                assetsBean -> {
                    //不想写这样判断的代码可是 服务器能搞出这样的奇葩问题我无奈
                   if(null != assetsBean){
                       List<AssetsBean.AssetsItemBean> assets = assetsBean.getAssets();
                       if(null != assets){
                           if (null != assets && assets.size() > 0) {
                               for (int i = 0; i < assets.size(); i++) {
                                   AssetsBean.AssetsItemBean assetsItemBean = assets.get(i);
                                  if(null !=assetsItemBean){
                                      assetsItemBeanMap.put(assetsItemBean.getCoinId(), assetsItemBean);
                                  }
                               }
                               this.cnyTotal = assetsBean.getCnyTotal();
                               this.usdtTotal = assetsBean.getUsdtTotal();
                           }
                       }
                   }
                }, t -> {

                }
        );
    }

    private String cnyTotal;
    private String usdtTotal;

    public String getCnyTotal() {
        return cnyTotal;
    }

    public AssetsBean.AssetsItemBean getCoinInfo(String s) {
        return assetsItemBeanMap.get(s);
    }
}
