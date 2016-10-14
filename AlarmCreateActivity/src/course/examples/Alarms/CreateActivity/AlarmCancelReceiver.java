package course.examples.Alarms.CreateActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmCancelReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		final Intent newIntent = new Intent(context,
				ScoldActivity.class);
		final PendingIntent sender = PendingIntent.getActivity(
				context, 0, newIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(sender);
		System.out.println("Sender cancelled");
	}
}
