package com.whereru.fragmentmultilayernest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LayerOneViewPagerFragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layerOneFragmentOneView = inflater.inflate(
                R.layout.layer_one_fragment_one, container, false);
        return layerOneFragmentOneView;
    }

}
