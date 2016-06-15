package com.prabhukonchada.popularmovies;

import java.util.List;

/**
 * Created by Prabhu Konchada on 15/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class MovieGsonBean {

    /**
     * poster_path : /sM33SANp9z6rXW8Itn7NnG1GOEs.jpg
     * adult : false
     * overview : Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery.
     * release_date : 2016-02-11
     * genre_ids : [16,12,10751,35]
     * id : 269149
     * original_title : Zootopia
     * original_language : en
     * title : Zootopia
     * backdrop_path : /mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg
     * popularity : 84.576505
     * vote_count : 1144
     * video : false
     * vote_average : 7.47
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String poster_path;
        private String overview;
        private String release_date;
        private String original_title;
        private String title;
        private String backdrop_path;
        private double vote_average;

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
    }
}
