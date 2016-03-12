/*
* Homework Assignment No - 4
* File Name - MovieDetailsActivity.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */


package com.example.shamalee.imdbmovieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieDetailsActivity extends AppCompatActivity {

    String id;
    static final String IMDB_KEY="imdb";

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        this.setTitle(R.string.detailsActivity);
        Bundle b= getIntent().getExtras();
        final ArrayList<Movies> movies= (ArrayList<Movies>) b.getSerializable("list");
        String imdbID = getIntent().getStringExtra("ID");
        id=imdbID;
        Log.d("size",""+movies.size());
        progressDialog = new ProgressDialog(MovieDetailsActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading Movie");
        progressDialog.show();

        new GetMovieDetails().execute("http://www.omdbapi.com/?i=" + imdbID);



        findViewById(R.id.ivnext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int imbid = 0;
                TextView t1 = (TextView) findViewById(R.id.tvMoviename);
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getTitle().equals(t1.getText().toString())) {
                        if (i == movies.size() - 1) {
                            imbid = 0;
                            break;
                        } else {
                            imbid = i + 1;
                            break;
                        }
                    }
                }

                new GetMovieDetails().execute("http://www.omdbapi.com/?i=" + movies.get(imbid).getImdbID());
            }
        });



           findViewById(R.id.ivprevious).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   int imbid = 0;
                   TextView t1 = (TextView) findViewById(R.id.tvMoviename);
                   for (int i = 0; i < movies.size(); i++) {
                       if (movies.get(i).getTitle().equals(t1.getText().toString())) {
                           i = i - 1;
                           if (i == -1) {
                               i = movies.size() - 1;
                           }
                           imbid = i;
                           break;
                       }
                   }

                   new GetMovieDetails().execute("http://www.omdbapi.com/?i=" + movies.get(imbid).getImdbID());


               }
           });

         findViewById(R.id.ivmovielink).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int index=0;
                 TextView t1 = (TextView) findViewById(R.id.tvMoviename);
                 for (int i = 0; i < movies.size(); i++) {
                     if (movies.get(i).getTitle().equals(t1.getText().toString())) {
                         index = i;
                     }
                 }
                 Intent webviewactivity = new Intent(MovieDetailsActivity.this,MovieWebview.class);
               //  Log.d("id",movies.get(index).getImdbID());
                 webviewactivity.putExtra(IMDB_KEY,movies.get(index).getImdbID());
                 startActivity(webviewactivity);
             }
         });



        findViewById(R.id.btnfinish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });



    }
    public class GetMovieDetails extends AsyncTask<String,Void, Movies>{

        @Override
        protected Movies doInBackground(String... params) {
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
                    Log.d("sbdetails",""+sb.toString());
                    return MovieDetailsUtil.MovieDetailsJSonParser.parseMoviesDetails(sb.toString());
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
        protected void onPostExecute(Movies movies) {
            super.onPostExecute(movies);
            TextView t1=(TextView)findViewById(R.id.tvMoviename);
            t1.setText(movies.getTitle());

            SimpleDateFormat sb=new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat sb1=new SimpleDateFormat("MMM dd yyyy");

            try {
                Date date=sb.parse(movies.getReleased());
                TextView t2=(TextView)findViewById(R.id.tvreleasedisplay);
                Log.d("date",sb1.format(date));
                t2.setText(sb1.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TextView t3=(TextView)findViewById(R.id.genredisplay);
            t3.setText(movies.getGenre());
            TextView t4=(TextView)findViewById(R.id.etdirdisp);
            t4.setText(movies.getDirector());
            TextView t5=(TextView)findViewById(R.id.etactordisp);
            t5.setText(movies.getActors());
            TextView t6=(TextView)findViewById(R.id.tvplotdes);
            t6.setText(movies.getPlot());
            RatingBar r1=(RatingBar)findViewById(R.id.ratingBar);
            Float a=Float.parseFloat(movies.getImdbRating());
            a=a/2;
            Log.d("a", "" + a);
            r1.setNumStars(5);
            r1.setStepSize(0.5f);
            r1.setRating(a);

            Log.d("demo", movies.getPoster());
            new GetImage(MovieDetailsActivity.this).execute(movies.getPoster());


            progressDialog.dismiss();
        }

    }
}
