package com.temp.buda.bean;

public class WithdrawBean {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
         * record : {"id":6374,"memberId":10005,"otcId":8,"memberCardId":8,"state":0,"payType":1,"reciveItemId":8,"currency":"CNY","inOrOut":false,"coinId":"USDT","money":450,"amount":3235.5,"actualAmount":3235.5,"fee":"0E-8","ratio":"7.19000000","msg":null,"created":1599143444112,"updated":1599143444112}
         * otc : null
         * memberPayTypeCard : {"id":8,"memberId":10005,"coinId":"CNY","isEnabled":"1","type":1,"firstName":"骏","lastName":"熊","bankName":"交通银行","province":"北京","city":"北京","receivingAccount":"twst1234567890","openingBank":"朝阳","createUser":"10005","createTime":"2020-08-31 10:52:55"}
         */
        private boolean success;
        private RecordBean record;
        private Object otc;
        private MemberPayTypeCardBean memberPayTypeCard;

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

        public MemberPayTypeCardBean getMemberPayTypeCard() {
            return memberPayTypeCard;
        }

        public void setMemberPayTypeCard(MemberPayTypeCardBean memberPayTypeCard) {
            this.memberPayTypeCard = memberPayTypeCard;
        }

        public static class RecordBean {
            /**
             * id : 6374
             * memberId : 10005
             * otcId : 8
             * memberCardId : 8
             * state : 0
             * payType : 1
             * reciveItemId : 8
             * currency : CNY
             * inOrOut : false
             * coinId : USDT
             * money : 450.0
             * amount : 3235.5
             * actualAmount : 3235.5
             * fee : 0E-8
             * ratio : 7.19000000
             * msg : null
             * created : 1599143444112
             * updated : 1599143444112
             */

            private int id;
            private int memberId;
            private int otcId;
            private int memberCardId;
            private int state;
            private int payType;
            private int reciveItemId;
            private String currency;
            private int inOrOut;
            private String coinId;
            private double money;
            private double amount;
            private double actualAmount;
            private String fee;
            private String ratio;
            private Object msg;
            private long created;
            private long updated;

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

            public int getMemberCardId() {
                return memberCardId;
            }

            public void setMemberCardId(int memberCardId) {
                this.memberCardId = memberCardId;
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

            public int getReciveItemId() {
                return reciveItemId;
            }

            public void setReciveItemId(int reciveItemId) {
                this.reciveItemId = reciveItemId;
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public int isInOrOut() {
                return inOrOut;
            }

            public void setInOrOut(int inOrOut) {
                this.inOrOut = inOrOut;
            }

            public String getCoinId() {
                return coinId;
            }

            public void setCoinId(String coinId) {
                this.coinId = coinId;
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

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getRatio() {
                return ratio;
            }

            public void setRatio(String ratio) {
                this.ratio = ratio;
            }

            public Object getMsg() {
                return msg;
            }

            public void setMsg(Object msg) {
                this.msg = msg;
            }

            public long getCreated() {
                return created;
            }

            public void setCreated(long created) {
                this.created = created;
            }

            public long getUpdated() {
                return updated;
            }

            public void setUpdated(long updated) {
                this.updated = updated;
            }
        }

        public static class MemberPayTypeCardBean {
            /**
             * id : 8
             * memberId : 10005
             * coinId : CNY
             * isEnabled : 1
             * type : 1
             * firstName : 骏
             * lastName : 熊
             * bankName : 交通银行
             * province : 北京
             * city : 北京
             * receivingAccount : twst1234567890
             * openingBank : 朝阳
             * createUser : 10005
             * createTime : 2020-08-31 10:52:55
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
