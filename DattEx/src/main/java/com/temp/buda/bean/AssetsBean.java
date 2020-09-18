package com.temp.buda.bean;

import java.io.Serializable;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : AssetsBean.java
 * @Author       : chao
 * @Date         : 2020/6/2
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
public class AssetsBean implements Serializable {

    /**
     * assets : [{"addr":"string","balance":"string","cnyprice":"string","coinId":"string","created":true,"ercAddr":"string","frozen":"string","memberId":0}]
     * cnyTotal : string
     * usdtTotal : string
     */

    private String cnyTotal;
    private String usdtTotal;
    private List<AssetsItemBean> assets;

    public String getCnyTotal() {
        return cnyTotal;
    }

    public void setCnyTotal(String cnyTotal) {
        this.cnyTotal = cnyTotal;
    }

    public String getUsdtTotal() {
        return usdtTotal;
    }

    public void setUsdtTotal(String usdtTotal) {
        this.usdtTotal = usdtTotal;
    }

    public List<AssetsItemBean> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetsItemBean> assets) {
        this.assets = assets;
    }

    public static class AssetsItemBean {
        /**
         * addr : string
         * balance : string
         * cnyprice : string
         * coinId : string
         * created : true
         * ercAddr : string
         * frozen : string
         * memberId : 0
         */

        private String addr;
        private String balance;
        private String cnyprice;
        private String coinId;
        private boolean created;
        private String ercAddr;
        private String frozen;
        private int memberId;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getCnyprice() {
            return cnyprice;
        }

        public void setCnyprice(String cnyprice) {
            this.cnyprice = cnyprice;
        }

        public String getCoinId() {
            return coinId;
        }

        public void setCoinId(String coinId) {
            this.coinId = coinId;
        }

        public boolean isCreated() {
            return created;
        }

        public void setCreated(boolean created) {
            this.created = created;
        }

        public String getErcAddr() {
            return ercAddr;
        }

        public void setErcAddr(String ercAddr) {
            this.ercAddr = ercAddr;
        }

        public String getFrozen() {
            return frozen;
        }

        public void setFrozen(String frozen) {
            this.frozen = frozen;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }
    }
}
