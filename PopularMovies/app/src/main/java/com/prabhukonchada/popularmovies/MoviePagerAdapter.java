package com.prabhukonchada.popularmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by prabhukonchada on 14/09/17.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    int count;
    int current_position;

    public MoviePagerAdapter(FragmentManager fm,int count,int position) {
        super(fm);
        this.count = count;
        current_position = position;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        Log.d("adapter_position",String.valueOf(position));
        args.putInt("my_current_position_now",position);
        MovieDetailActivityFragment movieDetail = new MovieDetailActivityFragment();
        movieDetail.position = position;
        return movieDetail;
    }

    @Override
    public int getCount() {
        return count;
    }
}
