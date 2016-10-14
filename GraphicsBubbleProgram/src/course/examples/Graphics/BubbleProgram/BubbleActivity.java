package course.examples.Graphics.BubbleProgram;

import course.examples.Graphics.Bubble.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BubbleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout);
		ImageView bubbleView = new ImageView(this);
		bubbleView.setBackgroundResource(R.drawable.b128);
		bubbleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		ll.addView(bubbleView);
	}
}