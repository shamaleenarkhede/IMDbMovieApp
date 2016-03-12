/*
* Homework Assignment No - 4
* File Name - MainActivity.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static final String MOVIE_KEY="moviename";
    String moviename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        final EditText etmovies = (EditText)findViewById(R.id.etsearchkey);
        etmovies.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                etmovies.setHint("");
                return false;
            }
        });

        findViewById(R.id.btnsearchkey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviename = etmovies.getText().toString();
                Log.d("movie", "" + moviename);
                Intent mainactivityintent = new Intent(MainActivity.this, SearchMoviesActivity.class);
                mainactivityintent.putExtra(MOVIE_KEY, moviename);
                startActivity(mainactivityintent);
            }
        });
    }
}
