package com.prabhukonchada.popularmovies;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFragment extends Fragment implements OnDataUpdateListener{

    ArrayList<MovieDataModel> movieDataModelArrayList;
    RetrieveMovieDataFromNetwork retrieveMovieDataFromNetworkAsyncTaskObject;
    MovieGridAdapter movieGridAdapter;
    GridView moviesGrid;
    String preferenceValue;

    public MovieGridFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getString(R.string.parcelable_movie_model_list_key), getMovieDataModelArrayList());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.preference_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sort_movies) {
            Intent showMovieSortPreference = new Intent(getActivity(), SortMoviesPreferenceActivity.class);
            startActivity(showMovieSortPreference);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceValue = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getString(R.string.pref_sort_key), getString(R.string.default_preference_of_user));
        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.parcelable_movie_model_list_key))) {
            retrieveMovieDataFromNetworkAsyncTaskObject = new RetrieveMovieDataFromNetwork(getActivity());
            retrieveMovieDataFromNetworkAsyncTaskObject.setUpdateListener(this);
            retrieveMovieDataFromNetworkAsyncTaskObject.execute(preferenceValue);
        }
        else
        {
            movieDataModelArrayList = savedInstanceState.getParcelableArrayList(getString(R.string.parcelable_movie_model_list_key));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        moviesGrid = (GridView) rootView.findViewById(R.id.movieGrid);
        AdapterView.OnItemClickListener movieGridListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDataModel movieObject = (MovieDataModel) parent.getItemAtPosition(position);
                Intent navigateToMovieDetail = new Intent(getActivity(), MovieDetailActivity.class);
                navigateToMovieDetail.putExtra(getString(R.string.movie_object), movieObject);
                startActivity(navigateToMovieDetail);
            }
        };
        movieGridAdapter = new MovieGridAdapter(getActivity());
        moviesGrid.setAdapter(movieGridAdapter);
        moviesGrid.setOnItemClickListener(movieGridListener);

        return rootView;
    }

    @Override
    public void onUpdate(ArrayList<MovieDataModel> movieDataModelArrayList) {
        setMovieDataModelArrayList(movieDataModelArrayList);
        movieGridAdapter.setMovieDataModelArrayList(movieDataModelArrayList);
        movieGridAdapter.notifyDataSetChanged();
    }

    public ArrayList<MovieDataModel> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }

    public void setMovieDataModelArrayList(ArrayList<MovieDataModel> movieDataModelArrayList) {
        this.movieDataModelArrayList = movieDataModelArrayList;
    }
}
