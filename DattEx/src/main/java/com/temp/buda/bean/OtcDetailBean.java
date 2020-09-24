package com.temp.buda.bean;

public class OtcDetailBean {

        /**
         * record : {"id":10,"memberId":578,"otcId":1,"state":3,"payType":1,"inorout":0,"coinId":"","currency":"CNY","money":1,"amount":1000,"actualAmount":999,"fee":1,"ratio":1,"msg":null,"created":"2020-09-23 22:28:42","updated":null,"memberCardId":null}
         * otc : null
         * feeTotal : null
         * reciveItem : null
         */

        private RecordBean record;
        private Object otc;
        private Object feeTotal;
        private Object reciveItem;

        public RecordBean getRecord() {
            return record;
        }

        public void setRecord(RecordBean record) {
            this.record = record;
        }

        public Object getOtc() {
            return otc;
        }

        public void setOtc(Object otc) {
            this.otc = otc;
        }

        public Object getFeeTotal() {
            return feeTotal;
        }

        public void setFeeTotal(Object feeTotal) {
            this.feeTotal = feeTotal;
        }

        public Object getReciveItem() {
            return reciveItem;
        }

        public void setReciveItem(Object reciveItem) {
            this.reciveItem = reciveItem;
        }

        public static class RecordBean {
            /**
             * id : 10
             * memberId : 578
             * otcId : 1
             * state : 3
             * payType : 1
             * inorout : 0
             * coinId :
             * currency : CNY
             * money : 1.0
             * amount : 1000.0
             * actualAmount : 999.0
             * fee : 1
             * ratio : 1.0
             * msg : null
             * created : 2020-09-23 22:28:42
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
            private double money;
            private double amount;
            private double actualAmount;
            private int fee;
            private double ratio;
            private Object msg;
            private String created;
            private String updated;
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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
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

            public String getUpdated() {
                return updated;
            }

            public void setUpdated(String updated) {
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
