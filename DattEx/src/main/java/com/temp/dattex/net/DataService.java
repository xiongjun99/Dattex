package com.temp.dattex.net;

import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.BaseResponse;
import com.temp.dattex.Application;
import com.temp.dattex.Constants;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.bean.AssetsRecordBean;
import com.temp.dattex.bean.BannerItemBean;
import com.temp.dattex.bean.DealItemBean;
import com.temp.dattex.bean.KlineDataBean;
import com.temp.dattex.bean.LeverageBean;
import com.temp.dattex.bean.LoginBean;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.bean.OtcDetailBean;
import com.temp.dattex.bean.PayTypeBean;
import com.temp.dattex.bean.RechargeBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.bean.TradeDepthBean;
import com.temp.dattex.bean.TransferredBean;
import com.temp.dattex.bean.WithdrawLimitBean;
import com.temp.dattex.database.LoginInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex
 * @FileName     : DataService.java
 * @Author       : chao
 * @Date         : 2020/5/13
 * @Email        : icechliu@gmail.com
 * @version      : V1
 *
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::  
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 *************************************************************************/
public class DataService {

    private static volatile DataService dataService = new DataService();

    public static DataService getInstance() {
        return dataService;
    }

    public Observable<BaseResponse<LoginBean>> userLogin(String userName, String passWord, String countryCode) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put("account", userName);
        params.put("pwd", passWord);
        params.put("countryCode", countryCode);
        return RetrofitClient.getInstance().create(ApiService.class).login(params);

    }

    public Observable<BaseResponse<String>> userProtocol() {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        return RetrofitClient.getInstance().create(ApiService.class).userProtocol(params);

    }

    public Observable<BaseResponse<Object>> resetPassword() {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        return RetrofitClient.getInstance().create(ApiService.class).resetPassword(params);

    }

    public Observable<BaseResponse<Object>> register(String userName, String phoneCode, String inviteCode, String countryCode, String passWord, String passWordRepeat) {

        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ACCOUNT, userName);
        params.put(Constants.REQUEST_KEY_PWD, passWord);
        params.put(Constants.REQUEST_KEY_PWD_TWO, passWordRepeat);
        params.put(Constants.REQUEST_KEY_RECODE, inviteCode);
        params.put(Constants.REQUEST_KEY_AREACODE, countryCode);
        params.put(Constants.REQUEST_KEY_CODE, phoneCode);



        return RetrofitClient.getInstance().create(ApiService.class).register(params);

    }

    public Observable<BaseResponse<Object>> sendPhoneMessage(String phone,String areaCode) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_PHONE, phone);
        params.put(Constants.REQUEST_KEY_AREACODE, areaCode);
        return RetrofitClient.getInstance().create(ApiService.class).sendMessage(params);

    }

    public Observable<BaseResponse<AssetsBean>> userAssets() {
        return RetrofitClient.getInstance().create(ApiService.class).userAssets(LoginInfo.getUserToken());
    }


    public Observable<BaseResponse<List<SymbolConfigBean>>> symbolConfig() {
        return RetrofitClient.getInstance().create(ApiService.class).symbolConfig(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<Boolean>> checkWithdraw() {
        return RetrofitClient.getInstance().create(ApiService.class).checkWithdraw(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<Object>> placeOrder(String symbol, int direction, String lever, String price, float stopLossRates, float stopProfitRates) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_DIRECTION, direction);
        params.put(Constants.REQUEST_KEY_LEVER, lever);
        params.put(Constants.REQUEST_KEY_PRICE, price);
        params.put(Constants.REQUEST_KEY_STOP_LOSS_RATES, stopLossRates);
        params.put(Constants.REQUEST_KEY_STOP_PROFIT_RATES, stopProfitRates);
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).placeAnOrder(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<Double>> getUsdtRate() {
        return RetrofitClient.getInstance().create(ApiService.class).usdtRate();
    }

    public Observable<BaseResponse<List<LeverageBean>>> getLeverage(String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).getLeverage(LoginInfo.getUserToken(), params);
    }
    public Observable<BaseResponse<List<TradeDepthBean>>> getDepth(int btcusdt) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.BTCUSDT, btcusdt);
        return RetrofitClient.getInstance().create(ApiService.class).getDepth(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<OrdersBean>> getAllOrders(int page, String filter) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_DIR, "asc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        return RetrofitClient.getInstance().create(ApiService.class).getAllOrders(LoginInfo.getUserToken(), params);
    }

    /**
     * 提交身份绑定
     *
     * @return
     */
    public Observable<BaseResponse<Object>> commitAuth(String identityCard, String realName) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ID_ENTITY_CARD, identityCard);
        params.put(Constants.REQUEST_KEY_REAL_NAME, realName);
        return RetrofitClient.getInstance().create(ApiService.class).commitRealName(params, LoginInfo.getUserToken());
    }


    public Observable<BaseResponse<AssetsRecordBean>> assetsRecorde(String coinId, int page, String sort) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_DIR, "asc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_SORT, sort);
        return RetrofitClient.getInstance().create(ApiService.class).assetsRecord(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<KlineDataBean>> klineHistory(String symbol, int size, String period) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        params.put(Constants.REQUEST_KEY_SIZE, size);
        params.put(Constants.REQUEST_KEY_PERIOD, period);
        return RetrofitClient.getInstance().create(ApiService.class).klineHistory(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<WithdrawLimitBean>> withdrawLimit(String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).withdrawLimit(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<Object>> withdrawCoin(String coinId, String amount, String toAddr) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, amount);
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_COIN_ID, toAddr);
        //{
        //  "amount": 0,
        //  "code": "string",
        //  "coderef": "string",
        //  "coinId": "string",
        //  "label": "string",
        //  "paypwd": "string",
        //  "toAddr": "string"
        //}
        return RetrofitClient.getInstance().create(ApiService.class).withdrawCoin(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<Object>> placePosition(long orderId) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ORDER_ID, orderId);
        return RetrofitClient.getInstance().create(ApiService.class).placePosition(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<List<BannerItemBean>>> appBanner() {
        return RetrofitClient.getInstance().create(ApiService.class).appBanner(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<List<DealItemBean>>> getDealList(String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).getDealList(params);
    }

    public Observable<BaseResponse<List<OTCcfgBean>>> getOtcCfg() {
        return RetrofitClient.getInstance().create(ApiService.class).getOtcCfg(LoginInfo.getUserToken());
    }
    public Observable<BaseResponse<List<PayTypeBean>>> getPayType() {
        return RetrofitClient.getInstance().create(ApiService.class).getPayType(LoginInfo.getUserToken());
    }
    public Observable<BaseResponse<RechargeBean>> getRecharge(String amount,String coinId,String currency,String money,int payType) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_AMOUNT, amount);
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_CURRENCY, currency);
        params.put(Constants.REQUEST_KEY_MONEY, money);
        params.put(Constants.REQUEST_KEY_PAYTYPE, payType);
        return RetrofitClient.getInstance().create(ApiService.class).getRecharge(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<Object>> getTransferred(int id) {
        return RetrofitClient.getInstance().create(ApiService.class).getTransferred(LoginInfo.getUserToken(),id);
    }
    public Observable<BaseResponse<Object>> Cancel(int id) {
        return RetrofitClient.getInstance().create(ApiService.class).Cancel(LoginInfo.getUserToken(),id);
    }
    public Observable<BaseResponse<OtcDetailBean>> getOtcDetail(int id) {
        return RetrofitClient.getInstance().create(ApiService.class).getOtcDetail(LoginInfo.getUserToken(),id);
    }
}
