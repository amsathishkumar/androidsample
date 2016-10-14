package course.examples.Threading.ThreadingHandlerRunnable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SimpleThreadingExample extends Activity {
	private ImageView iview;
	private final Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iview = (ImageView) findViewById(R.id.imageView1);
		final Button button = (Button) findViewById(R.id.loadButton);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new Thread(new LoadIconTask(R.drawable.icon)).start();
			}
		});
	}


	private class LoadIconTask implements Runnable {
		int resId;

		LoadIconTask(int resId) {
			this.resId = resId;
		}

		public void run() {
			final Bitmap tmp = BitmapFactory.decodeResource(getResources(),
					resId);
			handler.post(new Runnable() {
				@Override
				public void run() {
					iview.setImageBitmap(tmp);
				}
			});
		}
	}
}
