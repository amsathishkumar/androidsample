package course.examples.AudioVideo.Ringtone;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AudioVideoRingtoneManagerActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final Button ringtoneButton = (Button) findViewById(R.id.button1);
		ringtoneButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Ringtone r = RingtoneManager.getRingtone(AudioVideoRingtoneManagerActivity.this,
						Settings.System.DEFAULT_RINGTONE_URI);
				if (null != r) r.play();
			}
		});
		

		final Button notifButton = (Button) findViewById(R.id.button2);
		notifButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Ringtone r = RingtoneManager.getRingtone(AudioVideoRingtoneManagerActivity.this,
						Settings.System.DEFAULT_NOTIFICATION_URI);
				if (null != r) r.play();
			}
		});
		

		final Button alarmButton = (Button) findViewById(R.id.button3);
		alarmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Ringtone r = RingtoneManager.getRingtone(AudioVideoRingtoneManagerActivity.this,
						Settings.System.DEFAULT_ALARM_ALERT_URI);
				if (null != r) r.play();
			}
		});
	}
}