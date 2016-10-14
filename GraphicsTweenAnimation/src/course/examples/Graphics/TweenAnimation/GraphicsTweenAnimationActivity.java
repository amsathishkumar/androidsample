package course.examples.Graphics.TweenAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class GraphicsTweenAnimationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ImageView iv = (ImageView) findViewById(R.id.icon);
		Animation anim = AnimationUtils.loadAnimation(this,R.anim.icon_animation);
		iv.startAnimation(anim);
	}
}