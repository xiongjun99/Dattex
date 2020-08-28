package com.temp.dattex.bean;

public class NewAssetsBean {
    /**
     * memberId : 10005
     * coinId : USDT-ERC20
     * cnyprice : 7.01
     * addr : 0x8a78025449348d55f06d5f2b423e09fa47cfe97c
     * ercAddr : null
     * balance : 1000000
     * frozen : 1000
     * created : true
     */

    private int memberId;
    private String coinId;
    private String cnyprice;
    private String addr;
    private Object ercAddr;
    private String balance;
    private String frozen;
    private boolean created;

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

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }
}
