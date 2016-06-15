package com.prabhukonchada.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class RetrieveMovieDataFromNetwork extends AsyncTask<String,Void,ArrayList<MovieDataModel>> {

    Context context;
    String TAG = "Movie Task :";
    OkHttpClient client = new OkHttpClient();
    ArrayList<MovieDataModel> movieDataModelArrayList;

    public RetrieveMovieDataFromNetwork(Context context)
    {
        this.context = context;
        Log.d(TAG, "RetrieveMovieDataFromNetwork: Constructor");
    }

    @Override
    protected ArrayList<MovieDataModel> doInBackground(String... sortPreferenceKey) {
        Log.d(TAG, "doInBackground: Task");

        Uri.Builder builder = Uri.parse((String) context.getString(R.string.the_movie_db_url)).buildUpon().appendPath(sortPreferenceKey[0]).appendQueryParameter(context.getString(R.string.api_key_string),BuildConfig.MOVIE_DB_API_KEY);
        String jsonMovieResponse;
        try {
            jsonMovieResponse = run(builder.toString());
            movieDataModelArrayList = parseJsonResponse(jsonMovieResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieDataModelArrayList;
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

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDataModel> movieDataModelArrayList) {
        Log.d(TAG, "onPostExecute: Post Event Result");
        /**
         * FIXME
         * For Reviewer :
         * Using a Bus from the library or a listener would be efficient to transfer data from async task to activity without any leaks ??
         */
        DataBus.getInstance().post(new DataRetrivalResultEvent(movieDataModelArrayList));
    }
}
