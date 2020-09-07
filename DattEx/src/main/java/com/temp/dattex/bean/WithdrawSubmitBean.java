package com.temp.dattex.bean;

public class WithdrawSubmitBean {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * success : true
     * data : {"record":{"id":6374,"memberId":10005,"otcId":8,"memberCardId":8,"state":0,"payType":1,"reciveItemId":8,"currency":"CNY","inOrOut":false,"coinId":"USDT","money":450,"amount":3235.5,"actualAmount":3235.5,"fee":"0E-8","ratio":"7.19000000","msg":null,"created":1599143444112,"updated":1599143444112},"otc":null,"memberPayTypeCard":{"id":8,"memberId":10005,"coinId":"CNY","isEnabled":"1","type":1,"firstName":"骏","lastName":"熊","bankName":"交通银行","province":"北京","city":"北京","receivingAccount":"twst1234567890","openingBank":"朝阳","createUser":"10005","createTime":"2020-08-31 10:52:55"}}
     */

    private boolean success;

}
