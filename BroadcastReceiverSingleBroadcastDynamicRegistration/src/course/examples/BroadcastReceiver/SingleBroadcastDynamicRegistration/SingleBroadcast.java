package course.examples.BroadcastReceiver.SingleBroadcastDynamicRegistration;

import course.examples.BroadcastReceiver.SingleBroadcastDynamicRegistration.R;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SingleBroadcast extends Activity {

	public static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.intent.action.TEST1";
	final Receiver1 br1 = new Receiver1();
	final IntentFilter intfil = new IntentFilter(CUSTOM_INTENT);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendBroadcast(new Intent(CUSTOM_INTENT),android.Manifest.permission.VIBRATE);
			}
		});

		registerReceiver(br1, intfil);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(br1);
		super.onDestroy();
	}
}