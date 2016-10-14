package course.examples.ViewTransitions;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

public class ViewFlipperTestActivity extends Activity {
	private ViewFlipper mFlipper;
	private int mCurrentLayoutState;
	private GestureDetector gd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
		mCurrentLayoutState = 0;

		gd = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						if (velocityX < -10.0f) {
							mCurrentLayoutState = mCurrentLayoutState == 0 ? 1
									: 0;
							switchLayoutStateTo(mCurrentLayoutState);
						}
						return true;
					}
				});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gd.onTouchEvent(event);
	}

	public void switchLayoutStateTo(int switchTo) {
		mCurrentLayoutState = switchTo;
		mFlipper.setInAnimation(inFromRightAnimation());
		mFlipper.setOutAnimation(outToLeftAnimation());
		mFlipper.showPrevious();
	}

	private Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(500);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	private Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(500);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}
}