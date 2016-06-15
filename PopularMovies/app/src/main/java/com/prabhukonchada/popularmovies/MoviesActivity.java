package com.prabhukonchada.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MoviesActivity extends AppCompatActivity {

    String TAG = "Movies Activity :";
    MovieGridFragment movieGridFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Initialise Fresco Library
        Fresco.initialize(this);
        
        // Handling orientation change
        if(savedInstanceState == null) {
            movieGridFragment = new MovieGridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container,movieGridFragment,getString(R.string.movies_fragment_tag)).commit();
            Log.d(TAG, "onCreate: NEW FRAGMENT");
        }
        else
        {
            /**
             * FIXME
             * For Reviewer :
             * Though I replace it or not the fragment is automatically created and I dont know why ?
             */

            movieGridFragment = (MovieGridFragment)getSupportFragmentManager().getFragment(savedInstanceState,getString(R.string.movies_fragment_tag));
            getSupportFragmentManager().beginTransaction().replace(R.id.container,movieGridFragment,getString(R.string.movies_fragment_tag)).commit();
            Log.d(TAG,savedInstanceState.toString());
            Log.d(TAG, "onCreate: saved instance is not null");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Activity");
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, getString(R.string.movies_fragment_tag), movieGridFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: Activity");
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState == null)
        {
            // Add MovieGridFragment to this activity

        }
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: Activity");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: Activity");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: Activity");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: Activity");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Activity");
        super.onResume();
    }
}
