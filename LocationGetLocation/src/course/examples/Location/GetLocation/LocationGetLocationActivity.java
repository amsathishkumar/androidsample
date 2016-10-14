package course.examples.Location.GetLocation;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LocationGetLocationActivity extends Activity {

	private static final long TWO_MIN = 1000 * 60 * 2,
			MEASURE_TIME = 1000 * 30;

	private TextView mTextView = null;
	private Location mBestReading = null;
	private LocationManager mLocationManager = null;
	private LocationListener mLocationListener = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mTextView = (TextView) findViewById(R.id.textView1);
		int mTextViewColor = mTextView.getCurrentTextColor();
		
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mBestReading = mLocationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (null != mBestReading) {
			mBestReading.setAccuracy(Float.MAX_VALUE);
			if (System.currentTimeMillis() - mBestReading.getTime() > TWO_MIN) {
				mTextView.setTextColor(Color.RED);
			}
			mTextView.setText(getDisplayString(mBestReading));
			Log.i("XX","color:" + mTextView.getTextColors());
			mTextView.setTextColor(mTextViewColor);
		} else {
			mTextView.setText("No Initial Reading Available");
		}

		mLocationListener = new LocationListener() {
			public synchronized void onLocationChanged(Location location) {
				Log.i("XX", "onLocationChanged() called");
				if (null == mBestReading
						|| mBestReading.getTime() - System.currentTimeMillis() > TWO_MIN
						|| location.getAccuracy() < mBestReading.getAccuracy()) {
					mBestReading = location;
					mTextView.setText(getDisplayString(location));
				}
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};

		mLocationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				0, 0, mLocationListener);

		Executors.newScheduledThreadPool(1).schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("cancelled location updates");
				mLocationManager.removeUpdates(mLocationListener);
			}
		}, MEASURE_TIME, TimeUnit.MILLISECONDS);
	}

	@Override
	protected void onDestroy() {
		mLocationManager.removeUpdates(mLocationListener);
		super.onDestroy();
	}

	private String getDisplayString(Location location) {
		return "Accuracy:" + location.getAccuracy() + " Long.:"
				+ location.getLongitude() + " Lat.:" + location.getLatitude()
				+ " Time:" + location.getTime();
	}
}