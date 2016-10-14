package course.examples.FormWidgets.ToggleButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ToggleButtonExample extends Activity {
	int count = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final LinearLayout bg = (LinearLayout) findViewById(R.id.linearlayout);
		
		final ToggleButton button = (ToggleButton) findViewById(R.id.togglebutton);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (button.isChecked()) {
					bg.setBackgroundColor(0xFFFFFFFF);
				} else {
					bg.setBackgroundColor(0xFF000000);
				}
			}
		});
		
	}
}