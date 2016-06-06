package com.prabhukonchada.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Add MovieGridFragment to this activity
        getSupportFragmentManager().beginTransaction().add(R.id.container,new MovieGridFragment()).commit();


    }
}
