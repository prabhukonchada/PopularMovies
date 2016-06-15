package com.prabhukonchada.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prabhu Konchada on 15/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieBean implements Parcelable {
    private String poster_path;
    private String overview;
    private String release_date;
    private String original_title;
    private String title;
    private String backdrop_path;
    private double vote_average;

    protected MovieBean(Parcel in) {
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title= in.readString();
        title =  in.readString();
        backdrop_path = in.readString();
        vote_average = in.readDouble();
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieBean> CREATOR = new Parcelable.Creator<MovieBean>() {
        @Override
        public MovieBean createFromParcel(Parcel in) {
            return new MovieBean(in);
        }

        @Override
        public MovieBean[] newArray(int size) {
            return new MovieBean[size];
        }
    };

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeDouble(vote_average);
    }
}
