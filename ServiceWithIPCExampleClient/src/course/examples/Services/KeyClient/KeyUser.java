package course.examples.Services.KeyClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import course.examples.Services.KeyCommon.KeyGenerator;

public class KeyUser extends Activity {

	private KeyGenerator service;
	private boolean bound;

	private ServiceConnection connection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder iservice) {
			System.out.println("Connected to ComponentName:" + className);
			service = KeyGenerator.Stub.asInterface(iservice);
			bound = true;
		}

		public void onServiceDisconnected(ComponentName className) {
			service = null; bound = false;
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		final TextView output = (TextView) findViewById(R.id.output);
		final Button goButton = (Button) findViewById(R.id.go_button);
		goButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					output.setText(service.getKey());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(KeyGenerator.class.getName());
		intent.setFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);
		bindService(intent,	this.connection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		if (bound)
			unbindService(this.connection);
		super.onStop();
	}
}
