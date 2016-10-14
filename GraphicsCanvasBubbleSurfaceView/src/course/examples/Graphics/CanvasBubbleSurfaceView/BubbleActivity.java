package course.examples.Graphics.CanvasBubbleSurfaceView;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class BubbleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		RelativeLayout fl = (RelativeLayout) findViewById(R.id.frame);
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.b128);
		final BubbleView mcd = new BubbleView(this, bm);
		fl.addView(mcd);
	}

	private class BubbleView extends SurfaceView implements
			SurfaceHolder.Callback {
		final Bitmap mBitmap;
		int mBitmapWidth, mBitmapHeight, mBitmapWidthAdj, mBitmapHeightAdj;
		final DisplayMetrics mDisplay;
		int mDisplayWidth, mDisplayHeight;
		float mX, mY, mDx, mDy, mRotation;
		final SurfaceHolder mSurfaceHolder;
		final Paint mPainter = new Paint();

		public BubbleView(Context context, Bitmap bitmap) {
			super(context);
			mPainter.setAntiAlias(true);
			this.mBitmap = bitmap;
			mBitmapHeight = bitmap.getHeight();
			mBitmapWidth = bitmap.getWidth();
			mBitmapWidthAdj = mBitmapWidth / 2;
			mBitmapHeightAdj = mBitmapHeight / 2;

			mDisplay = new DisplayMetrics();
			BubbleActivity.this.getWindowManager().getDefaultDisplay()
					.getMetrics(mDisplay);
			mDisplayWidth = mDisplay.widthPixels;
			mDisplayHeight = mDisplay.heightPixels;

			Random r = new Random();
			mX = (float) r.nextInt(mDisplayHeight);
			mY = (float) r.nextInt(mDisplayWidth);
			mDx =(float) r.nextInt(mDisplayHeight) / mDisplayHeight;
			mDx *= r.nextInt(1) == 1 ? 1 : -1;
			mDy = (float) r.nextInt(mDisplayWidth) / mDisplayWidth;
			mDy *= r.nextInt(1) == 1 ? 1 : -1;
			mRotation = 1.0f;
			mSurfaceHolder = getHolder();
			mSurfaceHolder.addCallback(this);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.DKGRAY);
			mRotation += 1.0f;
			canvas.rotate(mRotation, mY + mBitmapWidthAdj, mX
					+ mBitmapHeightAdj);
			canvas.drawBitmap(mBitmap, mY, mX, mPainter);
		}

		private boolean move() {
			mX += mDx;
			mY += mDy;
			if (mX < 0 - mBitmapWidth || mX > mDisplayHeight + mBitmapWidth || mY < 0 - mBitmapWidth
					|| mY > mDisplayWidth + mBitmapWidth) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			new Thread(new Runnable() {
				public void run() {
					Canvas canvas = null;
					while (move()) {
						try {
							canvas = mSurfaceHolder.lockCanvas();
							onDraw(canvas);
						} finally {
							if (null != canvas)
								mSurfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			}).start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
		}

	}
}