package com.prabhukonchada.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieDataModel implements Parcelable{
    private String movieName;
    private String moviePosterImageThumbnail;
    private String moviePlotSynopsis;
    private String releaseDate;
    private String movieImage;
    private String voteAverage;

    public MovieDataModel()
    {

    }

    protected MovieDataModel(Parcel in) {
        movieName = in.readString();
        moviePosterImageThumbnail = in.readString();
        moviePlotSynopsis = in.readString();
        releaseDate = in.readString();
        movieImage = in.readString();
        voteAverage = in.readString();
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    // User rating (called vote_average in the api)
    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public String getMoviePosterImageThumbnail() {
        return moviePosterImageThumbnail;
    }

    public void setMoviePosterImageThumbnail(String moviePosterImageThumbnail) {
        this.moviePosterImageThumbnail = moviePosterImageThumbnail;
    }

    // A plot synopsis (called overview in the api)
    public String getMoviePlotSynopsis() {
        return moviePlotSynopsis;
    }

    public void setMoviePlotSynopsis(String moviePlotSynopsis) {
        this.moviePlotSynopsis = moviePlotSynopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeString(moviePosterImageThumbnail);
        dest.writeString(moviePlotSynopsis);
        dest.writeString(releaseDate);
        dest.writeString(movieImage);
        dest.writeString(voteAverage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MovieDataModel> CREATOR = new Parcelable.Creator<MovieDataModel>() {
        @Override
        public MovieDataModel createFromParcel(Parcel in) {
            return new MovieDataModel(in);
        }

        @Override
        public MovieDataModel[] newArray(int size) {
            return new MovieDataModel[size];
        }
    };
}
