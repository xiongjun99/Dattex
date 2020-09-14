package com.temp.dattex.bean;

public class OtcDetailBean {

    /**
     * record : {"id":6058,"memberId":10096,"otcId":20,"state":3,"payType":1000,"currency":"CNY","inOrOut":true,"coinId":"","money":1000,"amount":139.082,"actualAmount":139.082,"fee":"0.000","ratio":"7.190","msg":null,"created":1596781391000,"updated":1596810186000}
     * otc : {"id":20,"name":"test2222","card":"testste","qr":"http://121.40.199.201:10088/admin/file/31959954a2b50d30f29f0018bc599a60","type":1000,"state":0,"total":100,"succeed":100,"created":1596532023000,"updated":1596532023000}
     */

    private RecordBean record;
    private OtcBean otc;

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public OtcBean getOtc() {
        return otc;
    }

    public void setOtc(OtcBean otc) {
        this.otc = otc;
    }

    public static class RecordBean {
        /**
         * id : 6058
         * memberId : 10096
         * otcId : 20
         * state : 3
         * payType : 1000
         * currency : CNY
         * inOrOut : true
         * coinId :
         * money : 1000.0
         * amount : 139.082
         * actualAmount : 139.082
         * fee : 0.000
         * ratio : 7.190
         * msg : null
         * created : 1596781391000
         * updated : 1596810186000
         */

        private int id;
        private int memberId;
        private int otcId;
        private int state;
        private int payType;
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

    public static class OtcBean {
        /**
         * id : 20
         * name : test2222
         * card : testste
         * qr : http://121.40.199.201:10088/admin/file/31959954a2b50d30f29f0018bc599a60
         * type : 1000
         * state : 0
         * total : 100
         * succeed : 100
         * created : 1596532023000
         * updated : 1596532023000
         */

        private int id;
        private String name;
        private String card;
        private String qr;
        private int type;
        private int state;
        private int total;
        private int succeed;
        private long created;
        private long updated;

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

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getQr() {
            return qr;
        }

        public void setQr(String qr) {
            this.qr = qr;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSucceed() {
            return succeed;
        }

        public void setSucceed(int succeed) {
            this.succeed = succeed;
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
}
