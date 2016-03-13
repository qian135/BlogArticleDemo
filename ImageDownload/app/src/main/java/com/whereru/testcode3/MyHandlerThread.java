package com.whereru.testcode3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyHandlerThread extends HandlerThread {

    private Handler mMainThreadHandler;

//    private int num;

    public MyHandlerThread(Handler handler) {
        super("down_load_image");
        mMainThreadHandler = handler;
    }

    public void downloadImage(final ImageView imageView,String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            System.out.println(num++);
            mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
