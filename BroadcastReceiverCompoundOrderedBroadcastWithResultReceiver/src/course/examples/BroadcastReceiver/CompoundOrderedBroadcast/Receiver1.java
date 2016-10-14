package course.examples.BroadcastReceiver.CompoundOrderedBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Receiver1 extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String tmp = getResultData() != null ? getResultData() : "";
		setResultData(tmp + ":Receiver 1:");
	}

}
