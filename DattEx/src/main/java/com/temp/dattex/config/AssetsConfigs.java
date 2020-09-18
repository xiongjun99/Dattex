package com.temp.dattex.config;

import android.annotation.SuppressLint;

import com.independ.framework.response.ResponseTransformer;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.bean.CoinBean;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.database.LoginInfo;
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
    private Map<String, NewAssetsBean> newAssetsItemBeanMap = new HashMap<>();
    private Map<String, CoinBean> coinBeanHashMap = new HashMap<>();

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
    public void freshCoin() {
        DataService.getInstance().userCoin().compose(ResponseTransformer.<List<CoinBean>>handleResult()).subscribe(
                coinbean -> {
                    if(null != coinbean){
                        for (int i = 0; i < coinbean.size(); i++) {
                            CoinBean coinBean = coinbean.get(i);
                            System.out.println("------"+coinBean.getWithdrawFee());
                            if(null !=coinBean){
                                coinBeanHashMap.put(coinBean.getId(), coinBean);
                                if (LoginInfo.isSign()){
                                    freshAssetsByCoinId(coinBean.getId());
                                }
                            }
                        }
                    }
                }, t -> {
                    System.out.println("-------no----coin-list-币种配置列表");
                }
        );
    }
    @SuppressLint("CheckResult")
    public void freshAssetsByCoinId(String CoinId) {
        DataService.getInstance().getAssetsByCoinId(CoinId).compose(ResponseTransformer.<NewAssetsBean>handleResult()).subscribe(
                assetsBean -> {
                    if(null != assetsBean){
                        newAssetsItemBeanMap.put(assetsBean.getCoinId(), assetsBean);
                    }
                }, t -> {
                    System.out.println("-------no----根据币种ID获取对应币种的会员资产钱包信息");
                }
        );
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
                    System.out.println("-----------配置列表");
                }
        );
    }

    private String cnyTotal;
    private String usdtTotal;

    public String getCnyTotal() {
        return cnyTotal;
    }

//    public AssetsBean.AssetsItemBean getCoinInfo(String s) {
//        return assetsItemBeanMap.get(s);
//    }

    public NewAssetsBean getCoinInfo(String s) {
        return newAssetsItemBeanMap.get(s);
    }
    public CoinBean getCoinBeanHashMap(String s) {
        return coinBeanHashMap.get(s);
    }


    public Map<String, NewAssetsBean> getNewAssetsItemBeanMap() {
        return newAssetsItemBeanMap;
    }

    public void setNewAssetsItemBeanMap(Map<String, NewAssetsBean> newAssetsItemBeanMap) {
        this.newAssetsItemBeanMap = newAssetsItemBeanMap;
    }

    public Map<String, CoinBean> getCoinBeanHashMap() {
        return coinBeanHashMap;
    }

    public void setCoinBeanHashMap(Map<String, CoinBean> coinBeanHashMap) {
        this.coinBeanHashMap = coinBeanHashMap;
    }
}
