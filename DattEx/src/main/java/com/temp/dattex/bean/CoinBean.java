package com.temp.dattex.bean;

public class    CoinBean {

    /**
     * id : USDT-ERC20
     * engName : USDT-ERC20
     * cnName : null
     * minOut : 30.0
     * maxOut : 100000.0
     * gasPrice : 25.0
     * minFee : 1.0
     * feeRate : 0.02
     * hotWallet :
     * hotWalletKey :
     * coldWallet : null
     * nodeUrl : http://
     * nodeUsername : null
     * nodePwd : null
     * ethContract : 0xed41922817a74424c4dafd2cfc4f730b69751aa7
     * series : ETH
     * state : true
     * allowOut : true
     * sortNo : 3
     * withdrawFee
     */

    private String id;
    private String engName;
    private Object cnName;
    private double minOut;
    private double maxOut;
    private double gasPrice;
    private double minFee;
    private double feeRate;
    private String hotWallet;
    private String hotWalletKey;
    private Object coldWallet;
    private String nodeUrl;
    private Object nodeUsername;
    private Object nodePwd;
    private String ethContract;
    private String series;
    private boolean state;
    private boolean allowOut;
    private int sortNo;
    private float withdrawFee;
    public float getWithdrawFee() {
        return withdrawFee;
    }

    public void setWithdrawFee(float withdrawFee) {
        this.withdrawFee = withdrawFee;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Object getCnName() {
        return cnName;
    }

    public void setCnName(Object cnName) {
        this.cnName = cnName;
    }

    public double getMinOut() {
        return minOut;
    }

    public void setMinOut(double minOut) {
        this.minOut = minOut;
    }

    public double getMaxOut() {
        return maxOut;
    }

    public void setMaxOut(double maxOut) {
        this.maxOut = maxOut;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public double getMinFee() {
        return minFee;
    }

    public void setMinFee(double minFee) {
        this.minFee = minFee;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(double feeRate) {
        this.feeRate = feeRate;
    }

    public String getHotWallet() {
        return hotWallet;
    }

    public void setHotWallet(String hotWallet) {
        this.hotWallet = hotWallet;
    }

    public String getHotWalletKey() {
        return hotWalletKey;
    }

    public void setHotWalletKey(String hotWalletKey) {
        this.hotWalletKey = hotWalletKey;
    }

    public Object getColdWallet() {
        return coldWallet;
    }

    public void setColdWallet(Object coldWallet) {
        this.coldWallet = coldWallet;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public Object getNodeUsername() {
        return nodeUsername;
    }

    public void setNodeUsername(Object nodeUsername) {
        this.nodeUsername = nodeUsername;
    }

    public Object getNodePwd() {
        return nodePwd;
    }

    public void setNodePwd(Object nodePwd) {
        this.nodePwd = nodePwd;
    }

    public String getEthContract() {
        return ethContract;
    }

    public void setEthContract(String ethContract) {
        this.ethContract = ethContract;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isAllowOut() {
        return allowOut;
    }

    public void setAllowOut(boolean allowOut) {
        this.allowOut = allowOut;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
}
