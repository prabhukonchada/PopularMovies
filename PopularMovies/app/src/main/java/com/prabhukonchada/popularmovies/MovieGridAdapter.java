package com.prabhukonchada.popularmovies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieGridAdapter extends BaseAdapter
{
    private Context applicationContext;
    ArrayList<MovieDataModel> movieDataModelArrayList;
    private Integer[] mThumbIds = {
            R.drawable.sample_1,R.drawable.sample_2,
            R.drawable.sample_3,R.drawable.sample_4,
            R.drawable.sample_5,R.drawable.sample_6
    };

    public MovieGridAdapter(Context applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public MovieGridAdapter(Context applicationContext,ArrayList<MovieDataModel> movieDataModelArrayList)
    {
        this.applicationContext = applicationContext;
        this.movieDataModelArrayList = movieDataModelArrayList;
    }


    @Override
    public int getCount() {
        return movieDataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null)
        {
            imageView = new ImageView(applicationContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else
        {
            imageView = (ImageView)convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
