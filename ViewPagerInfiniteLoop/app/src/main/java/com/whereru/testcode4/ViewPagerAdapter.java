package com.whereru.testcode4;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<TextView> mList;

    public ViewPagerAdapter(List<TextView> mList) {
        this.mList = mList;
    }

    @Override
    public int getCount() {
//            return Integer.MAX_VALUE;
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mList.get(position % mList.size()));
//            return mList.get(position % mList.size());
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mList.get(position % mList.size()));
        container.removeView(mList.get(position));
    }

}
