package course.examples.DataManagement.PreferenceActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PreferencesActivityExample extends Activity {
	SharedPreferences prefs;
	final static String USERNAME = "uname";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		prefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		final Button button = (Button) findViewById(R.id.check_pref_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(
						PreferencesActivityExample.this,
						LoadPreferencesActivity.class));
			}
		});

	}
}