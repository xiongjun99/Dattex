package com.temp.dattex.bean;

import java.io.Serializable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : WithdrawLimitBean.java
 * @Author       : chao
 * @Date         : 2020/6/5
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
public class WithdrawLimitBean implements Serializable {


    /**
     * allowOut : true
     * coinId : string
     * feeRate : string
     * maxOut : string
     * minFee : string
     * minOut : string
     * withdrawFee
     */

    private boolean allowOut;
    private String coinId;
    private float feeRate;
    private float maxOut;
    private float minFee;
    private float minOut;
    private float withdrawFee;
    public float getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(float withdrawFee) {
        this.withdrawFee = withdrawFee;
    }
    public boolean getAllowOut() {
        return allowOut;
    }

    public void setAllowOut(boolean allowOut) {
        this.allowOut = allowOut;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public float getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(float feeRate) {
        this.feeRate = feeRate;
    }

    public float getMaxOut() {
        return maxOut;
    }

    public void setMaxOut(float maxOut) {
        this.maxOut = maxOut;
    }

    public float getMinFee() {
        return minFee;
    }

    public void setMinFee(float minFee) {
        this.minFee = minFee;
    }

    public float getMinOut() {
        return minOut;
    }

    public void setMinOut(float minOut) {
        this.minOut = minOut;
    }
}
