/*
* Homework Assignment No - 4
* File Name - Movies.java
* Team member - Shamalee Narkhede
*               Priyanka Mehta
*               Indraneel Bende
*
* */
package com.example.shamalee.imdbmovieapp;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Shamalee on 2/27/2016.
 */
public class Movies implements Comparable<Movies>,Serializable{
    private String title;
    private String year;
    private String imdbID;
    private String poster;
    private String released;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String imdbRating;

    public Movies(){

    }

    public Movies(String title, String year, String imdbID, String poster, String released,
                  String genre, String director, String actors, String plot, String imdbRating) {

        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
        this.released = released;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.imdbRating = imdbRating;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getGenre() {
        return genre;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
    public String getYear() {
        return year;
    }
    public String getImdbID() {
        return imdbID;
    }

    public String getReleased() {
        return released;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "com.example.shamalee.imdbmovieapp.Movies{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", imdbID=" + imdbID +
                ", poster='" + poster + '\'' +
                ", released=" + released +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating=" + imdbRating +
                '}';
    }

    @Override
    public int compareTo(Movies movies) {


        return movies.getYear().compareTo(getYear());
    }


}