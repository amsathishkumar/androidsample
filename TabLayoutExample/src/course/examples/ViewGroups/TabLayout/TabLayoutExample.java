package course.examples.ViewGroups.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TabLayoutExample extends TabActivity {
	private ArrayList<Integer> mThumbIdsFlowers = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));

	private ArrayList<Integer> mThumbIdsAnimals = new ArrayList<Integer>(
			Arrays.asList(R.drawable.sample_1, R.drawable.sample_2,
					R.drawable.sample_3, R.drawable.sample_4,
					R.drawable.sample_5, R.drawable.sample_6,
					R.drawable.sample_7, R.drawable.sample_0));

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, GridLayoutActivity.class)
				.putIntegerArrayListExtra("thumbNailIDs", mThumbIdsFlowers);
		spec = tabHost.newTabSpec("flowers").setIndicator("Flowers")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, GridLayoutActivity.class)
				.putIntegerArrayListExtra("thumbNailIDs", mThumbIdsAnimals);
		spec = tabHost.newTabSpec("animals").setIndicator("Animals")
				.setContent(intent);
		tabHost.addTab(spec);

	}
}
