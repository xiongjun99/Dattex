package com.temp.buda.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AutoSwipeRefreshLayout extends SwipeRefreshLayout {
    private float xDown;
    private float yDown;
    private float xDistance;
    private float yDistance;

    public AutoSwipeRefreshLayout(Context context) {
        super(context);
        this.init();
    }

    public AutoSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        this.setColorSchemeColors(Color.parseColor("#FF6100"));
        this.setDistanceToTriggerSync(200);
    }

    public void autoRefresh() {
        try {
            Field mCircleView = SwipeRefreshLayout.class.getDeclaredField("mCircleView");
            mCircleView.setAccessible(true);
            View progress = (View)mCircleView.get(this);
            progress.setVisibility(VISIBLE);
            ObjectAnimator animatorX = ObjectAnimator.ofFloat(progress, "scaleX", new float[]{0.0F, 1.0F});
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(progress, "scaleY", new float[]{0.0F, 1.0F});
            animatorX.setDuration(500L);
            animatorY.setDuration(500L);
            animatorX.start();
            animatorY.start();
            Method setRefreshing = SwipeRefreshLayout.class.getDeclaredMethod("setRefreshing", Boolean.TYPE, Boolean.TYPE);
            setRefreshing.setAccessible(true);
            setRefreshing.invoke(this, true, true);
        } catch (NoSuchFieldException var6) {
            var6.printStackTrace();
        } catch (IllegalAccessException var7) {
            var7.printStackTrace();
        } catch (NoSuchMethodException var8) {
            var8.printStackTrace();
        } catch (InvocationTargetException var9) {
            var9.printStackTrace();
        }

    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch(ev.getAction()) {
            case 0:
                this.xDown = ev.getX();
                this.yDown = ev.getY();
                break;
            case 2:
                this.xDistance = Math.abs(ev.getX() - this.xDown);
                this.yDistance = Math.abs(ev.getY() - this.yDown);
        }

        return this.xDistance < this.yDistance ? super.onInterceptTouchEvent(ev) : false;
    }

}
