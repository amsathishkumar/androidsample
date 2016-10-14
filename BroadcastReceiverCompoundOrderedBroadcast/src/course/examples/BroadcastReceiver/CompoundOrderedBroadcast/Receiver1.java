package course.examples.BroadcastReceiver.CompoundOrderedBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

public class Receiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println(this + ":GOT THE INTENT");
		// emulator doesn't support vibration
		Vibrator v = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(500);
	}

}
