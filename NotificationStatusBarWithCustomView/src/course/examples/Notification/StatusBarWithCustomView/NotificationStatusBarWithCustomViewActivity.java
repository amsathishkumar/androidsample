package course.examples.Notification.StatusBarWithCustomView;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;

public class NotificationStatusBarWithCustomViewActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		startService(new Intent(this, MyService.class));
	}

	public static class MyService extends Service {
		private static final int NOTIFICATION_ONE = 1;

		public MyService() {
			super();
		}

		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}

		@Override
		public void onStart(Intent intent, int startId) {
			super.onStart(intent, startId);

			int icon = android.R.drawable.stat_sys_warning;
			CharSequence tickerText = "Notification";
			long when = System.currentTimeMillis();

			Notification notification = new Notification(icon, tickerText, when);

			Context context = getApplicationContext();
			CharSequence contentTitle = "My Notification";
			CharSequence contentText = "You've got notifications!";
			Intent notificationIntent = new Intent(this,
					NotificationSubActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
					notificationIntent, 0);

			notification.setLatestEventInfo(context, contentTitle, contentText,
					contentIntent);
			notification.defaults |= Notification.DEFAULT_SOUND;
			//notification.sound = Uri
			//		.parse("file:///sdcard/media/audio/alarms/rooster_2.mp3");

			// notification.defaults |= Notification.DEFAULT_VIBRATE;
			long[] vibrate = { 0, 100, 200, 300 };
			notification.vibrate = vibrate;

			// notification.defaults |= Notification.DEFAULT_LIGHTS;

			notification.ledARGB = 0xff00ff00;
			notification.ledOnMS = 300;
			notification.ledOffMS = 1000;
			notification.flags |= Notification.FLAG_SHOW_LIGHTS;

			RemoteViews contentView = new RemoteViews(getPackageName(),
					R.layout.custom_notification);
			notification.contentView = contentView;

			NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			mNotificationManager.notify(NOTIFICATION_ONE, notification);
		}
	}
}