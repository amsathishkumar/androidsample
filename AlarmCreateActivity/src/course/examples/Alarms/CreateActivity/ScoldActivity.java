package course.examples.Alarms.CreateActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class ScoldActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scold_view);
		Toast.makeText(this, "Are you playing Angry Birds again?",
				Toast.LENGTH_LONG).show();
	}
}
