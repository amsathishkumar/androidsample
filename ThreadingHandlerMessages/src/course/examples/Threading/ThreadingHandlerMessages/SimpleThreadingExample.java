package course.examples.Threading.ThreadingHandlerMessages;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SimpleThreadingExample extends Activity {
	private final static int SET_PROGRESS_BAR_VISIBILITY = 0;
	private final static int PROGRESS_UPDATE = 1;
	private final static int SET_BITMAP = 2;

	private ImageView iview;
	private ProgressBar progress;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SET_PROGRESS_BAR_VISIBILITY: {
				progress.setVisibility((Integer) msg.obj);
				break;
			}
			case PROGRESS_UPDATE: {
				progress.setProgress((Integer) msg.obj);
				break;
			}
			case SET_BITMAP: {
				iview.setImageBitmap((Bitmap) msg.obj);
				break;
			}
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iview = (ImageView) findViewById(R.id.imageView1);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		final Button button = (Button) findViewById(R.id.loadButton);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new Thread(new LoadIconTask(R.drawable.icon, handler)).start();
			}
		});
	}

	private class LoadIconTask implements Runnable {
		private final int resId;
		private final Handler handler;

		LoadIconTask(int resId, Handler handler) {
			this.resId = resId;
			this.handler = handler;
		}

		public void run() {

			final Bitmap tmp = BitmapFactory.decodeResource(getResources(),
					resId);

			Message msg = null;

			msg = handler.obtainMessage(SET_PROGRESS_BAR_VISIBILITY,
					ProgressBar.VISIBLE);
			handler.sendMessage(msg);

			for (int i = 1; i < 11; i++) {
				sleep();
				msg = handler.obtainMessage(PROGRESS_UPDATE, i * 10);
				handler.sendMessage(msg);
			}

			msg = handler.obtainMessage(SET_BITMAP, tmp);
			handler.sendMessage(msg);

			msg = handler.obtainMessage(SET_PROGRESS_BAR_VISIBILITY,
					ProgressBar.INVISIBLE);
			handler.sendMessage(msg);
		}

		private void sleep() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
