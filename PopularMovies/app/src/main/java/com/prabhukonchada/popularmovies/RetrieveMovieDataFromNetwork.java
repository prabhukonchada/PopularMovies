package com.prabhukonchada.popularmovies;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class RetrieveMovieDataFromNetwork extends AsyncTask<Void,Void,ArrayList<MovieDataModel>>{

    public RetrieveMovieDataFromNetwork(MovieGridAdapter adapter)
    {

    }

    @Override
    protected ArrayList<MovieDataModel> doInBackground(Void... params) {
        return null;
    }
}
