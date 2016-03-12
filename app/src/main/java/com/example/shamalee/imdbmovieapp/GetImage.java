/*
* Homework Assignment No - 4
* File Name - GetImage.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Shamalee on 2/27/2016.
 */
public class GetImage extends AsyncTask<String, Void, Bitmap> {
        MovieDetailsActivity activity;

public GetImage(MovieDetailsActivity activity) {
        this.activity = activity;
        }

@Override

protected Bitmap doInBackground(String... params) {
        InputStream in = null;
        try {
        URL url = new URL(params[0]);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        in = con.getInputStream();
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        return bitmap;
        } catch (Exception e) {
        e.printStackTrace();
        } finally {
        if (in != null)
        try {
        in.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        return null;
        }

@Override
protected void onPostExecute(Bitmap result) {
        ImageButton iv = (ImageButton) activity.findViewById(R.id.ivmovielink);
        if (result != null) {
                Log.d("image",result.toString());
        iv.setImageBitmap(result);
        }
        //activity.progress.dismiss();
        }
        }
