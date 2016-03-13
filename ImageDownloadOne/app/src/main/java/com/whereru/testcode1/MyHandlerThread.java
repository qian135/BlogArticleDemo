package com.whereru.testcode1;

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

    private Handler mMainThreadHandler;//主线程的Handler

//    private int num;

    MyHandlerThread(Handler handler) {
        super("download_image");//线程名
        mMainThreadHandler = handler;
    }

    public void downloadImage(final String string, final ImageView imageView) {

        try {
            //下载图片
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

//            System.out.println(num++);

            //更新UI操作，放回主线程
            mMainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    //防止图片错位
                    if (imageView.getTag() == string) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
