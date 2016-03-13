package com.whereru.testcode3;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<GridViewItem> mGridViewItems;
    private MyGridView mGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridViewItems = new ArrayList<>();
        for (int i = 0;i < 100;i++) {
            mGridViewItems.add(new GridViewItem());
        }

        mGridView = (MyGridView) findViewById(R.id.grid_view);
        mGridView.setAdapter(new GridViewAdapter(this,mGridViewItems));

    }

    class GridViewAdapter extends BaseAdapter {

        private List<GridViewItem> mGridViewItems;
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private MyHandlerThread mMyHandlerThread;
        private Handler mMyHandlerThreadHandler;

        GridViewAdapter(Context context,List<GridViewItem> gridViewItems) {
            mContext = context;
            mGridViewItems = gridViewItems;
            mLayoutInflater = LayoutInflater.from(mContext);
            mMyHandlerThread = new MyHandlerThread(new Handler());
            mMyHandlerThread.start();
            mMyHandlerThreadHandler = new Handler(mMyHandlerThread.getLooper());
        }

        @Override
        public int getCount() {
            return mGridViewItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mGridViewItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.grid_item,null);
            }

            if (((MyGridView)parent).isOnMeasure()) {
                return convertView;
            }
//            System.out.println(position);
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
            mMyHandlerThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    mMyHandlerThread.downloadImage(imageView,
                            "http://img3.imgtn.bdimg.com/it/u=3611557844,609254425&fm=21&gp=0.jpg");
                }
            });
            imageView.setImageResource(R.drawable.game2);
            return convertView;
        }

    }

}