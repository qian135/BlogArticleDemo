package com.whereru.testcode1;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<PhotoItem> mData;
    private MyGridView mGridView;

    private GridViewAdapter mGridViewAdapter;

    private boolean mGridViewIsIdle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (MyGridView) findViewById(R.id.grid_view);
        mGridViewAdapter = new GridViewAdapter(this);
        //给GridView设置滑动监听
        mGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    mGridViewIsIdle = true;
                    mGridViewAdapter.notifyDataSetChanged();
                } else {
                    mGridViewIsIdle = false;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mAsyncTask.execute();

    }

    public boolean isGridViewIsIdle() {
        return mGridViewIsIdle;
    }

    AsyncTask<Void,Void,List<PhotoItem>> mAsyncTask = new AsyncTask<Void,Void,List<PhotoItem>>() {

        @Override
        protected List<PhotoItem> doInBackground(Void... params) {
            try {
                mData = parseJson(getJsonString());
                return mData;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<PhotoItem> data) {
            if (data != null) {
                mGridViewAdapter.setData(data);
                mGridView.setAdapter(mGridViewAdapter);
            }
        }

    };

    //解析JSON字符串
    List<PhotoItem> parseJson(String jsonString) throws JSONException {
        List<PhotoItem> data = new ArrayList<>();


        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject jsonObject1 = jsonObject.getJSONObject("photos");
        JSONArray jsonArray = jsonObject1.getJSONArray("photo");

        for (int i = 0; i < jsonArray.length(); i++) {
            PhotoItem photoItem = new PhotoItem();
            photoItem.setImageUrl((String) jsonArray.getJSONObject(i).get("url_s"));
            data.add(photoItem);
        }

        return data;
    }

    //获取JSON字符串
    public String getJsonString() throws IOException {

        URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.getRecent" +
                "&api_key=XXX&format=json&nojsoncallback=1" +
                "&extras=url_s");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            InputStream inputStream = httpURLConnection.getInputStream();

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byteArrayOutputStream.close();
            return new String(byteArrayOutputStream.toByteArray());

        } finally {
            httpURLConnection.disconnect();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mGridViewAdapter != null) {
            mGridViewAdapter.quitMyHandlerThread();
        }
    }

}
