package com.prabhukonchada.popularmovies;

import java.io.Serializable;

/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieDataModel implements Serializable {
    private String movieName;
    private String moviePosterImageThumbnail;
    private String moviePlotSynopsis;
    private String releaseDate;
    private String movieImage;
    private String voteAverage;

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
}
