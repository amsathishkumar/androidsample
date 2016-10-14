package course.examples.Services.Logging;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BGLoggingService extends IntentService {
	private static final String TAG = "LoggingService";

	public BGLoggingService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "Service created");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		Log.i(TAG, "Service command started");
		return START_NOT_STICKY; 
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Service destroyed");
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		final Intent arg = intent;
		Log.i(TAG, "Service intent handling started");
		new Thread() {
			public void run () {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Log.i(TAG,arg.getCharSequenceExtra("course.examples.Services.Logging").toString());
			}
		}.start();
	}
}
