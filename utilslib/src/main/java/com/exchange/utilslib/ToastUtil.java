package com.exchange.utilslib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;


/**
 *
 */
public class ToastUtil {

    public final static int TOAST_OF_WARING = 1;
    public final static int TOAST_OF_ERROR = 2;
    public final static int TOAST_OF_SUCCESS = 0;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;
    private static Object synObj = new Object();

    private ToastUtil() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        showMessage(context, context.getApplicationContext().getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        showMessage(context, context.getApplicationContext().getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        showMessage(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        showMessage(context.getApplicationContext(), text, duration);
    }

    public static void show(Context context, int resId, Object... args) {
        showMessage(context.getApplicationContext(), String.format(context.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        showMessage(context.getApplicationContext(), String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        showMessage(context.getApplicationContext(), String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        showMessage(context.getApplicationContext(), String.format(format, args), duration);
    }

    private static void showMessage(final Context context, final CharSequence text,
                                    final int duration) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null) {
                    toast.setText(text);
                    toast.setDuration(duration);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(), text, duration);
                }
                toast.setGravity(Gravity.CENTER, 0, 10);
                toast.show();
            }

        });
    }

    public static void cancel() {
        handler.removeCallbacksAndMessages(null);
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }


}
