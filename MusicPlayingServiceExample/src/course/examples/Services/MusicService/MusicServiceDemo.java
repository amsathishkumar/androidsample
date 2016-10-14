package course.examples.Services.MusicService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicServiceDemo extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button button = (Button) findViewById(R.id.buttonStart);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View src) {
				switch (src.getId()) {
				case R.id.buttonStart:
					startService(new Intent(MusicServiceDemo.this,
							MusicService.class));
					break;
				}
			}
		});
	}
	
	
}