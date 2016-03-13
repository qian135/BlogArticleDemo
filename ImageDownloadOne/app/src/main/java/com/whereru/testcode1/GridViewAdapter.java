package com.whereru.testcode1;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<PhotoItem> mData;
    private LayoutInflater mLayoutInflater;
    private MyHandlerThread mMyHandlerThread;
    private Handler mHandlerThreadHandler;
    private Handler mMainThreadHandler = new Handler();


    GridViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mMyHandlerThread = new MyHandlerThread(mMainThreadHandler);//把主线程Handler的引用传过去
        mMyHandlerThread.start();
        mHandlerThreadHandler = new Handler(mMyHandlerThread.getLooper());
    }

    public void setData(List<PhotoItem> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public PhotoItem getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        private ImageView mImageView;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.grid_view_item_layout, null);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //因为measure而调用getView不进行网络请求操作
        if (((MyGridView)parent).isOnMeasure()) {
            return convertView;
        }

        //滑动过程中不产生新的异步下载任务
        if (((MainActivity) mContext).isGridViewIsIdle()) {
            final ViewHolder finalViewHolder = viewHolder;
            mHandlerThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    mMyHandlerThread.downloadImage(
                            mData.get(position).getImageUrl(), finalViewHolder.mImageView);
                }
            });
        }

        viewHolder.mImageView.setImageResource(R.drawable.game0);
        viewHolder.mImageView.setTag(mData.get(position).getImageUrl());//防止图片错位
        return convertView;

    }

    public void quitMyHandlerThread() {
        mMyHandlerThread.quit();
    }

}
