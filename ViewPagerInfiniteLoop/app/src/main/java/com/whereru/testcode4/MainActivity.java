package com.whereru.testcode4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ViewPager.OnPageChangeListener {

    private MyHandler mMyHandler;

    private ViewPager mViewPager;

    private List<TextView> mList;

    //处理指示器的代码
    private ViewGroup mIndicatorPointLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();

        TextView textView0 = new TextView(this);
        textView0.setText("5");
        textView0.setTextSize(200);
        textView0.setGravity(Gravity.CENTER);
        mList.add(textView0);

        TextView textView1 = new TextView(this);
        textView1.setText("1");
        textView1.setTextSize(200);
        textView1.setGravity(Gravity.CENTER);
        mList.add(textView1);

        TextView textView2 = new TextView(this);
        textView2.setText("2");
        textView2.setTextSize(200);
        textView2.setGravity(Gravity.CENTER);
        mList.add(textView2);

        TextView textView3 = new TextView(this);
        textView3.setText("3");
        textView3.setTextSize(200);
        textView3.setGravity(Gravity.CENTER);
        mList.add(textView3);

        TextView textView4 = new TextView(this);
        textView4.setText("4");
        textView4.setTextSize(200);
        textView4.setGravity(Gravity.CENTER);
        mList.add(textView4);

        TextView textView5 = new TextView(this);
        textView5.setText("5");
        textView5.setTextSize(200);
        textView5.setGravity(Gravity.CENTER);
        mList.add(textView5);

        TextView textView6 = new TextView(this);
        textView6.setText("1");
        textView6.setTextSize(200);
        textView6.setGravity(Gravity.CENTER);
        mList.add(textView6);


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mList);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setCurrentItem(1);//从第一个开始，不是从第0个开始

        mViewPager.addOnPageChangeListener(this);

        mMyHandler = new MyHandler(this);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mMyHandler.postDelayed(this, 3000);
                Message message = new Message();
                message.what = 1;
                mMyHandler.sendMessage(message);
            }
        };
        mMyHandler.postDelayed(runnable, 3000);

        //处理指示器的代码
        mIndicatorPointLinearLayout = (ViewGroup) findViewById(R.id.indicator_point_linear_layout);
        mIndicatorPointLinearLayout.getChildAt(0).setBackgroundResource(
                R.drawable.indicator_point_selected);

    }

    static class MyHandler extends Handler {

        WeakReference<MainActivity> mMainActivityWeakReference;

        MyHandler(MainActivity mainActivity) {
            mMainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int pageIndex = mMainActivityWeakReference.get().mViewPager.getCurrentItem();
                    if (pageIndex == mMainActivityWeakReference.get().mList.size() - 2) {
                        mMainActivityWeakReference.get().mViewPager.setCurrentItem(1);
                        pageIndex = 1;//下面处理指示器的代码要用到
                    } else {
                        mMainActivityWeakReference.get().mViewPager.setCurrentItem(++pageIndex);
                    }

                    //处理指示器的代码

                    ViewGroup indicatorPointLinearLayout = mMainActivityWeakReference.get()
                            .mIndicatorPointLinearLayout;
                    for (int i = 0; i < indicatorPointLinearLayout.getChildCount(); i++) {
                        indicatorPointLinearLayout.getChildAt(i).setBackgroundResource(
                                R.drawable.indicator_point_normal);
                    }
                    //知道了实现这ViewPager循环的原理，就知道为什么这里为什么要pageIndex - 1了，
                    //因为ViewPager添加的View是7个，而点的View是5个
                    indicatorPointLinearLayout.getChildAt(pageIndex - 1).setBackgroundResource(
                            R.drawable.indicator_point_selected);

                    break;
            }
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        if (i == 0) {
            i = mList.size() - 2;
            mViewPager.setCurrentItem(i);
        } else {
            if (i == mList.size() - 1) {
                i = 1;
                mViewPager.setCurrentItem(i);
            }
        }

        //处理指示器的代码
        for (int j = 0; j < mIndicatorPointLinearLayout.getChildCount(); j++) {
            mIndicatorPointLinearLayout.getChildAt(j).setBackgroundResource(
                    R.drawable.indicator_point_normal);
        }
        //知道了实现这ViewPager循环的原理，就知道为什么这里为什么要i - 1了，
        //因为ViewPager添加的View是7个，而点的View是5个
        mIndicatorPointLinearLayout.getChildAt(i-1).setBackgroundResource(
                R.drawable.indicator_point_selected);

    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }

}
