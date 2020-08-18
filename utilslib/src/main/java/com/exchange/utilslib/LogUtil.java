package com.exchange.utilslib;

import android.util.Log;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.common.framework.utils
 * @FileName     : LogUtil.java
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
public class LogUtil {
    private static String TAG = "DattEx";

    public static void e(Object o) {
        if (null != o) {
            Log.e(TAG, o.toString());
        } else {
            Log.e(TAG, "Log object is null");
        }
    }

    public static void d(Object o) {
        if (null != o) {
            Log.d(TAG, o.toString());
        } else {
            Log.d(TAG, "Log object is null");
        }
    }

}
