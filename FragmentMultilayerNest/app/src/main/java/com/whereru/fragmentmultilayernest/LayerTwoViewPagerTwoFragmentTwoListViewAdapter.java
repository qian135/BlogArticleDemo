package com.whereru.fragmentmultilayernest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class LayerTwoViewPagerTwoFragmentTwoListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mLayerTwoViewPagerTwoFragmentTwoListViewData;
    private LayoutInflater mLayoutInflater;

    LayerTwoViewPagerTwoFragmentTwoListViewAdapter(
            Context context, List<String>layerTwoViewPagerTwoFragmentTwoListViewData) {
        mContext = context;
        mLayerTwoViewPagerTwoFragmentTwoListViewData = layerTwoViewPagerTwoFragmentTwoListViewData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mLayerTwoViewPagerTwoFragmentTwoListViewData.size();
    }

    @Override
    public Object getItem(int position) {
        return mLayerTwoViewPagerTwoFragmentTwoListViewData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        public TextView textView;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(
                    R.layout.layer_two_view_pager_two_fragment_two_list_view_item_layout,null);

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mLayerTwoViewPagerTwoFragmentTwoListViewData.get(position));

        return convertView;

    }

}
