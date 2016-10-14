package course.examples.AudioVideo.Camera;

import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class AudioVideoCameraActivity extends Activity {
	private Camera mCamera = null;
	private boolean mPreviewRunning = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		SurfaceView mSurfaceView = (SurfaceView) findViewById(R.id.cameraView);
		SurfaceHolder mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(mSurfaceHelper);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mCamera.release();
		mCamera = null;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	SurfaceHolder.Callback mSurfaceHelper = new SurfaceHolder.Callback() {
		LinearLayout mFrame = null;

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			mFrame = (LinearLayout) findViewById(R.id.frame);
			mFrame.setOnTouchListener(mTouchHelper);
			try {
				if (null == mCamera) {
					mCamera = Camera.open();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

			if (mPreviewRunning) {
				mCamera.stopPreview();
			}

			Camera.Parameters p = mCamera.getParameters();
			p.setPreviewSize(width, height);
			mCamera.setParameters(p);
			try {
				mCamera.setPreviewDisplay(holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mCamera.startPreview();
			mPreviewRunning = true;
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			mPreviewRunning = false;
			if (null != mCamera) {
				mCamera.stopPreview();
				mCamera.release();
			}
		}
	};

	Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
		@Override
		public void onShutter() {
			mCamera.stopPreview();
			mPreviewRunning = false;
		}
	};

	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mCamera.startPreview();
			mPreviewRunning = true;
		}
	};

	View.OnTouchListener mTouchHelper = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getActionMasked() == (MotionEvent.ACTION_UP)) {
				mCamera.takePicture(mShutterCallback, null, mPictureCallback);
			}
			return true;
		}
	};
}