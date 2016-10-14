package course.examples.Dialogs.AlertDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlertDialogExample extends Activity {
	private final int ALERTTAG = 0, PROGRESSTAG = 1;
	Button shutdownButton = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		shutdownButton = (Button) findViewById(R.id.shutdownButton);
		shutdownButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(ALERTTAG);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case ALERTTAG:
			Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Do you really want to exit?")
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								public void onClick(
										final DialogInterface dialog, int id) {
									dialog.cancel();
									showDialog(PROGRESSTAG);

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			return builder.create();
		
		case PROGRESSTAG:
			shutdownButton.setEnabled(false);
			final ProgressDialog dialog = new ProgressDialog(this);
			dialog.setMessage("Activity Shutting Down.");
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dialog.dismiss();
					AlertDialogExample.this.finish();
				}
			}).start();
			return dialog;

		default:
			return null;
		}
	}
}