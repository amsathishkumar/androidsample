package course.examples.BroadcastReceiver.CompoundOrderedBroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class CompoundOrderedBroadcastWithResultReceiver extends Activity {

	public static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.intent.action.TEST4";
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
				sendOrderedBroadcast(new Intent(CUSTOM_INTENT), null,
						new BroadcastReceiver() {
							@Override
							public void onReceive(Context context, Intent intent) {
							System.out.println("Final Result is:" + getResultData());
								
							}
						},
						null, 0, null, null);
			}
		});
		intfil.setPriority(10);
		registerReceiver(br1, intfil);
	}
    
    @Override
    protected void onDestroy() {
    	unregisterReceiver(br1);
    	super.onDestroy();
    }
}