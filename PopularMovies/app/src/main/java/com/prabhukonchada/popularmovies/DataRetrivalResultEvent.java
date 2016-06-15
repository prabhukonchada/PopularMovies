package com.prabhukonchada.popularmovies;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 14/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class DataRetrivalResultEvent {

    private ArrayList<MovieBean> movieDataModelArrayList;

    public DataRetrivalResultEvent(ArrayList<MovieBean> movieDataModelArrayList)
    {
        this.movieDataModelArrayList = movieDataModelArrayList;
    }

    public ArrayList<MovieBean> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }
}
