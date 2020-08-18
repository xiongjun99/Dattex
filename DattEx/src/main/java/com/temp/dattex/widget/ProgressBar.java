package com.temp.dattex.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.exchange.utilslib.DisplayUtil;
import com.temp.dattex.R;

import java.util.ArrayList;
import java.util.List;

public class ProgressBar extends LinearLayout {

    private float progressLineHeight = 20f;
    private int pointCount = 0;
    private float viewHeight;
    private float viewWidth;
    private float progressCircleRadius = 30;
    private float progressPointRadius = 25;
    private float progressEndRadius = 25;
    private int progress = 0;
    private List<Integer> list = new ArrayList<>();

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public ProgressBar(Context context) {
        super(context);
        initView(context);
    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        setWillNotDraw(false);
        progressBackgroundPaint.setColor(context.getResources().getColor(R.color.color_FF4D5273));
        clearCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        progressPaint.setColor(Color.BLUE);
        LinearGradient linearGradient = new LinearGradient(
                0,
                viewHeight / 2,
                viewWidth + progressPointRadius * 2,
                viewHeight / 2,
                getResources().getColor(R.color.color_FF47BAFC),
                getResources().getColor(R.color.color_FF5B70FC),
                Shader.TileMode.CLAMP
        );
        progressPaint.setShader(linearGradient);
        progressLineHeight = DisplayUtil.dp2px(context, 4);
        progressPointRadius = DisplayUtil.dp2px(context, 3);
        progressCircleRadius = DisplayUtil.dp2px(context, 4);
        progressEndRadius = DisplayUtil.dp2px(context, 6);
    }

    private float intervalWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        if (progressLineHeight > h) {
            progressLineHeight = h;
        }
        viewHeight = h;
        viewWidth = w - progressCircleRadius * 2 - getPaddingLeft() - getPaddingRight();
        if (getPointCount() > 0) {
            intervalWidth = viewWidth / getPointCount();
        } else {
            setPointCount(1);
        }
    }

    private Paint progressBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint clearCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float canvasHalfHeight = viewHeight / 2;
        float halfHeight = (progressLineHeight / 2);
        int sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawRoundRect(new RectF(getPaddingLeft() + progressCircleRadius, canvasHalfHeight - halfHeight, progressCircleRadius + viewWidth, canvasHalfHeight + halfHeight), halfHeight, halfHeight, progressBackgroundPaint);
        for (int i = 0; i <= getPointCount(); i++) {
            float x = getPaddingLeft() + progressCircleRadius + intervalWidth * i;
            canvas.drawCircle(x, viewHeight / 2, progressCircleRadius, clearCirclePaint);
        }
        canvas.restoreToCount(sc);

        for (int i = 0; i <= getPointCount(); i++) {
            float x = getPaddingLeft() + progressCircleRadius + intervalWidth * i;
            canvas.drawCircle(x, viewHeight / 2, progressPointRadius, progressBackgroundPaint);
        }

        sc = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);

        float progressRight = getPaddingLeft() + progressCircleRadius + viewWidth / list.get(getPointCount()) * progress;
        canvas.drawRoundRect(new RectF(getPaddingLeft() + progressCircleRadius, canvasHalfHeight - halfHeight, progressRight, canvasHalfHeight + halfHeight), halfHeight, halfHeight, progressPaint);

        for (int i = 0; i <= getPointCount(); i++) {
            float x = getPaddingLeft() + progressCircleRadius + intervalWidth * i;
            if (x <= progressRight) {
                canvas.drawCircle(x, viewHeight / 2, progressCircleRadius, clearCirclePaint);
            } else {
                break;
            }
        }
        canvas.restoreToCount(sc);

        for (int i = 0; i <= getPointCount(); i++) {
            float x = getPaddingLeft() + progressCircleRadius + intervalWidth * i;
            if (x <= progressRight) {
                canvas.drawCircle(x, viewHeight / 2, progressPointRadius, progressPaint);
            } else {
                break;
            }
        }
        canvas.drawCircle(progressRight, viewHeight / 2, progressEndRadius, progressPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int progress = (int) (event.getX() * list.get(getPointCount()) / viewWidth);
                int diffNum = Math.abs(list.get(0) - progress);
                int result = list.get(0);
                for (Integer integer : list)
                {
                    int diffNumTemp = Math.abs(integer - progress);
                    if (diffNumTemp < diffNum)
                    {
                        diffNum = diffNumTemp;
                        result = integer;
                    }
                }
                setProgress(result);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    public void setProgress(int progress) {
        if (progress != this.progress) {
            if (progress < 0) {
                this.progress = 0;
            } else this.progress = Math.min(progress, list.get(getPointCount()));
            if (null != onProgressChangeListener) {
                onProgressChangeListener.onProgressChanged(this.progress);
            }
        }
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }

    private OnProgressChangeListener onProgressChangeListener;

    public interface OnProgressChangeListener {
        void onProgressChanged(int progress);
    }
    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }
}
