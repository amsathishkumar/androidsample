package course.examples.Sensors.ShowValues;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorShowValuesActivity extends Activity implements
		SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private float mAlpha = 0.9f, mLastX, mLastY, mLastZ, mLowPassX, mLowPassY,
			mLowPassZ, mHighPassX, mHighPassY, mHighPassZ;

	private TextView xView, yView, zView, mXLowPassView, mYLowPassView,
			mZLowPassView, mXHighPassView, mYHighPassView, mZHighPassView;
	private long mLastUpdate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		xView = (TextView) findViewById(R.id.x_value_view);
		yView = (TextView) findViewById(R.id.y_value_view);
		zView = (TextView) findViewById(R.id.z_value_view);
		mXLowPassView = (TextView) findViewById(R.id.x_lowpass_view);
		mYLowPassView = (TextView) findViewById(R.id.y_lowpass_view);
		mZLowPassView = (TextView) findViewById(R.id.z_lowpass_view);
		mXHighPassView = (TextView) findViewById(R.id.x_highpass_view);
		mYHighPassView = (TextView) findViewById(R.id.y_highpass_view);
		mZHighPassView = (TextView) findViewById(R.id.z_highpass_view);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		for (Sensor sensor : mSensorManager.getSensorList(Sensor.TYPE_ALL)) {
			System.out.println(sensor.getName());
		}
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mLastUpdate = System.currentTimeMillis();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			long actualTime = System.currentTimeMillis();
			if (actualTime - mLastUpdate > 500) {
				mLastUpdate = actualTime;

				float x = event.values[0], y = event.values[1], z = event.values[2];
				xView.setText(String.valueOf(x));
				yView.setText(String.valueOf(y));
				zView.setText(String.valueOf(z));

				mLowPassX = lowPass(x, mLowPassX);
				mLowPassY = lowPass(y, mLowPassY);
				mLowPassZ = lowPass(z, mLowPassZ);
				mXLowPassView.setText(String.valueOf(mLowPassX));
				mYLowPassView.setText(String.valueOf(mLowPassY));
				mZLowPassView.setText(String.valueOf(mLowPassZ));

				mHighPassX = highPass(x, mLastX, mHighPassX);
				mHighPassY = highPass(y, mLastY, mHighPassY);
				mHighPassZ = highPass(z, mLastZ, mHighPassZ);
				mXHighPassView.setText(String.valueOf(mHighPassX));
				mYHighPassView.setText(String.valueOf(mHighPassY));
				mZHighPassView.setText(String.valueOf(mHighPassZ));

				mLastX = x;
				mLastY = y;
				mLastZ = z;
			}
		}
	}

	private float highPass(float current, float last, float filtered) {
		return mAlpha * (filtered + current - last);
	}

	private float lowPass(float current, float filtered) {
		return mAlpha * current + (1.0f - mAlpha) * filtered;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}