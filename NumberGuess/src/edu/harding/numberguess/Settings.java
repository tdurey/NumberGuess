package edu.harding.numberguess;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;



public class Settings extends PreferenceActivity {
	
	public static final String LARGEST_NUMBER_PREFERENCE_KEY = "largest_number";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		// Turn on up button
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(this) != null)
				this.getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		final EditTextPreference victoryMessagePref = (EditTextPreference) 
				findPreference(LARGEST_NUMBER_PREFERENCE_KEY);
		String victoryMessage = prefs.getString(LARGEST_NUMBER_PREFERENCE_KEY, getResources().getString(R.string.default_number));
		victoryMessagePref.setSummary("\"" + victoryMessage + "\"");
		victoryMessagePref.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
		
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				victoryMessagePref.setSummary((CharSequence) newValue);

				// Save the new preference
				SharedPreferences.Editor ed = prefs.edit();
				ed.putString(LARGEST_NUMBER_PREFERENCE_KEY, newValue.toString());
				ed.commit();
				
				return true;
			}
		});
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			// Go back to main screen.  Had to add android:launchMode="singleTop" 
			// to the parent activity in manifest to get it to not re-create itself
			if (NavUtils.getParentActivityName(this) != null)
				NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
