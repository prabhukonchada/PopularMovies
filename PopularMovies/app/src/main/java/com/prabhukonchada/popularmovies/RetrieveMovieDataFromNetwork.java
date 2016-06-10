package com.prabhukonchada.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        HttpURLConnection urlConnection=null;
        BufferedReader readMovieData = null;
        String jsonMovieResponse = null;

        try {
            URL url = new URL(builder.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream movieDataInputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(movieDataInputStream == null)
            {
                return null;
            }
            readMovieData = new BufferedReader(new InputStreamReader(movieDataInputStream));
            String line;

            while((line = readMovieData.readLine()) != null)
            {
                buffer.append(line +"\n");
            }

            if(buffer.length() == 0 )
            {
                // The stream is empty lets not parse
                return null;
            }
            jsonMovieResponse = buffer.toString();
            Log.d("Json Response :",jsonMovieResponse);

            return parseJsonResponse(jsonMovieResponse);
        }
        catch (Exception e)
        {
            Log.d("Exception Raised : ",e.toString());
        }
        finally {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }

            if(readMovieData != null)
            {
                try {
                    readMovieData.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    private ArrayList<MovieDataModel> parseJsonResponse(String jsonData) throws JSONException
    {
        final String RESULTS_LIST = "results";
        final String MOVIE_OVERVIEW = "overview";
        final String MOVIE_TITLE = "title";
        final String MOVIE_IMAGE_THUMBNAIL = "backdrop_path";


        ArrayList<MovieDataModel> movieDataItems = new ArrayList<MovieDataModel>();

        JSONObject moviesJsonObject = new JSONObject(jsonData);
        JSONArray movieResults = moviesJsonObject.getJSONArray(RESULTS_LIST);
        for (int i = 0; i < movieResults.length(); i++) {
            StringBuffer IMAGE_URL = new StringBuffer("http://image.tmdb.org/t/p/");
            JSONObject jsonMovieDataObject = movieResults.getJSONObject(i);
            MovieDataModel movieDataObject = new MovieDataModel();
            movieDataObject.setMovieName(jsonMovieDataObject.getString(MOVIE_TITLE));
            movieDataObject.setMoviePlotSynopsis(jsonMovieDataObject.getString(MOVIE_OVERVIEW));
            movieDataObject.setMoviePosterImageThumbnail((IMAGE_URL.append(jsonMovieDataObject.getString(MOVIE_IMAGE_THUMBNAIL))).toString());
            movieDataItems.add(movieDataObject);
        }

        Log.d("Movie Results: ",movieResults.toString());
        return movieDataItems;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDataModel> movieDataModels) {
        super.onPostExecute(movieDataModels);
    }
}
