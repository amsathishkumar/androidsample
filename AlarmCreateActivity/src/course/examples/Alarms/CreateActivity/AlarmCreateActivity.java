package course.examples.Alarms.CreateActivity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmCreateActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button alarmSetButton = (Button) findViewById(R.id.button1);
		alarmSetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent1 = new Intent(AlarmCreateActivity.this,
						ScoldActivity.class);
				intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent sender1 = PendingIntent.getActivity(
						AlarmCreateActivity.this, 0, intent1,
						PendingIntent.FLAG_UPDATE_CURRENT);

				Intent intent2 = new Intent(AlarmCreateActivity.this,
						AlarmCancelReceiver.class);
				PendingIntent sender2 = PendingIntent.getBroadcast(
						AlarmCreateActivity.this, 0, intent2,
						PendingIntent.FLAG_UPDATE_CURRENT);

				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				Calendar cal = Calendar.getInstance();
				am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
						10000, sender1);
				am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() + 20000,
						sender2);

				finish();
			}
		});
	}
}