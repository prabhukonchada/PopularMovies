package com.prabhukonchada.popularmovies;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MovieDetailActivity extends AppCompatActivity {

    ViewPager movieNavigationPager;
    FragmentPagerAdapter movieNavigationPagerAdapter;
    int dataSetSize;
    int current_item_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataSetSize =  this.getIntent().getIntExtra("count",0);
        current_item_position = this.getIntent().getIntExtra("grid_item_position",0);
        movieNavigationPager = (ViewPager)findViewById(R.id.navigateMoviePager);
        movieNavigationPagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(),dataSetSize,0);
        movieNavigationPager.setAdapter(movieNavigationPagerAdapter);
        movieNavigationPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                movieNavigationPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
