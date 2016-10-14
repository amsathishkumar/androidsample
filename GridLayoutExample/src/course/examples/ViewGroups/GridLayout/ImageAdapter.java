package course.examples.ViewGroups.GridLayout;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private List<Integer> mThumbIds;

	public ImageAdapter(Context c, Intent intent) {
		mContext = c;
		this.mThumbIds = intent.getExtras().getIntegerArrayList("thumbNailIDs");
	}

	public int getCount() {
		return mThumbIds.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return mThumbIds.get(position);
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds.get(position));
		return imageView;
	}
}
