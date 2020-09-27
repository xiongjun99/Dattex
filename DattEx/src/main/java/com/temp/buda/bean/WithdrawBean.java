package com.temp.buda.bean;

public class WithdrawBean {
        /**
         * record : {"id":11,"memberId":578,"otcId":1,"state":0,"payType":1,"inorout":1,"coinId":"USDT","currency":"CNY","money":99,"amount":100,"actualAmount":100,"fee":1,"ratio":1,"msg":null,"created":"2020-09-24 17:31:22","updated":null,"memberCardId":1}
         * otc : null
         * feeTotal : 1
         * reciveItem : {"id":1,"memberId":578,"coinId":"CNY","isEnabled":"1","type":1,"firstName":"骏","lastName":"熊","bankName":"中国银行","province":"北京","city":"北京","receivingAccount":"1101011990030655858744","openingBank":"长阳","createUser":"578","createTime":"2020-09-24 17:24:20"}
         */

        private RecordBean record;
        private Object otc;
        private int feeTotal;
        private ReciveItemBean reciveItem;

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

        public int getFeeTotal() {
            return feeTotal;
        }

        public void setFeeTotal(int feeTotal) {
            this.feeTotal = feeTotal;
        }

        public ReciveItemBean getReciveItem() {
            return reciveItem;
        }

        public void setReciveItem(ReciveItemBean reciveItem) {
            this.reciveItem = reciveItem;
        }

        public static class RecordBean {
            /**
             * id : 11
             * memberId : 578
             * otcId : 1
             * state : 0
             * payType : 1
             * inorout : 1
             * coinId : USDT
             * currency : CNY
             * money : 99.0
             * amount : 100
             * actualAmount : 100
             * fee : 1
             * ratio : 1.0
             * msg : null
             * created : 2020-09-24 17:31:22
             * updated : null
             * memberCardId : 1
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
            private int amount;
            private int actualAmount;
            private int fee;
            private double ratio;
            private Object msg;
            private String created;
            private Object updated;
            private int memberCardId;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getActualAmount() {
                return actualAmount;
            }

            public void setActualAmount(int actualAmount) {
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

            public int getMemberCardId() {
                return memberCardId;
            }

            public void setMemberCardId(int memberCardId) {
                this.memberCardId = memberCardId;
            }
        }

        public static class ReciveItemBean {
            /**
             * id : 1
             * memberId : 578
             * coinId : CNY
             * isEnabled : 1
             * type : 1
             * firstName : 骏
             * lastName : 熊
             * bankName : 中国银行
             * province : 北京
             * city : 北京
             * receivingAccount : 1101011990030655858744
             * openingBank : 长阳
             * createUser : 578
             * createTime : 2020-09-24 17:24:20
             */

            private int id;
            private int memberId;
            private String coinId;
            private String isEnabled;
            private int type;
            private String firstName;
            private String lastName;
            private String bankName;
            private String province;
            private String city;
            private String receivingAccount;
            private String openingBank;
            private String createUser;
            private String createTime;

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

            public String getCoinId() {
                return coinId;
            }

            public void setCoinId(String coinId) {
                this.coinId = coinId;
            }

            public String getIsEnabled() {
                return isEnabled;
            }

            public void setIsEnabled(String isEnabled) {
                this.isEnabled = isEnabled;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getReceivingAccount() {
                return receivingAccount;
            }

            public void setReceivingAccount(String receivingAccount) {
                this.receivingAccount = receivingAccount;
            }

            public String getOpeningBank() {
                return openingBank;
            }

            public void setOpeningBank(String openingBank) {
                this.openingBank = openingBank;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
}
