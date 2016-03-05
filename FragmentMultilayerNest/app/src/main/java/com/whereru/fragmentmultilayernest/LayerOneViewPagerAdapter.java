package com.whereru.fragmentmultilayernest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//用Fragment的时候就是把PagerAdapter换成了FragmentPagerAdapter
public class LayerOneViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mLayerOneViewPagerFragmentList;

    public LayerOneViewPagerAdapter(FragmentManager fragmentManager,
                                    List<Fragment> layerOneViewPagerFragmentList) {
        super(fragmentManager);
        mLayerOneViewPagerFragmentList = layerOneViewPagerFragmentList;
    }

    @Override
    public int getCount() {
        return mLayerOneViewPagerFragmentList.size();
    }

    @Override
    public Fragment getItem(int i) {
        return mLayerOneViewPagerFragmentList.get(i);
    }

}
