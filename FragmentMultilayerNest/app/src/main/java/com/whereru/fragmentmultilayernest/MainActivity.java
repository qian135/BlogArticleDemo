package com.whereru.fragmentmultilayernest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mLayerOneViewPager;
    private List<Fragment> mLayerOneFragmentList;

    private Button mLayerOneFragmentOneButton;
    private Button mLayerOneFragmentTwoButton;
    private Button mLayerOneFragmentThreeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayerOneFragmentList = new ArrayList<>();
        mLayerOneFragmentList.add(new LayerOneViewPagerFragmentOne());
        mLayerOneFragmentList.add(new LayerOneViewPagerFragmentTwo());
        mLayerOneFragmentList.add(new LayerOneViewPagerFragmentThree());

        mLayerOneViewPager = (ViewPager) findViewById(R.id.layer_one_view_pager);
        mLayerOneViewPager.setAdapter(
                new LayerOneViewPagerAdapter(getSupportFragmentManager(), mLayerOneFragmentList));

        mLayerOneFragmentOneButton = (Button) findViewById(R.id.layer_one_fragment_one_button);
        mLayerOneFragmentOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayerOneViewPager.setCurrentItem(0);
            }
        });
        mLayerOneFragmentTwoButton = (Button) findViewById(R.id.layer_one_fragment_two_button);
        mLayerOneFragmentTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayerOneViewPager.setCurrentItem(1);
            }
        });
        mLayerOneFragmentThreeButton = (Button) findViewById(R.id.layer_one_fragment_three_button);
        mLayerOneFragmentThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayerOneViewPager.setCurrentItem(2);
            }
        });

    }

}
