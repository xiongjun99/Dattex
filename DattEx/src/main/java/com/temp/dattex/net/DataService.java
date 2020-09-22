package com.temp.dattex.net;

import com.independ.framework.client.RetrofitClient;
import com.independ.framework.response.BaseResponse;
import com.temp.dattex.Application;
import com.temp.dattex.Constants;
import com.temp.dattex.bean.AssetsBean;
import com.temp.dattex.bean.AssetsRecordBean;
import com.temp.dattex.bean.BannerItemBean;
import com.temp.dattex.bean.CoinBean;
import com.temp.dattex.bean.CoinRecordBean;
import com.temp.dattex.bean.DealItemBean;
import com.temp.dattex.bean.FuncListBean;
import com.temp.dattex.bean.InfoBySymbolBean;
import com.temp.dattex.bean.InviteBean;
import com.temp.dattex.bean.KlineDataBean;
import com.temp.dattex.bean.LeverageBean;
import com.temp.dattex.bean.LoginBean;
import com.temp.dattex.bean.MarketListBean;
import com.temp.dattex.bean.NewApplyBean;
import com.temp.dattex.bean.NewAssetsBean;
import com.temp.dattex.bean.NewPayTypeBean;
import com.temp.dattex.bean.NoticeBean;
import com.temp.dattex.bean.OTCcfgBean;
import com.temp.dattex.bean.OrdersBean;
import com.temp.dattex.bean.OtcDetailBean;
import com.temp.dattex.bean.PayTypeBean;
import com.temp.dattex.bean.RechargeBean;
import com.temp.dattex.bean.SymbolConfigBean;
import com.temp.dattex.bean.TradeDepthBean;
import com.temp.dattex.bean.UpdateBean;
import com.temp.dattex.bean.WithdrawBean;
import com.temp.dattex.bean.WithdrawLimitBean;
import com.temp.dattex.database.LoginInfo;
import com.temp.dattex.util.Utils;

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

    public Observable<BaseResponse<String>> userProtocol(){
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        return RetrofitClient.getInstance().create(ApiService.class).userProtocol(params);

    }

    public Observable<BaseResponse<Object>> resetPassword(String account,String code,String areaCode,String newPwd) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put("account", account);
        params.put("code", code);
        params.put("areaCode", areaCode);
        params.put("newPwd", newPwd);
        return RetrofitClient.getInstance().create(ApiService.class).resetPassword(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<Object>> updatePwd(String orgPwd,String newPwd) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put("orgPwd", orgPwd);
        params.put("newPwd", newPwd);
        return RetrofitClient.getInstance().create(ApiService.class).updatePwd(LoginInfo.getUserToken(),params);

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
    public Observable<BaseResponse<Object>> sendCodeReset(String phone,String areaCode) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_PHONE, phone);
        params.put(Constants.REQUEST_KEY_AREACODE, areaCode);
        params.put(Constants.SENDTYPE, "03");
        return RetrofitClient.getInstance().create(ApiService.class).sendCodeReset(params);

    }
    public Observable<BaseResponse<Object>> sendCodeMessage(String sendType) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.SENDTYPE, sendType);
        return RetrofitClient.getInstance().create(ApiService.class).sendCode(LoginInfo.getUserToken(),params);

    }


    public Observable<BaseResponse<AssetsBean>> userAssets() {
        return RetrofitClient.getInstance().create(ApiService.class).userAssets(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<List<CoinBean>>> userCoin() {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, "USDT");
        return RetrofitClient.getInstance().create(ApiService.class).userCoin(LoginInfo.getUserToken(),params);
    }

    public Observable<BaseResponse<NewAssetsBean>> getAssetsByCoinId(String coinId) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        return RetrofitClient.getInstance().create(ApiService.class).getAssetsByCoinId(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<Object>> getProfitLossRate(String direction,String id,String lever,String price,String stopLossRates,String stopProfitRates,String symbol) {
          if (stopLossRates.equals("1.0")){
              stopLossRates = "1";
          }
          if (stopProfitRates.equals("1.0")){
              stopProfitRates = "1";
          }
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_DIRECTION, direction);
        params.put(Constants.REQUEST_KEY_ID, id);
        params.put(Constants.REQUEST_KEY_LEVER, lever);
        params.put(Constants.REQUEST_KEY_PRICE, price);
        params.put(Constants.REQUEST_KEY_STOP_LOSS_RATES, stopLossRates);
        params.put(Constants.REQUEST_KEY_STOP_PROFIT_RATES, stopProfitRates);
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).getProfitLossRate(LoginInfo.getUserToken(),params);
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
        if (stopProfitRates==1.0){
            params.put(Constants.REQUEST_KEY_STOP_PROFIT_RATES, Utils.format0(stopProfitRates));
        }else {
            params.put(Constants.REQUEST_KEY_STOP_PROFIT_RATES, stopProfitRates);
        }
        if (stopLossRates==1.0){
            params.put(Constants.REQUEST_KEY_STOP_LOSS_RATES, Utils.format0(stopLossRates));
        }else {
            params.put(Constants.REQUEST_KEY_STOP_LOSS_RATES, stopLossRates);
        }
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
    public Observable<BaseResponse<List<TradeDepthBean>>> getDepth(String symbols) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(symbols, 5);
        return RetrofitClient.getInstance().create(ApiService.class).getDepth(LoginInfo.getUserToken(), params);
    }
    public Observable<BaseResponse<List<MarketListBean>>> getMarketList() {
        return RetrofitClient.getInstance().create(ApiService.class).getMarketList(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<OrdersBean>> getAllOrders(String contractType,int state,int page, String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_DIR, "asc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_STATE, state);
        params.put(Constants.REQUEST_KEY_CONTRACTTYPE, contractType);
        params.put(Constants.REQUEST_KEY_SYMBOL,symbol);

        return RetrofitClient.getInstance().create(ApiService.class).getAllOrders(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<List<NewPayTypeBean>>> getMemberReciveItem(String isWallet) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ISWALLET, isWallet);
        return RetrofitClient.getInstance().create(ApiService.class).getMemberReciveItem(LoginInfo.getUserToken(), params);
    }

    /**
     * 提交身份绑定
     *
     * @return
     */
    public Observable<BaseResponse<Object>> commitAuth(String areaCode,String identityCard, String realName,String lastName,String firstName,String identityType) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ID_AREA_CODE, areaCode);
        params.put(Constants.REQUEST_KEY_ID_ENTITY_CARD, identityCard);
        params.put(Constants.REQUEST_KEY_REAL_NAME, realName);
        params.put(Constants.REQUEST_KEY_LASTNAME, lastName);
        params.put(Constants.REQUEST_KEY_FIRSTNAME, firstName);
        params.put(Constants.REQUEST_KEY_IDENTITYTYPE, identityType);
        return RetrofitClient.getInstance().create(ApiService.class).commitRealName(params, LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<AssetsRecordBean>> assetsRecorde(String coinId, int page, String sort) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_DIR, "desc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_SORT, sort);
        return RetrofitClient.getInstance().create(ApiService.class).assetsRecord(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<CoinRecordBean>> findWithdrawRecord(int inorout , String coinId, int page, String sort) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_DIR, "asc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_SORT, sort);
        if (inorout!=2){
        params.put(Constants.REQUEST_KEY_INOROUT, inorout);
        }
        return RetrofitClient.getInstance().create(ApiService.class).findWithdrawRecord(LoginInfo.getUserToken(), params);
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
        return RetrofitClient.getInstance().create(ApiService.class).withdrawLimit(LoginInfo.getUserToken(), symbol);
    }

    public Observable<BaseResponse<Object>> withdrawCoin(String code, String amount, String coinId,String toAddr) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_AMOUNT, amount);
        params.put(Constants.REQUEST_KEY_CODE, code);
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_TOADDR, toAddr);

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

    public Observable<BaseResponse<WithdrawBean>> inoutWithdraw(String validCode, String coinId, String amount, String currency, String memberCardId, String money, int payType, int reciveItemId) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_AMOUNT, amount);
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_CURRENCY, currency);
        params.put(Constants.REQUEST_KEY_MEMBERCARDID, memberCardId);
        params.put(Constants.REQUEST_KEY_MONEY, money);
        params.put(Constants.REQUEST_KEY_PAYTYPE, payType);
        params.put(Constants.REQUEST_KEY_RECIVEITEMID, reciveItemId);
        params.put(Constants.REQUEST_VALIDCODE, validCode);
        return RetrofitClient.getInstance().create(ApiService.class).inoutWithdraw(LoginInfo.getUserToken(), params);
    }


    //删除支付方式
    public Observable<BaseResponse<Object>> removeMemberPayType(int id) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ID, id);
        return RetrofitClient.getInstance().create(ApiService.class).removeMemberPayType(LoginInfo.getUserToken(), params);
    }

    public Observable<BaseResponse<Object>> placePosition(long orderId) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ORDER_ID, orderId);
        return RetrofitClient.getInstance().create(ApiService.class).placePosition(LoginInfo.getUserToken(), orderId);
    }

    public Observable<BaseResponse<List<BannerItemBean>>> appBanner() {
        return RetrofitClient.getInstance().create(ApiService.class).appBanner(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<FuncListBean>> getFuncList() {
        return RetrofitClient.getInstance().create(ApiService.class).getFuncList(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<List<DealItemBean>>> getDealList(String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).getDealList(params);
    }

    public Observable<BaseResponse<OTCcfgBean>> getOtcCfg() {
        return RetrofitClient.getInstance().create(ApiService.class).getOtcCfg(LoginInfo.getUserToken());
    }
    public Observable<BaseResponse<List<PayTypeBean>>> getPayType() {
        return RetrofitClient.getInstance().create(ApiService.class).getPayType(LoginInfo.getUserToken());
    }
    public Observable<BaseResponse<Object>> addMemberPayType(String addr,String bankName,String province,String city,String isWallet,String lastName,String firstName,String openingBank,String receivingAccount,int type,String validCode) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, "CNY");
        params.put(Constants.REQUEST_KEY_BANKNAME, bankName);
        params.put(Constants.REQUEST_KEY_PROVINCE, province);
        params.put(Constants.REQUEST_KEY_CITY, city);
        params.put(Constants.REQUEST_KEY_ISWALLET, isWallet);
        params.put(Constants.REQUEST_KEY_LASTNAME, lastName);
        params.put(Constants.REQUEST_KEY_FIRSTNAME, firstName);
        params.put(Constants.REQUEST_KEY_OPENINGBANK, openingBank);
        params.put(Constants.REQUEST_KEY_RECEIVINGACCOUNT, receivingAccount);
        params.put(Constants.REQUEST_KEY_TYPE, type);
        params.put(Constants.REQUEST_VALIDCODE, validCode);
        params.put(Constants.REQUEST_ADDR, addr);

        return RetrofitClient.getInstance().create(ApiService.class).addMemberPayType(LoginInfo.getUserToken(),params);
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

    public Observable<BaseResponse<NewApplyBean>> getFindByPageApplyCoin(int page) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        return RetrofitClient.getInstance().create(ApiService.class).getFindByPageApplyCoin(LoginInfo.getUserToken(),params);
    }

    public Observable<BaseResponse<List<InviteBean>>> getInviteRecord() {
        return RetrofitClient.getInstance().create(ApiService.class).getInviteRecord(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<NoticeBean>> getNotice(int page, int type, int isEnabled, int isSticky) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_TYPE, type);
        params.put(Constants.REQUEST_KEY_ISENABLED, isEnabled);
        params.put(Constants.ISSTICKY, isSticky);
        return RetrofitClient.getInstance().create(ApiService.class).getNotice(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<NoticeBean.RowsBean>> getNoticeListInfo(int id, int page, int type, int isEnabled, int isSticky) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ID, id);
        if (id!=1){
            params.put(Constants.REQUEST_KEY_PAGE, page);
            params.put(Constants.REQUEST_KEY_SIZE, 100);
            params.put(Constants.REQUEST_KEY_TYPE, type);
            params.put(Constants.REQUEST_KEY_ISENABLED, isEnabled);
            params.put(Constants.ISSTICKY, isSticky);
        }
        return RetrofitClient.getInstance().create(ApiService.class).getNoticeListInfo(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<UpdateBean>> UpDate() {
        return RetrofitClient.getInstance().create(ApiService.class).UpDate(LoginInfo.getUserToken());
    }

    public Observable<BaseResponse<Object>> getApplyCoin(int applyId ,int subscribeQty,String validCode) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_APPLYID, applyId);
        params.put(Constants.REQUEST_SUBSCRIBEQTY, subscribeQty);
        params.put(Constants.REQUEST_VALIDCODE, validCode);

        return RetrofitClient.getInstance().create(ApiService.class).getApplyCoin(LoginInfo.getUserToken(),params);
    }
    public Observable<BaseResponse<InfoBySymbolBean>> getInfoBySymbol(String symbol) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_SYMBOL, symbol);
        return RetrofitClient.getInstance().create(ApiService.class).getInfoBySymbol(LoginInfo.getUserToken(),params);
    }

    public Observable<BaseResponse<AssetsRecordBean>> getFindMemberBill(int type,String coinId, int page, String sort) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_COIN_ID, coinId);
        params.put(Constants.REQUEST_KEY_DIR, "asc");
        params.put(Constants.REQUEST_KEY_PAGE, page);
        params.put(Constants.REQUEST_KEY_SIZE, 100);
        params.put(Constants.REQUEST_KEY_SORT, sort);
        if (type!=-1){
        params.put(Constants.REQUEST_KEY_TYPE, type);
        }
        return RetrofitClient.getInstance().create(ApiService.class).getFindMemberBill(LoginInfo.getUserToken(), params);
    }
    public Observable<BaseResponse<Object>> withdrawCancle(int id) {
        Map<String, Object> params = ((Application) Application.getInstance()).createRequestParams();
        params.put(Constants.REQUEST_KEY_ID, id);
        return RetrofitClient.getInstance().create(ApiService.class).withdrawCancle(LoginInfo.getUserToken(), params);
    }

}
