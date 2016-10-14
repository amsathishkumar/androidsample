package course.examples.Services.Logging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BGLoggingDemo extends Activity {	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button buttonStart = (Button) findViewById(R.id.buttonStart);
		buttonStart.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(BGLoggingDemo.this, BGLoggingService.class);
				intent.putExtra("course.examples.Services.Logging", "Log this message");
				startService(intent);
			}
		});
	}
}