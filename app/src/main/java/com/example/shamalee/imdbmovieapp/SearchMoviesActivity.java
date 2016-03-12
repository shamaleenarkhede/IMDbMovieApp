/*
* Homework Assignment No - 4
* File Name - SearchMoviesAtivity.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class SearchMoviesActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    static final String MOVIE_KEY="movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        this.setTitle(R.string.searchActivity);
        progressDialog = new ProgressDialog(SearchMoviesActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading Movie List");
        progressDialog.show();



        String movie=getIntent().getStringExtra(MainActivity.MOVIE_KEY);
        Log.d("movie","" + movie.toString());
        new GetThreadAsync().execute("http://www.omdbapi.com/?type=movie&s="+movie.toString());

    }
    public class GetThreadAsync extends AsyncTask<String,Void, ArrayList<Movies>>
    {

        @Override
        protected ArrayList<Movies> doInBackground(String... params) {

            try {

                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int statuscode = con.getResponseCode();
                if(statuscode == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null)
                    {
                        sb.append(line);
                        line=reader.readLine();
                    }
                    Log.d("sb",""+sb.toString());
                    return MovieUtil.MovieJSonParser.parseMovies(sb.toString());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(final ArrayList<Movies> movies) {
            super.onPostExecute(movies);
            progressDialog.dismiss();
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.llMovies);
            Collections.sort(movies);

            TextView[] textView = new TextView[movies.size()];
            for(int i=0; i<movies.size();i++)
            {
                textView[i]=new TextView(SearchMoviesActivity.this);
                textView[i].setText(movies.get(i).getTitle());
                textView[i].setClickable(true);
                textView[i].setTextSize(50);
                final String movies_imdbID=movies.get(i).getImdbID();
                textView[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent movieDetails = new Intent(SearchMoviesActivity.this, MovieDetailsActivity.class);
                        movieDetails.putExtra("ID", movies_imdbID);
                        Bundle b=new Bundle();
                        b.putSerializable("list",movies);
                        movieDetails.putExtras(b);
                        startActivity(movieDetails);
                        finish();

                    }
                });
                linearLayout.addView(textView[i]);
            }
        }


    }

}
