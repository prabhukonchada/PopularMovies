package com.prabhukonchada.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieGridAdapter extends BaseAdapter
{
    private Context applicationContext;
    ArrayList<MovieDataModel> movieDataModelArrayList;

    public MovieGridAdapter(Context applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public ArrayList<MovieDataModel> getMovieDataModelArrayList() {
        return movieDataModelArrayList;
    }

    public void setMovieDataModelArrayList(ArrayList<MovieDataModel> movieDataModelArrayList) {
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
        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SimpleDraweeView imageView;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.movie_item,null);
        }

        imageView = (SimpleDraweeView) convertView.findViewById(R.id.movieThumbnail);
        imageView.setImageURI(Uri.parse(movieDataModelArrayList.get(position).getMoviePosterImageThumbnail()));
        return convertView;
    }
}
