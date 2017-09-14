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
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFragment extends Fragment{

    ArrayList<MovieBean> movieDataModelArrayList;
    MovieGridAdapter movieGridAdapter;
    GridView moviesGrid;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Fragment");
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.parcelable_movie_model_list_key))) {
            if(isOnline(getActivity())) {
                setMovieDataModelArrayList(movieDataModelArrayList);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState != null)
        {
            setMovieDataModelArrayList(movieDataModelArrayList);
        }
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        moviesGrid = (GridView) rootView.findViewById(R.id.movieGrid);
        AdapterView.OnItemClickListener movieGridListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieBean movieObject = (MovieBean) parent.getItemAtPosition(position);
                Intent navigateToMovieDetail = new Intent(getActivity(), MovieDetailActivity.class);
                navigateToMovieDetail.putExtra(getString(R.string.movie_object), movieObject);
                navigateToMovieDetail.putParcelableArrayListExtra("test",getMovieDataModelArrayList());
                navigateToMovieDetail.putExtra("count",getMovieDataModelArrayList().size());
                navigateToMovieDetail.putExtra("grid_item_position",position);
                Log.d("Grid_position",String.valueOf(position));
                startActivity(navigateToMovieDetail);
            }
        };
        movieGridAdapter = new MovieGridAdapter(getActivity());
        movieGridAdapter.setMovieDataModelArrayList(movieDataModelArrayList);
        moviesGrid.setAdapter(movieGridAdapter);
        moviesGrid.setOnItemClickListener(movieGridListener);



        return rootView;
    }


    public ArrayList<MovieBean> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }

    public void setMovieDataModelArrayList(ArrayList<MovieBean> movieDataModelArrayList) {
        MovieBean movieOne = new MovieBean("/uX7LXnsC7bZJZjn048UCOwkPXWJ.jpg","Minions","Minions Stuart, Kevin and Bob are recruited by Scarlet Overkill, a super-villain who, alongside her inventor husband Herb, hatches a plot to take over the world.","/q0R4crx2SehcEEQEkYObktdeFy.jpg","2015-06-17","Minions",6.4);
        MovieBean movieTwo = new MovieBean("/o8u0NyEigCEaZHBdCYTRfXR8U4i.jpg","Annabelle: Creation", "Several years after the tragic death of their little girl, a dollmaker and his wife welcome a nun and several girls from a shuttered orphanage into their home, soon becoming the target of the dollmaker's possessed creation, Annabelle.","/tb86j8jVCVsdZnzf8I6cIi65IeM.jpg","2017-08-03","Annabelle: Creation",6.4);
        MovieBean movieThree = new MovieBean("/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg","It","In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.","/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg", "2017-09-06","It",7.6);
        MovieBean movieFour = new MovieBean("/6aUWe0GSl69wMTSWWexsorMIvwU.jpg","Beauty and the Beast","A live-action adaptation of Disney's version of the classic 'Beauty and the Beast' tale of a cursed prince and a beautiful young woman who helps him break the spell.","/tWqifoYuwLETmmasnGHO7xBjEtt.jpg","2017-03-16","Beauty and the Beast",6.8);
        MovieBean movieFive = new MovieBean("/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg","Guardians of the Galaxy","Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.","/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg","2014-07-30","Guardians of the Galaxy",7.9);
        MovieBean movieSix = new MovieBean("/ibianpvL865w7rBPAg3sPlEXUyh.jpg","The Layover", "When their plane is rerouted due to a hurricane warning, two single female best friends find themselves competing for the same guy during an extended layover in St. Louis.","/kb9osnqanXRpkpm1bnSqAhKoq5T.jpg","2017-09-01","The Layover",4.7);
        movieDataModelArrayList = new ArrayList<MovieBean>();
        movieDataModelArrayList.add(0,movieOne);
        movieDataModelArrayList.add(1,movieTwo);
        movieDataModelArrayList.add(2,movieThree);
        movieDataModelArrayList.add(3,movieFour);
        movieDataModelArrayList.add(4,movieFive);
        movieDataModelArrayList.add(5,movieSix);

        this.movieDataModelArrayList = movieDataModelArrayList;
    }

    private boolean isOnline(Context context) {
        ConnectivityManager mngr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mngr.getActiveNetworkInfo();

        return !(info == null || (info.getState() != NetworkInfo.State.CONNECTED));
    }
}
