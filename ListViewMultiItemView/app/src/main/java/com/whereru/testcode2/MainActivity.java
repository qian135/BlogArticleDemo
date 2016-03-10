package com.whereru.testcode2;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ListItemBaseType> mListItems;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListItems = new ArrayList<>();
        mListItems.add(new ListItemTypeOne(0, "one"));
        mListItems.add(new ListItemTypeOne(0, "two"));
        mListItems.add(new ListItemTypeTwo(1, BitmapFactory
                .decodeResource(getResources(),R.drawable.game0)));
        mListItems.add(new ListItemTypeTwo(1, BitmapFactory
                .decodeResource(getResources(), R.drawable.game1)));
        mListItems.add(new ListItemTypeOne(0, "three"));
        mListItems.add(new ListItemTypeTwo(1, BitmapFactory
                .decodeResource(getResources(), R.drawable.game2)));


        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(new ListViewAdapter(this,mListItems));

    }

}
