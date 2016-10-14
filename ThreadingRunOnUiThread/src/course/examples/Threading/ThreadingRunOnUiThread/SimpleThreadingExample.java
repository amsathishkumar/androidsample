package course.examples.Threading.ThreadingRunOnUiThread;

import course.examples.Threading.ThreadingSimple.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SimpleThreadingExample extends Activity {
	private Bitmap bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView iview = (ImageView) findViewById(R.id.imageView1);

		final Button button = (Button) findViewById(R.id.loadButton);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						bitmap = BitmapFactory.decodeResource(getResources(),
								R.drawable.icon);
						SimpleThreadingExample.this
								.runOnUiThread(new Runnable() {
									public void run() {
										iview.setImageBitmap(bitmap);
									}
								});
					}
				}).start();
			}
		});
	}
}