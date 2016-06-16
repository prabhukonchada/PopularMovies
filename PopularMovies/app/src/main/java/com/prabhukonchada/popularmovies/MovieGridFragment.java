package com.prabhukonchada.popularmovies;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFragment extends Fragment{

    ArrayList<MovieBean> movieDataModelArrayList;
    MovieGridAdapter movieGridAdapter;
    GridView moviesGrid;
    String preferenceValue;
    String TAG = "MovieGridFragment";

    public MovieGridFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Fragment");
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
    public void onResume() {
        Log.d(TAG, "onResume: Fragment");
        super.onResume();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: Fragment");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: Fragment");
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Fragment");
        super.onCreate(savedInstanceState);
        preferenceValue = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(getString(R.string.pref_sort_key), getString(R.string.default_preference_of_user));
        DataBus.getInstance().register(this);
        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.parcelable_movie_model_list_key))) {
            if(isOnline(getActivity())) {
                new RetrieveMovieDataFromNetwork(getActivity()).execute(preferenceValue);
            }
            else
            {
                Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            movieDataModelArrayList = savedInstanceState.getParcelableArrayList(getString(R.string.parcelable_movie_model_list_key));
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: Fragment");
        super.onStart();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: Fragment");
        DataBus.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Fragment");
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        moviesGrid = (GridView) rootView.findViewById(R.id.movieGrid);
        AdapterView.OnItemClickListener movieGridListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieBean movieObject = (MovieBean) parent.getItemAtPosition(position);
                Intent navigateToMovieDetail = new Intent(getActivity(), MovieDetailActivity.class);
                navigateToMovieDetail.putExtra(getString(R.string.movie_object), movieObject);
                startActivity(navigateToMovieDetail);
            }
        };
        movieGridAdapter = new MovieGridAdapter(getActivity());
        moviesGrid.setAdapter(movieGridAdapter);
        moviesGrid.setOnItemClickListener(movieGridListener);

        if(savedInstanceState != null)
        {
            setListValuesAndNotify();
        }

        return rootView;
    }

    @Subscribe public void onDataRetrieved(DataRetrivalResultEvent event )
    {
        Log.d(TAG, "onDataRetrieved: Subscription");
        setMovieDataModelArrayList(event.getMovieDataModelArrayList());
        setListValuesAndNotify();
    }

    private void setListValuesAndNotify()
    {
        movieGridAdapter.setMovieDataModelArrayList(movieDataModelArrayList);
        movieGridAdapter.notifyDataSetChanged();
    }

    public ArrayList<MovieBean> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }

    public void setMovieDataModelArrayList(ArrayList<MovieBean> movieDataModelArrayList) {
        this.movieDataModelArrayList = movieDataModelArrayList;
    }

    private boolean isOnline(Context context) {
        ConnectivityManager mngr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mngr.getActiveNetworkInfo();

        return !(info == null || (info.getState() != NetworkInfo.State.CONNECTED));
    }
}
