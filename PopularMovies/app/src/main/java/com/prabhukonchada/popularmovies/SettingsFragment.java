package com.prabhukonchada.popularmovies;


import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;


/**
 * Created by Prabhu Konchada on 09/06/16
 * you can contact me at : prabhukonchada@gmail.com
 */
public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    ListPreference movieSortOrderList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_sort_movies);
        movieSortOrderList = (ListPreference)findPreference(getString(R.string.pref_sort_key));
        movieSortOrderList.setOnPreferenceChangeListener(this);
        movieSortOrderList.setSummary(movieSortOrderList.getEntry());
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d("Preference :",newValue.toString());
        String value = newValue.toString();
        if(preference instanceof ListPreference)
        {
            movieSortOrderList = (ListPreference)preference;
            int index = movieSortOrderList.findIndexOfValue(value);
            if(index >= 0)
            {
                movieSortOrderList.setSummary(movieSortOrderList.getEntries()[index]);
            }
        }
        return true;
    }
}
