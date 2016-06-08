package com.prabhukonchada.popularmovies;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieGridFragment extends Fragment {



    public MovieGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_grid, container, false);
        GridView moviesGrid = (GridView)rootView.findViewById(R.id.movieGrid);
        moviesGrid.setAdapter(new ImageAdapter(getActivity()));
        return rootView;
    }

    public class ImageAdapter extends BaseAdapter
    {
        private Context applicationContext;
        private Integer[] mThumbIds = {
                R.drawable.sample_1,R.drawable.sample_2,
                R.drawable.sample_3,R.drawable.sample_4,
                R.drawable.sample_5,R.drawable.sample_6
        };

        public ImageAdapter(Context context)
        {
            applicationContext = context;
        }


        @Override
        public int getCount() {
            return mThumbIds.length;
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

}
