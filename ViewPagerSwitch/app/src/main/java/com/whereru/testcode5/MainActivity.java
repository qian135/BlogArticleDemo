package com.whereru.testcode5;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> mViewPagerListView;

    private ViewPager mViewPager;

    private ImageView mSlideBarImageView;

    private int mSlideBarDivideNumber = 3;//顶部滑动部分划分的块数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_home_page);

        mViewPagerListView = new ArrayList<>();
        mViewPagerListView.add(getLayoutInflater().inflate(R.layout.campus_activity, null));
        mViewPagerListView.add(getLayoutInflater().inflate(R.layout.campus_competition, null));
        mViewPagerListView.add(getLayoutInflater().inflate(R.layout.campus_team, null));

        mViewPager = (ViewPager) findViewById(R.id.campus_home_viewpager);
        mViewPager.setAdapter(new MyViewPagerAdapter(mViewPagerListView));

        mSlideBarImageView = (ImageView) findViewById(R.id.campus_home_page_slide_bar);

        View view = findViewById(R.id.campus_home_page_text_view_linear_layout);
        mViewPager.addOnPageChangeListener(new MyViewPagerOnPageChangeListener(
                this, view, mSlideBarImageView, mSlideBarDivideNumber));


    }


}
