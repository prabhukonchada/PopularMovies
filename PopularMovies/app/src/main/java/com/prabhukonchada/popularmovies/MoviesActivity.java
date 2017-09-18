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

        // TODO:Initialise Apxor SDK
        // TODO: Call The Api To Get Apps
        // Add privacy policy
        
        // Handling orientation change
        if(savedInstanceState == null) {
            movieGridFragment = new MovieGridFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container,movieGridFragment).commit();
            Log.d(TAG, "onCreate: NEW FRAGMENT");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Activity");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: Activity");
        super.onRestoreInstanceState(savedInstanceState);
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
