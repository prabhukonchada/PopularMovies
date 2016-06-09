package com.prabhukonchada.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Add MovieGridFragment to this activity
        getSupportFragmentManager().beginTransaction().add(R.id.container,new MovieGridFragment()).commit();
    }
}
