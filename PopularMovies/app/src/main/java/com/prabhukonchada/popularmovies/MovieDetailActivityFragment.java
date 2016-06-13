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

    TextView movieTitle;
    TextView movieRating;
    TextView movieReleaseDate;
    TextView movieSynopsis;
    SimpleDraweeView movieBackgroundImage;
    MovieDataModel movieObject;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getString(R.string.parcelable_movie_model_object),movieObject);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        StringBuffer IMAGE_URL = new StringBuffer(getString(R.string.image_url_large));

        if(savedInstanceState == null || !savedInstanceState.containsKey("MovieData"))
        {
            movieObject = getActivity().getIntent().getParcelableExtra(getString(R.string.movie_object));
        }
        else
        {
            movieObject = savedInstanceState.getParcelable(getString(R.string.parcelable_movie_model_object));
        }

        StringBuffer voteAverage = new StringBuffer(movieObject.getVoteAverage());
        movieTitle = (TextView)rootView.findViewById(R.id.movieTitle);
        movieRating = (TextView)rootView.findViewById(R.id.movieRating);
        movieReleaseDate = (TextView)rootView.findViewById(R.id.movieReleaseDate);
        movieSynopsis = (TextView)rootView.findViewById(R.id.movieSynopsis);
        movieBackgroundImage = (SimpleDraweeView)rootView.findViewById(R.id.movieBackgroundImage);

        movieTitle.setText(movieObject.getMovieName());
        movieRating.setText(voteAverage.append(getString(R.string.vote_average_max)));
        movieReleaseDate.setText(movieObject.getReleaseDate());
        movieSynopsis.setText(movieObject.getMoviePlotSynopsis());
        String imageUrl = IMAGE_URL.append(movieObject.getMovieImage()).toString();
        movieBackgroundImage.setImageURI(Uri.parse(imageUrl));
        return rootView;

    }
}
