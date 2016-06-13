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
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFragment extends Fragment {

    ArrayList<MovieDataModel> movieDataModelArrayList;

    public MovieGridFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("MovieData",movieDataModelArrayList);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.preference_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.sort_movies)
        {
            Intent showMovieSortPreference = new Intent(getActivity(),SortMoviesPreferenceActivity.class);
            startActivity(showMovieSortPreference);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null || !savedInstanceState.containsKey("MovieData"))
        {
            String preferenceValue = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getString(R.string.pref_sort_key),getString(R.string.default_preference_of_user));
            try {
                movieDataModelArrayList = new RetrieveMovieDataFromNetwork(getActivity()).execute(preferenceValue).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else
        {
            movieDataModelArrayList = savedInstanceState.getParcelableArrayList("MovieData");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        GridView moviesGrid = (GridView)rootView.findViewById(R.id.movieGrid);
        AdapterView.OnItemClickListener movieGridListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieDataModel movieObject = (MovieDataModel) parent.getItemAtPosition(position);
                Intent navigateToMovieDetail = new Intent(getActivity(), MovieDetailActivity.class);
                navigateToMovieDetail.putExtra(getString(R.string.movie_object), movieObject);
                startActivity(navigateToMovieDetail);
            }
        };

        if(movieDataModelArrayList.size() >0) {
            MovieGridAdapter movieGridAdapter = new MovieGridAdapter(getActivity(), movieDataModelArrayList);
            moviesGrid.setAdapter(movieGridAdapter);
            moviesGrid.setOnItemClickListener(movieGridListener);
        }
        return rootView;
    }

}
