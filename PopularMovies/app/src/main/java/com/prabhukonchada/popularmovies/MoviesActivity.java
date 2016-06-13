package com.prabhukonchada.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Initialise Fresco Library
        Fresco.initialize(this);

        // Add MovieGridFragment to this activity
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MovieGridFragment()).commit();
    }
}
