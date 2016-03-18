package com.whereru.testcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ExecutorService mExecutorService;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private boolean mRecyclerViewIsIdle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    mRecyclerViewIsIdle = true;
                    mRecyclerViewAdapter.notifyDataSetChanged();
                } else {
                    mRecyclerViewIsIdle = false;
                }
            }
        });

        mExecutorService = Executors.newFixedThreadPool(10);
        new DownloadImageURL().startDownloadImageURL();

    }

//    private int num;

    public class DownloadImageThread implements Runnable {

        private String mUrlString;
        private Handler mMainThreadHandler;
        private ImageView mImageView;

        public DownloadImageThread(String string, Handler handler, ImageView imageView) {
            mUrlString = string;
            mMainThreadHandler = handler;
            mImageView = imageView;
        }

        @Override
        public void run() {

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection)
                        new URL(mUrlString).openConnection();
                final Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
//                System.out.println(++num + "Bitmap");
                mMainThreadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mImageView.getTag().equals(mUrlString)) {
//                            System.out.println(num + "mImageView.getTag()");
                            mImageView.setImageBitmap(bitmap);
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<PhotoItem> mPhotoItems;
        private MyViewHolder mMyViewHolder;

        public void setPhotoItems(List<PhotoItem> photoItems) {
            mPhotoItems = photoItems;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            mMyViewHolder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recycler_view_item, viewGroup, false));
            return mMyViewHolder;
        }


        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int i) {
            viewHolder.mImageView.setImageResource(R.drawable.game0);
            if (mRecyclerViewIsIdle) {
                viewHolder.mImageView.setTag(mPhotoItems.get(i).getImageUrl());
                //把HandlerThread用线程池替代
                mExecutorService.execute(new DownloadImageThread(
                        mPhotoItems.get(i).getImageUrl(), new Handler(), viewHolder.mImageView));
            }
        }

        @Override
        public int getItemCount() {
            return mPhotoItems.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view);
        }

    }

    public class DownloadImageURL {

        public void startDownloadImageURL() {
            new AsyncTask<Void, Void, List<PhotoItem>>() {
                @Override
                protected List<PhotoItem> doInBackground(Void... params) {
                    try {
                        return parseJson(getJsonString());
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
                        mRecyclerViewAdapter.setPhotoItems(data);
                        mRecyclerView.setAdapter(mRecyclerViewAdapter);
                    } else {
                        new DownloadImageURL().startDownloadImageURL();
                    }
                }

            }.execute();
        }

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
                    "&api_key=8f47b0dcceabd83f680b17d7826a5ad0&format=json&nojsoncallback=1" +
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

    }

}
