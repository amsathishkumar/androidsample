package course.examples.SimpleActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MapLocation extends Activity {
	private static final int PICK_CONTACT_REQUEST = 0;
	static String TAG = "MapLocation";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.mapButton);

		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(Intent.ACTION_PICK,
							ContactsContract.Contacts.CONTENT_URI);
					startActivityForResult(intent, PICK_CONTACT_REQUEST);
				} catch (Exception e) {}
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK
				&& requestCode == PICK_CONTACT_REQUEST) {

			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(data.getData(), null, null, null, null);
			cursor.moveToFirst();

			String id = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			String where = ContactsContract.Data.CONTACT_ID + " = ? AND "
					+ ContactsContract.Data.MIMETYPE + " = ?";
			String[] whereParameters = new String[] {
					id,
					ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE };
			Cursor addrCur = cr.query(ContactsContract.Data.CONTENT_URI, null,
					where, whereParameters, null);

			addrCur.moveToFirst();
			String street = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
			String city = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
			String state = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
			String postalCode = addrCur
					.getString(addrCur
							.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
			addrCur.close();

			String address = (street + " " + city + " " + state + " " + postalCode);
			address.replace(' ', '+');
			Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=" + address));
			startActivity(geoIntent);
		}
	}
}
