package com.temp.buda.bean;

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
         * size : 100
         * total : 4
         * rows : [{"code":"ORDER_PRICE_UNFROZEN","amount":"0.00000000","income":1,"left":"40016196.75000000","rectime":"2020-09-13 19:35:22","remarks":"订单本金解除冻结"},{"code":"WITHDRAW_FROZEN","amount":"2991.00000000","income":0,"left":"40016196.75000000","rectime":"2020-09-13 19:34:25","remarks":"提币冻结"},{"code":"ORDER_PRICE_FROZEN","amount":"500.00000000","income":0,"left":"40019237.75000000","rectime":"2020-09-13 19:33:43","remarks":"订单本金冻结"},{"code":"FEE_DEDUCT","amount":"50.00000000","income":0,"left":"40019187.75000000","rectime":"2020-09-13 19:33:43","remarks":"手续费扣减"}]
         * start : 0
         * summber : null
         */

        private int size;
        private int total;
        private int start;
        private List<RowsBean> rows;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * code : ORDER_PRICE_UNFROZEN
             * amount : 0.00000000
             * income : 1
             * left : 40016196.75000000
             * rectime : 2020-09-13 19:35:22
             * remarks : 订单本金解除冻结
             */

            private String code;
            private String amount;
            private int income;
            private String left;
            private String rectime;
            private String remarks;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }


            public String getBizname() {
                return bizname;
            }

            public void setBizname(String bizname) {
                this.bizname = bizname;
            }

            private String bizname;


            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getIncome() {
                return income;
            }

            public void setIncome(int income) {
                this.income = income;
            }

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getRectime() {
                return rectime;
            }

            public void setRectime(String rectime) {
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
