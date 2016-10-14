package course.examples.Graphics.TransitionDrawable;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TransitionDrawableActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		RelativeLayout rl = (RelativeLayout) findViewById(R.id.main_window);
		ImageView iv = new ImageView(this);
		Resources res = getResources();
		TransitionDrawable trans = (TransitionDrawable) res
				.getDrawable(R.drawable.shape_transition);
		iv.setImageDrawable(trans);
		rl.addView(iv);
		trans.setCrossFadeEnabled(true);
		trans.startTransition(5000);
	}
}