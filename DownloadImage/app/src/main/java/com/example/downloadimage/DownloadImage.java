package com.example.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urls = strings[0];
        Bitmap bitmap = null;
        try {
            URL url = new URL(urls);
            URLConnection connection = url.openConnection();
            connection.setUseCaches(true);
            InputStream input = url.openStream();
            bitmap = BitmapFactory.decodeStream(input);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
