package com.temp.dattex.net;

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
    public static final String BASE_URL = "http://121.40.199.201:10088";
    public static final String WEB_SOCKET_URL = "ws://tanjj.zhangyueyouxi.com:10001/imserver/10000";
    public static final String DEAL_LIST = "app/market/trade";
    public static final String APP_BANNER = "/app/banner/list";
    public static final String SYMBOL_CONFIG = "/app/exchange/config/coin-list";
    public static final String LOGIN_URL = "/app/login";
    public static final String USER_PROTOCOL = "/app/login";
    public static final String RESET_PASSWORD = "";
    public static final String USER_REGISTER = "/app/reg";
    public static final String USER_SEND_MESSAGE = "/app/send-code-reg";
    public static final String USER_CHECK_WITHDRAW = "/app/common/withdraw/judge";
    public static final String PLACE_ORDER = "/app/exchange/addOrder";
    public static final String USDT_RATE = "/app/market/usdtcny";
    public static final String USER_ASSETS = "/app/wallet/assets";
    public static final String SYMBOL_LEVERAGE = "/app/exchange/config/lever-list";
    public static final String COLSE_POSTION = "/app/exchange/quitOrder/{orderId}";
    public static final String ALL_ORDERS = "/app/exchange/page";
    public static final String DEPTH = " /app/market/depth";
    public static final String CREATE_REAL_NAME = "/app/member/create-real-name";
    public static final String ASSETS_RECORD = "/app/wallet/assets-rec";
    public static final String KLINE_HISTORY = "/app/market/history/kline";
    public static final String WITHDRAW_CHECK = "/app/wallet/withdraw-conf/{coinId}";
    public static final String WITHDRAW_COIN = "/app/wallet/withdraw";
    public static final String PLACE_POSITION = "/app/exchange/quitOrder/{orderId}";

    public static final String OTC_CFG = "/app/otc/cfg";
    public static final String PAY_TYPE = "/app/otc/pay-type";
    public static final String TRANSFERRED = "/app/otc/inout/transferred/{id}";
    public static final String CANCEL = "/app/otc/inout/cancel/{id}";
    public static final String RECHARGE = "/app/otc/inout/recharge";
    public static final String OTC_DETAIL = "/app/otc/inout/detail/{id}";

}
