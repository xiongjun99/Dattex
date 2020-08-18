package com.temp.dattex.bean;

import java.io.Serializable;
import java.util.List;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.bean
 * @FileName     : AssetsRecordBean.java
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
public class AssetsRecordBean implements Serializable {

    /**
     * rows : [{"amount":"string","code":"string","income":true,"left":"string","rectime":"2020-06-04T10:17:14.121Z","remarks":"string"}]
     * size : 0
     * start : 0
     * total : 0
     */

    private int size;
    private int start;
    private int total;
    private List<AssetsItemBean> rows;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AssetsItemBean> getRows() {
        return rows;
    }

    public void setRows(List<AssetsItemBean> rows) {
        this.rows = rows;
    }

    public static class AssetsItemBean {
        /**
         * amount : string
         * code : string
         * income : true
         * left : string
         * rectime : 2020-06-04T10:17:14.121Z
         * remarks : string
         */

        private double amount;
        private String code;
        private boolean income;
        private String left;
        private long rectime;
        private String remarks;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isIncome() {
            return income;
        }

        public void setIncome(boolean income) {
            this.income = income;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public long getRectime() {
            return rectime;
        }

        public void setRectime(long rectime) {
            this.rectime = rectime;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
