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
public class MovieGridAdapter extends BaseAdapter {
    ArrayList<MovieBean> movieDataModelArrayList = new ArrayList<>();
    private Context applicationContext;

    public MovieGridAdapter(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setMovieDataModelArrayList(ArrayList<MovieBean> movieDataModelArrayList) {
        this.movieDataModelArrayList = movieDataModelArrayList;
    }

    @Override
    public int getCount() {
        return movieDataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieDataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.movie_item, null);
            holder = new ViewHolder();
            holder.moviePosterImage = (SimpleDraweeView)convertView.findViewById(R.id.movieThumbnail);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        StringBuffer posterUrl = new StringBuffer(applicationContext.getString(R.string.image_url_small));
        holder.moviePosterImage.setImageURI(Uri.parse(posterUrl.append(movieDataModelArrayList.get(position).getPoster_path()).toString()));
        return convertView;
    }

    class ViewHolder
    {
        SimpleDraweeView moviePosterImage;
    }
}
