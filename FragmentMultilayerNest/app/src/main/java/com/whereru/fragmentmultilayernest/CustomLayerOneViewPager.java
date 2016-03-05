package com.whereru.fragmentmultilayernest;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomLayerOneViewPager extends ViewPager {

    public CustomLayerOneViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //禁止ViewPager通过手指滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

}

