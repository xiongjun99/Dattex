package com.temp.dattex.bean;

public class NewAssetsBean {

        /**
         * memberId : 31
         * coinId : USDT
         * cnyprice : 7.01
         * addr : 0x6971c7d49574a7843b4f2db60ab959ed23c5898b
         * ercAddr : null
         * balance : 40019737.75000000
         * frozen : 3800.00000000
         * created : 1
         * isCertification : 2
         */

        private int memberId;
        private String coinId;
        private String cnyprice;
        private String addr;
        private Object ercAddr;
        private String balance;
        private String frozen;
        private int created;
        private String isCertification;

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

        public String getCnyprice() {
            return cnyprice;
        }

        public void setCnyprice(String cnyprice) {
            this.cnyprice = cnyprice;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Object getErcAddr() {
            return ercAddr;
        }

        public void setErcAddr(Object ercAddr) {
            this.ercAddr = ercAddr;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getFrozen() {
            return frozen;
        }

        public void setFrozen(String frozen) {
            this.frozen = frozen;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public String getIsCertification() {
            return isCertification;
        }

        public void setIsCertification(String isCertification) {
            this.isCertification = isCertification;
        }
}
