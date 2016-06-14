package com.prabhukonchada.popularmovies;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 14/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class DataRetrivalResultEvent {

    private ArrayList<MovieDataModel> movieDataModelArrayList;

    public DataRetrivalResultEvent(ArrayList<MovieDataModel> movieDataModelArrayList)
    {
        this.movieDataModelArrayList = movieDataModelArrayList;
    }

    public ArrayList<MovieDataModel> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }
}
