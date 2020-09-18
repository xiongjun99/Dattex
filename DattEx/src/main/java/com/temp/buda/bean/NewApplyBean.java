package com.temp.buda.bean;


import java.util.List;

public class NewApplyBean {


    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * applyTypeName : string
         * coinId : string
         * contractId : string
         * createTime : 2020-08-18T07:14:52.592Z
         * createUser : string
         * endTime : 2020-08-18T07:14:52.592Z
         * id : 0
         * memo : string
         * qty : 0
         * recordId : string
         * startTime : 2020-08-18T07:14:52.592Z
         * status : string
         * statusName : string
         * subscribeStatus : string
         * symbol : string
         * totalPrice : 0
         * tradingQty : 0
         * type : string
         * typeName : string
         * unitPrice : 0
         * updateTime : 2020-08-18T07:14:52.592Z
         * updateUser : string
         */

        private String applyTypeName;
        private String coinId;
        private String contractId;
        private String createTime;
        private String createUser;
        private String endTime;
        private int id;
        private String memo;
        private int qty;
        private String recordId;
        private String startTime;
        private String status;
        private String statusName;
        private String subscribeStatus;
        private String symbol;
        private int totalPrice;
        private int tradingQty;
        private int subscribePrice;
        private String type;
        private String typeName;
        private int unitPrice;
        private String updateTime;
        private String updateUser;
        private int subscribeQty;
        private int tradingPrice;

        public int getSubscribeQty() {
            return subscribeQty;
        }

        public void setSubscribeQty(int subscribeQty) {
            this.subscribeQty = subscribeQty;
        }

        public int getTradingPrice() {
            return tradingPrice;
        }

        public void setTradingPrice(int tradingPrice) {
            this.tradingPrice = tradingPrice;
        }

        public int getSubscribePrice() {
            return subscribePrice;
        }

        public void setSubscribePrice(int subscribePrice) {
            this.subscribePrice = subscribePrice;
        }
        public String getApplyTypeName() {
            return applyTypeName;
        }

        public void setApplyTypeName(String applyTypeName) {
            this.applyTypeName = applyTypeName;
        }

        public String getCoinId() {
            return coinId;
        }

        public void setCoinId(String coinId) {
            this.coinId = coinId;
        }

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getSubscribeStatus() {
            return subscribeStatus;
        }

        public void setSubscribeStatus(String subscribeStatus) {
            this.subscribeStatus = subscribeStatus;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getTradingQty() {
            return tradingQty;
        }

        public void setTradingQty(int tradingQty) {
            this.tradingQty = tradingQty;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }
    }
}
