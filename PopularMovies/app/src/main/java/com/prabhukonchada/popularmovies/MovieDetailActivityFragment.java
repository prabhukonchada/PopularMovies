package com.prabhukonchada.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
    SimpleDraweeView moviePosterImage;
    SeekBar imageScaleSeekbar;
    MovieBean movieObject;
    String TAG = "Movie Detail :";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(getString(R.string.parcelable_movie_model_object), movieObject);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.parcelable_movie_model_object))) {
            movieObject = getActivity().getIntent().getParcelableExtra(getString(R.string.movie_object));
        } else {
            movieObject = savedInstanceState.getParcelable(getString(R.string.parcelable_movie_model_object));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        StringBuffer IMAGE_URL_LARGE = new StringBuffer(getString(R.string.image_url_large));
        StringBuffer IMAGE_URL_SMALL= new StringBuffer(getString(R.string.image_url_small));

        StringBuffer voteAverage = new StringBuffer(String.valueOf(movieObject.getVote_average()));
        movieTitle = (TextView) rootView.findViewById(R.id.movieTitle);
        movieRating = (TextView) rootView.findViewById(R.id.movieRating);
        movieReleaseDate = (TextView) rootView.findViewById(R.id.movieReleaseDate);
        movieSynopsis = (TextView) rootView.findViewById(R.id.movieSynopsis);
        movieBackgroundImage = (SimpleDraweeView) rootView.findViewById(R.id.movieBackgroundImage);
        moviePosterImage = (SimpleDraweeView)rootView.findViewById(R.id.moviePosterImage);
        imageScaleSeekbar = (SeekBar)rootView.findViewById(R.id.imageScaleSeekbar);


        movieTitle.setText(movieObject.getTitle());
        movieRating.setText(voteAverage.append(getString(R.string.vote_average_max)));
        movieReleaseDate.setText(movieObject.getRelease_date());
        movieSynopsis.setText(movieObject.getOverview());
        String imageUrl = IMAGE_URL_LARGE.append(movieObject.getBackdrop_path()).toString();
        String posterImageUrl = IMAGE_URL_SMALL.append(movieObject.getPoster_path()).toString();
        movieBackgroundImage.setImageURI(Uri.parse(imageUrl));
        moviePosterImage.setImageURI(Uri.parse(posterImageUrl));


        imageScaleSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                ViewGroup.LayoutParams params=moviePosterImage.getLayoutParams();
                params.width=progress*4;
                params.height=progress*6;
                moviePosterImage.setLayoutParams(params);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
        });

        moviePosterImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageScaleSeekbar.setProgress(50);
                return false;
            }
        });

        moviePosterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(rootView, movieObject.getTitle(), Snackbar.LENGTH_LONG);
                snackbar.show();

                Toast.makeText(getContext(), movieObject.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;

    }
}
