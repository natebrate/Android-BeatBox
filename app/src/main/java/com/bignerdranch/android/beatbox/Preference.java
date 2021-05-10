package com.bignerdranch.android.beatbox;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceManager;

public class Preference extends PreferenceActivity{

    public static final int RESULT_CODE_THEME_UPDATED = 1;
    @Override
    public void onCreate(Bundle SavedInstanceState) {

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String themeName = pref.getString("THEME", "Theme1");
        if (themeName.equals("Africa")) {
            setTheme(R.style.AppTheme);

        } else if (themeName.equals("Colorful Beach")) {
            //Toast.makeText(this, "set theme", Toast.LENGTH_SHORT).show();
            setTheme(R.style.BrightTheme);

        } else if (themeName.equals("Abstract")) {
            //Toast.makeText(this, "set theme", Toast.LENGTH_SHORT).show();
            setTheme(R.style.DarkTheme);

        } else if (themeName.equals("Default")) {
            setTheme(R.style.AppTheme);
        }
        //Toast.makeText(this, "Theme has been reset to " + themeName,
        // Toast.LENGTH_SHORT).show();

        super.onCreate(SavedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        findPreference("THEME").setOnPreferenceChangeListener(new RefreshActivityOnPreferenceChangeListener(RESULT_CODE_THEME_UPDATED));
    }

    public class RefreshActivityOnPreferenceChangeListener implements OnPreferenceChangeListener {

        private final int resultCodeThemeUpdated;
        public RefreshActivityOnPreferenceChangeListener(int resultCodeThemeUpdated) {
            this.resultCodeThemeUpdated = resultCodeThemeUpdated;
        }

        public boolean onPreferenceChange(Preference p, Object newValue) {
            setResult(resultCodeThemeUpdated);
            return true;
        }

        @Override
        public boolean onPreferenceChange(android.preference.Preference preference, Object o) {
            return false;
        }
    }

}
