package com.prabhukonchada.popularmovies;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Prabhu Konchada on 13/01/17
 */
public class PopularMoviesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
