package course.examples.DataManagement.PreferenceActivity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class LoadPreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		addPreferencesFromResource(R.xml.user_prefs);

		
		final EditTextPreference uNamePref = (EditTextPreference) getPreferenceScreen()
				.findPreference(PreferencesActivityExample.USERNAME);
		uNamePref.setSummary(prefs.getString(PreferencesActivityExample.USERNAME, ""));
		
		prefs.registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
			   uNamePref.setSummary(prefs.getString(PreferencesActivityExample.USERNAME, ""));
			}
		});
		
	}
}
