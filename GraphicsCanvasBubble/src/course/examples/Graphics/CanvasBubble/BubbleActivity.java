package course.examples.Graphics.CanvasBubble;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class BubbleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		RelativeLayout frame = (RelativeLayout) findViewById(R.id.frame);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.b128);
		final BubbleView bubbleView = new BubbleView(this, bm);
		frame.addView(bubbleView);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (bubbleView.move()) {
					bubbleView.postInvalidate();
				}
			}
		}).start();
	}

	private class BubbleView extends View {
		Bitmap mBitmap;
		float mX, mY, mDx, mDy;
		DisplayMetrics mDisplayMetrics;
		int mDisplayWidth;
		int mDisplayHeight;
		int mBitmapWidthAndHeight;
		Paint mPainter = new Paint();

		public BubbleView(Context context, Bitmap bitmap) {
			super(context);
			this.mBitmap = bitmap;
			mBitmapWidthAndHeight = bitmap.getWidth();

			mDisplayMetrics = new DisplayMetrics();
			BubbleActivity.this.getWindowManager().getDefaultDisplay()
					.getMetrics(mDisplayMetrics);
			mDisplayWidth = mDisplayMetrics.widthPixels;
			mDisplayHeight = mDisplayMetrics.heightPixels;

			Random r = new Random();
			mY = (float) r.nextInt(mDisplayHeight);
			mX = (float) r.nextInt(mDisplayWidth);
			mDy = (float) r.nextInt(mDisplayHeight) / mDisplayHeight;
			mDy *= r.nextInt(1) == 1 ? 1 : -1;
			mDx = (float) r.nextInt(mDisplayWidth) / mDisplayWidth;
			mDx *= r.nextInt(1) == 1 ? 1 : -1;
			mPainter.setAntiAlias(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawBitmap(mBitmap, mX, mY, mPainter);
		}

		protected boolean move() {
			mX += mDx;
			mY += mDy;
			if (mY < 0 - mBitmapWidthAndHeight
					|| mY > mDisplayHeight + mBitmapWidthAndHeight
					|| mX < 0 - mBitmapWidthAndHeight
					|| mX > mDisplayWidth + mBitmapWidthAndHeight) {
				return false;
			} else {
				return true;
			}
		}

	}
}