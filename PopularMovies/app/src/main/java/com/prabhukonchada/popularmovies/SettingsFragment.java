package com.prabhukonchada.popularmovies;


import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;


/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class SettingsFragment extends PreferenceFragment {

    ListPreference movieSortOrderList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_sort_movies);
        movieSortOrderList = (ListPreference)findPreference(getString(R.string.pref_sort_key));
        movieSortOrderList.setSummary(movieSortOrderList.getEntry());
    }


}
