package course.examples.Touch.Gerstures;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.android.gestures.demo.R;

public class GesturesActivity extends Activity implements
		OnGesturePerformedListener {
	private GestureLibrary mLibrary;
	int bgColor = 0;
	FrameLayout frame;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		frame = (FrameLayout) findViewById(R.id.frame);
		bgColor = new Random().nextInt(0xFFFFFF) | 0xFF000000;
		frame.setBackgroundColor(bgColor);

		mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!mLibrary.load()) {
			finish();
		}

		GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
		gestures.addOnGesturePerformedListener(this);

	}

	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

		if (predictions.size() > 0) {
			Prediction prediction = predictions.get(0);
			if (prediction.score > 1.0) {
				if (prediction.name.equals("Down")) {
					bgColor -= 100;
					frame.setBackgroundColor(bgColor);
				} else if (prediction.name.equals("Up")) {
					bgColor += 100;
					frame.setBackgroundColor(bgColor);
				} else {
					Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}
}