package com.whereru.testcode2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private final static int ITEM_TYPE = 2;
    private List<ListItemBaseType> mListItems;

    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public ListViewAdapter(Context context,List<ListItemBaseType> listItems) {
        mListItems = listItems;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }


    @Override
    public Object getItem(int position) {
        return mListItems.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolderOne {
        private TextView mTextView;
    }

    static class ViewHolderTwo {
        private ImageView mImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //根据要加载的Item类型，返回相应的ItemView
        if (getItemViewType(position) == 0) {
            ViewHolderOne viewHolderOne = null;
            if (convertView == null) {
                viewHolderOne = new ViewHolderOne();
                convertView = mLayoutInflater.inflate(R.layout.list_view_item_type_one,null);
                viewHolderOne.mTextView = (TextView) convertView.
                        findViewById(R.id.list_view_item_type_one_text_view);
                convertView.setTag(viewHolderOne);
            } else {
                viewHolderOne = (ViewHolderOne) convertView.getTag();
            }
            viewHolderOne.mTextView.setText(((ListItemTypeOne)mListItems.get(position)).getString());
        } else {
            ViewHolderTwo viewHolderTwo = null;
            if (convertView == null) {
                viewHolderTwo = new ViewHolderTwo();
                convertView = mLayoutInflater.inflate(R.layout.list_view_item_type_two,null);
                viewHolderTwo.mImageView = (ImageView) convertView.
                        findViewById(R.id.list_view_item_type_two_image_view);
                convertView.setTag(viewHolderTwo);
            } else {
                viewHolderTwo = (ViewHolderTwo) convertView.getTag();
            }
            viewHolderTwo.mImageView.setImageBitmap(((ListItemTypeTwo)mListItems.
                    get(position)).getBitmap());
        }
        return convertView;
    }

    //比一般情况多重写了下面两个方法
    @Override
    public int getItemViewType(int position) {
        return mListItems.get(position).getItemType();
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_TYPE;
    }

}
