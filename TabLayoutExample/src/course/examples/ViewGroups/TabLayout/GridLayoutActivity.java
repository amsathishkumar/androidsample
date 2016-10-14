package course.examples.ViewGroups.TabLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GridLayoutActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_view);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		Intent intent = getIntent();
		gridview.setAdapter(new ImageAdapter(this, intent));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				System.out.println("Id:" + id);
				Intent intent = new Intent(GridLayoutActivity.this, ImageViewActivity.class);
				
				Uri path = Uri
						.parse("android.resource://course.examples.ViewGroups/"
								+ id);
				intent.setDataAndType(path, "image/*");
				startActivity(intent);
			}
		});
	}
}