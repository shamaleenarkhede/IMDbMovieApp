/*
* Homework Assignment No - 4
* File Name - MovieWebview.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MovieWebview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_webview);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        this.setTitle(R.string.webviewActivity);
        String imdbID=getIntent().getStringExtra(MovieDetailsActivity.IMDB_KEY);

        WebView view=(WebView)findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl("http://m.imdb.com/title/"+ imdbID);
        String url="";
        url = "http://m.imdb.com/title/"+ imdbID;
        TextView tv = (TextView)findViewById(R.id.tvurl);
        tv.setText(url);

    }

}


