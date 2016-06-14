package com.prabhukonchada.popularmovies;

import android.content.Context;
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
public class RetrieveMovieDataFromNetwork extends AsyncTask<String,Void,ArrayList<MovieDataModel>> {

    Context context;
    String TAG = "Async Task :";

    public RetrieveMovieDataFromNetwork(Context context)
    {
        this.context = context;
        Log.d(TAG, "RetrieveMovieDataFromNetwork: Constructor");
    }

    @Override
    protected ArrayList<MovieDataModel> doInBackground(String... sortPreferenceKey) {
        Log.d(TAG, "doInBackground: Task");

        Uri.Builder builder = Uri.parse((String) context.getString(R.string.the_movie_db_url)).buildUpon().appendPath(sortPreferenceKey[0]).appendQueryParameter(context.getString(R.string.api_key_string),BuildConfig.MOVIE_DB_API_KEY);
        HttpURLConnection urlConnection=null;
        BufferedReader readMovieData = null;
        String jsonMovieResponse;

        try {
            URL url = new URL(builder.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod(context.getString(R.string.http_get_method));
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
        final String MOVIE_IMAGE_THUMBNAIL = "poster_path";
        final String MOVIE_IMAGE = "backdrop_path";
        final String VOTE_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";


        ArrayList<MovieDataModel> movieDataItems = new ArrayList<>();

        JSONObject moviesJsonObject = new JSONObject(jsonData);
        JSONArray movieResults = moviesJsonObject.getJSONArray(RESULTS_LIST);
        for (int i = 0; i < movieResults.length(); i++) {
            StringBuffer IMAGE_URL_SMALL = new StringBuffer(context.getString(R.string.image_url_small));
            StringBuffer IMAGE_URL_LARGE = new StringBuffer(context.getString(R.string.image_url_large));
            JSONObject jsonMovieDataObject = movieResults.getJSONObject(i);
            MovieDataModel movieDataObject = new MovieDataModel();
            movieDataObject.setMovieName(jsonMovieDataObject.getString(MOVIE_TITLE));
            movieDataObject.setMoviePlotSynopsis(jsonMovieDataObject.getString(MOVIE_OVERVIEW));
            movieDataObject.setMovieImage(IMAGE_URL_LARGE.append(jsonMovieDataObject.getString(MOVIE_IMAGE)).toString());
            movieDataObject.setReleaseDate(jsonMovieDataObject.getString(RELEASE_DATE));
            movieDataObject.setVoteAverage(jsonMovieDataObject.getString(VOTE_AVERAGE));
            movieDataObject.setMoviePosterImageThumbnail((IMAGE_URL_SMALL.append(jsonMovieDataObject.getString(MOVIE_IMAGE_THUMBNAIL))).toString());
            movieDataItems.add(movieDataObject);
        }

        return movieDataItems;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDataModel> movieDataModelArrayList) {
        Log.d(TAG, "onPostExecute: Post Event Result");
        DataBus.getInstance().post(new DataRetrivalResultEvent(movieDataModelArrayList));
    }
}
