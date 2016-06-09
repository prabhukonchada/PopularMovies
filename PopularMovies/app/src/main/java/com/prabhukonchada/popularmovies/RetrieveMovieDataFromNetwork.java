package com.prabhukonchada.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class RetrieveMovieDataFromNetwork extends AsyncTask<String,Void,ArrayList<MovieDataModel>>{

    MovieGridAdapter adapter;

    String theMovieDbBaseUrl = "http://api.themoviedb.org/3/movie/";
    public RetrieveMovieDataFromNetwork(MovieGridAdapter adapter)
    {
        this.adapter = adapter;
    }

    @Override
    protected ArrayList<MovieDataModel> doInBackground(String... sortPreferenceKey) {


        Uri.Builder builder = Uri.parse(theMovieDbBaseUrl).buildUpon().appendPath(sortPreferenceKey[0]).appendQueryParameter("api_key",BuildConfig.MOVIE_DB_API_KEY);
        Log.d("Url Hit :",builder.toString());

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDataModel> movieDataModels) {
        super.onPostExecute(movieDataModels);
    }
}
