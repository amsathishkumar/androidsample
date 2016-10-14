package course.examples.ViewGroups.GridLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		Uri uri = i.getData();
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setImageResource(Integer.parseInt(uri.getLastPathSegment()));
		setContentView(imageView);
	}
}