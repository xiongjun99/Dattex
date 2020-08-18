package com.exchange.utilslib;

import android.os.Handler;
import android.os.Looper;


/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.common.framework.utils
 * @FileName     : LooperUtil.java
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
public class LooperUtil {
    private static Handler handler;

    private LooperUtil() {
    }

    public static Handler getHandler() {
        if (null == handler) {
            synchronized (LooperUtil.class) {
                if (null == handler) {
                    handler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return handler;
    }
}
