package com.whereru.testcode3;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class MyGridView extends GridView {

    private boolean mIsOnMeasure;

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mIsOnMeasure = true;
//        System.out.println("onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mIsOnMeasure = false;
//        System.out.println("onLayout");
        super.onLayout(changed, l, t, r, b);
    }

    public boolean isOnMeasure() {
        return mIsOnMeasure;
    }
}
