package course.examples.Threading.ThreadingAsyncTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SimpleThreadingExample extends Activity {
	private ImageView iview;
	private ProgressBar progress;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		iview = (ImageView) findViewById(R.id.imageView1);;
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		final Button button = (Button) findViewById(R.id.loadButton);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new LoadIconTask().execute(R.drawable.icon);
			}
		});
	}

	class LoadIconTask extends AsyncTask<Integer, Integer, Bitmap> {
		protected Bitmap doInBackground(Integer... resId) {
			Bitmap tmp = BitmapFactory.decodeResource(getResources(), resId[0]);
			// simulate long-running operation 
			for (int i = 1; i < 11; i++) {
				sleep();
				publishProgress(i * 10);
			}
			return tmp;
		}


		@Override
		protected void onPreExecute() {
			progress.setVisibility(ProgressBar.VISIBLE);
		}

		protected void onProgressUpdate(Integer... values) {
			progress.setProgress(values[0]);
		}

		protected void onPostExecute(Bitmap result) {
			progress.setVisibility(ProgressBar.INVISIBLE);
			iview.setImageBitmap(result);
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