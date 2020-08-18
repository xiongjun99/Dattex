package com.temp.dattex.bean;

public class RechargeBean {

    /**
     * otc : {"card":"string","created":"2020-08-07T03:33:16.442Z","id":0,"name":"string","qr":"string","state":0,"succeed":0,"total":0,"type":0,"updated":"2020-08-07T03:33:16.442Z"}
     * record : {"actualAmount":0,"amount":0,"coinId":"string","created":"2020-08-07T03:33:16.442Z","currency":"string","fee":"string","id":0,"inOrOut":true,"memberId":0,"money":0,"msg":"string","otcId":0,"payType":0,"ratio":"string","state":0,"updated":"2020-08-07T03:33:16.442Z"}
     */

    private OtcBean otc;
    private RecordBean record;

    public OtcBean getOtc() {
        return otc;
    }

    public void setOtc(OtcBean otc) {
        this.otc = otc;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class OtcBean {
        /**
         * card : string
         * created : 2020-08-07T03:33:16.442Z
         * id : 0
         * name : string
         * qr : string
         * state : 0
         * succeed : 0
         * total : 0
         * type : 0
         * updated : 2020-08-07T03:33:16.442Z
         * ext
         */
        private String card;
        private String ext;
        private String created;
        private int id;
        private String name;
        private String qr;
        private int state;
        private int succeed;
        private int total;
        private int type;
        private String updated;

        public String getExt() {
            return ext;
        }

        public void setExt(String ext) {
            this.ext = ext;
        }
        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQr() {
            return qr;
        }

        public void setQr(String qr) {
            this.qr = qr;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getSucceed() {
            return succeed;
        }

        public void setSucceed(int succeed) {
            this.succeed = succeed;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    public static class RecordBean {
        /**
         * actualAmount : 0
         * amount : 0
         * coinId : string
         * created : 2020-08-07T03:33:16.442Z
         * currency : string
         * fee : string
         * id : 0
         * inOrOut : true
         * memberId : 0
         * money : 0
         * msg : string
         * otcId : 0
         * payType : 0
         * ratio : string
         * state : 0
         * updated : 2020-08-07T03:33:16.442Z
         */

        private float actualAmount;
        private float amount;
        private String coinId;
        private String created;
        private String currency;
        private String fee;
        private int id;
        private boolean inOrOut;
        private int memberId;
        private int money;
        private String msg;
        private int otcId;
        private int payType;
        private String ratio;
        private int state;
        private String updated;

        public float getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(float actualAmount) {
            this.actualAmount = actualAmount;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getCoinId() {
            return coinId;
        }

        public void setCoinId(String coinId) {
            this.coinId = coinId;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isInOrOut() {
            return inOrOut;
        }

        public void setInOrOut(boolean inOrOut) {
            this.inOrOut = inOrOut;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getOtcId() {
            return otcId;
        }

        public void setOtcId(int otcId) {
            this.otcId = otcId;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getRatio() {
            return ratio;
        }

        public void setRatio(String ratio) {
            this.ratio = ratio;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }
}
