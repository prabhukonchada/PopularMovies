package com.prabhukonchada.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class RetrieveMovieDataFromNetwork extends AsyncTask<String,Void,ArrayList<MovieBean>> {

    Context context;
    String TAG = "Movie Task :";
    OkHttpClient client = new OkHttpClient();
    ArrayList<MovieBean> movieDataModelArrayList;

    public RetrieveMovieDataFromNetwork(Context context)
    {
        this.context = context;
        Log.d(TAG, "RetrieveMovieDataFromNetwork: Constructor");
    }

    @Override
    protected ArrayList<MovieBean> doInBackground(String... sortPreferenceKey) {
        Log.d(TAG, "doInBackground: Task");

        Uri.Builder builder = Uri.parse((String) context.getString(R.string.the_movie_db_url)).buildUpon().appendPath(sortPreferenceKey[0]).appendQueryParameter(context.getString(R.string.api_key_string),BuildConfig.MOVIE_DB_API_KEY);
        Log.d("Hitting_Url",builder.toString());
        String jsonMovieResponse;
        try {
            jsonMovieResponse = run(builder.toString());
            Log.d(TAG, "doInBackground: json response"+jsonMovieResponse);
            movieDataModelArrayList = parseJsonResponse(jsonMovieResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieDataModelArrayList;
    }

    private ArrayList<MovieBean> parseJsonResponse(String jsonData) throws JSONException
    {
        Gson gson = new Gson();
        MovieResultsBean movieResults = gson.fromJson(jsonData,MovieResultsBean.class);
        ArrayList<MovieBean> results = movieResults.getResults();
        return movieResults.getResults();
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected void onPostExecute(ArrayList<MovieBean> movieDataModelArrayList) {
        if(movieDataModelArrayList != null)
        DataBus.getInstance().post(new DataRetrivalResultEvent(movieDataModelArrayList));
    }

}
