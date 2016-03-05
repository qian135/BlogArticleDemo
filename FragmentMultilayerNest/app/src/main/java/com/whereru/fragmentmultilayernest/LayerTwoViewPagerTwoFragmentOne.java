package com.whereru.fragmentmultilayernest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LayerTwoViewPagerTwoFragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View layerTwoViewPagerTwoFragmentOneView = inflater.inflate(
                R.layout.layer_two_view_pager_two_fragment_one,container,false);
        return layerTwoViewPagerTwoFragmentOneView;
    }

}
