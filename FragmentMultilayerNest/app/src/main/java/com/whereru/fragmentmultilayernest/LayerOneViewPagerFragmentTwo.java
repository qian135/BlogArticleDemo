package com.whereru.fragmentmultilayernest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class LayerOneViewPagerFragmentTwo extends Fragment {

    private ViewPager mLayerTwoViewPagerTwo;
    private List<Fragment> mLayerTwoViewPagerTwoFragmentList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layerOneFragmentTwoView = inflater.inflate(
                R.layout.layer_one_fragment_two, container, false);

        mLayerTwoViewPagerTwoFragmentList = new ArrayList<>();
        mLayerTwoViewPagerTwoFragmentList.add(new LayerTwoViewPagerTwoFragmentOne());
        mLayerTwoViewPagerTwoFragmentList.add(new LayerTwoViewPagerTwoFragmentTwo());
        mLayerTwoViewPagerTwoFragmentList.add(new LayerTwoViewPagerTwoFragmentThree());

        mLayerTwoViewPagerTwo = (ViewPager) layerOneFragmentTwoView.
                findViewById(R.id.layer_two_view_pager_two);
        mLayerTwoViewPagerTwo.setAdapter(new LayerOneViewPagerAdapter(
                getActivity().getSupportFragmentManager(), mLayerTwoViewPagerTwoFragmentList));


        return layerOneFragmentTwoView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
