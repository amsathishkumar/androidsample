package course.examples.Graphics.FrameAnimation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GraphicsFrameAnimationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView iv = (ImageView) findViewById(R.id.countdown_frame);
		iv.setBackgroundResource(R.drawable.view_animation);
		final AnimationDrawable ivAnim = (AnimationDrawable) iv.getBackground();

		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ivAnim.isRunning()) {
					ivAnim.stop();
				}
				ivAnim.start();
			}
		});
	}
}