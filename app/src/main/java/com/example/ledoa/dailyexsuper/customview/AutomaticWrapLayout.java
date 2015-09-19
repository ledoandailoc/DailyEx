package com.example.ledoa.dailyexsuper.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by THUANCHAT on 7/24/15.
 */
public class AutomaticWrapLayout extends ViewGroup {
    private final static int VIEW_MARGIN_LR = 30;
    private final static int VIEW_MARGIN_TB = 30;
    private int mitemWidth;
    private int mpaddingSize = 0;
    int measureWidth;

    public AutomaticWrapLayout(Context context) {
        super(context);
    }

    public AutomaticWrapLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutomaticWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void calculator() {

        int count = getChildCount();
        mpaddingSize = VIEW_MARGIN_LR * (count + 1);
        mitemWidth = (measureWidth - mpaddingSize) / count;
        
    }

    @Override
    protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {

        calculator();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = this.getChildAt(i);
            int l = i * mitemWidth + (i + 1) * VIEW_MARGIN_LR;
            int t = VIEW_MARGIN_TB / 2;
            int r = l + mitemWidth;
            int b = mitemWidth + VIEW_MARGIN_TB/2;
            child.layout(l, t, r, b);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        calculator();
        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            child.measure(mitemWidth, measureWidth);
        }

        measureWidth = measureWidth(widthMeasureSpec);
        int measureHeight = mitemWidth + VIEW_MARGIN_TB;
        setMeasuredDimension(measureWidth, measureHeight);
    }


    private int measureWidth(int pWidthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(pWidthMeasureSpec);
        int widthSize = MeasureSpec.getSize(pWidthMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = widthSize;
                break;
        }
        return result;
    }
}
