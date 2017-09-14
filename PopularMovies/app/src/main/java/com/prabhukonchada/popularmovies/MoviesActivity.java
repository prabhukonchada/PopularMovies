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


}
