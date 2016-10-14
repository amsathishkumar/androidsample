package course.examples.Activity.SimpleMapExample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import course.examples.SimpleActivity.R;

public class MapLocation extends Activity {

	private final String TAG = "MapLocation";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final EditText addrText = (EditText) findViewById(R.id.location);
		final Button button = (Button) findViewById(R.id.mapButton);

		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String address = addrText.getText().toString();
					address = address.replace(' ', '+');
					Intent geoIntent = new Intent(
							android.content.Intent.ACTION_VIEW, Uri
									.parse("geo:0,0?q=" + address));
					startActivity(geoIntent);
				} catch (Exception e) {
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "The activity is about to become visible.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "The activity has become visible (it is now \"resumed\")");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG,
				"Another activity is taking focus (this activity is about to be \"paused\")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "The activity is about to be destroyed.");
	}
}
