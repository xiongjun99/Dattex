package com.temp.dattex.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*************************************************************************
 * Description   :
 *
 * @PackageName  : com.temp.dattex.util
 * @FileName     : DateFormatter.java
 * @Author       : chao
 * @Date         : 2020/6/20
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
public class DateFormatter {
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd hh:mm");

    public static String formatTime(long time) {
        return dateFormatter.format(new Date(time));

    }
}
