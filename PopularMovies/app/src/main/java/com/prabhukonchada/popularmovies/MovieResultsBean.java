package com.prabhukonchada.popularmovies;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 15/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieResultsBean {
    public ArrayList<MovieBean> results;
    public MovieBean moviesBean;

    public MovieBean getMoviesBean() {
        return moviesBean;
    }

    public void setMoviesBean(MovieBean moviesBean) {
        this.moviesBean = moviesBean;
    }

    public ArrayList<MovieBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieBean> results) {
        this.results = results;
    }
}
