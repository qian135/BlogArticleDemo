package com.whereru.fragmentmultilayernest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class LayerTwoViewPagerTwoAdapter extends FragmentPagerAdapter {

    private List<Fragment> mLayerTwoViewPagerTwoFragmentList;

    public LayerTwoViewPagerTwoAdapter(FragmentManager fragmentManager,
                                       List<Fragment> layerTwoViewPagerTwoFragmentList) {
        super(fragmentManager);
        mLayerTwoViewPagerTwoFragmentList = layerTwoViewPagerTwoFragmentList;
    }

    @Override
    public int getCount() {
        return mLayerTwoViewPagerTwoFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mLayerTwoViewPagerTwoFragmentList.get(position);
    }

}
