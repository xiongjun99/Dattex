package com.temp.dattex.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.temp.dattex.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /**
     * 保留小数点后2位
     *
     * @return
     */
    public static String keepTwo(double b) {
        DecimalFormat format = new DecimalFormat("#0.00");
        String str = format.format(b);
        return str;
    }

    public static String dateTostring(Long date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateOld = new Date(date);
        String str=sdf.format(dateOld);
        return str;
    }
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
