/*
* Homework Assignment No - 4
* File Name - MovieDetailsUtil.java
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
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Shamalee on 2/27/2016.
 */
public class MovieDetailsUtil {
    static public class MovieDetailsJSonParser {
        static Movies parseMoviesDetails(String in) throws JSONException {
            Log.d("input", in);
            //ArrayList<Movies> moviesArrayList =new ArrayList<>();
            JSONObject movieJsonObject = new JSONObject(in);
            //JSONArray jsonArray = new JSONArray(in);
            //Log.d("jsondetails",jsonArray.toString());
            //for(int i =0; i<jsonArray.length();i++)
            //{
            //JSONObject movieJsonObject = jsonArray.getJSONObject(i);
            Movies objMovies = new Movies();
            if (movieJsonObject.getString("Title") != null) {
                objMovies.setTitle(movieJsonObject.getString("Title"));
            } else {
                objMovies.setTitle(movieJsonObject.getString(null));
            }
            if (movieJsonObject.getString("Year") != null) {
                objMovies.setYear(movieJsonObject.getString("Year"));
            } else {
                objMovies.setYear(null);

            }
            if (movieJsonObject.getString("imdbID") != null) {
                objMovies.setImdbID(movieJsonObject.getString("imdbID"));
            } else {
                objMovies.setImdbID(null);
            }
            if (movieJsonObject.getString("Poster") != null) {
                objMovies.setPoster(movieJsonObject.getString("Poster"));
            } else {
                objMovies.setPoster(null);
            }
            if (movieJsonObject.getString("Released") != null) {
                objMovies.setReleased(movieJsonObject.getString("Released"));
            } else {
                objMovies.setReleased(null);
            }
            if (movieJsonObject.getString("Genre") != null) {
                objMovies.setGenre(movieJsonObject.getString("Genre"));
            } else {
                objMovies.setGenre(null);
            }
            if (movieJsonObject.getString("Director") != null) {
                objMovies.setDirector(movieJsonObject.getString("Director"));
            } else {
                objMovies.setDirector(null);
            }
            if (movieJsonObject.getString("Actors") != null) {
                objMovies.setActors(movieJsonObject.getString("Actors"));
            } else {
                objMovies.setActors(null);
            }
            if (movieJsonObject.getString("Plot") != null) {
                objMovies.setPlot(movieJsonObject.getString("Plot"));
            } else {
                objMovies.setPlot(null);
            }
            if (movieJsonObject.getString("imdbRating") != null) {
                objMovies.setImdbRating(movieJsonObject.getString("imdbRating"));
            } else {
                objMovies.setImdbRating(null);
            }


            return objMovies;
        }


    }
}
