package com.temp.buda.util;

import java.text.ParseException;
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
    public static String StrformatTime(String time) {
        String now = "";
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm MM/dd");
            Date date = sf.parse(time);
            now = sf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return now;
    }
}
