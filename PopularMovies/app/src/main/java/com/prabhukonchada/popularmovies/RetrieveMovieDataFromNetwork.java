package com.prabhukonchada.popularmovies;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class RetrieveMovieDataFromNetwork extends AsyncTask<Void,Void,ArrayList<MovieDataModel>>{

    MovieGridAdapter adapter;
    public RetrieveMovieDataFromNetwork(MovieGridAdapter adapter)
    {
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<MovieDataModel> doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDataModel> movieDataModels) {
        super.onPostExecute(movieDataModels);
    }
}
