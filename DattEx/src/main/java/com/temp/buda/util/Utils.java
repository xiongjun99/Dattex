package com.temp.buda.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String keepTwo(double b) {
        DecimalFormat format = new DecimalFormat("#0.00");
        String str = format.format(b);
        return str;
    }
    public static String FloatkeepTwo(float b) {
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
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    public  static  boolean compareTo(String str,String str1){
        BigDecimal a = new BigDecimal (str);
        BigDecimal b = new BigDecimal (str1);

        if(a.compareTo(b) == 1) {
            System.out.println("a大于b");
            return false;
        }
            return true;
    }

    public static String subtraction(String value_1,String value_2) {
        BigDecimal bd = new BigDecimal(value_1);
        BigDecimal bd2 = new BigDecimal(value_2);
        BigDecimal bd3 = bd.subtract(bd2);
        System.out.println("--------------差  是：" + bd3);
        return bd3.toString();
    }
    public static String add(String value_1,String value_2) {
        BigDecimal bd = new BigDecimal(value_1);
        BigDecimal bd2 = new BigDecimal(value_2);
        BigDecimal bd3 = bd.add(bd2);
        System.out.println("--------------加法  是：" + bd3);
        return bd3.toString();
    }

    public static String multiply(String value_1,String value_2) {
        BigDecimal bd = new BigDecimal(value_1);
        BigDecimal bd2 = new BigDecimal(value_2);
        BigDecimal bd3 = bd.multiply(bd2);
        System.out.println("--------------乘法  是：" + bd3);
        return bd3.toString();
    }

    public static String divide(String value_1,String value_2) {
        BigDecimal bd = new BigDecimal(value_1);
        BigDecimal bd2 = new BigDecimal(value_2);
        BigDecimal bd3 =  bd.divide(bd2,8,RoundingMode.DOWN);
        return bd3.toString();
    }
    public static String format0(float value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.toString();
    }

    public static String format0(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.toString();
    }
    public static String format(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }
    public static String format8(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(8, RoundingMode.HALF_UP);
        return bd.toString();
    }
    public static String formatPlainString(String value) {
        BigDecimal bd = new BigDecimal(value);
        return bd.toPlainString();
    }

    public static String format4(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.toString();
    }
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void setClipboard(Context context, String str){
//获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
// 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", str);
// 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }
    /**

     * 获取版本号

     * @return 当前应用的版本号

     */

    public static String getVersion(Context context) {

        try {

            PackageManager manager = context.getPackageManager();

            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);

            String version = info.versionName;

            return version;

        } catch (Exception e) {

            e.printStackTrace();

            return "找不到版本号";

        }

    }

    public static int compareVersion(String s1, String s2) {
        String[] s1Split = s1.split("\\.", -1);
        String[] s2Split = s2.split("\\.", -1);
        int len1 = s1Split.length;
        int len2 = s2Split.length;
        int lim = Math.min(len1, len2);
        int i = 0;
        while (i < lim) {
            int c1 = "".equals(s1Split[i]) ? 0 : Integer.parseInt(s1Split[i]);
            int c2 = "".equals(s2Split[i]) ? 0 : Integer.parseInt(s2Split[i]);
            if (c1 != c2) {
                return c1 - c2;
            }
            i++;
        }
        return len1 - len2;
    }
    public  static void DownLoadAPK(Context context ,String url){
        if (downLoadMangerIsEnable(context)==true){
            DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//设置下载的文件存储的地址，我们这里将下载的apk文件存在/Download目录下面
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Dattex.apk");
//设置现在的文件可以被MediaScanner扫描到。
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(0);
//设置通知的标题
            request.setTitle("Dattex下载");
//设置下载的时候Notification的可见性
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//设置下载文件类型
            request.setMimeType("application/vnd.android.package-archive");
            long id = downloadManager.enqueue(request);
            listener(context,id,Environment.DIRECTORY_DOWNLOADS+"Dattex.apk");
//            if (id == 100){
//                System.out.println("----------"+id);
//                install(context,Environment.DIRECTORY_DOWNLOADS+"Dattex.apk");
//                installAuto(context,Environment.DIRECTORY_DOWNLOADS+"Dattex.apk");
//            }
        }else {
            System.out.println("---系统暂时无法下载");
        }
    }
    private static BroadcastReceiver broadcastReceiver;
    private static void listener(Context context ,final long Id ,String path) {
        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ID == Id) {
                    Toast.makeText(context, " 下载完成!", Toast.LENGTH_LONG).show();
                    installAuto(context , path);
                } else {
                }
            }
        };
        context.registerReceiver(broadcastReceiver, intentFilter);
    }


    private static void install(Context context,String path) {
        Uri uri;
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //如果是7.0以上的系统，要使用FileProvider的方式构建Uri
            uri = FileProvider.getUriForFile(context, "com.hm.retrofitrxjavademo.fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }
    /**
     * 判断 系统下载功能是否可用
     *
     * @return true  可用 false 不可用
     */
    static boolean downLoadMangerIsEnable(Context context) {
        try {
            int state = context.getApplicationContext().getPackageManager()
                    .getApplicationEnabledSetting("com.android.providers.downloads");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return !(state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
                        state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                        || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED);
            } else {
                return !(state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
                        state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER);
            }
        } catch (Exception e) {
            Log.e("Exception",e.getMessage());
        }
        return false;
    }
    public static void installAuto(Context context ,String apkPath) {
        System.out.println("---------"+apkPath);
        Intent localIntent = new Intent(Intent.ACTION_VIEW);
        localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri;
        /**
         * Android7.0+禁止应用对外暴露file://uri，改为content://uri；具体参考FileProvider
         */
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context, "com.science.fileprovider", new File(apkPath));
            localIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(new File(apkPath));
        }
        localIntent.setDataAndType(uri, "application/vnd.android.package-archive"); //打开apk文件
        context.startActivity(localIntent);
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE",
     "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_NETWORK_STATE"};
    public static void verifyStoragePermissions(Activity activity) {
        try {
        //检测是否有写的权限

       int permission = ActivityCompat.checkSelfPermission(activity,

         "android.permission.WRITE_EXTERNAL_STORAGE");

       if (permission != PackageManager.PERMISSION_GRANTED) {

       // 没有写的权限，去申请写的权限，会弹出对话框

         ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
     }

       } catch (Exception e) {

        e.printStackTrace();

        }
   }

    /**
     * 全透状态栏
     */
    public static void setStatusBarFullTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
