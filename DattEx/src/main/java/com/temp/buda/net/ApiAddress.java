package com.temp.buda.net;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.net
 * @FileName     : ApiAddress.java
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
public class ApiAddress {

    public static final String HELP_CENTER_URL = "http://www.baidu.com";
    public static final String NEWS_NOTICE_URL = "http://www.baidu.com";
    public static final String NOTICE_LIST_URL = "http://www.baidu.com";
    public static final String NOTICE_DETAIL_URL = "http://www.baidu.com";
    public static final String USER_PROTOCOL_URL = "http://www.baidu.com";
    public static final String USER_INVITED_URL = "http://www.baidu.com";
    public static final String BASE_URL = "http://app.buda.tc:8088"; //生产
    public static final String WEB_URL = "http://app.buda.tc";//WEB生产
    public static final String KF_URL = "http://kf.buda.tc"; //生产
//  public static final String BASE_URL = "http://app.dattex.cc:8088"; //
//  public static final String BASE_URL = "http://45.132.238.178:8088";//测试
//    public static final String WEB_URL = "http://45.132.238.178";//BANNER测试
    public static final String WEB_SOCKET_URL = "ws://8.210.211.248:10088/imserver/10000";
    public static final String UPDATE_URL = "/app/common/version";
//  public static final String WEB_SOCKET_URL = "ws://tanjj.zhangyueyouxi.com:10001/imserver/10000";
    public static final String DEAL_LIST = "/app/market/trade";
    public static final String APP_BANNER = "/app/common/getBannerList";
    public static final String SYMBOL_CONFIG = "/app/exchange/config/coin-list";
    public static final String LOGIN_URL = "/app/login";
    public static final String USER_PROTOCOL = "/app/member/updatePwd";
    public static final String RESET_PASSWORD = "/app/member/resetPwd";
    public static final String USER_REGISTER = "/app/reg";
    public static final String USER_SEND_MESSAGE = "/app/send-code-reg";
    public static final String SEND_CODE_RESET = "/app/sendCodeReset";

    public static final String SENDCODE = "/app/sendCode";
    public static final String FUNCLIST = "/app/common/getFuncList";
    public static final String USER_CHECK_WITHDRAW = "/app/common/withdraw/judge";
    public static final String PLACE_ORDER = "/app/exchange/addOrder";
    public static final String USDT_RATE = "/app/market/usdtcny";
    public static final String USER_ASSETS = "/app/wallet/assets";
    public static final String USER_COIN = "/app/coin/list";
    public static final String SYMBOL_LEVERAGE = "/app/exchange/config/lever-list";
    public static final String COLSE_POSTION = "/app/exchange/quitOrder/{orderId}";
    public static final String ALL_ORDERS = "/app/exchange/page";
    public static final String GETMEMBERRECIVEITEM = "/app/otc/getMemberReciveItem";
    public static final String DEPTH = "/app/market/depth";
    public static final String FINDBYPAGEAPPLYCOIN = "/app/subscribe/findByPageApplyCoins";
    public static final String APPLYCOIN = "/app/subscribe/applyCoin";
    public static final String INFOBYSYMBOL = "/app/exchange-coin/getInfoBySymbol";
    public static final String CREATE_REAL_NAME = "/app/member/create-real-name";
    public static final String ASSETS_RECORD = "/app/otc/inout/list";
    public static final String KLINE_HISTORY = "/app/market/history/kline";
    public static final String WITHDRAW_CHECK = "/app/wallet/withdraw-conf/{coinId}";
    public static final String WITHDRAW_FINDWITHDRAWRECORD = "/app/wallet/findWithdrawRecord";
    public static final String WITHDRAW_COIN = "/app/wallet/withdraw";
    public static final String PLACE_POSITION = "/app/exchange/quitOrder/{orderId}";
    public static final String OTC_CFG = "/app/otc/cfg";
    public static final String PAY_TYPE = "/app/otc/pay-type";
    public static final String TRANSFERRED = "/app/otc/inout/transferred/{id}";
    public static final String CANCEL = "/app/otc/inout/cancel/{id}";
    public static final String RECHARGE = "/app/otc/inout/recharge";
    public static final String OTC_DETAIL = "/app/otc/inout/detail/{id}";
    public static final String OTC_REMOVEMEMBERPAYTYPE = "/app/otc/removeMemberPayType";
    public static final String OTC_WITHDRAW = "/app/otc/inout/withdraw";
    public static final String GETASSETSBYCOINID = "/app/wallet/getAssetsByCoinId";
    public static final String MARKETLIST = "/app/market/list";
    public static final String ProfitLossRate = "/app/exchange/setProfitLossRate";
    public static final String ADDMEMBERPAYTYPE = "/app/otc/addMemberPayType";
    public static final String FINDBYPAGE = "/app/article/findByPage";
    public static final String FINDBYID = "/app/article/findById";
    public static final String FINDMEMBERBILL = "/app/wallet/findMemberBill";
    public static final String WITHDRAWCANCLE = "/app/wallet/withdrawCancle";
    public static final String GETINVITERECORD = "/app/member/getInviteRecord";

}

