package com.prabhukonchada.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    final String TAG = "DetailActivityFragment";
    TextView movieTitle;
    TextView movieRating;
    TextView movieReleaseDate;
    TextView movieRuntime;
    TextView movieSynopsis;
    SimpleDraweeView movieBackgroundImage;

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        StringBuffer IMAGE_URL = new StringBuffer("http://image.tmdb.org/t/p/w780");

        MovieDataModel movieObject = (MovieDataModel) getActivity().getIntent().getSerializableExtra(getString(R.string.movie_object));
        StringBuffer voteAverage = new StringBuffer(movieObject.getVoteAverage());
        movieTitle = (TextView)rootView.findViewById(R.id.movieTitle);
        movieRating = (TextView)rootView.findViewById(R.id.movieRating);
        movieReleaseDate = (TextView)rootView.findViewById(R.id.movieReleaseDate);
        movieRuntime = (TextView)rootView.findViewById(R.id.movieRuntime);
        movieSynopsis = (TextView)rootView.findViewById(R.id.movieSynopsis);
        movieBackgroundImage = (SimpleDraweeView)rootView.findViewById(R.id.movieBackgroundImage);
        movieTitle.setText(movieObject.getMovieName());
        movieRating.setText(voteAverage.append("/10"));
        movieReleaseDate.setText(movieObject.getReleaseDate());
        movieSynopsis.setText(movieObject.getMoviePlotSynopsis());
        String imageUrl = IMAGE_URL.append(movieObject.getMovieImage()).toString();
        movieBackgroundImage.setImageURI(Uri.parse(imageUrl));

        return rootView;

    }
}
