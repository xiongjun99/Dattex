package com.temp.dattex.bean;

import java.util.List;

public class CoinRecordBean {

        /**
         * size : 100
         * total : 22
         * rows : [{"id":16,"coinId":"USDT","mobile":null,"amount":100,"actualAmount":93,"itocName":null,"operatorsName":null,"createTime":"2020-09-10 00:07:15","auditTime":"2020-09-10 16:07:27","fee":7,"states":3,"fromAddr":"0x90bd4bb38b3094f944f9eece64bf7b10e31bb665","toAddr":"0xddbc466147bd308a2d8643e16f2a84f728814991","txhash":null,"type":1,"reason":null}]
         */

        private int size;
        private int total;
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

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 16
             * coinId : USDT
             * mobile : null
             * amount : 100.0
             * actualAmount : 93.0
             * itocName : null
             * operatorsName : null
             * createTime : 2020-09-10 00:07:15
             * auditTime : 2020-09-10 16:07:27
             * fee : 7.0
             * states : 3
             * fromAddr : 0x90bd4bb38b3094f944f9eece64bf7b10e31bb665
             * toAddr : 0xddbc466147bd308a2d8643e16f2a84f728814991
             * txhash : null
             * type : 1
             * reason : null
             */

            private int id;
            private String coinId;
            private Object mobile;
            private double amount;
            private double actualAmount;
            private int itocName;
            private Object operatorsName;
            private String createTime;
            private String auditTime;
            private double fee;
            private int states;
            private String fromAddr;
            private String toAddr;
            private String txhash;
            private int type;
            private Object reason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCoinId() {
                return coinId;
            }

            public void setCoinId(String coinId) {
                this.coinId = coinId;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(double actualAmount) {
                this.actualAmount = actualAmount;
            }

            public int getItocName() {
                return itocName;
            }

            public void setItocName(int itocName) {
                this.itocName = itocName;
            }

            public Object getOperatorsName() {
                return operatorsName;
            }

            public void setOperatorsName(Object operatorsName) {
                this.operatorsName = operatorsName;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public int getStates() {
                return states;
            }

            public void setStates(int states) {
                this.states = states;
            }

            public String getFromAddr() {
                return fromAddr;
            }

            public void setFromAddr(String fromAddr) {
                this.fromAddr = fromAddr;
            }

            public String getToAddr() {
                return toAddr;
            }

            public void setToAddr(String toAddr) {
                this.toAddr = toAddr;
            }

            public String getTxhash() {
                return txhash;
            }

            public void setTxhash(String txhash) {
                this.txhash = txhash;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }
        }
}
