/*
* Homework Assignment No - 4
* File Name - MovieUtil.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Shamalee on 2/27/2016.
 */
public class MovieUtil {
    static public class MovieJSonParser{
        static ArrayList<Movies> parseMovies(String in) throws JSONException {
            ArrayList<Movies> moviesArrayList =new ArrayList<>();
            JSONObject obj = new JSONObject(in);
            JSONArray jsonArray = obj.getJSONArray("Search");

            Log.d("json",jsonArray.toString());
            for(int i =0; i<jsonArray.length();i++)
            {
                JSONObject movieJsonObject = jsonArray.getJSONObject(i);
                Movies objMovies = new Movies();
                if (movieJsonObject.getString("Title")!= null)
                {
                    objMovies.setTitle(movieJsonObject.getString("Title"));
                }
                else
                {
                    objMovies.setTitle(movieJsonObject.getString(null));
                }
                if (movieJsonObject.getString("Year")!= null)
                {
                    objMovies.setYear(movieJsonObject.getString("Year"));
                }
                else
                {
                    objMovies.setYear(movieJsonObject.getString(null));

                }
                if (movieJsonObject.getString("imdbID")!= null)
                {
                    objMovies.setImdbID(movieJsonObject.getString("imdbID"));
                }
                else
                {
                    objMovies.setImdbID(movieJsonObject.getString(null));
                }
                if (movieJsonObject.getString("Poster")!= null)
                {
                    objMovies.setPoster(movieJsonObject.getString("Poster"));
                }
                else
                {
                    objMovies.setPoster(movieJsonObject.getString(null));
                }
//                if (movieJsonObject.getString("Released")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("Released"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//                if (movieJsonObject.getString("Genre")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("Genre"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//                if (movieJsonObject.getString("Director")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("Director"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//                if (movieJsonObject.getString("Actor")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("Actor"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//                if (movieJsonObject.getString("Plot")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("Plot"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//                if (movieJsonObject.getString("ImdbRating")!= null)
//                {
//                    objMovies.setPoster(movieJsonObject.getString("ImdbRating"));
//                }
//                else
//                {
//                    objMovies.setPoster(movieJsonObject.getString(null));
//                }
//

                moviesArrayList.add(objMovies);
            }

            Log.d("arraylist",""+moviesArrayList.size());
            return moviesArrayList;
        }
    }
}
