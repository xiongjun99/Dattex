package com.temp.buda.net;

import com.independ.framework.response.BaseResponse;
import com.temp.buda.bean.AssetsBean;
import com.temp.buda.bean.AssetsRecordBean;
import com.temp.buda.bean.BannerItemBean;
import com.temp.buda.bean.CoinBean;
import com.temp.buda.bean.CoinRecordBean;
import com.temp.buda.bean.DealItemBean;
import com.temp.buda.bean.FuncListBean;
import com.temp.buda.bean.InfoBySymbolBean;
import com.temp.buda.bean.InviteBean;
import com.temp.buda.bean.KlineDataBean;
import com.temp.buda.bean.LeverageBean;
import com.temp.buda.bean.LoginBean;
import com.temp.buda.bean.MarketListBean;
import com.temp.buda.bean.NewApplyBean;
import com.temp.buda.bean.NewAssetsBean;
import com.temp.buda.bean.NewPayTypeBean;
import com.temp.buda.bean.NoticeBean;
import com.temp.buda.bean.OTCcfgBean;
import com.temp.buda.bean.OrdersBean;
import com.temp.buda.bean.OtcDetailBean;
import com.temp.buda.bean.PayTypeBean;
import com.temp.buda.bean.RechargeBean;
import com.temp.buda.bean.RecordBean;
import com.temp.buda.bean.SymbolConfigBean;
import com.temp.buda.bean.TradeDepthBean;
import com.temp.buda.bean.UpdateBean;
import com.temp.buda.bean.WithdrawBean;
import com.temp.buda.bean.WithdrawLimitBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.net
 * @FileName     : ApiService.java
 * @Author       : chao
 * @Date         : 2020/5/14
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
public interface ApiService {

    @POST(ApiAddress.SYMBOL_CONFIG)
    Observable<BaseResponse<List<SymbolConfigBean>>> symbolConfig(@Header("x-app-token") String token);

    @POST(ApiAddress.LOGIN_URL)
    Observable<BaseResponse<LoginBean>> login(@Body Map<String, Object> params);

    @POST(ApiAddress.USER_PROTOCOL)
    Observable<BaseResponse<String>> userProtocol(@Body Map<String, Object> params);

    @POST(ApiAddress.RESET_PASSWORD)
    Observable<BaseResponse<Object>> resetPassword(@Header("x-app-token") String token,@Body Map<String, Object> params);

    @POST(ApiAddress.USER_PROTOCOL)
    Observable<BaseResponse<Object>> updatePwd(@Header("x-app-token") String token,@Body Map<String, Object> params);

    @POST(ApiAddress.USER_REGISTER)
    Observable<BaseResponse<Object>> register(@Body Map<String, Object> params);

    @POST(ApiAddress.USER_SEND_MESSAGE)
    Observable<BaseResponse<Object>> sendMessage(@Body Map<String, Object> params);


    @POST(ApiAddress.SEND_CODE_RESET)
    Observable<BaseResponse<Object>> sendCodeReset(@Body Map<String, Object> params);

    @POST(ApiAddress.SENDCODE)
    Observable<BaseResponse<Object>> sendCode(@Header("x-app-token") String token,@Body Map<String, Object> params);

    @POST(ApiAddress.USER_CHECK_WITHDRAW)
    Observable<BaseResponse<Boolean>> checkWithdraw(@Header("x-app-token") String token);

    @POST(ApiAddress.PLACE_ORDER)
    Observable<BaseResponse<Object>> placeAnOrder(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.USDT_RATE)
    Observable<BaseResponse<Double>> usdtRate();

    @POST(ApiAddress.USER_ASSETS)
    Observable<BaseResponse<AssetsBean>> userAssets(@Header("x-app-token") String token);

    @POST(ApiAddress.USER_COIN)
    Observable<BaseResponse<List<CoinBean>>> userCoin(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.SYMBOL_LEVERAGE)
    Observable<BaseResponse<List<LeverageBean>>> getLeverage(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.COLSE_POSTION)
    Observable<BaseResponse<List<LeverageBean>>> closePosition(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.ALL_ORDERS)
    Observable<BaseResponse<OrdersBean>> getAllOrders(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.GETMEMBERRECIVEITEM)
    Observable<BaseResponse<List<NewPayTypeBean>>> getMemberReciveItem(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.CREATE_REAL_NAME)
    Observable<BaseResponse<Object>> commitRealName(@Body Map<String, Object> param, @Header("x-app-token") String userToken);

    @POST(ApiAddress.ASSETS_RECORD)
    Observable<BaseResponse<RecordBean>> assetsRecord(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

    @POST(ApiAddress.WITHDRAW_FINDWITHDRAWRECORD)
    Observable<BaseResponse<CoinRecordBean>> findWithdrawRecord(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

    @POST(ApiAddress.KLINE_HISTORY)
    Observable<BaseResponse<KlineDataBean>> klineHistory(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

    @POST(ApiAddress.WITHDRAW_CHECK)
    Observable<BaseResponse<WithdrawLimitBean>> withdrawLimit(@Header("x-app-token") String userToken, @Path("coinId") String coinId);

    @POST(ApiAddress.WITHDRAW_COIN)
    Observable<BaseResponse<Object>> withdrawCoin(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

    @POST(ApiAddress.OTC_WITHDRAW)
    Observable<BaseResponse<WithdrawBean>> inoutWithdraw(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

    @POST(ApiAddress.PLACE_POSITION)
    Observable<BaseResponse<Object>> placePosition(@Header("x-app-token") String userToken,@Path("orderId") long orderId);

    @POST(ApiAddress.OTC_REMOVEMEMBERPAYTYPE)
    Observable<BaseResponse<Object>> removeMemberPayType(@Header("x-app-token") String userToken,@Body Map<String, Object> param);

    @POST(ApiAddress.APP_BANNER)
    Observable<BaseResponse<BannerItemBean>> appBanner(@Header("x-app-token") String userToken);


    @POST(ApiAddress.DEAL_LIST)
    Observable<BaseResponse<List<DealItemBean>>> getDealList(@Body Map<String, Object> param);

    @POST(ApiAddress.FUNCLIST)
    Observable<BaseResponse<FuncListBean>> getFuncList(@Header("x-app-token") String userToken);

    @POST(ApiAddress.OTC_CFG)
    Observable<BaseResponse<OTCcfgBean>> getOtcCfg(@Header("x-app-token") String userToken);

    @POST(ApiAddress.PAY_TYPE)
    Observable<BaseResponse<List<PayTypeBean>>> getPayType(@Header("x-app-token") String userToken);

    @POST(ApiAddress.RECHARGE)
    Observable<BaseResponse<RechargeBean>> getRecharge(@Header("x-app-token") String userToken,@Body Map<String, Object> param);

    @POST(ApiAddress.ADDMEMBERPAYTYPE)
    Observable<BaseResponse<Object>> addMemberPayType(@Header("x-app-token") String userToken,@Body Map<String, Object> param);

    @POST(ApiAddress.TRANSFERRED)
    Observable<BaseResponse<Object>> getTransferred(@Header("x-app-token") String userToken,@Path("id") int id);

    @POST(ApiAddress.CANCEL)
    Observable<BaseResponse<Object>> Cancel(@Header("x-app-token") String userToken,@Path("id") int id);

    @POST(ApiAddress.OTC_DETAIL)
    Observable<BaseResponse<OtcDetailBean>> getOtcDetail(@Header("x-app-token") String userToken, @Path("id") int id);

    @POST(ApiAddress.DEPTH)
    Observable<BaseResponse<List<TradeDepthBean>>> getDepth(@Header("x-app-token") String token, @Body Map<String, Object> param);


    @POST(ApiAddress.UPDATE_URL)
    Observable<BaseResponse<UpdateBean>> UpDate(@Header("x-app-token") String token);

    @POST(ApiAddress.MARKETLIST)
    Observable<BaseResponse<List<MarketListBean>>> getMarketList(@Header("x-app-token") String token);

    @POST(ApiAddress.FINDBYPAGEAPPLYCOIN)
    Observable<BaseResponse<NewApplyBean>> getFindByPageApplyCoin(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.GETINVITERECORD)
    Observable<BaseResponse<List<InviteBean>>> getInviteRecord(@Header("x-app-token") String token);

    @POST(ApiAddress.FINDBYPAGE)
    Observable<BaseResponse<NoticeBean>> getNotice(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.FINDBYID)
    Observable<BaseResponse<NoticeBean.RowsBean>> getNoticeListInfo(@Header("x-app-token") String token, @Body Map<String, Object> param);


    @POST(ApiAddress.APPLYCOIN)
    Observable<BaseResponse<Object>> getApplyCoin(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.INFOBYSYMBOL)
    Observable<BaseResponse<InfoBySymbolBean>> getInfoBySymbol(@Header("x-app-token") String token, @Body Map<String, Object> param);


    @POST(ApiAddress.GETASSETSBYCOINID)
    Observable<BaseResponse<NewAssetsBean>> getAssetsByCoinId(@Header("x-app-token") String token, @Body Map<String, Object> param);

    @POST(ApiAddress.ProfitLossRate)
    Observable<BaseResponse<Object>> getProfitLossRate(@Header("x-app-token") String token, @Body Map<String, Object> param);


    @POST(ApiAddress.FINDMEMBERBILL)
    Observable<BaseResponse<AssetsRecordBean>> getFindMemberBill(@Header("x-app-token") String userToken, @Body Map<String, Object> param);


    @POST(ApiAddress.WITHDRAWCANCLE)
    Observable<BaseResponse<Object>> withdrawCancle(@Header("x-app-token") String userToken, @Body Map<String, Object> param);

}
