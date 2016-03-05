package com.whereru.fragmentmultilayernest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class LayerTwoViewPagerTwoFragmentTwo extends Fragment {

    private ListView mLayerTwoViewPagerTwoFragmentTwoListView;
    private List<String> mLayerTwoViewPagerTwoFragmentTwoListViewData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layerTwoViewPagerTwoFragmentTwoView = inflater.inflate(
                R.layout.layer_two_view_pager_two_fragment_two, container, false);

        mLayerTwoViewPagerTwoFragmentTwoListView = (ListView) layerTwoViewPagerTwoFragmentTwoView.
                findViewById(R.id.layer_two_view_pager_two_fragment_two_list_view);

        mLayerTwoViewPagerTwoFragmentTwoListViewData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mLayerTwoViewPagerTwoFragmentTwoListViewData.add("item" + i);
        }
        mLayerTwoViewPagerTwoFragmentTwoListView.setAdapter(
                new LayerTwoViewPagerTwoFragmentTwoListViewAdapter(
                        getActivity().getApplicationContext(),
                        mLayerTwoViewPagerTwoFragmentTwoListViewData));

        return layerTwoViewPagerTwoFragmentTwoView;

    }

}
