package course.examples.Maps.EarthQuakeMap;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import android.graphics.drawable.Drawable;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsEarthquakeMapActivity extends MapActivity {
	List<Overlay> mapOverlays;
	Drawable mOverlayDrawable;
	EartQuakeDataOverlay itemizedOverlay;
	MapView mapView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * new HttpGetTask() .execute(
		 * "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo"
		 * );
		 */

		new HttpGetTask().execute("http://206.196.162.28/json_example.txt");
		setContentView(R.layout.main);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();
		mOverlayDrawable = this.getResources().getDrawable(
				R.drawable.pushpin_red);
		itemizedOverlay = new EartQuakeDataOverlay(mOverlayDrawable);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void onFinishGetRequest(List<EarthQuakeRec> result) {
		for (EarthQuakeRec rec : result) {
			itemizedOverlay.addOverlay(new OverlayItem(rec.getGeoPoint(),
					String.valueOf(rec.getMagnitude()), ""));
		}
		mapOverlays.add(itemizedOverlay);
		MapController mc = mapView.getController();
		mc.setCenter(new GeoPoint((int) (14.6041667 * 1E6),
				(int) (120.9822222 * 1E6)));
	}

	private class HttpGetTask extends
			AsyncTask<String, Void, List<EarthQuakeRec>> {

		@Override
		protected List<EarthQuakeRec> doInBackground(String... params) {
			AndroidHttpClient client = AndroidHttpClient.newInstance("");
			HttpGet request = new HttpGet(params[0]);
			JSONResponseHandler responseHandler = new JSONResponseHandler();
			try {
				return client.execute(request, responseHandler);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<EarthQuakeRec> result) {
			onFinishGetRequest(result);
		}
	}

}