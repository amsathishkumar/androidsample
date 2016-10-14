package course.examples.ViewGroups.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridLayoutExample extends Activity {
	
	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		Intent intent = getIntent().putIntegerArrayListExtra("thumbNailIDs", mThumbIdsFlowers);;
		gridview.setAdapter(new ImageAdapter(this, intent));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				System.out.println("Id:" + id);
				Intent intent = new Intent(GridLayoutExample.this, ImageViewActivity.class);
				Uri path = Uri
						.parse("android.resource://course.examples.ViewGroups/"
								+ id);
				intent.setDataAndType(path, "image/*");
				startActivity(intent);
			}
		});
	}
}