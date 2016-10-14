package course.examples.Maps.EarthQuakeMap;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class EartQuakeDataOverlay extends ItemizedOverlay<OverlayItem> {
	final private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();	
	
	protected EartQuakeDataOverlay(Drawable defaultMarker) {
		super(boundCenter(defaultMarker));
	}

	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		 return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
}
