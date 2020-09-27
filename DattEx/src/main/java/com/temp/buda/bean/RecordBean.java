package com.temp.buda.bean;

import java.util.List;

public class RecordBean {
        /**
         * size : 100
         * total : 4
         * rows : [{"id":5,"memberId":578,"otcId":1,"state":1,"payType":1,"inorout":0,"coinId":"USDT","currency":"CNY","money":1,"amount":1000,"actualAmount":999,"fee":1,"ratio":6.9,"msg":null,"created":"2020-09-24 08:57:42","updated":null,"memberCardId":null},{"id":6,"memberId":578,"otcId":1,"state":1,"payType":1,"inorout":0,"coinId":"USDT","currency":"CNY","money":1,"amount":1000,"actualAmount":999,"fee":1,"ratio":6.9,"msg":null,"created":"2020-09-24 08:59:32","updated":null,"memberCardId":null},{"id":7,"memberId":578,"otcId":1,"state":1,"payType":1,"inorout":0,"coinId":"USDT","currency":"CNY","money":1,"amount":1000,"actualAmount":999,"fee":1,"ratio":6.9,"msg":null,"created":"2020-09-24 09:01:06","updated":null,"memberCardId":null},{"id":8,"memberId":578,"otcId":1,"state":1,"payType":1,"inorout":0,"coinId":"USDT","currency":"CNY","money":1,"amount":1000,"actualAmount":999,"fee":1,"ratio":6.9,"msg":null,"created":"2020-09-24 09:02:11","updated":null,"memberCardId":null}]
         * start : 0
         * summber : null
         */

        private int size;
        private int total;
        private int start;
        private Object summber;
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

        public Object getSummber() {
            return summber;
        }

        public void setSummber(Object summber) {
            this.summber = summber;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 5
             * memberId : 578
             * otcId : 1
             * state : 1
             * payType : 1
             * inorout : 0
             * coinId : USDT
             * currency : CNY
             * money : 1.0
             * amount : 1000.0
             * actualAmount : 999.0
             * fee : 1
             * ratio : 6.9
             * msg : null
             * created : 2020-09-24 08:57:42
             * updated : null
             * memberCardId : null
             */

            private int id;
            private int memberId;
            private int otcId;
            private int state;
            private int payType;
            private int inorout;
            private String coinId;
            private String currency;
            private float money;
            private double amount;
            private double actualAmount;
            private int fee;
            private double ratio;
            private Object msg;
            private String created;
            private Object updated;
            private Object memberCardId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMemberId() {
                return memberId;
            }

            public void setMemberId(int memberId) {
                this.memberId = memberId;
            }

            public int getOtcId() {
                return otcId;
            }

            public void setOtcId(int otcId) {
                this.otcId = otcId;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public int getInorout() {
                return inorout;
            }

            public void setInorout(int inorout) {
                this.inorout = inorout;
            }

            public String getCoinId() {
                return coinId;
            }

            public void setCoinId(String coinId) {
                this.coinId = coinId;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public float getMoney() {
                return money;
            }

            public void setMoney(float money) {
                this.money = money;
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

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public double getRatio() {
                return ratio;
            }

            public void setRatio(double ratio) {
                this.ratio = ratio;
            }

            public Object getMsg() {
                return msg;
            }

            public void setMsg(Object msg) {
                this.msg = msg;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public Object getUpdated() {
                return updated;
            }

            public void setUpdated(Object updated) {
                this.updated = updated;
            }

            public Object getMemberCardId() {
                return memberCardId;
            }

            public void setMemberCardId(Object memberCardId) {
                this.memberCardId = memberCardId;
            }
        }
    }
